package com.mc.mvc.module.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.infra.code.ErrorCode;
import com.mc.mvc.infra.exception.AuthException;
import com.mc.mvc.infra.exception.HandlableException;
import com.mc.mvc.infra.util.file.FilePath;
import com.mc.mvc.infra.util.file.FileRepository;
import com.mc.mvc.infra.util.file.FileUtil;
import com.mc.mvc.infra.util.file.dto.FilePathDto;
import com.mc.mvc.infra.util.file.dto.FileUploadDto;
import com.mc.mvc.infra.util.paging.Paging;
import com.mc.mvc.module.board.dto.request.BoardModifyRequest;
import com.mc.mvc.module.board.dto.request.BoardRegistRequest;
import com.mc.mvc.module.board.dto.resonse.BoardDetailResponse;
import com.mc.mvc.module.board.dto.resonse.BoardListResponse;
import com.mc.mvc.module.board.repository.BoardRepository;
import com.mc.mvc.module.member.Member;
import com.mc.mvc.module.member.MemberRepository;
import com.mc.mvc.module.member.dto.Principal;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
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
		
		List<FileUploadDto> fileUploadDtos = fileUtil.generateFileUploadDtos("board", files);
		
		fileUploadDtos.forEach(e -> {
			board.addFile(FilePath.createFilePath(e.getFilePathDto()));
		});
		
		// JPA가 변경된 내용을 데이터베이스에 반영
		boardRepository.saveAndFlush(board);
		
		fileUtil.uploadFile(fileUploadDtos);
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
		if(!board.getMember().getUserId().equals(dto.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		board.updateBoard(dto);
		
		List<FilePathDto> delFilePath = new ArrayList<FilePathDto>();
		
		//사용자가 삭제한 파일을 지워주기
		dto.getDelFiles().forEach(e -> {
			FilePath filePath = fileRepository.findById(e).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
			delFilePath.add(new FilePathDto(filePath));
			board.removeFile(filePath);
		});
		
		
		List<FileUploadDto> fileUploadDtos = fileUtil.generateFileUploadDtos("board", files);
		
		fileUploadDtos.forEach(e -> {
			board.addFile(FilePath.createFilePath(e.getFilePathDto()));
		});
		
		
		// 엔티티 변경사항을 데이터베이스에 반영
		boardRepository.flush();
		
		// 파일을 삭제 및 추가
		fileUtil.uploadFile(fileUploadDtos);
		
		delFilePath.forEach(e -> {
			fileUtil.deleteFile(e);
		});
	}

	@Transactional
	public void removeBoard(Long bdIdx, Principal principal) {
		Board board = boardRepository.findById(bdIdx)
					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!board.getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		List<FilePathDto> filePathDtos = board.getFiles()
									.stream().map(e -> new FilePathDto(e)).collect(Collectors.toList());
		
		boardRepository.delete(board);
		
		filePathDtos.forEach(e ->{
			fileUtil.deleteFile(e);
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
