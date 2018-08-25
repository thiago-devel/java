package com.rubyit.metaltrade.orderbook;

import static com.rubyit.metaltrade.Utils.getGson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.rubyit.metaltrade.obj.Asset;
import com.rubyit.metaltrade.orderbook.Order.Type;

class OrderBook {

	public Order createOrder(String traderID, MyAsset amountAsset, MyAsset priceAsset, Type sell) {
		return this.createOrder(traderID, amountAsset.getAsset().getID(), amountAsset.getBalance().doubleValue(), priceAsset.getAsset().getID(), priceAsset.getBalance().doubleValue(), Order.Type.SELL);
	}

	public Order createOrder(String traderID, String assetID, Double amount, String exchangePriceAssetID,
			Double exchangePriceAssetAmount, Type sell) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getBidOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getAskOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void performMatcher() {
		// TODO Auto-generated method stub
		
	}

}

class Order {

	public enum Type {
		BUY, SELL
	}
}

class Wallet {
	
	private String ID;
	private Set<MyAsset> assets;
	transient private Lock assetChangeLock;

	public Wallet() {
		ID = UUID.randomUUID().toString();
		assets = new HashSet<>();
		assetChangeLock = new ReentrantLock();
	}
	
	public void transfer(final Asset assetToTransfer, final Double amountToTranfer, final Account accountToTranfer) {
		
		if (assetToTransfer == null || amountToTranfer == null || accountToTranfer == null) {
			throw new RuntimeException("ERROR: unable to perform transfer with "
					+ "invalid fields: {assetToTransfer=" + assetToTransfer + ", amountToTranfer=" 
					+ amountToTranfer + ", accountToTranfer=" + accountToTranfer +"}");
		}
		
		assetChangeLock.lock();
		try {
			final MyAsset myAsset = getExtractAssetBy(assetToTransfer);
			final Wallet otherWallet = accountToTranfer.getWallet();
			final MyAsset otherAsset = otherWallet.getExtractAssetBy(assetToTransfer);
			final BigDecimal amount = new BigDecimal(amountToTranfer, new MathContext(8, RoundingMode.HALF_EVEN));
			
			try {
				myAsset.withdraw(amount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assetChangeLock.unlock();
				throw new RuntimeException("ERROR: unable to perform transfer", e);
			}
			otherAsset.deposit(amount);
			this.addAsset(myAsset);
			otherWallet.addAsset(otherAsset);
		} finally {
			assetChangeLock.unlock();
		}
	}
	
	public MyAsset getAsset(final Asset asset) {
		
		for (MyAsset myAsset : assets) {
			if (myAsset.getAsset().equals(asset)) {
				return myAsset;
			}
		}
		
		return null;
	}
	
	private MyAsset getExtractAssetBy(final Asset asset) {
		
		for (MyAsset myAsset : assets) {
			if (myAsset.getAsset().equals(asset)) {
				assets.remove(myAsset);
				return myAsset;
			}
		}
		
		return new MyAsset(asset);
	}
	
	private void addAsset(final MyAsset newAsset) {
		
		assets.add(newAsset);
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("assets", assets);
		return getGson().toJson(json);
	}
}


class Trader extends Account {

	protected Wallet myWallet;
	private OrderBook orderbook;
	protected String name;
	
	Trader(String name, OrderBook orderbook) {
		super();
		if (name == null || name.trim().isEmpty() || orderbook == null) {
			throw new RuntimeException("ERROR: unable to create a invalid Trader with { name=" + name + ", orderbook=" + orderbook +  "}");
		}
		this.name = name;
		this.orderbook = orderbook;
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", getID());
		json.put("name", name);
		json.put("myWallet", myWallet);
		return getGson().toJson(json);
	}

	public String getID() {
		return super.getID();
	}

	public Order createOrder(final String assetID, final Double amount, final String exchangePriceAssetID,
			final Double exchangePriceAssetAmount, final Order.Type type) {
		
		return orderbook.createOrder(getID(), assetID, amount, exchangePriceAssetID, exchangePriceAssetAmount, type);
	}
}

class Account {
	
	private Wallet myWallet;
	private String ID;
	
	Account() {
		myWallet = new Wallet();
		ID = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("myWallet", myWallet);
		return getGson().toJson(json);
	}
	
	public Wallet getWallet() {
		return myWallet;
	}
	
	public String getID() {
		return ID;
	}
}

class MockOriginAccountGOLD extends Account {
	
	public static final Double INITIAL_BALANCE = 500.0;
	
	MockOriginAccountGOLD(Asset assetGOLD) {
		super();
		MyAsset myGOLD = new MyAsset(assetGOLD);
		ReflectionTestUtils.setField(myGOLD, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<MyAsset> assets = new HashSet<>(Arrays.asList(myGOLD));
		ReflectionTestUtils.setField(getWallet(), "assets", assets);
	}
}

class MockOriginAccountUSD extends Account {
	
	public static final Double INITIAL_BALANCE = 500.0;
	
	MockOriginAccountUSD(Asset assetGOLD) {
		super();
		MyAsset myUSD = new MyAsset(assetGOLD);
		ReflectionTestUtils.setField(myUSD, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<MyAsset> assets = new HashSet<>(Arrays.asList(myUSD));
		ReflectionTestUtils.setField(getWallet(), "assets", assets);
	}
}

class MyAsset {
	
