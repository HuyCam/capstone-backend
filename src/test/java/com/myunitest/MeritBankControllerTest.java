package com.myunitest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meritamerica.main.repositories.AccountHolderRepo;

@RunWith(SpringRunner.class)
@WebMvcTest
class MeritBankControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AccountHolderRepo ahRepo;
	
	@Test
	public void test() throws Exception {
		
//		Mockito.when(ahRepo.findAll()).thenReturn(Collections.emptyList());
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/AccountHolders")
				.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
//		Mockito.verify(ahRepo).findAll();
	}

}
