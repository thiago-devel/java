package com.rubyit.metaltrade.banking.transactions;

public class Account {

	private Money balance;
	private Currency currency;
	
	public Account(Currency currency) {
		this.currency = currency;
	}

	public Money balance() {
		return balance;
	}
	
	public void withdraw(Money amount, Account target, MfDate date) {
		
	}

	public void addEntry(Entry entry) {
		// TODO Auto-generated method stub
		
	}
}
