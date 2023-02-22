package com.mc.boot.rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;

import com.mc.boot.rent.Rent;
import com.mc.boot.rent.dto.RentDto;
import com.querydsl.core.Tuple;

public interface RentRepositoryExtension {
	
	List<Rent> whereAnd();
	
	List<Rent> whereOr();

	List<Rent> rightJoin();

	List<RentDto> testProjections();

	List<Tuple> testTuple();

	List<Tuple> testThetaJoin();

}
