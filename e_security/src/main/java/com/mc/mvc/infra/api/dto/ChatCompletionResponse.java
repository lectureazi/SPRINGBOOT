package com.mc.mvc.infra.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatCompletionResponse {
	
	private String message;
	private String role;
	private Long created;

}
