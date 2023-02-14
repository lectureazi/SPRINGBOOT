package com.mc.bookmanager.member;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MemberRepository {

	public List<Member> findAllMember(EntityManager em) {
		String jpql = "select m from Member m";
		return em.createQuery(jpql, Member.class).getResultList();
	}

	public List<Member> findMemberByRegDate(EntityManager em, LocalDateTime begin, LocalDateTime end) {
		
		String jpql = "select m from Member m where m.regDate between :begin and :end";
		
		return em.createQuery(jpql, Member.class)
				.setParameter("begin", begin)
				.setParameter("end", end)
				.getResultList();
	}

}
