package com.mc.mvc.group;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mc.mvc.module.group.TravelGroup;
import com.mc.mvc.module.group.TravelGroupRepository;
import com.mc.mvc.module.member.Member;
import com.mc.mvc.module.member.MemberRepository;

@SpringBootTest
public class TravleGroupServiceTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TravelGroupRepository travelGroupRepository;
	
	@Test
	public void testCreateTravelGroup() {
		
		Member group1A = memberRepository.findById("group1A").get();
		Member group1B = memberRepository.findById("group1B").get();
		Member group2A = memberRepository.findById("group2A").get();
		Member group2B = memberRepository.findById("group2B").get();
		
		TravelGroup group1 = new TravelGroup();
		TravelGroup group2 = new TravelGroup();
		
		group1.addMembers(group1A);
		group1.addMembers(group1B);
		
		group2.addMembers(group2A);
		group2.addMembers(group2B);
		
		travelGroupRepository.save(group1);
		travelGroupRepository.save(group2);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
