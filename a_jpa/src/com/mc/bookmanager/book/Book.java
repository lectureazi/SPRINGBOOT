package com.mc.bookmanager.book;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.bookmanager.book.dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Book {
	
	//기본키
	@Id
	@GeneratedValue  // JPA의 정책에 따른 식별자 자동 생성
	private Long bkIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	
	@ColumnDefault("0")
	private Integer bookAmt;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ColumnDefault("0")
	private Integer rentCnt;

	public static Book createBook(BookDto dto) {
		return Book.builder().title(dto.getTitle()).author(dto.getAuthor()).isbn(dto.getIsbn()).category(dto.getCategory()).build();
	}

	public void updateInfo(String info) {
		this.info = info;
	}
	
}
