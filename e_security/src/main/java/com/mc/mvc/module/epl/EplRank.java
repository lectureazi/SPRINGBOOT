package com.mc.mvc.module.epl;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.mvc.module.epl.dto.EplRankDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class EplRank {
	
	@Id
	@GeneratedValue
	private Long eplIdx;
	private String team;
	private Integer matchCnt;
	private Integer win;
	private Integer draw;
	private Integer lose;
	private Integer goalsFor;
	private Integer goalsAgainst;
	private Integer goalsDiffrence;
	private Integer point;
	
	@Builder.Default
	private LocalDateTime regDate = LocalDateTime.now();
	
	public static EplRank createEplRank(EplRankDto dto) {
		return EplRank.builder()
				.goalsAgainst(dto.getGoalsAgainst())
				.goalsFor(dto.getGoalsFor())
				.goalsDiffrence(dto.getGoalsDiffrence())
				.team(dto.getTeam())
				.matchCnt(dto.getMatchCnt())
				.win(dto.getWin())
				.lose(dto.getLose())
				.draw(dto.getDraw())
				.point(dto.getPoint())
				.build();
				
	}

}


























