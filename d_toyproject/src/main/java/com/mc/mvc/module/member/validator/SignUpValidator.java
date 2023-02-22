package com.mc.mvc.module.member.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mc.mvc.module.member.MemberRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignUpValidator implements Validator{
	
	private MemberRepository memberRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}

}
