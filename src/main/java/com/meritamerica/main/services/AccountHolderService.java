package com.meritamerica.main.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.meritamerica.main.exceptions.NotFoundException;
import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.models.CDAccount;
import com.meritamerica.main.models.CDOffering;
import com.meritamerica.main.models.CheckingAccount;
import com.meritamerica.main.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.main.models.ExceedsFraudSuspicionLimitException;
import com.meritamerica.main.models.FieldErrorException;
import com.meritamerica.main.models.NegativeAmountException;
import com.meritamerica.main.models.SavingsAccount;
import com.meritamerica.main.repositories.AccountHolderRepo;
import com.meritamerica.main.repositories.CDAccountRepo;
import com.meritamerica.main.repositories.CDOfferRepo;
import com.meritamerica.main.repositories.CheckingAccountRepo;
import com.meritamerica.main.repositories.SavingAccountRepo;

/**
 * A service class act as an abstract layer between controller and DAO
 * 
 * @author camhuy
 *
 */
@Service
public class AccountHolderService {
	
	@Autowired
	private AccountHolderRepo accHolderRepo;
	
	@Autowired
	private CheckingAccountRepo checkingRepo;
	
	@Autowired
	SavingAccountRepo savingRepo;
	
	@Autowired
	CDAccountRepo cdaccRepo;
	
	@Autowired
	CDOfferRepo cdofferingRepo;
	/** 
	 * Pipe a new Account Holder through Merit Bank to have inner validation
	 * then save it to database
	 * 
	 * @param an new account holder
	 * @return a account holder has been processed by database.
	 */
	public AccountHolder createAccountHolder(AccountHolder newAccountHolder) {
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
		account.addCheckingAccount(checking);	// this step actually to checking if that checking is correct
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
	
	public SavingsAccount addSaving(long id, SavingsAccount savings ) throws NotFoundException, ExceedsCombinedBalanceLimitException,
	NegativeAmountException
	{	
		AccountHolder account = this.getAccountHolder(id);
		account.addSavingsAccount(savings);
		savings.setAccHolder(account);
		savings = savingRepo.save(savings);
		return savings;
	}
	
	public List<SavingsAccount> getSavings(long id) throws NotFoundException {
		Optional<AccountHolder> account = accHolderRepo.findById(id);
		
		if (account.isPresent()) {
			return account.get().getSavingsAccounts();
		} else {
			throw new NotFoundException("Saving Account is Not Found ");
		}
	}
	
	public CDAccount addCDAccount(long id, CDAccount CDAccount ) throws NotFoundException, ExceedsCombinedBalanceLimitException,
	NegativeAmountException, ExceedsFraudSuspicionLimitException, FieldErrorException
	{			
		AccountHolder account = this.getAccountHolder(id);		
		account.addCDAccount(CDAccount);	// validation 
	
		Optional<CDOffering> offer = cdofferingRepo.findById(CDAccount.getOffering().getId());
		CDAccount.setOffering(offer.get());
		
		CDAccount.setAccHolder(account);
		CDAccount = cdaccRepo.save(CDAccount);

		return CDAccount;
	}
	
	public List<CDAccount> getCDAccounts(long id) throws NotFoundException {
		Optional<AccountHolder> account = accHolderRepo.findById(id);
		
		if (account.isPresent()) {
			return account.get().getCDAccounts();
		} else {
			throw new NotFoundException("CD Account is Not Found ");
		}
	}
}
