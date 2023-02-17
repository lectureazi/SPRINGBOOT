package com.mc.boot.rent.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.boot.book.dto.BookDto;
import com.mc.boot.rent.RentBook;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentBookDto {
	
	private Long rbIdx;
	private LocalDateTime regDate;
	private String state;
	private LocalDateTime returnDate;
	private int extensionCnt; // 연장 횟수
	private BookDto book;
	private Long rmIdx;
	
	public RentBookDto(RentBook rentBook) {
		super();
		this.rbIdx = rentBook.getRbIdx();
		this.regDate = rentBook.getRegDate();
		this.state = rentBook.getState();
		this.returnDate = rentBook.getReturnDate();
		this.extensionCnt = rentBook.getExtensionCnt();
		this.book = new BookDto(rentBook.getBook());
		this.rmIdx = rentBook.getRent().getRmIdx();
	}
	
	public static List<RentBookDto> toDtoList(List<RentBook> entityList){
		return entityList.stream().map(e -> new RentBookDto(e)).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	

}
