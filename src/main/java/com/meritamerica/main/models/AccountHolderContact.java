package com.meritamerica.main.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="accountholdercontact")
public class AccountHolderContact {
	
	@Id
	@Column(name="id")
	private Long id;
	private String phoneNo;
	private String address;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id", referencedColumnName = "id")
//	private AccountHolder accountHolder;
	
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

//	public AccountHolder getAccountHolder() {
//		return accountHolder;
//	}
//
//	public void setAccountHolder(AccountHolder accountHolder) {
//		this.accountHolder = accountHolder;
//	}
//	
}
