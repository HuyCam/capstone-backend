package com.meritamerica.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.main.models.AccountHolderContact;
//import com.meritamerica.main.repositories.AccHolderContactRepo;

//@RequestMapping(value="/contacts")
//@RestController
//public class AccountHolderContactController {
//	@Autowired
//	AccHolderContactRepo accHolderContactRepo;
//	
//	@GetMapping("/getall")
//	public List<AccountHolderContact> getContacts() {
//		return accHolderContactRepo.findAll();
//	}
//	
//	@PostMapping("/create")
//	public AccountHolderContact createContact(@RequestBody @Valid AccountHolderContact acc) {
//		acc = accHolderContactRepo.save(acc);
//		return acc;
//	}
//}
