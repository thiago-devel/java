package com.rubyit.metaltrade;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class Utils {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final Integer SCALE_BASE = 8;
	private static final MathContext mc = new MathContext(SCALE_BASE, RoundingMode.HALF_EVEN);
	
	public static Gson getGson() {
				
		return gson;
	}
	
	/*
	public static MathContext getMC() {
		return mc;
	}*/
	
	public static BigDecimal formatNumber(Double value) {
		return BigDecimal.valueOf(value).setScale(SCALE_BASE, RoundingMode.FLOOR);
	}
	
	public static BigDecimal formatNumber(String value) {
		return new BigDecimal(value).setScale(SCALE_BASE, RoundingMode.FLOOR);
	}
	
	public static BigDecimal formatNumber(BigDecimal value) {
		return value.setScale(SCALE_BASE, RoundingMode.FLOOR);
	}
}
