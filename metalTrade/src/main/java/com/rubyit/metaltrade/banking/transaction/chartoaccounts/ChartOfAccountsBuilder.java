package com.rubyit.metaltrade.banking.transaction.chartoaccounts;

import java.util.HashSet;
import java.util.Set;

import com.rubyit.metaltrade.banking.transaction.account.AccountDetails;
import com.rubyit.metaltrade.banking.transaction.account.AccountSide;

public class ChartOfAccountsBuilder {
    private Set<AccountDetails> accountDetails = new HashSet<>();

    private ChartOfAccountsBuilder() {
    }

    public static ChartOfAccountsBuilder create() {
        return new ChartOfAccountsBuilder();
    }

    public ChartOfAccountsBuilder addAccount(String accountNumber, String name, AccountSide increaseSide) {
        AccountDetails accountDetails = new AccountDetails(accountNumber, name, increaseSide);
        this.accountDetails.add(accountDetails);
        return this;
    }

    public ChartOfAccounts build() {
        return new ChartOfAccounts(accountDetails);
    }
}
