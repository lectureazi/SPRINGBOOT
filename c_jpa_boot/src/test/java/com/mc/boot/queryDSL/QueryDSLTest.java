package com.mc.boot.queryDSL;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mc.boot.book.dto.BookDto;
import com.mc.boot.book.repository.BookRepository;
import com.mc.boot.member.repository.MemberRepository;
import com.mc.boot.rent.dto.RentDto;
import com.mc.boot.rent.repository.RentRepository;

@SpringBootTest
public class QueryDSLTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private RentRepository rentRepository;
	
	
	@Test
	@DisplayName("대출건 제목이 '디디' 로 시작하고 대출자 id가 test인 대출건 조회")
	public void whereAnd() {
		rentRepository.whereAnd().forEach(e -> {
			System.out.println(new RentDto(e));
		});
	}
	
	@Test
	@DisplayName("대출건 제목이 '디디' 시작하거나 대출자가 jpa인 모든 대출건을 조회")
	public void whereOr() {
		rentRepository.whereOr().forEach(e -> {
			System.out.println(new RentDto(e));
		});
	}
	
	@Test
	@DisplayName("whereOr에서 N+1 문제 해결")
	public void rightJoin() {
		rentRepository.rightJoin().forEach(e -> {
			System.out.println(new RentDto(e));
		});
	}
	
	@Test
	@DisplayName("projections 테스트")
	public void testProjections() {
		rentRepository.testProjections().forEach(e -> {
			System.out.println(e);
		});
	}

	@Test
	@DisplayName("Tuple 테스트")
	public void testTuple() {
		rentRepository.testTuple().forEach(e -> {
			System.out.println(e.get(0, Long.class));
			System.out.println(e.get(1, String.class));
			System.out.println(e.get(2, String.class));
		});
	}
	
	@Test
	@DisplayName("thetaJoin")
	public void testThetaJoin() {
		rentRepository.testThetaJoin().forEach(e -> {
			System.out.println(e);
		});
	}
	
	@Test
	@DisplayName("groupBy")
	public void groupBy() {
		// 카테고리별 도서들의 최대 재고, 평균 재고, 평균 대출 횟수
		bookRepository.groupBy().forEach(e -> {
			System.out.println(e);
		});
	}
	
	
	@Test
	@DisplayName("orderBy")
	public void orderBy(){
		//도서 재고 수량을 기준으로 내림차순으로 2권까지 조회
		bookRepository.orderBy().forEach(e -> {
			System.out.println(new BookDto(e));
		});
	}
	
	@Test
	@DisplayName("subQuery")
	public void testSubQuery() {
		// 대출 도서의 상태가 '대출' 인 대출도서가 한권이라도 존재하는 회원을 조회
		memberRepository.subQuery();
	}
	
	
	@Test
	@DisplayName("도서 동적쿼리")
	public void dynamicQueryWithBook() {
		BookDto bookDto = new BookDto();
		bookDto.setRentCnt(2);
		
		// bookDto의 bookAmt보다 도서재고가 많거나 같으면서
		// bookDto의 rentCnt보다 대출횟수가 적거나 같은 도서를 조회
		// 만약 bookDto의 bookAmt나 rentCnt에 null이 담겨 있다면 쿼리에서 제외
		
		bookRepository.dynamicQueryWithBook(bookDto).forEach(e -> {
			System.out.println(new BookDto(e));
		});
	}
	
	@Test
	@DisplayName("도서 동적쿼리")
	public void dynamicQueryWithBookOr() {

		// 사용자화면에서 사용자가 검색조건을 체크하고 키워드를 입력할 수 있다.
		List<String> filters = List.of("author");
		String keyword = "은";
		
		bookRepository.dynamicQueryWithBookOr(filters, keyword).forEach(e -> {
			System.out.println(new BookDto(e));
		});
	}
	
	@Test
	@DisplayName("entityGraph")
	public void entityGraph() {
		rentRepository.findByTitleStartsWithOrMemberUserIdEquals("디디", "jpa").forEach(e -> {
			System.out.println(new RentDto(e));
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
