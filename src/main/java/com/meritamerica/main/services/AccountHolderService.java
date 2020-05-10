package com.meritamerica.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.main.exceptions.NotFoundException;
import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.models.MeritBank;
import com.meritamerica.main.repositories.AccountHolderRepo;

@Service
public class AccountHolderService {
	
	
	private AccountHolderRepo accHolderRepo;
	
	@Autowired
	public AccountHolderService(AccountHolderRepo accHolderRepo)  {
		this.accHolderRepo = accHolderRepo;
	}
	
	/** 
	 * Pipe a new Account Holder through Merit Bank to have inner validation
	 * then save it to database
	 * 
	 * @param an new account holder
	 * @return a account holder has been processed by database.
	 */
	public AccountHolder createAccountHolder(AccountHolder newAccountHolder) {
		newAccountHolder = MeritBank.addAccountHolder(newAccountHolder);
		newAccountHolder =  accHolderRepo.save(newAccountHolder);
		return newAccountHolder;
	}
	
	public List<AccountHolder> getAccountHolders() {
		return accHolderRepo.findAll();
	}
	
	public AccountHolder getAccountHolder(long id) throws NotFoundException {
		try {
			Optional<AccountHolder> account = accHolderRepo.findById(id);
			
			return account.get();
		} catch(Exception e) {
			throw new NotFoundException("No account exists");
		}
	}
}
