package com.rubyit.metaltrade.banking.old.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rubyit.metaltrade.banking.old.Account;
import com.rubyit.metaltrade.banking.old.Currency;
import com.rubyit.metaltrade.banking.old.MfDate;
import com.rubyit.metaltrade.banking.old.Money;

public class BalanceUsingTransactionsTest {

	@Test
	public void testBalanceUsingTransactions() {
		Account revenue = new Account(Currency.USD);
		Account deferred = new Account(Currency.USD);
		Account receivables = new Account(Currency.USD);
		revenue.withdraw(Money.dollars(500.0), receivables, new MfDate(1, 4, 99));
		revenue.withdraw(Money.dollars(200.0), deferred, new MfDate(1, 4, 99));
		assertEquals(Money.dollars(500.0), receivables.balance());
		assertEquals(Money.dollars(200.0), deferred.balance());
		assertEquals(Money.dollars(-700.0), revenue.balance());
	}
}
