package com.mc.bookmanager.book;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
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
	
}
