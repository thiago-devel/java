package com.rubyit.metaltrade.banking.test;

import com.rubyit.metaltrade.banking.BankAccount;
import com.rubyit.metaltrade.banking.DepositRunnable;
import com.rubyit.metaltrade.banking.WithdrawRunnable;

/**
 * Thanks to Tacksoo for the base code here imported from his github:
 * https://gist.github.com/tacksoo/4728939
 * 
 * This program runs threads that deposit and withdraw money from the same bank
 * account.
 */
public class BankAccountThreadRunner {
	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		final double AMOUNT = 100;
		final int REPETITIONS = 100;
		final int THREADS = 100;

		for (int i = 1; i <= THREADS; i++) {
			DepositRunnable d = new DepositRunnable(account, AMOUNT, REPETITIONS);
			WithdrawRunnable w = new WithdrawRunnable(account, AMOUNT, REPETITIONS);

			Thread dt = new Thread(d);
			Thread wt = new Thread(w);

			dt.start();
			wt.start();
		}
	}
}
