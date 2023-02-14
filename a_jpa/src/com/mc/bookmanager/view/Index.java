package com.mc.bookmanager.view;

import java.util.Scanner;

import com.mc.bookmanager.view.book.BookMenu;
import com.mc.bookmanager.view.member.MemberMenu;
import com.mc.bookmanager.view.rent.RentMenu;

public class Index {
	
	private Scanner sc = new Scanner(System.in);
	MemberMenu memberMenu = new MemberMenu();
	BookMenu bookMenu = new BookMenu();
	RentMenu rentMenu = new RentMenu();
	
	public void startMenu() {
		while(true) {
			System.out.println("관리할 메뉴를 선택하세요.");
			System.out.println("1. 회원관리");
			System.out.println("2. 도서관리");
			System.out.println("3. 대출관리");
			System.out.println("4. 종료");
			System.out.print("입력 : ");
			
			switch(sc.nextInt()) {
			case 1 : memberMenu.memberMenu(); break;
			case 2 : bookMenu.bookMenu(); break;
			case 3 : rentMenu.rentMenu(); break;
			case 4 : return;
			default :  System.out.println("잘못된 숫자를 입력하셨습니다.");
			
			}
		}
	}
}
