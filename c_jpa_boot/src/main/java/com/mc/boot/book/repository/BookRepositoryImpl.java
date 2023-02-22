package com.mc.boot.book.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.mc.boot.book.Book;
import com.mc.boot.book.QBook;
import com.mc.boot.book.dto.BookDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

// Repository명 + Impl 형태로 인터페이스 이름 작성 (필수)
public class BookRepositoryImpl implements BookRepositoryExtension{

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

	@Override
	public List<Book> orderBy() {
		List<Book> books = 
				queryFactory
				.select(book)
				.from(book)
				.orderBy(book.bookAmt.desc())
				.limit(2)
				.fetch();
		return books;
	}

	@Override
	public List<Tuple> groupBy() {
		List<Tuple> tuples = 
				queryFactory
				.select(book.category, book.bookAmt.max(), book.bookAmt.avg(), book.rentCnt.avg())
				.from(book)
				.groupBy(book.category)
				.fetch();
		
		return tuples;
	}

	@Override
	public List<Book> dynamicQueryWithBook(BookDto bookDto) {
		
		// bookDto의 bookAmt보다 도서재고가 많거나 같으면서
		// bookDto의 rentCnt보다 대출횟수가 적거나 같은 도서를 조회
		// 만약 bookDto의 bookAmt나 rentCnt에 null이 담겨 있다면 쿼리에서 제외
		List<Book> books = 
				queryFactory.select(book)
				.from(book)
				.where(bookAmtGoe(bookDto), rentCntLoe(bookDto))
				.fetch();
		
		return books;
	}
	
	private BooleanExpression bookAmtGoe(BookDto bookDto) {
		return bookDto.getBookAmt() == null 
				? null
				: book.bookAmt.goe(bookDto.getBookAmt());
	}

	private BooleanExpression rentCntLoe(BookDto bookDto) {
		return bookDto.getRentCnt() == null
				? null
				: book.rentCnt.loe(bookDto.getRentCnt());
	}

	@Override
	public List<Book> dynamicQueryWithBookOr(List<String> filters, String keyword) {
		
		List<Book> books = 
				queryFactory.select(book)
				.from(book)
				.where(keywordContains(filters, keyword))
				.fetch();
		
		return books;
	}
	
	private BooleanExpression keywordContains(List<String> filters, String keyword) {
		
		BooleanExpression predicate = null;
		
		if(filters.contains("title"))
			predicate = book.title.contains(keyword);
		
		if(filters.contains("author")) {
			if(predicate == null) {
				predicate = book.author.contains(keyword);
			}else {
				predicate = predicate.or(book.author.contains(keyword));
			}
		}
		
		return predicate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
