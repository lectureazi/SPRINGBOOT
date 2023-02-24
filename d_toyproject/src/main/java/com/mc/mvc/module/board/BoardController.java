package com.mc.mvc.module.board;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.infra.util.file.dto.FilePathDto;
import com.mc.mvc.module.board.dto.request.BoardModifyRequest;
import com.mc.mvc.module.board.dto.request.BoardRegistRequest;
import com.mc.mvc.module.board.dto.resonse.BoardDetailResponse;
import com.mc.mvc.module.member.dto.Principal;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("board")
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("form")
	public String boardForm() {
		return "/board/board-form";
	}
	
	@PostMapping("upload")
	public String upload(
			@RequestParam List<MultipartFile> files,
			@SessionAttribute(name="auth", required=false) Principal principal,
			BoardRegistRequest dto
			) {
		
		dto.setUserId(principal.getUserId());
		boardService.createBoard(dto, files);
		
		return "redirect:/";
	}
	
	@GetMapping("list")
	public String boardList(
			@PageableDefault(size=10, sort="bdIdx", direction = Direction.DESC, page = 0)
			Pageable pageable,
			Model model
			
			) {

		Map<String, Object> commandMap = boardService.findBoardList(pageable); 
		model.addAllAttributes(commandMap);
		
		return "/board/board-list";
	}
	
	@GetMapping("detail")
	public String boardDetail(Long bdIdx, Model model) {
		
		BoardDetailResponse dto = boardService.findBoardByBdIdx(bdIdx);
		model.addAttribute("board", dto);
		
		return "/board/board-contents";
	}
	
	@GetMapping("download")
	public ResponseEntity<FileSystemResource> downloadFile(Long fpIdx){
		
		FilePathDto dto = boardService.findFilePathByFpIdx(fpIdx);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(ContentDisposition.builder("attachment")
									.filename(dto.getOriginFileName(), Charset.forName("utf-8"))
									.build());
		
		FileSystemResource fsr = new FileSystemResource(dto.getFullPath());
		return ResponseEntity.ok().headers(headers).body(fsr);
	}
	
	
	@GetMapping("modify")
	public String boardModify(Long bdIdx, Model model) {
		BoardDetailResponse dto = boardService.findBoardByBdIdx(bdIdx);
		model.addAttribute("board", dto);
		return "board/board-modify";
	}
	
	@PostMapping("modify")
	public String modify(BoardModifyRequest dto,
						@RequestParam List<MultipartFile> fileList,
						@SessionAttribute("auth") Principal principal
			) {
		
		dto.setUserId(principal.getUserId());
		boardService.updateBoard(dto, fileList);
		
		return "redirect:/board/detail?bdIdx="+dto.getBdIdx();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
