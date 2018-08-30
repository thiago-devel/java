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
import java.util.Optional;
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

	public String getCurrencyPairName() {
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
	/*
	public List<Order> getBidOrders() {
		return new ArrayList<Order>(buyOrders);
	}
	
	public List<Order> getAskOrders() {
		return new ArrayList<Order>(sellOrders);
	}
	*/
	public List<Order> getBuyOrders() {
		return new ArrayList<Order>(buyOrders); // TODO: to order by value before return
	}
	
	public List<Order> getSellOrders() {
		return new ArrayList<Order>(sellOrders); // TODO: to order by value before return
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
	private Pair pair;
	private Type type;
	
	public Order(String traderID, Asset asset, Double amount, Asset exchangeAsset,
			Double exchangeAssetAmount, Pair pair) {
		if (
				(traderID == null || traderID.trim().isEmpty())
				|| (amount == null)
				|| (exchangeAssetAmount == null)
			) {
			throw new RuntimeException("ERROR: unable to create a order with "
					+ "invalid fields: {traderID=" + traderID + ", amount=" 
					+ amount+ ", exchangeAssetAmount=" + exchangeAssetAmount 
					+ ", pair=" + pair + "}") ;
		}
		ID = UUID.randomUUID().toString();
		this.traderID = traderID;
		this.asset = asset;
		this.amount = amount;
		this.exchangeAsset = exchangeAsset;
		this.exchangeAssetAmount = exchangeAssetAmount;
		this.pair = pair;
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
	
	public Pair getPair() {
		return pair;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public int compareTo(Order other) {
		
		return exchangeAssetAmount.compareTo(other.exchangeAssetAmount);
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("pair", pair);
		json.put("type", type);
		json.put("traderID", traderID);
		json.put("asset", asset);
		json.put("amount", amount);
		json.put("exchangeAsset", exchangeAsset);
		json.put("exchangeAssetAmount", exchangeAssetAmount);
		return getGson().toJson(json);
	}
	
	public enum Type {
		BUY, SELL;
	}
}

class OrderBook {
	
	// notActivedOrder
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
	
	public CurrencyPair findCurrencyPairBy(Optional<String> currencyPairName) {
		
		pairChangeLock.lock();
		try {
			for (CurrencyPair currencyPair : pairs) {
				String pairName = currencyPair.getPair().getCurrencyPairName();
				if (currencyPairName.get().equals(pairName)) {
					return currencyPair;
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
					return currencyPair;//new CurrencyPair(currencyPair);
				}
				if ((priceAssetID.equals(assetID)) && (amountAssetID.equals(exchangeAssetID))) {
					return currencyPair;//new CurrencyPair(currencyPair);
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	/*
	public Order getBidOrder(Pair pair) {
		CurrencyPair cPair = findCurrencyPairBy(Optional.of(pair.getCurrencyPairName()));
		List<Order> orders = (cPair == null) ? null : cPair.getBidOrders();
		return (orders == null) ? null : cPair.getBidOrders().get(0);
	}
	
	public Order getAskOrder(Pair pair) {
		CurrencyPair cPair = findCurrencyPairBy(Optional.of(pair.getCurrencyPairName()));
		List<Order> orders = (cPair == null) ? null : cPair.getAskOrders();
		return (orders == null) ? null : cPair.getAskOrders().get(0);
	}
	*/
	
	public List<Order> getSellOrders(Pair pair) {
		CurrencyPair cPair = findCurrencyPairBy(Optional.of(pair.getCurrencyPairName()));
		return (cPair == null) ? null : cPair.getSellOrders();
	}
	
	public List<Order> getBuyOrders(Pair pair) {
		CurrencyPair cPair = findCurrencyPairBy(Optional.of(pair.getCurrencyPairName()));
		return (cPair == null) ? null : cPair.getBuyOrders();
	}

	public List<CurrencyPair> getAllCurrencyPairs() {
		return new ArrayList<>(pairs);
	}
	
	public List<CurrencyPair> getCurrencyPairs(Pair pair) {
		return new ArrayList<>(pairs);
	}
	
	public void performMatcher(Pair pair) {
		// TODO Auto-generated method stub
		
	}

	// TODO: implement check if the order was matched to other existing order, that case, update the two Trade accounts, or add it to notActivedOrder
	public Order createOrder(Trader trader, Asset asset, Double amount, Asset exchangeAsset,
			Double exchangeAssetAmount) {
		
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
	protected String name;
	protected List<Order> createdOrders;
	protected List<Order> filledOrders;
	
	Trader(String name, OrderBook orderbook) {
		super();
		if (name == null || name.trim().isEmpty()) {
			throw new RuntimeException("ERROR: unable to create a invalid name {name=" + name + "}");
		}
		this.name = name;
		this.createdOrders = new ArrayList<>();
	}
	
	public String getID() {
		return super.getID();
	}
	
	public String getName() {
		return name;
	}
	
	public List<Order> getCreatedOrders() {
		return new ArrayList<>(createdOrders);
	}
	
	public List<Order> getFilledOrders() {
		return new ArrayList<>(filledOrders);
	}
	
	void update() { // it will be called by the orderbook
		
	}
	
	void orderFilled(Order order) { // it will be called by the orderbook
		
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", getID());
		json.put("name", name);
		json.put("myWallet", myWallet);
		json.put("createdOrders", createdOrders);
		json.put("filledrders", filledOrders);
		return getGson().toJson(json);
	}

	// TODO: implements
	public Order createOrder(final OrderBook orderbook, final Asset asset, final Double amount, final Asset exchangeAsset,
			final Double exchangeAssetAmount) {
		
		Order createdOrder = orderbook.createOrder(this, asset, amount, exchangeAsset, exchangeAssetAmount);
		createdOrders.add(createdOrder);
		
		return null;
		
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
		Order rOrder = renata.createOrder(rAsset, rAmount, rExchangeAssetID, rExchangeAssetAmount);
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
		Order tOrder = thiago.createOrder(tAsset, tAmount, tExchangeAsset, tExchangeAssetAmount);
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
		Double mExchangeAssetAmount = silverAmount;
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
		Double aExchangeAssetAmount = bronzeAmount;
		Order aOrder = alice.createOrder(aAsset, aAmount, aExchangeAsset, aExchangeAssetAmount);
		assertNotNull(error1Message, alice.getWallet().getAsset(SILVER).getAsset());
		assertEquals(0, alice.getWallet().getAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(silverAmount)));
		assertEquals(null, alice.getWallet().getAsset(BRONZE));
		
		/*
		private static final Pair GOLDxUSD = new Pair(GOLD, USD);//"GOLD/USD" 
		private static final Pair GOLDxSILVER = new Pair(GOLD, SILVER);//"GOLD/SILVER" 
		private static final Pair BRONZExSILVER = new Pair(BRONZE, SILVER);//"BRONZE/SILVER" 
		 */
		/*
		assertNotNull("BID should not be null", orderbook.getBidOrder(GOLDxUSD));
		assertNotNull("ASK should not be null", orderbook.getAskOrder(GOLDxUSD));
		assertEquals(rOrder, orderbook.getBidOrder(GOLDxUSD));
		assertEquals(tOrder, orderbook.getAskOrder(GOLDxUSD));
		
		assertNotNull("BID should not be null", orderbook.getBidOrder(GOLDxSILVER));
		assertNotNull("ASK should not be null", orderbook.getAskOrder(GOLDxSILVER));
		assertEquals(rOrder, orderbook.getBidOrder(GOLDxSILVER));
		assertEquals(tOrder, orderbook.getAskOrder(GOLDxSILVER));
		
		assertNotNull("BID should not be null", orderbook.getBidOrder(BRONZExSILVER));
		assertNotNull("ASK should not be null", orderbook.getAskOrder(BRONZExSILVER));
		assertEquals(rOrder, orderbook.getBidOrder(BRONZExSILVER));
		assertEquals(tOrder, orderbook.getAskOrder(BRONZExSILVER));
		*/
		
		
		
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