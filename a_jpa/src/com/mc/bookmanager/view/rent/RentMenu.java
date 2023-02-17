package com.mc.bookmanager.view.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mc.bookmanager.rent.RentController;
import com.mc.bookmanager.rent.dto.RentDto;

public class RentMenu {
	
	private RentController rentController = new RentController();
	
	public void rentMenu() {
		Scanner sc = new Scanner(System.in);
		String userId = null;
		
		do {
			System.out.println("\n*** 대출 관리 ***");
			System.out.println("1. 대출번호로 대출건 조회");
			System.out.println("2. 도서 대출");
			System.out.println("3. 도서 반납");
			System.out.println("4. 사용자 아이디로 대출건 조회");
			System.out.println("5. 끝내기");
			System.out.print("선택 : ");
			
			switch (sc.nextInt()) {
			case 1: System.out.print("조회할 대출번호를 입력하세요 : ");
					RentDto rentDto = rentController.findRentByIdx(sc.nextLong());
					System.out.println(rentDto);
					break;
			case 2: //대출자의 아이디를 입력받고
					System.out.print("대출자의 아이디를 입력하세요 : ");
					userId = sc.next();
					List<Long> bkIdxs = new ArrayList<>();
					
					//대출자가 대출 하고자 하는 도서번호를 입력받는다. 한번에 대출 가능한 도서는 최대 5권까지이다.
					for (int i = 0; i < 5; i++) {
						
						System.out.print("대출할 도서의 도서번호를 입력하세요 : ");
						
						bkIdxs.add(sc.nextLong());
						
						if(i < 4) {
							System.out.print("대출할 도서가 더 존재하나요?(y/n) : ");
							if(sc.next().toLowerCase().equals("n")) {
								break;
							}
						}
					}
					
					if(rentController.regisRent(userId, bkIdxs)) {
						System.out.println("도서 대출이 완료되었습니다.");
					}else {
						System.out.println("실패하였습니다.");
					}
					
				break;
			case 3: //반납할 대출도서번호(rbIdx)를 입력받아
					//해당 rbIdx의 대출도서를 반납처리
				System.out.print("반납할 도서 대출번호를 입력하세요 : ");
				
				if(rentController.returnRentBook(sc.nextLong())) {
					System.out.println("반납에 성공했습니다.");
				}else {
					System.out.println("반납에 실패했습니다.");
				}
				
				
				break;
				
			case 4: sc.nextLine();
					System.out.print("대출내역을 조회할 사용자 아이디를 입력하세요 : ");
					userId = sc.nextLine();
					
					List<RentDto> rents = rentController.findRentByUserId(userId);
					
					rents.forEach(e -> {
						System.out.println(e);
					});
					
					System.out.print("상세조회 할 대출건이 존재하나요? (y/n) : ");
					String input = sc.nextLine();
					
					if(input.equalsIgnoreCase("y")) {
						System.out.print("상세조회할 대출번호를 입력하세요 : ");
						Long rmIdx = sc.nextLong();
						sc.nextLine(); 
					}
				break;
				
			case 5: return;
			
			default:System.out.println("잘못된 숫자를 입력하셨습니다.");

			}
			
		}while(true);
	}

}
