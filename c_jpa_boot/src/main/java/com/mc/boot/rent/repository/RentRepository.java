package com.mc.boot.rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.boot.rent.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>, RentRepositoryExtension{
	
	//대출건 제목이 '디디' 로 시작하거나 대출자 id가 test인 대출건 조회
	@EntityGraph(value = "Rent.rentBooks")
	List<Rent> findByTitleStartsWithOrMemberUserIdEquals(String title, String userId);

}
