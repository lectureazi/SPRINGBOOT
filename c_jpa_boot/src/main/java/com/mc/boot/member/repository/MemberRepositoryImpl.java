package com.mc.boot.member.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.mc.boot.member.Member;
import com.mc.boot.member.QMember;
import com.mc.boot.rent.QRent;
import com.mc.boot.rent.QRentBook;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MemberRepositoryImpl implements MemberRepositoryExtension{
	
	private final JPAQueryFactory query;
	private QMember member = QMember.member;
	private QRent rent = QRent.rent;
	private QRentBook rentBook = QRentBook.rentBook;
	
	public MemberRepositoryImpl(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}

	@Override
	public List<Member> subQuery() {
		
		// 대출 도서의 상태가 '대출' 인 대출도서가 한권이라도 존재하는 회원을 조회
		List<Member> members =
				query
				.select(member)
				.from(member)
				.where(member.userId.in(JPAExpressions
						.select(rent.member.userId)
						.from(rent)
						.innerJoin(rent.rentBooks, rentBook)
						.where(rentBook.state.eq("대출"))
						)).fetch();
		return members;
	}


}
