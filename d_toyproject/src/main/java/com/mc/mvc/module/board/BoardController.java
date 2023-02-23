package com.mc.mvc.module.board;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.module.board.dto.request.BoardRegistRequest;
import com.mc.mvc.module.member.dto.Principal;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("board")
public class BoardController {

	private final BoardService boardService;
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
