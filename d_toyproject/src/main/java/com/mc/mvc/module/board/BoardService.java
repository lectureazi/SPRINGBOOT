package com.mc.mvc.module.board;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.infra.code.ErrorCode;
import com.mc.mvc.infra.exception.HandlableException;
import com.mc.mvc.infra.util.file.FilePath;
import com.mc.mvc.infra.util.file.FileRepository;
import com.mc.mvc.infra.util.file.FileUtil;
import com.mc.mvc.infra.util.file.dto.FilePathDto;
import com.mc.mvc.infra.util.paging.Paging;
import com.mc.mvc.module.board.dto.request.BoardModifyRequest;
import com.mc.mvc.module.board.dto.request.BoardRegistRequest;
import com.mc.mvc.module.board.dto.resonse.BoardDetailResponse;
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
	private final FileRepository fileRepository;
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
		
		return Map.of("boardList",BoardListResponse.toDtoList(page.getContent()), "paging", paging);
	}

	public BoardDetailResponse findBoardByBdIdx(Long bdIdx) {
		Board board = boardRepository.findById(bdIdx)
						.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		return new BoardDetailResponse(board);
	}

	public FilePathDto findFilePathByFpIdx(Long fpIdx) {
		FilePath filePath = fileRepository.findById(fpIdx)
						.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		return new FilePathDto(filePath);
	}

	@Transactional
	public void updateBoard(BoardModifyRequest dto, List<MultipartFile> files) {

		Board board = boardRepository.findById(dto.getBdIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		if(!board.getMember().getUserId().equals(dto.getUserId())) throw new HandlableException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		board.updateBoard(dto);
		
		List<FilePathDto> delFilePath = new ArrayList<FilePathDto>();
		
		//사용자가 삭제한 파일을 지워주기
		dto.getDelFiles().forEach(e -> {
			FilePath filePath = fileRepository.findById(e).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
			delFilePath.add(new FilePathDto(filePath));
			board.removeFile(filePath);
		});
		
		FilePathDto fileInfo = new FilePathDto();
		fileInfo.setGroupName("board");
		
		List<FilePathDto> filePathDtos = fileUtil.uploadFile(fileInfo, files);
		
		filePathDtos.forEach(e -> {
			board.addFile(FilePath.createFilePath(e));
		});
		
		delFilePath.forEach(e -> {
			new File(e.getFullPath()).delete();
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
