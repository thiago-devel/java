package com.rubyit.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Challenge1 {

	@Test
	public void shouldMaskDigitsForBasicCreditCards() {
		assertEquals("5###########0694", CreditCard.maskify("5512103073210694"));
	}

	@Test
	public void shouldNotMaskDigitsForShortCreditCards() {
		assertEquals("54321", CreditCard.maskify("54321"));
	}

	@Test
	public void test1() {
		assertEquals("4###########5616", CreditCard.maskify("4556364607935616"));
	}

	@Test
	public void test2() {
		assertEquals("4###-####-####-5616", CreditCard.maskify("4556-3646-0793-5616"));
	}

	@Test
	public void test3() {
		assertEquals("6######5616", CreditCard.maskify("64607935616"));
	}

	@Test
	public void test4() {
		assertEquals("ABCD-EFGH-IJKLM-NOPQ", CreditCard.maskify("ABCD-EFGH-IJKLM-NOPQ"));
	}

	@Test
	public void test5() {
		assertEquals("A#######BCDEFG89HI", CreditCard.maskify("A1234567BCDEFG89HI"));
	}

	@Test
	public void test6() {
		assertEquals("12345", CreditCard.maskify("12345"));
	}

	@Test
	public void test7() {
		assertEquals("", CreditCard.maskify(""));
	}

	@Test
	public void test8() {
		assertEquals("Skippy", CreditCard.maskify("Skippy"));
	}

	public static class CreditCard {

		public static String maskify(String creditCardNumber) {

			if (creditCardNumber == null || creditCardNumber.length() == 0) {
				return "";
			}

			if (creditCardNumber != null && creditCardNumber.length() <= 5) {
				return creditCardNumber;
			}

			final String text = creditCardNumber;
			final StringBuffer maskedText = new StringBuffer();

			for (int i = 0; i < text.length(); i++) {

				if ((i == 0) && (Character.isDigit(text.charAt(i)))) {

					maskedText.append(text.charAt(i));
					continue;
				}

				if ((i >= (text.length() - 4)) && (Character.isDigit(text.charAt(i)))) {

					maskedText.append(text.charAt(i));
					continue;
				}

				if (Character.isDigit(text.charAt(i))) {

					maskedText.append('#');
				} else {

					maskedText.append(text.charAt(i));
				}
			}

			return maskedText.toString();
		}
	}
}
