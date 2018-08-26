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

import com.rubyit.metaltrade.obj.Asset;

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
	
	private String ID;
	private Pair pair;
	private Set<Order> sellOrders;
	private Set<Order> buyOrders;
	
	CurrencyPair(Pair pair) {
		if (pair == null) {
			throw new RuntimeException("ERROR: enable to create a CurrencyPair with a NULL pair");
		}
		ID = UUID.randomUUID().toString();
		this.pair = pair;
		buyOrders = new TreeSet<>();
		sellOrders = new TreeSet<>();
	}
	
	CurrencyPair(CurrencyPair pair) {
		ID = pair.ID;
		this.pair = pair.pair;
		buyOrders = pair.buyOrders;
		sellOrders = pair.sellOrders;
	}
	
	/*
	 *  "an instrument that is bought or sold. If you buy a currency pair, you 
	 *  buy the base currency and implicitly sell the quoted currency."
	 */
	public void addOrder(Order order) {
		String assetID = order.getAsset().getID();
		String exchangeAssetID = order.getExchangeAsset().getID();
		String amountAssetID = pair.getAmountAsset().getID();
		String priceAssetID = pair.getPriceAsset().getID();
		if (
			(assetID.equals(amountAssetID))
			&& (exchangeAssetID.equals(priceAssetID))
		   ) {
			sellOrders.add(order);
			return;
		}
		if (
			(assetID.equals(priceAssetID))
			&& (exchangeAssetID.equals(amountAssetID))
		   ) {
			
			buyOrders.add(order);
			return;
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
	
	public Pair getPair() {
		return pair;
	}
	
	public String getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("pair", pair);
		json.put("buyOrders", buyOrders);
		json.put("sellOrders", sellOrders);
		return getGson().toJson(json);
	}
}

class Order implements Comparable<Order> {
	
	private String ID;
	private String traderID;
	private Asset asset;
	private Double amount;
	private Asset exchangeAsset;
	private Double exchangeAssetAmount;
	
	public Order(String traderID, Asset asset, Double amount, Asset exchangeAsset,
			Double exchangeAssetAmount) {
		if (
				(traderID == null || traderID.trim().isEmpty())
				|| (amount == null)
				|| (exchangeAssetAmount == null)
			) {
			throw new RuntimeException("ERROR: unable to create a order with "
					+ "invalid fields: {traderID=" + traderID + ", amount=" 
					+ amount+ ", exchangeAssetAmount=" + exchangeAssetAmount + "}") ;
		}
		ID = UUID.randomUUID().toString();
		this.traderID = traderID;
		this.asset = asset;
		this.amount = amount;
		this.exchangeAsset = exchangeAsset;
		this.exchangeAssetAmount = exchangeAssetAmount;
	}
	
	public String getTraderID() {
		return traderID;
	}
	
	public Asset getAsset() {
		return asset;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public Asset getExchangeAsset() {
		return exchangeAsset;
	}
	
	public Double getExchangeAssetAmount() {
		return exchangeAssetAmount;
	}
	
	public String getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("traderID", traderID);
		json.put("asset", asset);
		json.put("amount", amount);
		json.put("exchangeAsset", exchangeAsset);
		json.put("exchangeAssetAmount", exchangeAssetAmount);
		return getGson().toJson(json);
	}
	
	@Override
	public int compareTo(Order other) {
		
		return exchangeAssetAmount.compareTo(other.exchangeAssetAmount);
	}
}

class OrderBook {
	
	private final Set<CurrencyPair> pairs;
	transient private Lock pairChangeLock;
	transient private Condition pairNotFoundCondition;
	
	public OrderBook(CurrencyPair... currencyPairs) {
		
		if (currencyPairs == null || currencyPairs.length == 0) {
			throw new RuntimeException("ERROR: unable to create a orderbook without at least one CurrencyPair");
		}
		
		pairs = new HashSet<>(Arrays.asList(currencyPairs));
		pairChangeLock = new ReentrantLock();
		pairNotFoundCondition = pairChangeLock.newCondition();
	}

