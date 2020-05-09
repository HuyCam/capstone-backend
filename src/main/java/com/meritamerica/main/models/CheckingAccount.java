package com.meritamerica.main.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

	// CheckingAccount(child class) inherit methods and variables from BankAccount(parent class)
@Entity
@Table(name="checking_account")
public class CheckingAccount extends BankAccount {
	private static double INTEREST_RATE = 0.0001;
	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public CheckingAccount(double balance, double interestRate, Date openDate) {
		super(balance, interestRate, openDate);
	}
	
	public CheckingAccount() {
		super(0, INTEREST_RATE);
	}
	
	// 0.001 is the default interest rate
	public CheckingAccount(double balance) {
		super(balance, INTEREST_RATE);
	}
}