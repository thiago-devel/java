package com.rubyit.metaltrade.banking.old;

public class Account {

	private Money balance;
	private Currency currency;
	
	public Account(Currency currency) {
		this.currency = currency;
	}

	public Money balance() {
		return balance;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void withdraw(Money amount, Account target, MfDate date) {
		new AccountingTransaction(amount, this, target, date);
	}

	public void addEntry(Entry entry) {
		// TODO Auto-generated method stub
		
	}
}