	/*
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
	 */
	public void updatePairOrders(final CurrencyPair pair) throws InterruptedException {
		
		CurrencyPair currencyPairOther = null;
		for (CurrencyPair currencyPair : pairs) {
			if (currencyPair.getID().equals(pair.getID())) {
				currencyPairOther = currencyPair;
			}
		}
		if (currencyPairOther == null) {
			throw new RuntimeException("ERROR: unable to update pair orders with an "
					+ "invalid pair: {pair=" + pair + "}");
		}
		
		pairChangeLock.lock();
		try {
			pairs.remove(currencyPairOther);
			pairs.add(pair);
		} finally {
			pairChangeLock.unlock();
		}
		
	}
	
	public CurrencyPair findCurrencyPairBy(String assetID) {
		
		pairChangeLock.lock();
		try {
			for (CurrencyPair currencyPair : pairs) {
				String amountAssetID = currencyPair.getPair().getAmountAsset().getID();
				String priceAssetID = currencyPair.getPair().getPriceAsset().getID();
				if (amountAssetID.equals(assetID)) {
					return currencyPair;//new CurrencyPair(currencyPair);
				}
				if (priceAssetID.equals(assetID)) {
					return currencyPair;//new CurrencyPair(currencyPair);
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
	public CurrencyPair findCurrencyPairBy(String assetID, String exchangeAssetID) {
		
		pairChangeLock.lock();
		try {
			for (CurrencyPair currencyPair : pairs) {
				String amountAssetID = currencyPair.getPair().getAmountAsset().getID();
				String priceAssetID = currencyPair.getPair().getPriceAsset().getID();
				if ((amountAssetID.equals(assetID)) && (priceAssetID.equals(exchangeAssetID))) {
					return new CurrencyPair(currencyPair);
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
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

	public Order createOrder(final Asset asset, final Double amount, final Asset exchangeAsset,
			final Double exchangeAssetAmount) {
		
		CurrencyPair pair = null;
		pair = orderbook.findCurrencyPairBy(asset.getID(), exchangeAsset.getID());

		if (pair == null) {
			
			for (String id : Arrays.asList(asset.getID(), exchangeAsset.getID())) {
				pair = orderbook.findCurrencyPairBy(id);
				if (pair != null) {
					break;
				}
			}
		}
		if (pair == null) {
			throw new RuntimeException("ERROR: unable to find on the orderbook a "
					+ "compatible CurrencyPair for Assets with {asset=" + asset + 
					", exchangeAsset=" + exchangeAsset + "}");
		}
		
		Order order = new Order(getID(), asset, amount, exchangeAsset, exchangeAssetAmount);
		pair.addOrder(order);
		
		try {
			orderbook.updatePairOrders(pair);
		} catch (InterruptedException e) {
			throw new RuntimeException("ERROR: update pair {" + pair + "} orders", e);
		}
		
		return order;
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
	private static final Pair GOLDxUSD = new Pair(GOLD, USD);//"GOLD/USD" 
	private static final Pair GOLDxSILVER = new Pair(GOLD, SILVER);//"GOLD/SILVER" 
	private static final Pair BRONZExSILVER = new Pair(BRONZE, SILVER);//"BRONZE/SILVER" 
	
	@Test
	public void testSellAndBuyViaOrderBook() {
		String error1Message = "ERROR: Asset not found. Did you performed an add funds transaction before used it?";
		
		final Double usdAmount = 49.99;
		final Double goldAmount = 4.11;
		final Double bronzeAmount = 157.33;
		final Double silverAmount = 43.44;
		
		MockOriginAccount mockOriginAccountUSD = new MockOriginAccount(USD);
		MockOriginAccount mockOriginAccountGOLD = new MockOriginAccount(GOLD);
		MockOriginAccount mockOriginAccountSILVER = new MockOriginAccount(SILVER);
		MockOriginAccount mockOriginAccountBRONZE = new MockOriginAccount(BRONZE);
		
		OrderBook orderbook = new OrderBook(new CurrencyPair(GOLDxUSD), new CurrencyPair(GOLDxSILVER), new CurrencyPair(BRONZExSILVER));
		
		Trader thiago = new Trader("Thiago", orderbook);
		Trader renata = new Trader("Renata", orderbook);
		Trader maria = new Trader("Maria", orderbook);
		Trader alice = new Trader("Alice", orderbook);
		
		
		mockOriginAccountGOLD.getWallet().transfer(GOLD, goldAmount, renata);
		String rTraderID = renata.getID();
		assertNotNull(rTraderID);
		assertTrue(!rTraderID.trim().isEmpty());
		Asset rAsset = renata.getWallet().getAsset(GOLD).getAsset();
		Double rAmount = renata.getWallet().getAsset(GOLD).getBalance().doubleValue(); // 4.11
		Asset rExchangeAssetID = USD; 
		Double rExchangeAssetAmount = usdAmount;
		Order bidOrder = renata.createOrder(rAsset, rAmount, rExchangeAssetID, rExchangeAssetAmount);
		assertNotNull(error1Message, renata.getWallet().getAsset(GOLD).getAsset());
		assertEquals(0, renata.getWallet().getAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(goldAmount)));
		assertEquals(null, renata.getWallet().getAsset(USD));
		
		mockOriginAccountUSD.getWallet().transfer(USD, usdAmount, thiago);
		String tTraderID = thiago.getID();
		assertNotNull(tTraderID);
		assertTrue(!tTraderID.trim().isEmpty());
		Asset tAsset = thiago.getWallet().getAsset(USD).getAsset();
		Double tAmount = thiago.getWallet().getAsset(USD).getBalance().doubleValue(); // 49.99
		Asset tExchangeAsset = GOLD; 
		Double tExchangeAssetAmount = goldAmount;
		Order askOrder = thiago.createOrder(tAsset, tAmount, tExchangeAsset, tExchangeAssetAmount);
		assertNotNull(error1Message, thiago.getWallet().getAsset(USD).getAsset());
		assertEquals(0, thiago.getWallet().getAsset(USD).getBalance().compareTo(BigDecimal.valueOf(usdAmount)));
		assertEquals(null, thiago.getWallet().getAsset(GOLD));
		
		mockOriginAccountBRONZE.getWallet().transfer(BRONZE, bronzeAmount, maria);
		String mTraderID = maria.getID();
		assertNotNull(mTraderID);
		assertTrue(!mTraderID.trim().isEmpty());
		Asset mAsset = maria.getWallet().getAsset(BRONZE).getAsset();
		Double mAmount = maria.getWallet().getAsset(BRONZE).getBalance().doubleValue(); // 157.33
		Asset mExchangeAsset = SILVER; 
		Double mExchangeAssetAmount = bronzeAmount;
		Order mOrder = maria.createOrder(mAsset, mAmount, mExchangeAsset, mExchangeAssetAmount);
		assertNotNull(error1Message, maria.getWallet().getAsset(BRONZE).getAsset());
		assertEquals(0, maria.getWallet().getAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(bronzeAmount)));
		assertEquals(null, maria.getWallet().getAsset(SILVER));
		
		mockOriginAccountSILVER.getWallet().transfer(SILVER, silverAmount, alice);
		String aTraderID = alice.getID();
		assertNotNull(aTraderID);
		assertTrue(!aTraderID.trim().isEmpty());
		Asset aAsset = alice.getWallet().getAsset(SILVER).getAsset();
		Double aAmount = alice.getWallet().getAsset(SILVER).getBalance().doubleValue(); // 43.44
		Asset aExchangeAsset = BRONZE; 
		Double aExchangeAssetAmount = silverAmount;
		Order aOrder = alice.createOrder(aAsset, aAmount, aExchangeAsset, aExchangeAssetAmount);
		assertNotNull(error1Message, alice.getWallet().getAsset(SILVER).getAsset());
		assertEquals(0, alice.getWallet().getAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(silverAmount)));
		assertEquals(null, alice.getWallet().getAsset(BRONZE));
		
		
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
		
		assertEquals(BigDecimal.ZERO, thiago.getWallet().getAsset(USD).getBalance());
		assertEquals(0, thiago.getWallet().getAsset(GOLD).getBalance().compareTo(new BigDecimal(goldAmount)));
		
		assertEquals(BigDecimal.ZERO, maria.getWallet().getAsset(BRONZE).getBalance());
		assertEquals(0, maria.getWallet().getAsset(SILVER).getBalance().compareTo(new BigDecimal(silverAmount)));
		
		assertEquals(BigDecimal.ZERO, alice.getWallet().getAsset(SILVER).getBalance());
		assertEquals(0, alice.getWallet().getAsset(BRONZE).getBalance().compareTo(new BigDecimal(bronzeAmount)));
	}
}