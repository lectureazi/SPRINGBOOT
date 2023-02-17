package com.mc.thymeleaf.study.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberDto {
	
	private String userId;
	private String password;
	private String tell;
	private String grade;
	private Integer loginCnt;
	private LocalDateTime regDate;

}
