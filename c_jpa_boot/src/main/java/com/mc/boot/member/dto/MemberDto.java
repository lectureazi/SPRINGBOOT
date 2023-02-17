package com.mc.boot.member.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.boot.member.Member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	private Boolean isLeave;
	private LocalDateTime regDate;
	private LocalDateTime rentableDate;
	
	public MemberDto(Member entity) {
		this.userId = entity.getUserId();
		this.password =  entity.getPassword();
		this.email =  entity.getEmail();
		this.grade =  entity.getGrade();
		this.tell =  entity.getTell();
		this.regDate =  entity.getRegDate();
		this.rentableDate =  entity.getRentableDate();
		this.isLeave = entity.getIsLeave();
	}
	
	public static List<MemberDto> toDtoList(List<Member> entityList){
		return entityList.stream().map(e -> new MemberDto(e)).collect(Collectors.toList());
	}

}
