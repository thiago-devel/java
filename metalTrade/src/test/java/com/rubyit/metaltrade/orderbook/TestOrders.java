package com.rubyit.metaltrade.orderbook;

import static com.rubyit.metaltrade.Utils.getGson;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.rubyit.metaltrade.obj.AssetType;

public class TestOrders {
	
	private static final AssetType BRONZE = new AssetType("BRONZE");
	private static final AssetType SILVER = new AssetType("SILVER");
	private static final AssetType GOLD = new AssetType("GOLD");
	private static final AssetType USD = new AssetType("USD");
	
	// {BASE CURRENCY == AMOUNT}/{QUOTE CURRENCY == price} 
	private static final Pair GOLDxUSD = new Pair(GOLD, USD);//"GOLD/USD" 
	private static final Pair SILVERxUSD = new Pair(SILVER, USD);//"GOLD/USD" 
	private static final Pair BRONZExUSD = new Pair(BRONZE, USD);//"GOLD/USD" 
	private static final Pair GOLDxSILVER = new Pair(GOLD, SILVER);//"GOLD/SILVER" 
	private static final Pair BRONZExSILVER = new Pair(BRONZE, SILVER);//"BRONZE/SILVER"
	
	@Test
	public void testCreateAndAutoeexecuteOrders() {
		final Double usdAmount = 49.99;
		final Double usdUnitPrice = 1.00;
		final Double goldAmount = 4.11;
		final Double goldUnitPriceInUSD = 40.12;
		final Double silverAmount = 43.44;
		final Double silverUnitPriceInUSD = 8.88;
		final Double bronzeAmount = 157.33;
		final Double bronzeUnitPriceInUSD = 1.62;
		
		MockOriginAccount mockOriginAccountUSD = new MockOriginAccount(USD);
		assertNotNull(mockOriginAccountUSD.getWalletAsset(USD));
		assertNotNull(mockOriginAccountUSD.getWalletAsset(USD).getBalance());
		assertEquals(0, mockOriginAccountUSD.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		MockOriginAccount mockOriginAccountGOLD = new MockOriginAccount(GOLD);
		assertNotNull(mockOriginAccountGOLD.getWalletAsset(GOLD));
		assertNotNull(mockOriginAccountGOLD.getWalletAsset(GOLD).getBalance());
		assertEquals(0, mockOriginAccountGOLD.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		MockOriginAccount mockOriginAccountSILVER = new MockOriginAccount(SILVER);
		assertNotNull(mockOriginAccountSILVER.getWalletAsset(SILVER));
		assertNotNull(mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance());
		assertEquals(0, mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		MockOriginAccount mockOriginAccountBRONZE = new MockOriginAccount(BRONZE);
		assertNotNull(mockOriginAccountBRONZE.getWalletAsset(BRONZE));
		assertNotNull(mockOriginAccountBRONZE.getWalletAsset(BRONZE).getBalance());
		assertEquals(0, mockOriginAccountBRONZE.getWalletAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		
		TraderType thiago = new MockTrader("Thiago", USD);
		assertNotNull(thiago.getWalletAsset(USD));
		assertNotNull(thiago.getWalletAsset(USD).getBalance());
		assertEquals(0, thiago.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		TraderType renata = new MockTrader("Renata", USD);
		assertNotNull(renata.getWalletAsset(USD));
		assertNotNull(renata.getWalletAsset(USD).getBalance());
		assertEquals(0, renata.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		TraderType maria = new MockTrader("Maria", USD);
		assertNotNull(maria.getWalletAsset(USD));
		assertNotNull(maria.getWalletAsset(USD).getBalance());
		assertEquals(0, maria.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		TraderType alice = new MockTrader("Alice", USD);
		assertNotNull(alice.getWalletAsset(USD));
		assertNotNull(alice.getWalletAsset(USD).getBalance());
		assertEquals(0, alice.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		
		
		OrderBook orderbook = new OrderBook(GOLDxUSD, SILVERxUSD, BRONZExUSD, GOLDxSILVER, BRONZExSILVER);
		AssetType offeredAsset = null;
		Double offeredAmount = null;
		AssetType expectedAsset = null;
		Double expectedAssetUnitPrice = null;
		
		offeredAsset = USD;
		offeredAmount = usdAmount;
		expectedAsset = USD;
		expectedAssetUnitPrice = usdUnitPrice;
		//mockOriginAccountUSD.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);//getWallet().transfer(USD, usdAmount, thiago);
		
		
		offeredAsset = GOLD;
		offeredAmount = goldAmount;
		expectedAsset = USD;
		expectedAssetUnitPrice = goldUnitPriceInUSD;
		Order order1 = mockOriginAccountGOLD.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order1.getPair());
		assertNotNull(order1.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceGoldUsd = order1.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order1.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(assetTotalAmountPriceGoldUsd, BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice)) );
		
		offeredAsset = SILVER;
		offeredAmount = silverAmount;
		expectedAsset = USD;
		expectedAssetUnitPrice = silverUnitPriceInUSD;
		Order order2 = mockOriginAccountSILVER.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(SILVERxUSD, order2.getPair());
		assertNotNull(order2.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceSilverUsd = order2.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order2.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(assetTotalAmountPriceSilverUsd, BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice)) );
		
		offeredAsset = BRONZE;
		offeredAmount = bronzeAmount;
		expectedAsset = USD;
		expectedAssetUnitPrice = bronzeUnitPriceInUSD;
		Order order3 = mockOriginAccountBRONZE.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(BRONZExUSD, order3.getPair());
		assertNotNull(order3.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceBronzeUsd = order3.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order2.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(assetTotalAmountPriceBronzeUsd, BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice)) );

		offeredAsset = USD;
		offeredAmount = goldUnitPriceInUSD;
		expectedAsset = GOLD;
		expectedAssetUnitPrice = goldAmount;
		Order order4 = renata.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order4.getPair());
		assertNotNull(order4.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceUsdGold = order4.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order4.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(assetTotalAmountPriceUsdGold, BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice)) );
		
		offeredAsset = USD;
		offeredAmount = silverUnitPriceInUSD;
		expectedAsset = SILVER;
		expectedAssetUnitPrice = silverAmount;
		Order order5 = maria.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(SILVERxUSD, order5.getPair());
		assertNotNull(order5.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceUsdSilver = order5.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order5.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(assetTotalAmountPriceUsdSilver, BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice)) );
		
		offeredAsset = USD;
		offeredAmount = bronzeUnitPriceInUSD;
		expectedAsset = BRONZE;
		expectedAssetUnitPrice = bronzeAmount;
		Order order6 = alice.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(BRONZExUSD, order6.getPair());
		assertNotNull(order6.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceUsdBronze = order5.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order6.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(assetTotalAmountPriceUsdBronze, BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice)) );
		
		
		assertEquals(0, thiago.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		assertEquals(0, renata.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(goldAmount)));
		assertEquals(0, maria.getWalletAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(silverAmount)));
		assertEquals(0, alice.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(bronzeAmount)));
		
		assertEquals(0, mockOriginAccountGOLD.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(goldAmount))));
		assertEquals(0, mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(silverAmount))));
		assertEquals(0, mockOriginAccountBRONZE.getWalletAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(bronzeAmount))));
		
		assertEquals(0, thiago.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		assertEquals(0, renata.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(goldUnitPriceInUSD))));
		assertEquals(0, maria.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(silverUnitPriceInUSD))));
		assertEquals(0, alice.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(bronzeUnitPriceInUSD))));
	}

}

