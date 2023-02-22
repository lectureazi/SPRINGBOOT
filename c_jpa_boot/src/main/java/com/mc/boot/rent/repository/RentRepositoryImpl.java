package com.mc.boot.rent.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.mc.boot.member.QMember;
import com.mc.boot.rent.QRent;
import com.mc.boot.rent.QRentBook;
import com.mc.boot.rent.Rent;
import com.mc.boot.rent.dto.RentDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RentRepositoryImpl implements RentRepositoryExtension{
	
	private final JPAQueryFactory query;
	private QRent rent = QRent.rent;
	private QRentBook rentBook = QRentBook.rentBook;
	private QMember member = QMember.member;
	
	public RentRepositoryImpl(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}

	@Override
	public List<Rent> whereAnd() {
		//대출건 제목이 '디디' 로 시작하고 대출자 id가 test인 대출건 조회
		
		List<Rent> rents = 
				query.select(rent)
				.from(rent)
				.where(rent.title.startsWith("디디"), rent.member.userId.eq("test"))
				.fetch();
		
		return rents;
	}

	@Override
	public List<Rent> whereOr() {
		List<Rent> rents = 
				query
				.select(rent)
				.from(rent)
				.where(rent.title.startsWith("디디")
				.or(rent.member.userId.eq("jpa")))
				.fetch();
		
		return rents;
	}

	@Override
	public List<Rent> rightJoin() {
		
		List<Rent> rents = 
				query
				.select(rent)
				.from(rent)
				.innerJoin(rent.member)
				.fetchJoin()
				.where(rent.title.startsWith("디디").or(rent.member.userId.eq("jpa")))
				.fetch();
		
		return rents;
	}

	@Override
	public List<RentDto> testProjections() {
		// 대출자 아이디가 test인 모든 대출건의, 대출건 제목과 대출번호만 조회
		List<RentDto> rents = 
				query
				.select(Projections.fields(RentDto.class, rent.title, rent.rmIdx))
				.from(rent)
				.where(rent.member.userId.eq("test"))
				.fetch();
		
		return rents;
	}

	@Override
	public List<Tuple> testTuple() {
		
		List<Tuple> tuples =
				query
				.select(rent.rmIdx, rent.title, rent.member.userId)
				.from(rent)
				.where(rent.member.userId.eq("test"))
				.fetch();
		
		return tuples;
	}

	@Override
	public List<Tuple> testThetaJoin() {
		//연관관계 매핑이 되어있지 않은 entity간의 join
		//cross join이 발생
		List<Tuple> tuples = 
				query
				.selectDistinct(rentBook.book.title, member.userId)
				.from(rentBook, member)
				.where(member.userId.eq("jpa"))
				.fetch();
		
		return tuples;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
