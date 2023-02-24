package com.mc.mvc.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mc.mvc.module.board.repository.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void testQueryDSL() {
		boardRepository.testQueryDSL("게시판", false);
	}
}