final class MockTrader extends Trader {
	
	public static final Double INITIAL_BALANCE = 60.44;
	MockTrader(String name, AssetType usd) {
		super(name);
		Asset myAsset = new Asset(usd);
		ReflectionTestUtils.setField(myAsset, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<Asset> assets = new HashSet<>(Arrays.asList(myAsset));
		ReflectionTestUtils.setField(myWallet, "assets", assets);
	}
}

class MockOriginAccount extends Trader {
	
	public static final Double INITIAL_BALANCE = 500.00;
	
	MockOriginAccount(AssetType asset) {
		super("Mock" + asset.getName());
		Asset myAsset = new Asset(asset);
		ReflectionTestUtils.setField(myAsset, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<Asset> assets = new HashSet<>(Arrays.asList(myAsset));
		Wallet myWallet = new Wallet();
		ReflectionTestUtils.setField(myWallet, "assets", assets);
		this.myWallet = myWallet;
	}
}

class OrderBook {
	
	private Set<Pair> pairs;
	transient private Lock pairChangeLock;
	
	public OrderBook(Pair... pairs) {
		if (pairs == null || pairs.length == 0) {
			throw new RuntimeException("ERROR: unable to create a orderbook without at least one Pair");
		}
		
		this.pairs = new HashSet<>(Arrays.asList(pairs));
		pairChangeLock = new ReentrantLock();
	}

