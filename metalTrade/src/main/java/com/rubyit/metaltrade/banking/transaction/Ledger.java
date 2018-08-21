package com.rubyit.metaltrade.banking.transaction;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import com.rubyit.metaltrade.banking.transaction.account.Account;
import com.rubyit.metaltrade.banking.transaction.account.AccountDetails;
import com.rubyit.metaltrade.banking.transaction.account.AccountingEntry;
import com.rubyit.metaltrade.banking.transaction.chartoaccounts.ChartOfAccounts;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransaction;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransactionBuilder;

/**
 * Represents a set of accounts and their transactions.
 */
final public class Ledger {
    final private HashMap<String, Account> accountNumberToAccount = new HashMap<>();
    final private Journal journal = new Journal();
    final private ChartOfAccounts coa;

    public Ledger(ChartOfAccounts coa) {
        this.coa = coa;
        // Create coa accounts
        coa.getAccountNumberToAccountDetails().values().forEach(this::addAccount);
    }

    public Ledger(Journal journal, ChartOfAccounts coa) {
        this(coa);
        // Add transactions
        journal.getTransactions().forEach(this::commitTransaction);
    }

    public AccountingTransactionBuilder createTransaction(@Nullable Map<String, String> info) {
        return AccountingTransactionBuilder.create(info);
    }

    public void commitTransaction(AccountingTransaction transaction) {
        // Add entries to accounts
        transaction.getEntries().forEach(this::addAccountEntry);
        journal.addTransaction(transaction);
    }

    public TrialBalanceResult computeTrialBalance() {
        return new TrialBalanceResult(Sets.newHashSet(accountNumberToAccount.values()));
    }

    public Journal getJournal() {
        return journal;
    }

    public BigDecimal getAccountBalance(String accountNumber) {
        return accountNumberToAccount.get(accountNumber).getBalance();
    }

    private void addAccount(AccountDetails accountDetails) {
        String newAccountNumber = accountDetails.getAccountNumber();
        boolean accountNumberNotInUse = !accountNumberToAccount.containsKey(newAccountNumber);
        checkArgument(accountNumberNotInUse,
                "An account with the account number %s exists already in the ledger", newAccountNumber);
        accountNumberToAccount.put(accountDetails.getAccountNumber(), new Account(accountDetails));
    }

    private void addAccountEntry(AccountingEntry entry) {
        accountNumberToAccount.get(entry.getAccountNumber()).addEntry(entry);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("accountNumberToAccountMap", accountNumberToAccount)
                .add("journal", journal)
                .add("chartOfAccounts", coa)
                .toString();
    }
}
