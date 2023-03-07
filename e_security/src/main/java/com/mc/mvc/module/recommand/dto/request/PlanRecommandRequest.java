package com.mc.mvc.module.recommand.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanRecommandRequest {
	
	private String location;
	private int month;
	private int participantCnt;

}
