package com.mc.boot.book.repository;

import java.util.List;

import com.mc.boot.book.Book;

public interface BookQueryDSLCustom {
	
	List<Book> findBookWithQueryDSL(String category, int bookAmt, String title);

}
