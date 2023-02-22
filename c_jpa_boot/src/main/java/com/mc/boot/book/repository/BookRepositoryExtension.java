package com.mc.boot.book.repository;

import java.util.List;

import com.mc.boot.book.Book;
import com.mc.boot.book.dto.BookDto;
import com.mc.boot.rent.Rent;
import com.querydsl.core.Tuple;

public interface BookRepositoryExtension {
	
	List<Book> findBookWithQueryDSL(String category, int bookAmt, String title);

	List<Book> orderBy();

	List<Tuple> groupBy();

	List<Book> dynamicQueryWithBook(BookDto bookDto);

	List<Book> dynamicQueryWithBookOr(List<String> filters, String keyword);

}
