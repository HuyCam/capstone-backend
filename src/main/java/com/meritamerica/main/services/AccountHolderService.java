package com.meritamerica.main.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.meritamerica.main.exceptions.NotFoundException;
import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.models.CheckingAccount;
import com.meritamerica.main.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.main.models.MeritBank;
import com.meritamerica.main.models.NegativeAmountException;
import com.meritamerica.main.repositories.AccountHolderRepo;
import com.meritamerica.main.repositories.CheckingAccountRepo;

@Service
public class AccountHolderService {
	
	@Autowired
	private AccountHolderRepo accHolderRepo;
	
	@Autowired
	private CheckingAccountRepo checkingRepo;
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
	
	public CheckingAccount addChecking(long id, CheckingAccount checking ) throws NotFoundException, ExceedsCombinedBalanceLimitException,
	NegativeAmountException
	{				
		AccountHolder account = this.getAccountHolder(id);
	/*
	 * Why account can not save checking but checking has to save account
	 */
//		account.addCheckingAccount(checking);
//		
//		accHolderRepo.save(account);
		
		checking.setAccHolder(account);
		checking = checkingRepo.save(checking);
		
		return checking;
	}
	
	public List<CheckingAccount> getChecking(long id) throws NotFoundException {
		Optional<AccountHolder> account = accHolderRepo.findById(id);
		
		if (account.isPresent()) {
			return account.get().getCheckingAccounts();
		} else {
			throw new NotFoundException("Checking Account is Not Found ");
		}
	}
}
