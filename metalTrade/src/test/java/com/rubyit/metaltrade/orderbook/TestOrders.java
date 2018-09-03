package com.rubyit.metaltrade.orderbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rubyit.metaltrade.TraderType;
import com.rubyit.metaltrade.obj.AssetType;
import com.rubyit.metaltrade.obj.Pair;

public class TestOrders {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
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
	public void testShouldThrowExceptionWhenCreateOrderWithNegativeBalance() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("ERROR: unable to create order for the asset {asset="
				+ "USD} with a balance {balance=260.44000} lower than assetTotalAmountPrice "
				+ "{assetTotalAmountPrice=385.7472}.");
		
		final Double silverAmount = 43.44;
		final Double silverUnitPriceInUSD = 8.88;
		
		MockOriginAccount mockOriginAccountUSD = new MockOriginAccount(USD);
		assertNotNull(mockOriginAccountUSD.getWalletAsset(USD));
		assertNotNull(mockOriginAccountUSD.getWalletAsset(USD).getBalance());
		assertEquals(0, mockOriginAccountUSD.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		MockOriginAccount mockOriginAccountSILVER = new MockOriginAccount(SILVER);
		assertNotNull(mockOriginAccountSILVER.getWalletAsset(SILVER));
		assertNotNull(mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance());
		assertEquals(0, mockOriginAccountSILVER.getWalletAsset(SILVER).getBalance().compareTo(BigDecimal.valueOf(MockOriginAccount.INITIAL_BALANCE)));
		
		TraderType maria = new MockTrader("Maria", USD);
		assertNotNull(maria.getWalletAsset(USD));
		assertNotNull(maria.getWalletAsset(USD).getBalance());
		assertEquals(0, maria.getWalletAsset(USD).getBalance().compareTo(BigDecimal.valueOf(MockTrader.INITIAL_BALANCE)));
		
		
		OrderBook orderbook = new OrderBook(GOLDxUSD, SILVERxUSD, BRONZExUSD, GOLDxSILVER, BRONZExSILVER);
		AssetType offeredAsset = USD;
		Double offeredAmount = silverUnitPriceInUSD;
		AssetType expectedAsset = SILVER;
		Double expectedAssetUnitPrice = silverAmount;
		
		maria.createOrder(orderbook, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice);
	}
	
	@Test
	public void testCreateAndAutoeexecuteOrders() {
		final Double usdAmount = 49.99;
		final Double usdUnitPrice = 1.00;
		final Double goldAmount = 4.11;
		final Double goldUnitPriceInUSD = 40.12;
		final Double silverAmount = 22.22;
		final Double silverUnitPriceInUSD = 8.88;
		final Double bronzeAmount = 147.33;
		final Double bronzeUnitPriceInUSD = 1.62;
		
		final MathContext mc = new MathContext(8, RoundingMode.HALF_EVEN);
		
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
	}

}