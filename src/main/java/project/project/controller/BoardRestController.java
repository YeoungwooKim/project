package project.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import project.project.dto.BoardDto;
import project.project.service.BoardService;

@RestController
class BoardRestController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;

	@GetMapping("/doc")
	List<BoardDto> list(@RequestParam(value = "title", required = false) String title) throws Exception { // 전체 리스트 출력
		if (StringUtils.isEmpty(title)) {
			return boardService.selectBoardList();
		} else {
			return boardService.findBoardByTitle(title);
		}
	}

	@PostMapping("/doc") // 신규 게시글 등록
	void save(@RequestBody BoardDto board) throws Exception {
		boardService.insertBoardList(board);
	}

	@GetMapping("/doc/{id}") // 게시글 상세 조회
	BoardDto search(@PathVariable Long id) throws Exception {
		return boardService.searchBoard(id.intValue());
	}

	@PutMapping("/doc/{id}") // 게시글 수정
	void modify(@RequestBody BoardDto board, @PathVariable Long id) throws Exception {
		boardService.modifyBoardList(board, id.intValue());
	}

	@DeleteMapping("/doc/{id}") // 게시글 삭제
	void deleteBoardDto(@PathVariable Long id) throws Exception {
		boardService.delete(id.intValue());
	}
}