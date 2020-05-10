package com.meritamerica.main.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "accHolder", "offering" })
public class CDAccount extends BankAccount {
	@NotNull
	@Positive
	private int terms;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="offer_id")
	private CDOffering offering;
	
	@ManyToOne
	@JoinColumn(name="id")
	private AccountHolder accHolder;
	
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

	public CDOffering getOffering() {
		return offering;
	}

	public void setOffering(CDOffering offering) {
		this.offering = offering;
	}

	public AccountHolder getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}
}
