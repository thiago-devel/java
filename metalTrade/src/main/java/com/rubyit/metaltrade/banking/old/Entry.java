package com.rubyit.metaltrade.banking.old;

import java.util.AbstractMap;

public class Entry {
	
	private AbstractMap.SimpleImmutableEntry entry;

	public Entry(Money amount, MfDate date) {
		entry = new AbstractMap.SimpleImmutableEntry<Money, MfDate>(amount, date);
	}

}
