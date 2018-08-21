package com.rubyit.metaltrade.banking.transaction.chartoaccounts;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.rubyit.metaltrade.banking.transaction.account.AccountDetails;

/**
 * Represents an immutable collection of available accounts.
 */
public class ChartOfAccounts {
    private final Map<String, AccountDetails> accountNumberToAccountDetails;

    public ChartOfAccounts(Set<AccountDetails> accountDetails) {
        checkNotNull(accountDetails);
        checkArgument(!accountDetails.isEmpty());
        this.accountNumberToAccountDetails = new HashMap<>();
        accountDetails.forEach(ad -> this.accountNumberToAccountDetails.put(ad.getAccountNumber(), ad));
    }

    public Map<String, AccountDetails> getAccountNumberToAccountDetails() {
        return new HashMap<>(accountNumberToAccountDetails);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("accounts", accountNumberToAccountDetails.values())
                .toString();
    }
}