	public Order createOrder(Trader trader, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset,
			Double expectedAssetUnitPrice) {
		
		String offeredAssetID = offeredAsset.getID();
		String expectedAssetID = expectedAsset.getID();
		Pair pair = findPairBy(offeredAssetID, expectedAssetID);

		if (pair == null) {
			
			for (String id : Arrays.asList(offeredAssetID, expectedAssetID)) {
				pair = findPairBy(id);
				if (pair != null) {
					break;
				}
			}
		}
		if (pair == null) {
			throw new RuntimeException("ERROR: unable to find on the orderbook a "
					+ "compatible Pair for Assets with {offeredAsset=" + offeredAsset + 
					", expectedAsset=" + expectedAsset + "}");
		}
		
		String amountAssetID = pair.getAmountAsset().getID();
		String priceAssetID = pair.getPriceAsset().getID();
		if (
			(offeredAssetID.equals(amountAssetID))
			&& (expectedAssetID.equals(priceAssetID))
		   ) {
			
			return new Order(trader, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice, pair, Order.Type.SELL);
		}
		if (
			(offeredAssetID.equals(priceAssetID))
			&& (expectedAssetID.equals(amountAssetID))
		   ) {
			
			return new Order(trader, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice, pair, Order.Type.BUY);
		}
		
		throw new RuntimeException("ERROR: that order {offeredAsset=" + offeredAsset + ", expectedAsset=" + expectedAsset+ "} doesn't belongs to that pair {" + pair + "}");
	}
	
