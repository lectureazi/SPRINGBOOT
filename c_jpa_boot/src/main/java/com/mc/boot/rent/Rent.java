package com.mc.boot.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.boot.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// EntityGraph : Entity 조회시에 함께 조회한 연관엔티티를 지정

@NamedEntityGraph( 
	name = "Rent.rentBooks",
	attributeNodes = {
			@NamedAttributeNode(value = "rentBooks", subgraph = "RentBook.book")
		   ,@NamedAttributeNode(value = "member")
	}
	,subgraphs = {
			@NamedSubgraph(name="RentBook.book", attributeNodes = {@NamedAttributeNode(value = "book")})
	}
)

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Rent {
	
	@Id
	@GeneratedValue
	private Long rmIdx;
	
	// create문에서 컬럼명을 지정한 뒤 작성하는 쿼리를 columnDefinition에 설정
	// create Rent(regDate timestamp default now()...)
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;

	@ColumnDefault("false")
	private Boolean isReturn;
	
	private String title;
	
	@ColumnDefault("0")
	private Integer rentBookCnt;
	
	@ManyToOne()
	@JoinColumn(name = "userId")
	private Member member;
	
	//CascadeType
	// PERSIST : PERSIST를 수행할 때 연관엔티티도 함께 수행, RENT가 테이블에 저장될때 RENTBOOK도 함께 저장
	// REMOVE  : 엔티티를 삭제할 때 연관엔티티도 함께 삭제
	// MERGE   : 준영속상태인 엔티티를 MERGE해서 영속상태로 만들 때 연관엔티티도 함께 영속상태로 만듦
	// DETACH  : 영속상태인 엔티티를 준영속상태로 만들 때 연관엔티티도 함께 수행
	// ALL	   : PERSIST + REMOVE + MERGE + DETACH
	
	
	// JPA에서  toMany 관계의 FetchType은 default가 LAZY
	// 		  toOne 관계의 FetchType은 default가 EAGER
	
	//		  EAGER : 엔티티를 조회할 때 연관 엔티티를 함께 조회함
	//		  LAZY  : 엔티티를 조회할 때는 연관 엔티티를 조회하지 않음, 해당 연관엔티티(속성)가 처음으로 호출되는 시점에 연관엔티티를 조회
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rent", fetch = FetchType.EAGER)
	private List<RentBook> rentBooks = new ArrayList<>();

	public static Rent createRent(String title, Member member, int rentBookCnt) {
		return Rent.builder()
				.title(title)
				.member(member)
				.rentBookCnt(rentBookCnt)
				.build();
	}

	public void addRentBooks(List<RentBook> rentBooks) {
		this.rentBooks = rentBooks;
	}

	public void returnComplete() {
		this.isReturn = true;
	}
	
	
	
	
	
	
	
	
	
	
	

}
