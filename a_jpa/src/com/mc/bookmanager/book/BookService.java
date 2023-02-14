package com.mc.bookmanager.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mc.bookmanager.book.dto.BookDto;
import com.mc.bookmanager.jpa.EntityTemplate;

public class BookService {
	
	private BookRepository bookRepository = new BookRepository();

	public List<BookDto> findAllBook() {
		
		EntityManager em = EntityTemplate.getEntityManager();
		List<Book> books = null;
		
		try {
			books = bookRepository.findAllBook(em); 
		} finally {
			em.close();
		}
		
		return BookDto.toDtoList(books);
	}

	public boolean createBook(BookDto dto) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Book book = Book.createBook(dto);
			em.persist(book);
			tx.commit();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}

	public boolean updateBookInfo(long bkIdx, String info) {
		
		EntityManager em =EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Book book = em.find(Book.class, bkIdx);
			book.updateInfo(info);
			
			tx.commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}

	public boolean removeBook(long bkIdx) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Book book = em.find(Book.class, bkIdx);
			em.remove(book);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}

	public List<BookDto> findBookByTitle(String keyword) {
		EntityManager em = EntityTemplate.getEntityManager();
		List<BookDto> bookDtos = null;
		
		try {
		
			List<Book> books = bookRepository.findBookByTitle(em, keyword);
			bookDtos = BookDto.toDtoList(books);
			System.out.println(bookDtos);
		} finally {
			em.close();
		}
		
		return bookDtos;
	}

	public List<BookDto> findBookTopN(int limit) {
		EntityManager em = EntityTemplate.getEntityManager();
		List<BookDto> bookDtos = null;
		
		try {
			List<Book> books = bookRepository.findBookTopN(em, limit);
			bookDtos = BookDto.toDtoList(books);
		} finally {
			em.close();
		}
		
		return bookDtos;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
