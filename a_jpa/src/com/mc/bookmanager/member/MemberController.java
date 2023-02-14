package com.mc.bookmanager.member;

import java.time.LocalDate;
import java.util.List;

import com.mc.bookmanager.member.dto.MemberDto;

public class MemberController {

	private MemberService memberService = new MemberService();
	
	public MemberDto findMemberById(String userId) {
		MemberDto member = memberService.findMemberById(userId);
		return member;
	}

	public List<MemberDto> findAllMember() {
		return memberService.findAllMember();
	}

	public boolean signUp(MemberDto join) {
		return memberService.createMember(join);
	}

	public boolean updateMember(MemberDto member) {
		return memberService.updateMember(member);
	}

	public List<MemberDto> findMemberByRegDate(String begin, String end) {
		List<MemberDto> members = memberService.findMemberByRegDate(LocalDate.parse(begin).atStartOfDay(),
																	LocalDate.parse(end).atStartOfDay());
		return members;
	}

	public boolean removeMember(String userId) {
		return memberService.removeMemberByUserId(userId);
	}

	
	
	
	
	
	
	
}
