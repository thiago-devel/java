package com.rubyit.metaltrade.bs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.test.util.ReflectionTestUtils;

import com.rubyit.metaltrade.obj.AssetType;

public class MockOriginAccount extends Account {
	
	public static final Double INITIAL_BALANCE = 500.0;
	
	public MockOriginAccount(AssetType asset) {
		super();
		MyAsset myAsset = new MyAsset(asset);
		ReflectionTestUtils.setField(myAsset, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<MyAsset> assets = new HashSet<>(Arrays.asList(myAsset));
		ReflectionTestUtils.setField(getWallet(), "assets", assets);
	}
}