	private Asset asset;
	private BigDecimal balance;
	transient private Lock balanceChangeLock;
	transient private Condition sufficientFundsCondition;

	public MyAsset(Asset asset) {
		this.asset = asset;
		balance = BigDecimal.ZERO;
		balanceChangeLock = new ReentrantLock();
		sufficientFundsCondition = balanceChangeLock.newCondition();
	}

	void deposit(BigDecimal amount) {
		balanceChangeLock.lock();
		try {
			System.out.print("Depositing " + amount);
			BigDecimal newBalance = balance.add(amount);
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
			sufficientFundsCondition.signalAll();
		} finally {
			balanceChangeLock.unlock();
		}
	}

	void withdraw(BigDecimal amount) throws InterruptedException {
		balanceChangeLock.lock();
		try {
			while (balance.compareTo(amount) < 0) {
				sufficientFundsCondition.await();
			}
			System.out.print("Withdrawing " + amount);
			BigDecimal newBalance = balance.subtract(amount);;
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
		} finally {
			balanceChangeLock.unlock();
		}
	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	public Asset getAsset() {
		return asset;
	}

	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("asset", asset);
		json.put("balance", balance);
		return getGson().toJson(json);
	}
}




public class TestOrderBook {
	
	private static final Asset GOLD = new Asset("GOLD");
	private static final Asset USD = new Asset("USD");
	
	@Test
	public void testSellAndBuyViaOrderBook() {
		String error1Message = "ERROR: Asset not found. Did you performed an add funds transaction before used it?";
		final Double usdValue = 12.0;
		final Double goldValue = 1.0;
		MockOriginAccountUSD mockOriginAccountUSD = new MockOriginAccountUSD(USD);
		MockOriginAccountGOLD mockOriginAccountGOLD = new MockOriginAccountGOLD(GOLD);
		OrderBook orderbook = new OrderBook();
		Trader thiago = new Trader("Thiago", orderbook);
		Trader renata = new Trader("Renata", orderbook);
		
		
		mockOriginAccountGOLD.getWallet().transfer(GOLD, goldValue, renata);

		
		String rTraderID = renata.getID();
		assertNotNull(rTraderID);
		assertTrue(!rTraderID.trim().isEmpty());
		String rAssetID = renata.getWallet().getAsset(GOLD).getAsset().getID();
		assertNotNull(rAssetID);
		assertTrue(!rAssetID.trim().isEmpty());
		Double rAmount = renata.getWallet().getAsset(GOLD).getBalance().doubleValue(); // 1
		String rExchangePriceAssetID = USD.getID(); 
		Double rExchangePriceAssetAmount = usdValue;
		Order bidOrder = orderbook.createOrder(rTraderID, rAssetID, rAmount, rExchangePriceAssetID, rExchangePriceAssetAmount, Order.Type.SELL);
		assertNotNull(error1Message, renata.getWallet().getAsset(GOLD).getAsset());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(goldValue)));
		assertEquals(null, renata.getWallet().getAsset(USD));
		
		mockOriginAccountUSD.getWallet().transfer(USD, usdValue, thiago);
		
		
		String tTraderID = thiago.getID();
		assertNotNull(tTraderID);
		assertTrue(!tTraderID.trim().isEmpty());
		String tAssetID = thiago.getWallet().getAsset(USD).getAsset().getID();
		assertNotNull(tAssetID);
		assertTrue(!tAssetID.trim().isEmpty());
		Double tAmount = thiago.getWallet().getAsset(USD).getBalance().doubleValue(); //12
		String tExchangePriceAssetID = GOLD.getID(); 
		Double tExchangePriceAssetAmount = goldValue;
		Order askOrder = orderbook.createOrder(tTraderID, tAssetID, tAmount, tExchangePriceAssetID, tExchangePriceAssetAmount, Order.Type.BUY);
		assertNotNull(error1Message, thiago.getWallet().getAsset(USD).getAsset());
		assertEquals(0, thiago.getWallet().getAsset(USD).getBalance().compareTo(BigDecimal.valueOf(usdValue)));
		assertEquals(null, thiago.getWallet().getAsset(GOLD));
				
		
		
		assertNotNull("BID should not be null", orderbook.getBidOrder());
		assertNotNull("ASK should not be null", orderbook.getAskOrder());
		assertEquals(bidOrder, orderbook.getBidOrder());
		assertEquals(askOrder, orderbook.getAskOrder());
		
		
		
		/*
		 * FROM Investopedia1s website:
		 * 
		 * "The bid (buy price) represents how much of the quote currency you need to get one unit 
		 * of the base currency. Conversely, when you sell the currency pair, you sell the base 
		 * currency and receive the quote currency. The ask (sell price) for the currency pair 
		 * represents how much you will get in the quote currency for selling one unit of base 
		 * currency."
		 */
		orderbook.performMatcher();
		
		
		
		assertEquals(BigDecimal.ZERO, renata.getWallet().getAsset(GOLD).getBalance());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(new BigDecimal(usdValue)));
		
		assertEquals(BigDecimal.ZERO, renata.getWallet().getAsset(USD).getBalance());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(new BigDecimal(goldValue)));
	}
}