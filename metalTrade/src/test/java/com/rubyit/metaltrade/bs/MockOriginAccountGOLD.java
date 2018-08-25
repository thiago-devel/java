package com.rubyit.metaltrade.bs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.test.util.ReflectionTestUtils;

import com.rubyit.metaltrade.obj.Asset;

public class MockOriginAccountGOLD extends Account {
	
	public static final Double INITIAL_BALANCE = 500.0;
	
	public MockOriginAccountGOLD(Asset assetGOLD) {
		super();
		MyAsset myGOLD = new MyAsset(assetGOLD);
		ReflectionTestUtils.setField(myGOLD, "balance", new BigDecimal(INITIAL_BALANCE, new MathContext(8, RoundingMode.HALF_EVEN)));
		Set<MyAsset> assets = new HashSet<>(Arrays.asList(myGOLD));
		ReflectionTestUtils.setField(getWallet(), "assets", assets);
	}
}