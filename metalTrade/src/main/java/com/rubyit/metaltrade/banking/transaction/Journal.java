package com.rubyit.metaltrade.banking.transaction;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransaction;

/**
 * Represents a collection of transactions.
 */
public class Journal {
    final private List<AccountingTransaction> transactions = new ArrayList<>();

    public void addTransaction(AccountingTransaction transaction) {
        checkNotNull(transaction);
        transactions.add(transaction);
    }

    public List<AccountingTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("transactions", transactions)
                .toString();
    }
}
