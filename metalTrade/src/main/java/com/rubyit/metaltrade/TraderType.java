package com.rubyit.metaltrade;

import java.util.List;

import com.rubyit.metaltrade.obj.AssetType;
import com.rubyit.metaltrade.orderbook.Order;
import com.rubyit.metaltrade.orderbook.OrderBook;

public interface TraderType {
	String getID();
	String getName();
	List<Order> getCreatedOrders();
	List<Order> getFilledOrders();
	Asset getWalletAsset(AssetType asset);
	List<Asset> getWalletAllAssets();
	Order createOrder(OrderBook orderbook, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset, Double expectedAssetUnitPrice);
	void addCreatedOrder(Order order);
	void removeCreatedOrder(Order order);
	void fillOrder(Order filledOrder, String createdOrderID);
}


