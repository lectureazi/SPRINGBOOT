package com.mc.mvc.module.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.mvc.infra.util.file.FilePath;
import com.mc.mvc.module.board.dto.request.BoardModifyRequest;
import com.mc.mvc.module.board.dto.request.BoardRegistRequest;
import com.mc.mvc.module.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Board {
	
	@Id
	@GeneratedValue
	private Long bdIdx;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	private String title;
	
	private String content;
	
	@ColumnDefault("false")
	private Boolean isDel;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<FilePath> files = new ArrayList<FilePath>();

	public static Board createBoard(BoardRegistRequest dto, Member member) {
		return Board.builder()
				.member(member)
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
	}

	public void addFile(FilePath filePath) {
		this.files.add(filePath);
	}

	public void removeFile(FilePath filePath) {
		this.files.remove(filePath);
	}

	public void updateBoard(BoardModifyRequest dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
