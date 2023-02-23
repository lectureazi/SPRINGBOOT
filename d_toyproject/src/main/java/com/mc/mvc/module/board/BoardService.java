package com.mc.mvc.module.board;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.infra.util.file.FilePath;
import com.mc.mvc.infra.util.file.FileUtil;
import com.mc.mvc.infra.util.file.dto.FilePathDto;
import com.mc.mvc.infra.util.paging.Paging;
import com.mc.mvc.module.board.dto.request.BoardRegistRequest;
import com.mc.mvc.module.board.dto.resonse.BoardListResponse;
import com.mc.mvc.module.member.Member;
import com.mc.mvc.module.member.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	private final FileUtil fileUtil;
	
	@Transactional
	public void createBoard(BoardRegistRequest dto, List<MultipartFile> files) {
		
		Member member = memberRepository.findById(dto.getUserId()).get(); 
		Board board = Board.createBoard(dto, member);
		
		FilePathDto filePath = new FilePathDto();
		filePath.setGroupName("board");
		
		List<FilePathDto> fileDtos = fileUtil.uploadFile(filePath, files);
		
		fileDtos.forEach(e -> {
			board.addFile(FilePath.createFilePath(e));
		});
		
		
		boardRepository.save(board);

	}

	public Map<String, Object> findBoardList(Pageable pageable) {
		
		Page<Board> page = boardRepository.findAll(pageable);
		
		Paging paging = Paging.builder()
				.page(page)
				.blockCnt(5)
				.build();
		 
		 System.out.println("blist.getNumber() : " + page.getNumber());
		 System.out.println("blist.getNumberOfElements() : " + page.getNumberOfElements());
		 System.out.println("blist.getSize() : " + page.getSize());
		 System.out.println("blist.getTotalElements() : " + page.getTotalElements());
		 System.out.println("blist.getTotalPages() : " + page.getTotalPages());
		 
		 System.out.println(paging);
		 
		
		return Map.of("boardList",BoardListResponse.toDtoList(page.getContent()), "paging", paging);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
