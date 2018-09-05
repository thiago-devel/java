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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((assetTotalAmountPrice == null) ? 0 : assetTotalAmountPrice.hashCode());
		result = prime * result + ((expectedAsset == null) ? 0 : expectedAsset.hashCode());
		result = prime * result + ((expectedAssetUnitPrice == null) ? 0 : expectedAssetUnitPrice.hashCode());
		result = prime * result + ((offeredAmount == null) ? 0 : offeredAmount.hashCode());
		result = prime * result + ((offeredAsset == null) ? 0 : offeredAsset.hashCode());
		result = prime * result + ((operationDate == null) ? 0 : operationDate.hashCode());
		result = prime * result + ((pair == null) ? 0 : pair.hashCode());
		result = prime * result + ((traderID == null) ? 0 : traderID.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (assetTotalAmountPrice == null) {
			if (other.assetTotalAmountPrice != null)
				return false;
		} else if (!assetTotalAmountPrice.equals(other.assetTotalAmountPrice))
			return false;
		if (expectedAsset == null) {
			if (other.expectedAsset != null)
				return false;
		} else if (!expectedAsset.equals(other.expectedAsset))
			return false;
		if (expectedAssetUnitPrice == null) {
			if (other.expectedAssetUnitPrice != null)
				return false;
		} else if (!expectedAssetUnitPrice.equals(other.expectedAssetUnitPrice))
			return false;
		if (offeredAmount == null) {
			if (other.offeredAmount != null)
				return false;
		} else if (!offeredAmount.equals(other.offeredAmount))
			return false;
		if (offeredAsset == null) {
			if (other.offeredAsset != null)
				return false;
		} else if (!offeredAsset.equals(other.offeredAsset))
			return false;
		if (operationDate == null) {
			if (other.operationDate != null)
				return false;
		} else if (!operationDate.equals(other.operationDate))
			return false;
		if (pair == null) {
			if (other.pair != null)
				return false;
		} else if (!pair.equals(other.pair))
			return false;
		if (traderID == null) {
			if (other.traderID != null)
				return false;
		} else if (!traderID.equals(other.traderID))
			return false;
		if (type != other.type)
			return false;
		
		return true;
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
