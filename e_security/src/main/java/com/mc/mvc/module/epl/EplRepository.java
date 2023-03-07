package com.mc.mvc.module.epl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EplRepository extends JpaRepository<EplRank, Long>{

}
