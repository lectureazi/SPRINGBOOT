package com.mc.mvc.module.board.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRegistRequest {

	private String userId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
}
