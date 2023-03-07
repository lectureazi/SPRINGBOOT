package com.mc.mvc.module.epl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EplRankDto {
	
	private Long eplIdx;
	private String team;
	private Integer matchCnt;
	private Integer win;
	private Integer draw;
	private Integer lose;
	private Integer point;
	private Integer goalsFor;
	private Integer goalsAgainst;
	private Integer goalsDiffrence;

}
