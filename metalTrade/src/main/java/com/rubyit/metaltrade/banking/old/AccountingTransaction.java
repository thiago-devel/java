package com.rubyit.metaltrade.banking.old;

import java.util.Collection;
import java.util.HashSet;

public class AccountingTransaction {
	private Collection<Entry> entries = new HashSet<>();

	public AccountingTransaction(Money amount, Account from, Account to, MfDate date) {
		Entry fromEntry = new Entry(amount.negate(), date);
		from.addEntry(fromEntry);
		entries.add(fromEntry);
		Entry toEntry = new Entry(amount, date);
		to.addEntry(toEntry);
		entries.add(toEntry);
	}

}
