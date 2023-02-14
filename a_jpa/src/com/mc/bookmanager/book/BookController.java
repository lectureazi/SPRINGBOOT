package com.mc.bookmanager.book;

import java.util.List;

import com.mc.bookmanager.book.dto.BookDto;

public class BookController {

	private BookService bookService = new BookService();
	
	public List<BookDto> findAllBook() {
		return bookService.findAllBook();
	}

	public boolean registBook(BookDto registBook) {
		return bookService.createBook(registBook);
	}

	public boolean updateBookInfo(long bkIdx, String info) {
		return bookService.updateBookInfo(bkIdx, info);
	}

	public boolean removeBook(long bkIdx) {
		return bookService.removeBook(bkIdx);
	}

	public List<BookDto> findBookByTitle(String keyword) {
		return bookService.findBookByTitle(keyword);
	}

	public List<BookDto> findBookTopN(int limit) {
		return bookService.findBookTopN(limit);
	}

}
