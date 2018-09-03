package com.rubyit.metaltrade.orderbook;

import static com.rubyit.metaltrade.Utils.getGson;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.rubyit.metaltrade.TraderType;
import com.rubyit.metaltrade.obj.AssetType;
import com.rubyit.metaltrade.obj.Pair;

public class Order implements Comparable<Order> {

	private String ID;
	private String traderID;
	private AssetType offeredAsset;
	private BigDecimal offeredAmount;
	private AssetType expectedAsset;
	private BigDecimal expectedAssetUnitPrice;
	private BigDecimal assetTotalAmountPrice;
	transient private MathContext mc;
	private Pair pair;
	private Type type;
	transient private DateTimeFormatter sdf;
	private LocalDateTime operationDate;

	public Order(TraderType trader, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset,
			Double expectedAssetUnitPrice, Pair pair, Type type) {

		if (trader == null || offeredAsset == null || offeredAmount == null || expectedAsset == null
				|| expectedAssetUnitPrice == null || pair == null || type == null) {
			throw new RuntimeException("ERROR: unable to create an invalid " + "order with fields {trader=" + trader
					+ ", offeredAmount=" + offeredAmount + ", expectedAsset=" + expectedAsset
					+ ", expectedAssetUnitPrice=" + expectedAssetUnitPrice + ", pair=" + pair + ", type=" + type + "}");
		}

		ID = UUID.randomUUID().toString();
		traderID = trader.getID();
		this.offeredAsset = offeredAsset;
		mc = new MathContext(8, RoundingMode.HALF_EVEN);
		this.offeredAmount = new BigDecimal(offeredAmount, mc);
		this.expectedAsset = expectedAsset;
		this.expectedAssetUnitPrice = new BigDecimal(expectedAssetUnitPrice, mc);
		this.assetTotalAmountPrice = this.offeredAmount.multiply(this.expectedAssetUnitPrice, mc);
		this.pair = pair;
		this.type = type;
		sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		operationDate = LocalDateTime.now();
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

	public LocalDateTime getOperationDate() {
		return operationDate;
	}

	@Override
	public int compareTo(Order other) {
		int result = expectedAssetUnitPrice.compareTo(other.expectedAssetUnitPrice);

		return (result != 0) ? result : operationDate.compareTo(other.operationDate);
	}

	@Override
	public String toString() {
		Map<String, Object> json = new LinkedHashMap<String, Object>();
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
