package com.meritamerica.main.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.models.CheckingAccount;
import com.meritamerica.main.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.main.repositories.CDAccountRepo;
import com.meritamerica.main.repositories.CDOfferRepo;
import com.meritamerica.main.repositories.CheckingAccountRepo;
import com.meritamerica.main.repositories.MyUserRepo;
import com.meritamerica.main.repositories.SavingAccountRepo;
import com.meritamerica.main.security.Users;

@RequestMapping(value="/Me")
@RestController
public class MyAccountController {
	@Autowired
	MyUserRepo userRepo;
	
	@Autowired
	private CheckingAccountRepo checkingRepo;
	
	@Autowired
	SavingAccountRepo savingRepo;
	
	@Autowired
	CDAccountRepo cdaccRepo;
	
	@Autowired
	CDOfferRepo cdofferingRepo;
	
	@GetMapping
	public AccountHolder getMyAccountHolder(Principal principal) {
		Users user = userRepo.findByUserName(principal.getName());
		AccountHolder acc = user.getAccountHolder();
		return acc;
	}
	
	@PostMapping("/CheckingAccounts")
	public CheckingAccount addChecking(@RequestBody @Valid CheckingAccount checking,Principal principal) throws ExceedsCombinedBalanceLimitException {
		Users user = userRepo.findByUserName(principal.getName());
		AccountHolder acc = user.getAccountHolder();
		acc.addCheckingAccount(checking);
		checking = checkingRepo.save(checking);
		return checking;
	}
	
	
}
