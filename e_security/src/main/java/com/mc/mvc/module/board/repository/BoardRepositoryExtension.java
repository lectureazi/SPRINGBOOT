package com.mc.mvc.module.board.repository;

import java.util.List;

import com.mc.mvc.module.board.Board;

public interface BoardRepositoryExtension {
	
	List<Board> testQueryDSL(String title, boolean isDel);

}
