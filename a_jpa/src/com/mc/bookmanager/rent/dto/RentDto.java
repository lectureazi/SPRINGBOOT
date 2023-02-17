package com.mc.bookmanager.rent.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.member.dto.MemberDto;
import com.mc.bookmanager.rent.Rent;
import com.mc.bookmanager.rent.RentBook;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentDto {
	
	private Long rmIdx;
	private LocalDateTime regDate;
	private Boolean isReturn;
	private String title;
	private Integer rentBookCnt;
	private MemberDto member;
	private List<RentBookDto> rentBooks = new ArrayList<>();
	
	public RentDto(Rent entity) {
		this.rmIdx = entity.getRmIdx();
		this.regDate = entity.getRegDate();
		this.isReturn = entity.getIsReturn();
		this.title = entity.getTitle();
		this.rentBookCnt = entity.getRentBookCnt();
		this.member = new MemberDto(entity.getMember());
		this.rentBooks = RentBookDto.toDtoList(entity.getRentBooks());
	}
	
	public static List<RentDto> toDtoList(List<Rent> entityList){
		return entityList.stream().map(e -> new RentDto(e)).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
