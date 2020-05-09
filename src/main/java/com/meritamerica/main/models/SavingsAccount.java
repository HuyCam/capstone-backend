package com.meritamerica.main.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

// SavingsAccount(child class) inherit methods and variables from BankAccount(parent class)
@Entity
@Table(name="savings_acount")
public class SavingsAccount extends BankAccount {
	private static double INTEREST_RATE = 0.01;
	SavingsAccount(double balance, double interestRate){
		super(balance, interestRate);    
     }
	
	SavingsAccount( double balance, double interestRate, Date openDate){
		super( balance, interestRate, openDate);    
     }
	
	// 0.00001 is the default interest rate.
	SavingsAccount(double balance) {
		super(balance, INTEREST_RATE);
	}
	
	public SavingsAccount() {
		super(0, INTEREST_RATE);
	}
}