package com.rubyit.metaltrade.banking.old;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Money {
	
	private BigDecimal amount;
	
	private Money(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}

	public static Money dollars(Double amount) {
		return new Money(new BigDecimal(amount, new MathContext(8, RoundingMode.HALF_EVEN)));
	}

	public Money negate() {
		BigDecimal amountNegate = BigDecimal.valueOf(amount.doubleValue());
		amountNegate.negate(new MathContext(8, RoundingMode.HALF_EVEN));
		return new Money(amountNegate);
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + "]";
	}
}
