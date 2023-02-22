package com.mc.mvc.module.member.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

	@NotBlank
	private String userId;
	
	@NotBlank
	private String password;
	
}
