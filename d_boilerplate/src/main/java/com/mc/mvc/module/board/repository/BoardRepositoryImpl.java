package com.mc.mvc.module.board.repository;

import java.util.List;

import com.mc.mvc.module.board.Board;
import com.mc.mvc.module.board.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class BoardRepositoryImpl implements BoardRepositoryExtension{

	private final JPAQueryFactory query;
	private QBoard board = QBoard.board;
	
	public BoardRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	
	@Override
	public List<Board> testQueryDSL(String title, boolean isDel) {
		
		List<Board> boards = query.select(board)
				.from(board)
				.where(board.title.contains(title).and(board.isDel.eq(isDel)))
				.fetch();
		
		return boards;
	}

}
