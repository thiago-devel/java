package com.rubyit.metaltrade.orderbook;

import static com.rubyit.metaltrade.Utils.getGson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.jayway.jsonpath.internal.path.ArraySliceOperation;
import com.rubyit.metaltrade.obj.Asset;
import com.rubyit.metaltrade.orderbook.Order.Type;

class Pair {

	private final Asset amountAsset;
	private final Asset priceAsset;
	private final String currencyPair; 
	
	Pair(Asset amountAsset, Asset priceAsset) {
		if (amountAsset == null || priceAsset == null) {
			throw new RuntimeException("ERROR: unable to create a currency pair with invalid fields: {amountAsset=" + amountAsset + ", priceAsset=" + priceAsset + "}") ;
		}
		this.amountAsset = amountAsset;
		this.priceAsset = priceAsset;
		this.currencyPair = amountAsset.getName() + "/" + priceAsset.getName(); 
	}

	public Asset getAmountAsset() {
		return amountAsset;
	}

	public Asset getPriceAsset() {
		return priceAsset;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("amountAsset", amountAsset);
		json.put("priceAsset", priceAsset);
		json.put("currencyPair", currencyPair);
		return getGson().toJson(json);
	}
}

class CurrencyPair {
	
	private Pair pair;
	private Set<Order> sellOrders;
	private Set<Order> buyOrders;
	
	CurrencyPair(Pair pair) {
		this.pair = pair;
		buyOrders = new TreeSet<>();
		sellOrders = new TreeSet<>();
	}
	
	/*
	 *  "an instrument that is bought or sold. If you buy a currency pair, you 
	 *  buy the base currency and implicitly sell the quoted currency."
	 */
	public void addOrder(Order order) {
		String assetID = order.getAssetID();
		String exchangeAssetID = order.getExchangeAssetID();
		String amountAssetID = pair.getAmountAsset().getID();
		String priceAssetID = pair.getPriceAsset().getID();
		if (
			(assetID.equals(amountAssetID))
			&& (exchangeAssetID.equals(priceAssetID))
		   ) {
			sellOrders.add(order);
		}
		if (
			(assetID.equals(priceAssetID))
			&& (exchangeAssetID.equals(amountAssetID))
		   ) {
			
			buyOrders.add(order);
		}
		
		throw new RuntimeException("ERROR: that order {order=" + order + "} doesn't belongs to that pair {" + pair + "}");
	}
	
	public List<Order> getBuyOrders() {
		return new ArrayList<>(buyOrders); // TODO: to order by value before return
	}
	
	public List<Order> getSellOrder() {
		return new ArrayList<>(sellOrders); // TODO: to order by value before return
	}
	
