package com.mc.boot.book.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.mc.boot.book.Book;
import com.mc.boot.book.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;

// Repository명 + Impl 형태로 인터페이스 이름 작성 (필수)
public class BookRepositoryImpl implements BookQueryDSLCustom{

	private final JPAQueryFactory queryFactory;
	private QBook book = QBook.book;
	
	// Spring EntityManger는 thread-safe하다.
	public BookRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	//카테고리가 문학이고, bookAmt가 3 이상이면서 제목이 '디'로 시작하는 도서를 찾아보자.
	@Override
	public List<Book> findBookWithQueryDSL(String category, int bookAmt, String title) {
		
		List<Book> books = 
				queryFactory.select(book)
							.from(book)
							.where(book.category.eq(category)
									.and(book.bookAmt.goe(bookAmt))
									.and(book.title.startsWith("디")))
							.fetch();
		return books;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
