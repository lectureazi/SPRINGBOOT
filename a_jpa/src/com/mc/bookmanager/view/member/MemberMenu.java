package com.mc.bookmanager.view.member;

import java.util.Scanner;

import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.member.MemberController;

public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	public void memberMenu() {
		
		do {
			System.out.println("\n***  회원 관리 프로그램 ***");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 새 회원 등록");
			System.out.println("3. 회원 수정");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 회원 검색");
			System.out.println("6. 이전 페이지");
			System.out.print("번호 입력 : ");
			
			switch(sc.nextInt()) {
				case 1:
					
					break;
				case 2: 
					break;
				case 3: 
					sc.nextLine();
					System.out.print("회원정보를 수정할 아이디 : ");
					
					System.out.print("변경할 비밀번호 : ");
					String password = sc.nextLine();
					if(!password.equals("")) 
					
					System.out.print("변경할 전화번호 : ");
					String tell = sc.nextLine();
					if(!tell.equals("")) 
					
					System.out.print("변경할 이메일 : ");
					String email = sc.nextLine();
					if(!email.equals("")) 
					
				
					break;
					
				case 4: 
					System.out.print("탈퇴 시킬 회원의 아이디 입력 : ");
					String userId = sc.next();
					
					
					break;
					
				case 5: searchMenu(); break;
				case 6: return;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}
		}while(true);
	}
	
	public void searchMenu() {
		
		do {
			System.out.println("\n*** 회원 검색 메뉴 ***");
			//cache설명하기
			System.out.println("1. 아이디로 검색");
			System.out.println("2. 가입날짜별 검색");
			System.out.println("3. 이전 메뉴로 이동");
			System.out.print("번호 선택 : ");
			
			switch(sc.nextInt()) {
				case 1 : System.out.print("검색할 아이디 : ");
						 String userId = sc.next();
						 
						 break;
				case 2 : System.out.print("검색할 가입 시작 날짜[yyyy-mm-dd] :");
						 String begin = sc.next();
						 System.out.print("검색할 가입 끝날짜[yyyy-mm-dd] : ");
						 String end = sc.next();
						 
						 break;
						 
				case 3 : return;
				default : System.out.println("잘못 입력되었습니다. 다시 입력하세요.");
			}
		}while(true);
	}
		
	//사용자로부터 회원가입 정보를 받아서 member객체로 반환
	public Member join() {
		
		Member member = new Member();
		
		System.out.println("회원 정보를 입력하세요.-------------");
		
		System.out.print("아이디 : ");
		
		System.out.print("암호 : ");
		
		System.out.print("이메일 : ");
		
		System.out.print("전화 번호 : ");
		return member;
	}

	
	
	
	
	
	
	
}
