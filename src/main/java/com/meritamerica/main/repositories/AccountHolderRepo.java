package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.meritamerica.main.models.AccountHolder;


//@RepositoryRestResource(path = "AccountHolders",collectionResourceRel="AccountHolders")
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

}
