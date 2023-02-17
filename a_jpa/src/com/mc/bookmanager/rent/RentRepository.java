package com.mc.bookmanager.rent;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

public class RentRepository {

//	public List<Rent> findRentByUserId(EntityManager em, String userId) {
//		String jpql = "select r from Rent r where r.member.userId = :userId";
//		return em.createQuery(jpql, Rent.class).setParameter("userId", userId).getResultList();
//	}
	
//	public List<Rent> findRentByUserId(EntityManager em, String userId) {
//		String jpql = "select distinct r from Rent r "				
//				+ " inner join fetch r.rentBooks rb "
//				+ " inner join fetch rb.book "
//				+ " inner join fetch r.member m "				
//				+ " where m.userId = :userId";
//		return em.createQuery(jpql, Rent.class).setParameter("userId", userId).getResultList();
//	}
	
//	public List<Rent> findRentByUserId(EntityManager em, String userId) {
//		String jpql = "select r from Rent r where r.member.userId = :userId";
//		return em.createQuery(jpql, Rent.class)
//				.setParameter("userId", userId)
//				.setHint("javax.persistence.fetchgraph", em.getEntityGraph("Rent.rentBooks"))
//				.getResultList();
//	}
	
	public List<Rent> findRentByUserId(EntityManager em, String userId) {
		
		EntityGraph<Rent> rentGraph = em.createEntityGraph(Rent.class);
		rentGraph.addAttributeNodes("rentBooks");
		Subgraph<RentBook> rentBookGraph = rentGraph.addSubgraph("rentBooks");
		rentBookGraph.addAttributeNodes("book");
		
		String jpql = "select r from Rent r where r.member.userId = :userId";
		return em.createQuery(jpql, Rent.class)
				.setParameter("userId", userId)
				.setHint("javax.persistence.fetchgraph", rentGraph)
				.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
