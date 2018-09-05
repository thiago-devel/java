package com.rubyit.metaltrade.orderbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rubyit.metaltrade.TraderType;
import com.rubyit.metaltrade.obj.AssetType;
import com.rubyit.metaltrade.obj.Pair;

public class TestOrders {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	final MathContext mc = new MathContext(8, RoundingMode.HALF_EVEN);
	
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
	public void shouldNotHaveOrdersInThePairAfterTheMatch(){
		Double usdAmount = null;
		Double usdUnitPrice = null;
		Double goldAmount = null;
		Double goldUnitPriceInUSD = null;
		Double silverAmount = null;
		Double silverUnitPriceInUSD = null;
		Double bronzeAmount = null;
		Double bronzeUnitPriceInUSD = null;
		
		MockOriginAccount mockOriginAccountUSD = null;
		MockOriginAccount mockOriginAccountGOLD = null;
		MockOriginAccount mockOriginAccountSILVER = null;
		MockOriginAccount mockOriginAccountBRONZE = null;
		
		TraderType thiago = null;
		TraderType renata = null;
		TraderType maria = null;
		TraderType alice = null;
		
		OrderBook orderbook = execute(usdAmount, usdUnitPrice, goldAmount, goldUnitPriceInUSD, silverAmount, 
				silverUnitPriceInUSD, bronzeAmount, bronzeUnitPriceInUSD, mc, mockOriginAccountUSD,
				mockOriginAccountGOLD, mockOriginAccountSILVER, mockOriginAccountBRONZE,
				thiago, renata, maria, alice);
		
		PairOrders goldXusdPairOrders = orderbook.retrievePairOrders(GOLDxUSD);
		assertEquals(0, goldXusdPairOrders.retrieveBuyOrders().size());
		assertEquals(0, goldXusdPairOrders.retrieveSellOrders().size());
		PairOrders silverXusdPairOrders = orderbook.retrievePairOrders(SILVERxUSD);
		assertEquals(0, silverXusdPairOrders.retrieveBuyOrders().size());
		assertEquals(0, silverXusdPairOrders.retrieveSellOrders().size());
		PairOrders bronzeXusdPairOrders = orderbook.retrievePairOrders(BRONZExSILVER);
		assertEquals(0, bronzeXusdPairOrders.retrieveBuyOrders().size());
		assertEquals(0, bronzeXusdPairOrders.retrieveSellOrders().size());
	}
	
