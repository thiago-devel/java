package com.rubyit.metaltrade.banking.transaction.test;
import static com.rubyit.metaltrade.banking.transaction.account.AccountSide.CREDIT;
import static com.rubyit.metaltrade.banking.transaction.account.AccountSide.DEBIT;

import java.math.BigDecimal;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.rubyit.metaltrade.banking.transaction.account.AccountingEntry;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransaction;

public class AccountingEntryTest {

    @Test(expected = IllegalStateException.class)
    public void testFreeze() {
        // Arrange
        AccountingEntry entry1 = new AccountingEntry(new BigDecimal(20), "0001", DEBIT);
        AccountingEntry entry2 = new AccountingEntry(new BigDecimal(20), "0002", CREDIT);
        AccountingTransaction transaction = new AccountingTransaction(Sets.newHashSet(entry1, entry2));
        // Act + Assert
        entry1.setTransaction(transaction);
    }
}
