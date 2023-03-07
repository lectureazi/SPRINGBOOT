package com.mc.mvc.infra.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.mc.mvc.infra.api.dto.ChatCompletionResponse;
import com.mc.mvc.infra.code.Code;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenAI {
	
	private final String CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";
	private final String IMAGE_GENERATIONS_URL= "https://api.openai.com/v1/images/generations";
	
	private final RestTemplate restTemplate;
	
	public ChatCompletionResponse chatCompletion(String question) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("model","gpt-3.5-turbo");
		body.put("messages", List.of(Map.of("role","user","content", question)));
		
		ResponseEntity<JsonNode> response = restTemplate.exchange(
													RequestEntity.post(CHAT_COMPLETION_URL)
													.header("Authorization", "Bearer " + Code.OPENAI_API_KEY)
													.contentType(MediaType.APPLICATION_JSON)
													.body(body)
													, JsonNode.class);
		
		JsonNode node = response.getBody();
		
		ChatCompletionResponse res = new ChatCompletionResponse();
		res.setCreated(node.at("/created").asLong());
		res.setMessage(node.at("/choices/0/message/content").asText());
		res.setRole(node.at("/choices/0/message/role").asText());
		
		return res;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
