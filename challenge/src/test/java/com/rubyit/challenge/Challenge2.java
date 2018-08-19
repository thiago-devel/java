package com.rubyit.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Challenge2 {

	@Test
	public void shouldHandleSingleDigits() {
		assertEquals("0", Challenge.numberToOrdinal(0));
		assertEquals("1st", Challenge.numberToOrdinal(1));
		assertEquals("2nd", Challenge.numberToOrdinal(2));
		assertEquals("3rd", Challenge.numberToOrdinal(3));
		assertEquals("4th", Challenge.numberToOrdinal(4));
		assertEquals("11th", Challenge.numberToOrdinal(11));
		assertEquals("21st", Challenge.numberToOrdinal(21));
		assertEquals("101st", Challenge.numberToOrdinal(101));
		assertEquals("111th", Challenge.numberToOrdinal(111));
	}

	public static class Challenge {

		public static String numberToOrdinal(Integer number) {

			if (number == null || number < 1) {
				return "0";
			}

			final int dozens = (number % 100);

			String ending = "th";
			if (dozens > 9 && dozens < 20) {
				return number + ending;
			}

			final int units = (dozens % 10);
			if (units == 1) {
				ending = "st";
			} else if (units == 2) {
				ending = "nd";
			} else if (units == 3) {
				ending = "rd";
			}

			return number + ending;
		}
	}
}
