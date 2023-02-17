package com.mc.boot.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.boot.member.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Member {
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	
	@ColumnDefault("false")
	private Boolean isLeave;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime rentableDate;

	public static Member createMember(MemberDto dto) {
		return Member.builder()
				.userId(dto.getUserId())
				.password(dto.getPassword())
				.tell(dto.getTell())
				.email(dto.getEmail())
				.build();
	}
	
	public void updateMember(MemberDto memberDto) {
		if(memberDto.getPassword() != null) this.password = memberDto.getPassword();
		if(memberDto.getEmail() != null) this.email = memberDto.getEmail();
		if(memberDto.getTell() != null) this.tell = memberDto.getTell();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
