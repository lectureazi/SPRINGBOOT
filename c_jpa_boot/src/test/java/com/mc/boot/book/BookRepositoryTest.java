package com.mc.boot.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mc.boot.book.dto.BookDto;
import com.mc.boot.book.repository.BookRepository;

@SpringBootTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	@DisplayName("도서 추가")
	public void save() {
		Book book = Book.builder()
				.author("황정은")
				.title("파씨의 입문")
				.category("문학")
				.bookAmt(3)
				.build();
		
		bookRepository.save(book);
	}

	
	@Test
	@DisplayName("도서 수정")
	public void saveOrUpdate() {
		Book book = bookRepository.findById(1L).get();
		book.updateBookAmt(5);
		bookRepository.save(book);
	}
	
	
	@Test
	@DisplayName("전체 도서 조회")
	public void findAll() {
		BookDto.toDtoList(bookRepository.findAll()).forEach(e -> {
			System.out.println(e);
		});
	}
	
	@Test
	@DisplayName("전체 도서 CNT")
	public void count() {
		System.out.println(bookRepository.count());
	}
	
	@Test
	@DisplayName("도서 존재 여부 확인")
	public void existById() {
		System.out.println(bookRepository.existsById(100L));
	}
	
	
	@Test
	@DisplayName("도서 삭제")
	public void deleteById() {
		bookRepository.deleteById(1L);
	}
	
	@Test
	@DisplayName("도서명과 작가로 도서 검색")
	public void findByTitleOrAuthor() {
		bookRepository.findByTitleOrAuthor("비행운", "김애란")
		.forEach(e -> {
			System.out.println(new BookDto(e));
		});
	}
	
	@Test
	@DisplayName("카테고리가 문학인 도서의 row 갯수 확인")
	public void countByCategory() {
		System.out.println(bookRepository.countByCategory("문학"));
	}
	
	@Test
	@DisplayName("카테고리가 문학이고, bookAmt가 3 이상이면서 제목이 '디'로 시작하는 도서를 찾아보자.")
	public void findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith() {
		System.out.println(bookRepository.findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith("문학",3,"디"));
	}
	
	@Test
	@DisplayName("카테고리가 문학이고, bookAmt가 3 이상이면서 제목이 '디'로 시작하는 도서를 찾아보자.")
	public void findBookWithQueryDSL() {
		System.out.println(bookRepository.findBookWithQueryDSL("문학",3,"디"));
	}
	
	
	
	
	
	
	
	
	
	
}
