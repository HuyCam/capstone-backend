package com.meritamerica.main.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.repositories.AccountHolderRepo;

public class AccountHolderService {
	
	@Autowired
	AccountHolderRepo accHolderRepo;
	
	public AccountHolder createAccountHolder(AccountHolder newAccountHolder) {
		newAccountHolder = accHolderRepo.save(newAccountHolder);
		return newAccountHolder;
	}
}
