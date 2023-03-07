package com.mc.mvc.ai;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.mc.mvc.infra.code.Code;

@SpringBootTest
public class NaverAITest {
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testFaceRecognition() {
		String url = "https://naveropenapi.apigw.ntruss.com/vision/v1/celebrity";
		final String CLIENT_ID = "";
		final String CLIENT_SECRET="";
		
		
		FileSystemResource fsr = new FileSystemResource(Code.STORAGE_PATH+"face/face.jpg");
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("image", fsr);
		
		ResponseEntity<JsonNode> response = restTemplate.exchange(
													RequestEntity.post(url)
													.header("X-NCP-APIGW-API-KEY-ID", CLIENT_ID)
													.header("X-NCP-APIGW-API-KEY", CLIENT_SECRET)
													.contentType(MediaType.MULTIPART_FORM_DATA)
													.body(body)
													, JsonNode.class);
		
		JsonNode node = response.getBody();
		System.out.println(node);

	}
	
	
}
