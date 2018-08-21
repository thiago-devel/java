package com.rubyit.metaltrade.banking.transaction.test;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.rubyit.metaltrade.banking.transaction.account.AccountingEntry;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransaction;
import com.rubyit.metaltrade.banking.transaction.tx.AccountingTransactionBuilder;


public class AccountingTransactionBuilderTest {
    @Test
    public void testValidTransaction() {
        // Arrange
        String cashAccountNumber = "01";
        String checkingAccountNumber = "02";
        String liabilitiesAccountNumber = "11";

        // Act
        // We borrow money and get it part of it in cash, the rest as a wire transfer
        AccountingTransaction t = AccountingTransactionBuilder
                .create(null)
                .debit(new BigDecimal(10), cashAccountNumber)
                .debit(new BigDecimal(25), checkingAccountNumber)
                .credit(new BigDecimal(35), liabilitiesAccountNumber)
                .build();

        // Assert
        Assert.assertEquals(new BigDecimal(10), getEntryById(t, cashAccountNumber).getAmount());
        Assert.assertEquals(new BigDecimal(25), getEntryById(t, checkingAccountNumber).getAmount());
        Assert.assertEquals(new BigDecimal(35), getEntryById(t, liabilitiesAccountNumber).getAmount());
    }

    final private AccountingEntry getEntryById(AccountingTransaction t, String accountId) {
        return t.getEntries().stream()
                .filter(e -> e.getAccountNumber() == accountId)
                .findFirst().get();
    }
}
