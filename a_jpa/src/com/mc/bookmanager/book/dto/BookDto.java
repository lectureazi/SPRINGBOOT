package com.mc.bookmanager.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.bookmanager.book.Book;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
	
	private Long bkIdx;
	private String title;
	private String author;
	private String category;
	private String info;
	private String isbn;
	private int bookAmt;  //도서 재고
	private LocalDateTime regDate; //등록 일자
	private int rentCnt;  //대출 횟수

	public BookDto(Book book) {
		this.bkIdx = book.getBkIdx();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.category = book.getCategory();
		this.info = book.getInfo();
		this.isbn = book.getIsbn();
		this.bookAmt = book.getBookAmt();
		this.regDate = book.getRegDate();
		this.rentCnt = book.getRentCnt();
	}
	
	
	public static List<BookDto> toDtoList(List<Book> entityList){
		return entityList.stream().map(e -> new BookDto(e)).collect(Collectors.toList());
	}

}
