package com.meritamerica.main.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

   // CDAccount(child class) inherit methods and variables from BankAccount(parent class)
public class CDAccount extends BankAccount {
	private CDOffering offering;
	@NotNull
	@Positive
	private int terms;
	
	CDAccount(CDOffering offering, double openingBalance) {
		super(openingBalance, offering.getInterestRate());
		this.offering = offering;
	}	
	
	CDAccount(int term, double openingBalance, double interestRate) {
		this(new CDOffering(term, interestRate), openingBalance);
	}
	
	
	CDAccount(double openingBalance, double interestRate, Date openDate, int term) {
		super( openingBalance, interestRate, openDate);
		this.offering = new CDOffering(term, interestRate);
	}	
	
	public CDAccount() {
		super();
		this.offering = new CDOffering();
	}
		
	public double futureValue() {
		double futureVal = this.getBalance() * Math.pow(1 + this.getInterestRate(), offering.getTerm());
		return futureVal;
	}
	
	public int getTerm() {
		return offering.getTerm();
	}
	
	public void setTerm(int years) {
		this.terms = years;
		offering.setTerm(years);
	}
	
	@Override
	public void setInterestRate(double interestRate) throws FieldErrorException {
		super.setInterestRate(interestRate);
		offering.setInterestRate(interestRate);
	}
	
	public double getInterestRate() {
		return offering.getInterestRate();
	}

	// CDA account can not do withdraw or deposit within the term period
	@Override
	public boolean withdraw(double amount) {
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		return false;
	}
}
