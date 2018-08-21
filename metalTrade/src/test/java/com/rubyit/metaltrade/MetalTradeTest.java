package com.rubyit.metaltrade;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetalTradeTest {

	@Test
	public void shouldDoSomething() {
		
	}

	public static void main(String[] args) throws InterruptedException {
		Asset gold = new Asset("Gold");
		Asset silver = new Asset("Silver");
		
		Buyer renata = new Buyer("Renata");
		Seller thiago = new Seller("Thiago");
		
		Transaction tx01 = new Transaction(gold, com.rubyit.metaltrade.Transaction.Type.DEPOSIT);
		Transaction tx02 = new Transaction(silver, com.rubyit.metaltrade.Transaction.Type.DEPOSIT);
		renata.myWallet.update(tx01);
		renata.myWallet.update(tx02);
		System.out.println("Buyer: " + renata);
		System.out.println("Seller: " + thiago);
		final double value = 50.01;
		MyAsset renataGold = new MyAsset(gold);
		renataGold.deposit(new BigDecimal(value));
		Transaction tx03 = new Transaction(gold, value, com.rubyit.metaltrade.Transaction.Type.DEPOSIT);
		renata.myWallet.update(tx03);
		thiago.myWallet.update(tx01);
		System.out.println("Buyer: " + renata);
		System.out.println("Seller: " + thiago);
		final double value02 = 33.33;
		renataGold.withdraw(new BigDecimal(value02));
		Transaction tx04 = new Transaction(gold, value02, com.rubyit.metaltrade.Transaction.Type.WITHDRAW);
		renata.myWallet.update(tx04);
		
		
		System.out.println("Buyer: " + renata);
		System.out.println("Seller: " + thiago);
	}
}
