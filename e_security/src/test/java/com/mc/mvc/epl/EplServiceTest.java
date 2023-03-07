package com.mc.mvc.epl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.mvc.module.epl.EplService;
import com.mc.mvc.module.epl.dto.EplRankDto;

@SpringBootTest
public class EplServiceTest {

	@Autowired
	private EplService eplService;
	
	@Test
	public void testSaveEplRank() throws JsonProcessingException {
		
		eplService.saveEplRank();
	}
	
	
}
