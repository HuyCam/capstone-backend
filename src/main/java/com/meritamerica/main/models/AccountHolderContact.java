package com.meritamerica.main.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_holder_contact")
public class AccountHolderContact {
	
	@Id
	private Long id;
	private String phoneNo;
	private String address;
	
	@OneToOne
	@JoinColumn(name="id", referencedColumnName = "id")
	private AccountHolder accHolder;
	public AccountHolderContact() {
		
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
