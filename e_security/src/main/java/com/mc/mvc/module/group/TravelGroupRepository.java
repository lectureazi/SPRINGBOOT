package com.mc.mvc.module.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelGroupRepository extends JpaRepository<TravelGroup, Long>{

	TravelGroup findTravelGroupByMembersUserId(String userId);

}
