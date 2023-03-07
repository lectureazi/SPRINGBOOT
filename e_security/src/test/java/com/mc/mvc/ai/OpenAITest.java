package com.mc.mvc.ai;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.mc.mvc.infra.code.Code;

@SpringBootTest
public class OpenAITest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void testChatCompletion() {
		
		String url = "https://api.openai.com/v1/chat/completions";
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("model","gpt-3.5-turbo");
		body.put("messages", List.of(Map.of("role","user","content","프랑스 여행 계획 짜줘")));
		
		ResponseEntity<JsonNode> response = restTemplate.exchange(
													RequestEntity.post(url)
													.header("Authorization", "Bearer " + Code.OPENAI_API_KEY)
													.contentType(MediaType.APPLICATION_JSON)
													.body(body)
													, JsonNode.class);
		
		JsonNode node = response.getBody();
		System.out.println(node);
		System.out.println(node.at("/choices/0/message"));
		System.out.println(node.at("/choices/0/message/content").asText());
	}
	
	
	@Test
	public void testImageGenerations() {

		String url = "https://api.openai.com/v1/images/generations";
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("prompt","cute ironman character");
		body.put("n", 1);
		body.put("size","256x256");
		
		ResponseEntity<JsonNode> response = restTemplate.exchange(
													RequestEntity.post(url)
													.header("Authorization", "Bearer " + Code.OPENAI_API_KEY)
													.contentType(MediaType.APPLICATION_JSON)
													.body(body)
													, JsonNode.class);
		
		JsonNode node = response.getBody();
		System.out.println(node);
		System.out.println(node.at("/data/0/url"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