	public Pair findPairBy(String offeredAssetID, String expectedAssetID) {
		
		pairChangeLock.lock();
		try {
			for (Pair pair : pairs) {
				String amountAssetID = pair.getAmountAsset().getID();
				String priceAssetID = pair.getPriceAsset().getID();
				if ((amountAssetID.equals(offeredAssetID)) && (priceAssetID.equals(expectedAssetID))) {
					return pair;
				}
				if ((priceAssetID.equals(offeredAssetID)) && (amountAssetID.equals(expectedAssetID))) {
					return pair;
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
	public Pair findPairBy(Optional<String> optionalPairName) {
		
		pairChangeLock.lock();
		try {
			for (Pair pair : pairs) {
				String pairName = pair.getPairName();
				if (optionalPairName.get().equals(pairName)) {
					return pair;
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
	public Pair findPairBy(String assetID) {
		
		pairChangeLock.lock();
		try {
			for (Pair pair : pairs) {
				String amountAssetID = pair.getAmountAsset().getID();
				String priceAssetID = pair.getPriceAsset().getID();
				if (amountAssetID.equals(assetID)) {
					return pair;//new CurrencyPair(currencyPair);
				}
				if (priceAssetID.equals(assetID)) {
					return pair;//new CurrencyPair(currencyPair);
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
}

class Order implements Comparable<Order> {
	
	private String ID;
	private String traderID;
	private AssetType offeredAsset;
	private BigDecimal offeredAmount;
	private AssetType expectedAsset;
	private BigDecimal expectedAssetUnitPrice;
	private BigDecimal assetTotalAmountPrice;
	private MathContext mc;
	private Pair pair;
	private Type type;
	private DateTimeFormatter sdf;
	private LocalDate operationDate;
	
	public Order(Trader trader, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset,
			Double expectedAssetUnitPrice, Pair pair, Type type) {
		ID = UUID.randomUUID().toString();
		traderID = trader.getID();
		this.offeredAsset = offeredAsset;
		mc = new MathContext(8, RoundingMode.HALF_EVEN);
		this.offeredAmount = new BigDecimal(offeredAmount, mc);
		this.expectedAsset = expectedAsset;
		this.expectedAssetUnitPrice = new BigDecimal(expectedAssetUnitPrice, mc);
		this.assetTotalAmountPrice = this.offeredAmount.multiply(this.expectedAssetUnitPrice);
		sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		operationDate = LocalDate.now();
	}
	
	public String getID() {
		return ID;
	}
	
	public String getTraderID() {
		return traderID;
	}
	
	public AssetType getOfferedAsset() {
		return offeredAsset;
	}
	
	public BigDecimal getOfferedAmount() {
		return offeredAmount;
	}
	
	public AssetType getExpectedAsset() {
		return expectedAsset;
	}
	
	public BigDecimal getExpectedAssetUnitPrice() {
		return expectedAssetUnitPrice;
	}

	public BigDecimal getAssetTotalAmountPrice() {
		return assetTotalAmountPrice;
	}
	
	public Pair getPair() {
		return pair;
	}
	
	public Type getType() {
		return type;
	}

	public LocalDate getOperationDate() {
		return operationDate;
	}
	
	@Override
	public int compareTo(Order other) {
		
		return operationDate.compareTo(other.operationDate);
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("traderID", traderID);
		json.put("offeredAsset", offeredAsset);
		json.put("offeredAmount", offeredAmount);
		json.put("expectedAsset", expectedAsset);
		json.put("expectedAssetUnitPrice", expectedAssetUnitPrice);
		json.put("assetTotalAmountPrice", assetTotalAmountPrice);
		json.put("pair", pair);
		json.put("type", type);
		json.put("operationDate", sdf.format(operationDate));
		return getGson().toJson(json);
	}
	
	public enum Type {
		BUY, SELL;
	}
}

class Trader extends Account implements TraderType {

	protected String name;
	protected List<Order> createdOrders;
	protected List<Order> filledOrders;
	
	Trader(String name) {
		super();
		if (name == null || name.trim().isEmpty()) {
			throw new RuntimeException("ERROR: unable to create a invalid name {name=" + name + "}");
		}
		this.name = name;
		this.createdOrders = new ArrayList<>();
		this.filledOrders = new ArrayList<>();
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
	
	public Order createOrder(OrderBook orderbook, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset, Double expectedAssetUnitPrice) {
		Order createdOrder = orderbook.createOrder(this, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		createdOrders.add(createdOrder);
		
		return createdOrder;
	}
	
	public void fillOrder(Order filledOrder, String createdOrderID) {
		for (Order order : createdOrders) {
			
			if (order.getID().equals(createdOrderID)) {
				
				// perform withdraw and deposit
				AssetType offeredAsset = filledOrder.getOfferedAsset();
				BigDecimal offeredAmount = filledOrder.getOfferedAmount();
				AssetType expectedAsset = filledOrder.getExpectedAsset();
				BigDecimal totalAmountPrice = filledOrder.getAssetTotalAmountPrice();
				try {
					myWallet.getAsset(offeredAsset).withdraw(offeredAmount);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//assetChangeLock.unlock();
					throw new RuntimeException("ERROR: unable to perform transfer", e);
				}
				myWallet.getAsset(expectedAsset).deposit(totalAmountPrice);
				
				
				createdOrders.remove(order);
				filledOrders.add(filledOrder);
			}
		}
	}
}

interface TraderType {
	String getID();
	String getName();
	List<Order> getCreatedOrders();
	List<Order> getFilledOrders();
	Asset getWalletAsset(AssetType asset);
	List<Asset> getWalletAllAssets();
	Order createOrder(OrderBook orderbook, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset, Double expectedAssetUnitPrice);
}

class Account {

	protected Wallet myWallet;
	private String ID;
	
	Account() {
		myWallet = new Wallet();
		ID = UUID.randomUUID().toString();
	}
	
	public Asset getWalletAsset(AssetType asset) {
		return myWallet.getAsset(asset);
	}
	
	public List<Asset> getWalletAllAssets() {
		return myWallet.getAssets();
	}
	public String getID() {
		return ID;
	}
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("myWallet", myWallet);
		return getGson().toJson(json);
	}
}

class Wallet {
	
	private String ID;
	private Set<Asset> assets;
	
	public Wallet() {
		ID = UUID.randomUUID().toString();
		assets = new HashSet<>();
	}
	public Asset getAsset(final AssetType asset) {
		
		for (Asset myAsset : assets) {
			if (myAsset.getAsset().equals(asset)) {
				return myAsset;
			}
		}
		
		return null;
	}
	public List<Asset> getAssets() {
		return new ArrayList<>(assets);
	}
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("assets", assets);
		return getGson().toJson(json);
	}
}

class Asset {
	
	private AssetType asset;
	private BigDecimal balance;
	transient private Lock balanceChangeLock;
	transient private Condition sufficientFundsCondition;
	
	public Asset(AssetType asset) {
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
	public AssetType getAsset() {
		return asset;
	}
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("assetType", asset);
		json.put("balance", balance);
		return getGson().toJson(json);
	}
}

class Pair {

	private final AssetType amountAsset;
	private final AssetType priceAsset;
	private final String currencyPair; 
	
	Pair(AssetType amountAsset, AssetType priceAsset) {
		if (amountAsset == null || priceAsset == null) {
			throw new RuntimeException("ERROR: unable to create a currency pair with invalid fields: {amountAsset=" + amountAsset + ", priceAsset=" + priceAsset + "}") ;
		}
		this.amountAsset = amountAsset;
		this.priceAsset = priceAsset;
		this.currencyPair = amountAsset.getName() + "/" + priceAsset.getName(); 
	}

	public AssetType getAmountAsset() {
		return amountAsset;
	}

	public AssetType getPriceAsset() {
		return priceAsset;
	}

	public String getPairName() {
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