	@Test
	public void shouldHaveAPairWithNotNullBidOrder(){
		Double usdAmount = null;
		Double usdUnitPrice = null;
		Double goldAmount = 4.11;
		Double goldUnitPriceInUSD = 40.12;
		Double silverAmount = null;
		Double silverUnitPriceInUSD = null;
		Double bronzeAmount = null;
		Double bronzeUnitPriceInUSD = null;
		
		MockOriginAccount mockOriginAccountUSD = null;
		MockOriginAccount mockOriginAccountGOLD = new MockOriginAccount(GOLD);
		MockOriginAccount mockOriginAccountSILVER = null;
		MockOriginAccount mockOriginAccountBRONZE = null;
		
		TraderType thiago = null;
		TraderType renata = new MockTrader("Renata", USD);
		TraderType maria = null;
		TraderType alice = null;
		
		AssetType offeredAsset = null;
		Double offeredAmount = null;
		AssetType expectedAsset = null;
		Double expectedAssetUnitPrice = null;
		
		OrderBook orderbook = execute(usdAmount, usdUnitPrice, goldAmount, goldUnitPriceInUSD, silverAmount, 
				silverUnitPriceInUSD, bronzeAmount, bronzeUnitPriceInUSD, mc, mockOriginAccountUSD,
				mockOriginAccountGOLD, mockOriginAccountSILVER, mockOriginAccountBRONZE,
				thiago, renata, maria, alice);
		
		Double assetTotalAmountPriceGoldUsd = null;
		
		offeredAsset = GOLD;
		offeredAmount = 4.11d;
		expectedAsset = USD;
		expectedAssetUnitPrice = 0.02492522d; // 1 / goldUnitPriceInUSD;
		Order order1 = renata.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order1.getPair());
		assertNotNull(order1.getAssetTotalAmountPrice());
		assetTotalAmountPriceGoldUsd = order1.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order1.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceGoldUsd), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		offeredAsset = GOLD;
		offeredAmount = 8.22d;
		expectedAsset = USD;
		expectedAssetUnitPrice = 40.12d;
		Order order2 = mockOriginAccountGOLD.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order2.getPair());
		assertNotNull(order2.getAssetTotalAmountPrice());
		assetTotalAmountPriceGoldUsd = order2.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order2.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceGoldUsd), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		PairOrders goldXusdPairOrders = orderbook.retrievePairOrders(GOLDxUSD);
		assertNotNull(goldXusdPairOrders);
		assertEquals(order1, goldXusdPairOrders.getBidOrder());
	}
	
	@Test
	public void shouldHaveAPairWithNotNullAskOrder(){
		Double usdAmount = null;
		Double usdUnitPrice = null;
		Double goldAmount = null;
		Double goldUnitPriceInUSD = null;
		Double silverAmount = null;
		Double silverUnitPriceInUSD = null;
		Double bronzeAmount = null;
		Double bronzeUnitPriceInUSD = null;
		
		MockOriginAccount mockOriginAccountUSD = new MockOriginAccount(USD);
		MockOriginAccount mockOriginAccountGOLD = null;
		MockOriginAccount mockOriginAccountSILVER = null;
		MockOriginAccount mockOriginAccountBRONZE = null;
		
		TraderType thiago = new MockTrader("Thiago", USD);
		TraderType renata = null;
		TraderType maria = null;
		TraderType alice = null;
		
		AssetType offeredAsset = null;
		Double offeredAmount = null;
		AssetType expectedAsset = null;
		Double expectedAssetUnitPrice = null;
		
		OrderBook orderbook = execute(usdAmount, usdUnitPrice, goldAmount, goldUnitPriceInUSD, silverAmount, 
				silverUnitPriceInUSD, bronzeAmount, bronzeUnitPriceInUSD, mc, mockOriginAccountUSD,
				mockOriginAccountGOLD, mockOriginAccountSILVER, mockOriginAccountBRONZE,
				thiago, renata, maria, alice);
		
		Double assetTotalAmountPriceUsdGold = null;
		
		offeredAsset = USD;
		offeredAmount = 1.33d;
		expectedAsset = GOLD;
		expectedAssetUnitPrice = 48.44d;
		Order order1 = thiago.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order1.getPair());
		assertNotNull(order1.getAssetTotalAmountPrice());
		assetTotalAmountPriceUsdGold = order1.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order1.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceUsdGold), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		offeredAsset = USD;
		offeredAmount = 12.55d;
		expectedAsset = GOLD;
		expectedAssetUnitPrice = 7.99d;
		Order order2 = mockOriginAccountUSD.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order2.getPair());
		assertNotNull(order2.getAssetTotalAmountPrice());
		assetTotalAmountPriceUsdGold = order2.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order2.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceUsdGold), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );

		PairOrders usdXgoldPairOrders = orderbook.retrievePairOrders(GOLDxUSD);
		assertNotNull(usdXgoldPairOrders);
		assertEquals(order1, usdXgoldPairOrders.getAskOrder());
	}

	@Test
	public void shouldNotMatchTwoOrdersIfTheBidPriceIsMajorThanTheAskPrice(){
		throw new UnsupportedOperationException("Not yet Implemented test.");
	}

	@Test
	public void shouldNotMatchTwoOrdersIfTheAskPriceIsMajorThanTheBidPrice(){
		throw new UnsupportedOperationException("Not yet Implemented test.");
	}
	
	@Test
	public void shouldPrintAPairWithThreeBuyAndSellOrders(){
		throw new UnsupportedOperationException("Not yet Implemented test.");
	}
	
	@Test
	public void createAndAutoeexecuteOrdersWithNonZeroTransactionFees(){
		throw new UnsupportedOperationException("Not yet Implemented test.");
	}
	
	@Test
	public void createAndAutoeexecuteOrdersWithNonZeroTransactionFeesMultithreading(){
		throw new UnsupportedOperationException("Not yet Implemented test."); //TODO: Need to figure out how to do it
	}
	
	@Test
	public void createAndAutoeexecuteOrdersWithZeroTransactionFees() {
		Double usdAmount = null;
		Double usdUnitPrice = null;
		Double goldAmount = null;
		Double goldUnitPriceInUSD = null;
		Double silverAmount = null;
		Double silverUnitPriceInUSD = null;
		Double bronzeAmount = null;
		Double bronzeUnitPriceInUSD = null;
		
		MockOriginAccount mockOriginAccountUSD = null;
		MockOriginAccount mockOriginAccountGOLD = null;
		MockOriginAccount mockOriginAccountSILVER = null;
		MockOriginAccount mockOriginAccountBRONZE = null;
		
		TraderType thiago = null;
		TraderType renata = null;
		TraderType maria = null;
		TraderType alice = null;
		
		AssetType offeredAsset = null;
		Double offeredAmount = null;
		AssetType expectedAsset = null;
		Double expectedAssetUnitPrice = null;
		
		execute(usdAmount, usdUnitPrice, goldAmount, goldUnitPriceInUSD, silverAmount, 
				silverUnitPriceInUSD, bronzeAmount, bronzeUnitPriceInUSD, mc, mockOriginAccountUSD,
				mockOriginAccountGOLD, mockOriginAccountSILVER, mockOriginAccountBRONZE,
				thiago, renata, maria, alice);
		
	}
	
	@Test
	public void shouldThrowExceptionWhenCreateOrderWithNegativeBalance() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage(
				"ERROR: unable to create order for the asset {asset=USD} offering "
				+ "the amount {offeredAmount=8.88} and expecting for "
				+ "{expectedAssetUnitPrice=43.44} having a balance "
				+ "{balance=260.44000} lower than assetTotalAmountPrice "
				+ "{assetTotalAmountPrice=385.7472}.");
		
		Double usdAmount = null;
		Double usdUnitPrice = null;
		Double goldAmount = null;
		Double goldUnitPriceInUSD = null;
		Double silverAmount = 43.44; // it will result in an insufictient silver balance
		Double silverUnitPriceInUSD = 8.88; // it will result in an insufictient silver balance
		Double bronzeAmount = null;
		Double bronzeUnitPriceInUSD = null;
		
		MockOriginAccount mockOriginAccountUSD = null;
		MockOriginAccount mockOriginAccountGOLD = null;
		MockOriginAccount mockOriginAccountSILVER = null;
		MockOriginAccount mockOriginAccountBRONZE = null;
		
		TraderType thiago = null;
		TraderType renata = null;
		TraderType maria = null;
		TraderType alice = null;
		
		AssetType offeredAsset = null;
		Double offeredAmount = null;
		AssetType expectedAsset = null;
		Double expectedAssetUnitPrice = null;
		
		execute(usdAmount, usdUnitPrice, goldAmount, goldUnitPriceInUSD, silverAmount, 
				silverUnitPriceInUSD, bronzeAmount, bronzeUnitPriceInUSD, mc, mockOriginAccountUSD,
				mockOriginAccountGOLD, mockOriginAccountSILVER, mockOriginAccountBRONZE,
				thiago, renata, maria, alice);
	}
	
	private OrderBook execute(Double usdAmount,  Double usdUnitPrice,  Double goldAmount,
			 Double goldUnitPriceInUSD,  Double silverAmount,  Double silverUnitPriceInUSD,
			 Double bronzeAmount,  Double bronzeUnitPriceInUSD,  MathContext mc,
			 MockOriginAccount mockOriginAccountUSD, MockOriginAccount mockOriginAccountGOLD,
			 MockOriginAccount mockOriginAccountSILVER, MockOriginAccount mockOriginAccountBRONZE,
			 TraderType thiago, TraderType renata, TraderType maria, TraderType alice) {
		
		usdAmount = (usdAmount == null) ? 49.99 : usdAmount;
		usdUnitPrice = (usdUnitPrice == null) ? 1.00 : usdUnitPrice;
		goldAmount = (goldAmount == null) ? 4.11 : goldAmount;
		goldUnitPriceInUSD = (goldUnitPriceInUSD == null) ? 40.12 : goldUnitPriceInUSD;
		silverAmount = (silverAmount == null) ? 22.22 : silverAmount;
		silverUnitPriceInUSD = (silverUnitPriceInUSD == null) ? 8.88 : silverUnitPriceInUSD;
		bronzeAmount = (bronzeAmount == null) ? 147.33 : bronzeAmount;
		bronzeUnitPriceInUSD = (bronzeUnitPriceInUSD == null) ? 1.62 : bronzeUnitPriceInUSD;
		
		mockOriginAccountUSD = (mockOriginAccountUSD == null) ? new MockOriginAccount(USD) : mockOriginAccountUSD;
		mockOriginAccountGOLD = (mockOriginAccountGOLD == null) ? new MockOriginAccount(GOLD) : mockOriginAccountGOLD;
		mockOriginAccountSILVER = (mockOriginAccountSILVER == null) ? new MockOriginAccount(SILVER) : mockOriginAccountSILVER;
		mockOriginAccountBRONZE = (mockOriginAccountBRONZE == null) ? new MockOriginAccount(BRONZE) : mockOriginAccountBRONZE;

		assertNotNull(mockOriginAccountUSD.getWalletAsset(USD));
		assertNotNull(mockOriginAccountUSD.getWalletAsset(USD).getBalance());
		assertEquals(0, mockOriginAccountUSD.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		assertNotNull(mockOriginAccountGOLD.getWalletAsset(GOLD));
		assertNotNull(mockOriginAccountGOLD.getWalletAsset(GOLD).getBalance());
		assertEquals(0, mockOriginAccountGOLD.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		assertNotNull(mockOriginAccountSILVER.getWalletAsset(SILVER));
		assertNotNull(mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance());
		assertEquals(0, mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		assertNotNull(mockOriginAccountBRONZE.getWalletAsset(BRONZE));
		assertNotNull(mockOriginAccountBRONZE.getWalletAsset(BRONZE).getBalance());
		assertEquals(0, mockOriginAccountBRONZE.getWalletAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		
		thiago = (thiago == null) ? new MockTrader("Thiago", USD) : thiago;
		renata = (renata == null) ? new MockTrader("Renata", USD) : renata;
		maria = (maria == null) ? new MockTrader("Maria", USD) : maria;
		alice = (alice == null) ? new MockTrader("Alice", USD): alice;
		
		assertNotNull(thiago.getWalletAsset(USD));
		assertNotNull(thiago.getWalletAsset(USD).getBalance());
		assertEquals(0, thiago.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		assertNotNull(renata.getWalletAsset(USD));
		assertNotNull(renata.getWalletAsset(USD).getBalance());
		assertEquals(0, renata.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		assertNotNull(maria.getWalletAsset(USD));
		assertNotNull(maria.getWalletAsset(USD).getBalance());
		assertEquals(0, maria.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
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
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceGoldUsd), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		offeredAsset = SILVER;
		offeredAmount = silverAmount;
		expectedAsset = USD;
		expectedAssetUnitPrice = silverUnitPriceInUSD;
		Order order2 = mockOriginAccountSILVER.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(SILVERxUSD, order2.getPair());
		assertNotNull(order2.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceSilverUsd = order2.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order2.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceSilverUsd), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		offeredAsset = BRONZE;
		offeredAmount = bronzeAmount;
		expectedAsset = USD;
		expectedAssetUnitPrice = bronzeUnitPriceInUSD;
		Order order3 = mockOriginAccountBRONZE.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(BRONZExUSD, order3.getPair());
		assertNotNull(order3.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceBronzeUsd = order3.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order2.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceBronzeUsd), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );

		offeredAsset = USD;
		offeredAmount = goldUnitPriceInUSD;
		expectedAsset = GOLD;
		expectedAssetUnitPrice = goldAmount;
		Order order4 = renata.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(GOLDxUSD, order4.getPair());
		assertNotNull(order4.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceUsdGold = order4.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order4.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceUsdGold), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		offeredAsset = USD;
		offeredAmount = silverUnitPriceInUSD;
		expectedAsset = SILVER;
		expectedAssetUnitPrice = silverAmount;
		Order order5 = maria.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(SILVERxUSD, order5.getPair());
		assertNotNull(order5.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceUsdSilver = order5.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order5.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceUsdSilver), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		offeredAsset = USD;
		offeredAmount = bronzeUnitPriceInUSD;
		expectedAsset = BRONZE;
		expectedAssetUnitPrice = bronzeAmount;
		Order order6 = alice.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
		assertEquals(BRONZExUSD, order6.getPair());
		assertNotNull(order6.getAssetTotalAmountPrice());
		Double assetTotalAmountPriceUsdBronze = order6.getAssetTotalAmountPrice().doubleValue();
		assertNotEquals(0, order6.getAssetTotalAmountPrice().compareTo(BigDecimal.ZERO));
		assertEquals(BigDecimal.valueOf(assetTotalAmountPriceUsdBronze), BigDecimal.valueOf(offeredAmount).multiply(BigDecimal.valueOf(expectedAssetUnitPrice), mc) );
		
		
		assertEquals(0, thiago.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		assertNotNull(renata.getWalletAsset(GOLD));
		assertNotNull(renata.getWalletAsset(GOLD).getBalance());
		assertEquals(0, renata.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(goldAmount)));
		assertEquals(0, renata.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(goldAmount)));
		assertNotNull(maria.getWalletAsset(SILVER));
		assertNotNull(maria.getWalletAsset(SILVER).getBalance());
		assertEquals(0, maria.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(silverAmount)));
		assertNotNull(alice.getWalletAsset(BRONZE));
		assertNotNull(alice.getWalletAsset(BRONZE).getBalance());
		assertEquals(0, alice.getWalletAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(bronzeAmount)));
		
		assertEquals(0, mockOriginAccountGOLD.getWalletAsset(GOLD).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE).subtract(BigDecimal.valueOf(goldUnitPriceInUSD).multiply(BigDecimal.valueOf(goldAmount)), mc)));
		assertEquals(0, mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE).subtract(BigDecimal.valueOf(silverUnitPriceInUSD).multiply(BigDecimal.valueOf(silverAmount)), mc)));
		assertEquals(0, mockOriginAccountBRONZE.getWalletAsset(BRONZE).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE).subtract(BigDecimal.valueOf(bronzeUnitPriceInUSD).multiply(BigDecimal.valueOf(bronzeAmount)), mc)));
		
		assertEquals(0, thiago.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		assertNotNull(renata.getWalletAsset(USD));
		assertNotNull(renata.getWalletAsset(USD).getBalance());
		assertEquals(0, renata.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(goldUnitPriceInUSD).multiply(BigDecimal.valueOf(goldAmount)), mc)));
		assertNotNull(maria.getWalletAsset(USD));
		assertNotNull(maria.getWalletAsset(USD).getBalance());
		assertEquals(0, maria.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(silverUnitPriceInUSD).multiply(BigDecimal.valueOf(silverAmount)), mc)));
		assertNotNull(alice.getWalletAsset(USD));
		assertNotNull(alice.getWalletAsset(USD).getBalance());
		assertEquals(0, alice.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE).subtract(BigDecimal.valueOf(bronzeUnitPriceInUSD).multiply(BigDecimal.valueOf(bronzeAmount)), mc)));
		
		
		return orderbook;
	}
}