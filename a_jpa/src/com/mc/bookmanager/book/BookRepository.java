package com.mc.bookmanager.book;

import java.util.List;

import javax.persistence.EntityManager;

public class BookRepository {

	public List<Book> findAllBook(EntityManager em) {
		String jpql = "from Book";
		return em.createQuery(jpql, Book.class).getResultList();
	}

	public List<Book> findBookByTitle(EntityManager em, String keyword) {
		String jpql = "select b from Book b where b.title like :keyword ";
		return em.createQuery(jpql, Book.class)
				.setParameter("keyword", "%" + keyword + "%") //'%해리포터%'
				.getResultList();
	}

	public List<Book> findBookTopN(EntityManager em, int limit) {
		String jpql = "select b from Book b order by b.rentCnt desc";
		return em.createQuery(jpql, Book.class)
				.setMaxResults(limit)
				.getResultList();
	}

}
