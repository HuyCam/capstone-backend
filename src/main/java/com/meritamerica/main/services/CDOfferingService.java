package com.meritamerica.main.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.meritamerica.main.exceptions.NotFoundException;
import com.meritamerica.main.models.CDOffering;
import com.meritamerica.main.models.FieldErrorException;
import com.meritamerica.main.models.MeritBank;
import com.meritamerica.main.repositories.CDOfferRepo;

@Service
public class CDOfferingService {
	@Autowired
	CDOfferRepo cdofferingRepo;
	
	public CDOffering createCDOffering(CDOffering offering) throws FieldErrorException {		
		MeritBank.addCDOffering(offering); // this is for validation
		offering = cdofferingRepo.save(offering);
		return offering;
	}
	
	public List<CDOffering> getCDOfferings() throws NotFoundException {
	    return cdofferingRepo.findAll();
	}
	
}
