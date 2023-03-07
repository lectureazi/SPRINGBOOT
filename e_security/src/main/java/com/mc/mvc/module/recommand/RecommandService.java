package com.mc.mvc.module.recommand;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mc.mvc.infra.api.OpenAI;
import com.mc.mvc.infra.api.dto.ChatCompletionResponse;
import com.mc.mvc.module.group.TravelGroup;
import com.mc.mvc.module.group.TravelGroupRepository;
import com.mc.mvc.module.member.UserPrincipal;
import com.mc.mvc.module.member.dto.Principal;
import com.mc.mvc.module.recommand.dto.request.PlanRecommandRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommandService {

	private final OpenAI openAI;
	private final TravelGroupRepository travelGroupRepository;
	
	public ChatCompletionResponse generateTravelPlan(PlanRecommandRequest dto) {
		
		String question = dto.getMonth() + "월에 " + dto.getParticipantCnt() + "명이 함께 가는" + dto.getLocation() + " 여행 일정 짜줘";
		return openAI.chatCompletion(question);
	}

	public Long findTravelGroupIdxByUserId() {
		TravelGroup group = travelGroupRepository.findTravelGroupByMembersUserId(UserPrincipal.getUserPrincipal().getUserId());
		return group.getTgIdx();
	}
	
	
}