	public Order getOrder(String orderID) {
		for (Order order : buyOrders) {
			if (order.getID().equals(orderID)) {
				return order;
			}
		}
		for (Order order : sellOrders) {
			if (order.getID().equals(orderID)) {
				return order;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("pair", pair);
		json.put("orders", buyOrders);
		return getGson().toJson(json);
	}
}

class Order {
	
	private String ID;
	private String traderID;
	private String assetID;
	private Double amount;
	private String exchangeAssetID;
	private Double exchangePriceAssetAmount;
	
	public Order(String traderID, String assetID, Double amount, String exchangeAssetID,
			Double exchangePriceAssetAmount) {
		ID = UUID.randomUUID().toString();
		this.traderID = traderID;
		this.assetID = assetID;
		this.amount = amount;
		this.exchangeAssetID = exchangeAssetID;
		this.exchangePriceAssetAmount = exchangePriceAssetAmount;
	}
	
	public String getTraderID() {
		return traderID;
	}
	
	public String getAssetID() {
		return assetID;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public String getExchangeAssetID() {
		return exchangeAssetID;
	}
	
	public Double getExchangePriceAssetAmount() {
		return exchangePriceAssetAmount;
	}
	
	public String getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		return getGson().toJson(json);
	}
	
	public enum Type {
		BUY, SELL
	}
}

class OrderBook {
	
	private final Set<CurrencyPair> pairs;
	
	public OrderBook(CurrencyPair... currencyPairs) {
		if (currencyPairs == null || currencyPairs.length == 0) {
			throw new RuntimeException("ERROR: unable to create a orderbook without at least one CurrencyPair");
		}
		pairs = new TreeSet<>(Arrays.asList(currencyPairs));
	}

	public Order createOrder(String traderID, MyAsset amountAsset, MyAsset priceAsset, Type sell) {
		return this.createOrder(traderID, amountAsset.getAsset().getID(), amountAsset.getBalance().doubleValue(), priceAsset.getAsset().getID(), priceAsset.getBalance().doubleValue(), Order.Type.SELL);
	}

	public Order createOrder(String traderID, String assetID, Double amount, String exchangeAssetID,
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

	public List<CurrencyPair> getCurrencyPair() {
		return new ArrayList<>(pairs);
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

	public Order createOrder(final String assetID, final Double amount, final String exchangeAssetID,
			final Double exchangePriceAssetAmount) {
		
		//TODO: here a need to figure out how to create a sell//buy CurrenryPair Order and add it to the orderbook 
		
		return null;//orderbook.createOrder(getID(), assetID, amount, exchangeAssetID, exchangePriceAssetAmount, type);
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

class MockOriginAccount extends Account {
	
	public static final Double INITIAL_BALANCE = 500.0;
	
	MockOriginAccount(Asset asset) {
		super();
		MyAsset myAsset = new MyAsset(asset);
		ReflectionTestUtils.setField(myAsset, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<MyAsset> assets = new HashSet<>(Arrays.asList(myAsset));
		ReflectionTestUtils.setField(getWallet(), "assets", assets);
	}
}

public class TestOrderBook {
	
	private static final Asset BRONZE = new Asset("BRONZE");
	private static final Asset SILVER = new Asset("SILVER");
	private static final Asset GOLD = new Asset("GOLD");
	private static final Asset USD = new Asset("USD");
	
	// {BASE CURRENCY == AMOUNT}/{QUOTE CURRENCY == price} 
	private static final String GOLDxUSD = "GOLD/USD"; 
	private static final String GOLDxSILVER = "GOLD/SILVER"; 
	private static final String BRONZExSILVER = "BRONZE/SILVER"; 
	
	@Test
	public void testSellAndBuyViaOrderBook() {
		String error1Message = "ERROR: Asset not found. Did you performed an add funds transaction before used it?";
		
		final Double usdAmount = 49.99;
		final Double goldAmount = 4.11;
		final Double silverAmount = 43.44;
		final Double bronzeAmount = 157.33;
		
		MockOriginAccount mockOriginAccountUSD = new MockOriginAccount(USD);
		MockOriginAccount mockOriginAccountGOLD = new MockOriginAccount(GOLD);
		MockOriginAccount mockOriginAccountSILVER = new MockOriginAccount(SILVER);
		MockOriginAccount mockOriginAccountBRONZE = new MockOriginAccount(BRONZE);
		
		OrderBook orderbook = new OrderBook();
		
		Trader thiago = new Trader("Thiago", orderbook);
		Trader renata = new Trader("Renata", orderbook);
		Trader maria = new Trader("Maria", orderbook);
		Trader alice = new Trader("Alice", orderbook);
		
		
		mockOriginAccountGOLD.getWallet().transfer(GOLD, goldAmount, renata);
		String rTraderID = renata.getID();
		assertNotNull(rTraderID);
		assertTrue(!rTraderID.trim().isEmpty());
		String rAssetID = renata.getWallet().getAsset(GOLD).getAsset().getID();
		assertNotNull(rAssetID);
		assertTrue(!rAssetID.trim().isEmpty());
		Double rAmount = renata.getWallet().getAsset(GOLD).getBalance().doubleValue(); // 4.11
		String rExchangeAssetID = USD.getID(); 
		Double rExchangePriceAssetAmount = usdAmount;
		Order bidOrder = renata.createOrder(rAssetID, rAmount, rExchangeAssetID, rExchangePriceAssetAmount);
		assertNotNull(error1Message, renata.getWallet().getAsset(GOLD).getAsset());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(goldAmount)));
		assertEquals(null, renata.getWallet().getAsset(USD));
		
		mockOriginAccountUSD.getWallet().transfer(USD, usdAmount, thiago);
		String tTraderID = thiago.getID();
		assertNotNull(tTraderID);
		assertTrue(!tTraderID.trim().isEmpty());
		String tAssetID = thiago.getWallet().getAsset(USD).getAsset().getID();
		assertNotNull(tAssetID);
		assertTrue(!tAssetID.trim().isEmpty());
		Double tAmount = thiago.getWallet().getAsset(USD).getBalance().doubleValue(); // 49.99
		String tExchangeAssetID = GOLD.getID(); 
		Double tExchangePriceAssetAmount = goldAmount;
		Order askOrder = thiago.createOrder(tAssetID, tAmount, tExchangeAssetID, tExchangePriceAssetAmount);
		assertNotNull(error1Message, thiago.getWallet().getAsset(USD).getAsset());
		assertEquals(0, thiago.getWallet().getAsset(USD).getBalance().compareTo(BigDecimal.valueOf(usdAmount)));
		assertEquals(null, thiago.getWallet().getAsset(GOLD));
		
		// TODO: Add Maria and Alice assets here.
		
		
		assertNotNull("BID should not be null", orderbook.getBidOrder());
		assertNotNull("ASK should not be null", orderbook.getAskOrder());
		assertEquals(bidOrder, orderbook.getBidOrder());
		assertEquals(askOrder, orderbook.getAskOrder());
		
		
		
		/*
		 * FROM Investopedia1s website:
		 * 
		 * "All forex trades involve the simultaneous purchase of one currency 
		 * and sale of another, but the currency pair itself can be thought of 
		 * as a single unit — an instrument that is bought or sold. If you buy 
		 * a currency pair, you buy the base currency and implicitly sell the 
		 * quoted currency. The bid (buy price) represents how much of the 
		 * quote currency you need to get one unit of the base currency. 
		 * 
		 * Conversely, when you sell the currency pair, you sell the base 
		 * currency and receive the quote currency. The ask (sell price) for 
		 * the currency pair represents how much you will get in the quote 
		 * currency for selling one unit of base currency."
		 */
		orderbook.performMatcher();
		
		
		
		assertEquals(BigDecimal.ZERO, renata.getWallet().getAsset(GOLD).getBalance());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(new BigDecimal(usdAmount)));
		
		assertEquals(BigDecimal.ZERO, renata.getWallet().getAsset(USD).getBalance());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(new BigDecimal(goldAmount)));
	}
}