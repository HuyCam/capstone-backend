package com.meritamerica.main.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.meritamerica.main.exceptions.*;
import com.meritamerica.main.models.*;
import com.meritamerica.main.repositories.AccHolderContactRepo;
import com.meritamerica.main.repositories.AccountHolderRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/AccountHolders")
@RestController
public class AccountHolderController {
	
	Logger logger = LoggerFactory.getLogger(AccountHolderController.class);
	
	@Autowired
	AccountHolderRepo accHolderRepo;

	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hi";
	}
	
	
	@PostMapping(value = "/") 
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolder newAccountHolder) {
		accHolderRepo.save(newAccountHolder);
		return newAccountHolder;
	}
	
	@GetMapping(value="/")
	public List<AccountHolder> getAccountHolders() {
		return accHolderRepo.findAll();
	}
	
	@GetMapping(value="/{id}") 
	public AccountHolder getAccountHolder(@PathVariable("id") long id) throws NotFoundException
	{
		try {
			Optional<AccountHolder> account = accHolderRepo.findById(id);
			
			return account.get();
		} catch(Exception e) {
			throw new NotFoundException("No account exists");
		}
	}
	
	
	@PostMapping(value="/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addChecking(@PathVariable("id") long id, @RequestBody @Valid CheckingAccount checking ) throws NotFoundException, ExceedsCombinedBalanceLimitException,
	NegativeAmountException
	{				
		AccountHolder account = this.getAccountHolder(id);
		
		account.addCheckingAccount(checking);
		
		return checking;
	}
	
	@GetMapping(value="/{id}/CheckingAccounts")
	public CheckingAccount[] getChecking(@PathVariable("id") long id) throws NotFoundException {
		AccountHolder account = this.getAccountHolder(id);
		
		return account.getCheckingAccounts();
	}
	
	@PostMapping(value="/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSaving(@PathVariable("id") long id, @RequestBody @Valid SavingsAccount savings ) throws NotFoundException, ExceedsCombinedBalanceLimitException,
	NegativeAmountException
	{
				
		AccountHolder account = this.getAccountHolder(id);		
		
		account.addSavingsAccount(savings);
		
		return savings;
	}
	
	@GetMapping(value="/{id}/SavingsAccounts")
	public SavingsAccount[] getSavings(@PathVariable("id") long id) throws NotFoundException {
		AccountHolder account = this.getAccountHolder(id);
		
		return account.getSavingsAccounts();
	}
	
	@PostMapping(value="/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable("id") long id, @RequestBody @Valid CDAccount CDAccount ) throws NotFoundException, ExceedsCombinedBalanceLimitException,
	NegativeAmountException, ExceedsFraudSuspicionLimitException, FieldErrorException
	{			
		AccountHolder account = this.getAccountHolder(id);				

		account.addCDAccount(CDAccount);
		
		return CDAccount;
	}
	
	@GetMapping(value="/{id}/CDAccounts")
	public CDAccount[] getCDAccounts(@PathVariable("id") long id) throws NotFoundException {
		AccountHolder account = this.getAccountHolder(id);
		
		return account.getCDAccounts();
	}
	
	@PostMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering createCDOffering(@RequestBody @Valid CDOffering offering) throws FieldErrorException {				
		MeritBank.addCDOffering(offering);
		return offering;
	}
	
	@GetMapping("/CDOfferings")
	public CDOffering[] getCDOfferings() throws NotFoundException {
		CDOffering[] cdOfferings = MeritBank.getCDOfferings();
	    return cdOfferings;
	}
	
}
