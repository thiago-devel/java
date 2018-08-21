package com.rubyit.metaltrade.banking.transaction.test;

import static com.rubyit.metaltrade.banking.transaction.account.AccountSide.DEBIT;

import java.math.BigDecimal;

import com.rubyit.metaltrade.banking.transaction.Ledger;
import com.rubyit.metaltrade.banking.transaction.TrialBalanceResult;
import com.rubyit.metaltrade.banking.transaction.chartoaccounts.ChartOfAccounts;
import com.rubyit.metaltrade.banking.transaction.chartoaccounts.ChartOfAccountsBuilder;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransaction;

public class Main {
    public static void main(String[] args) {
        String cashAccountNumber = "000001";
        String checkingAccountNumber = "000002";
        String accountsReceivableAccountNumber = "000003";

        // Setup ledger
        ChartOfAccounts coa = ChartOfAccountsBuilder.create()
                .addAccount(cashAccountNumber, "Cash", DEBIT)
                .addAccount(checkingAccountNumber, "Checking", DEBIT)
                .addAccount(accountsReceivableAccountNumber, "Accounts Receivable", DEBIT)
                .build();
        Ledger ledger = new Ledger(coa);

        // Accounts Receivable 35 was settled with cash 10 and wire transfer 25
        AccountingTransaction t = ledger.createTransaction(null)
                .debit(new BigDecimal(10), cashAccountNumber)
                .debit(new BigDecimal(25), checkingAccountNumber)
                .credit(new BigDecimal(35), accountsReceivableAccountNumber)
                .build();
        ledger.commitTransaction(t);

        // Print ledger
        System.out.println(ledger.toString());

        // Print trial balance
        TrialBalanceResult trialBalanceResult = ledger.computeTrialBalance();
        System.out.println(trialBalanceResult.toString());
    }
}
