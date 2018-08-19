package com.rubyit.challenge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Test;

public class Challenge3 {

	private Calc calc = new Calc();

	@Test
	public void shouldWorkForAnEmptyString() {
		assertEquals(0, calc.evaluate(""), 0);
	}

	@Test
	public void shouldParseNumbers() {
		assertEquals(3, calc.evaluate("1 2 3"), 0);
	}

	@Test
	public void shouldParseFloats() {
		assertEquals(3.5, calc.evaluate("1 2 3.5"), 0);
	}

	@Test
	public void shouldSupportAddition() {
		assertEquals(4, calc.evaluate("1 3 +"), 0);
	}

	@Test
	public void shouldSupportMultiplication() {
		assertEquals(3, calc.evaluate("1 3 *"), 0);
	}

	@Test
	public void shouldSupportSubtraction() {
		assertEquals(-2, calc.evaluate("1 3 -"), 0);
	}

	@Test
	public void shouldSupportDivision() {
		assertEquals(2, calc.evaluate("4 2 /"), 0);
	}

	public class Calc {

		public double evaluate(String expr) {

			if (expr == null || expr.length() == 0) {
				return 0;
			}
			final StringTokenizer st = new StringTokenizer(expr, " ");
			final List<String> ex = new ArrayList<>();
			while (st.hasMoreTokens()) {
				ex.add(st.nextToken());
			}
			final char prior = ex.get(2).charAt(0);
			if (Character.isDigit(prior)) {
				return Double.parseDouble(ex.get(2));
			}
			if (prior == '/') {
				return Double.parseDouble(ex.get(0)) / Double.parseDouble(ex.get(1)); 
			} else if (prior == '*') {
				return Double.parseDouble(ex.get(0)) * Double.parseDouble(ex.get(1));
			} else if (prior == '-') {
				return Double.parseDouble(ex.get(0)) - Double.parseDouble(ex.get(1));
			} else if (prior == '+') {
				return Double.parseDouble(ex.get(0)) + Double.parseDouble(ex.get(1));
			}
			
			return 0;
		}
	}
}
