package com.mc.mvc.infra.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mc.mvc.infra.code.ErrorCode;
import com.mc.mvc.infra.exception.AuthException;
import com.mc.mvc.infra.exception.HandlableException;
import com.mc.mvc.module.board.Board;
import com.mc.mvc.module.board.repository.BoardRepository;
import com.mc.mvc.module.member.Member;

public class BoardAuthInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[2]) {
		case "form":
			if(session.getAttribute("auth") == null) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
		
		case "upload":
			if(session.getAttribute("auth") == null) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
			
		case "modify":
			if(session.getAttribute("auth") == null) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
			
		default:
			break;
		}

		return true;
	}

	
	
	
	
	
	
	
}
