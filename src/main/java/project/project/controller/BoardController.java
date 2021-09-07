package project.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.project.dto.BoardDto;
import project.project.service.BoardService;
import project.validator.BoardValidator;

@Controller
@RequestMapping("/board")
public class BoardController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardValidator boardValidator;
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView mv = new ModelAndView("project/list");
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		mv.addObject("title", "Board");
		return mv;
	}

	@GetMapping("/write")
	public ModelAndView write(HttpServletRequest request) throws Exception {
		String boardIdx = request.getParameter("id");
		ModelAndView mv = new ModelAndView("project/write");
		if (boardIdx != null) { // 파라미터값이 존재, 상세열람
			mv.addObject("board", boardService.searchBoard(Integer.parseInt(boardIdx)));
		} else { // 파라미터값이 존재 X, 글쓰기
			mv.addObject("board", new BoardDto());
		}
		return mv;
	}

	@PostMapping("/write")
	public String save(@Valid BoardDto board, BindingResult bindingResult) throws Exception {
		//boardValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
			log.debug(bindingResult.getFieldError() + "  dto 어노테이션서 에러 출현");
			if (board.getBoardIdx() > 0)
				return "redirect:/board/write?id=" + board.getBoardIdx();
			else
				return "redirect:/board/write";
		} else {
			if (board.getBoardIdx() > 0) { // 파라미터값이 존재, 게시글 수정
				boardService.modifyBoardList(board);
			} else { // 파라미터값이 존재X, 게시글 생성
				boardService.insertBoardList(board);
			}
			return "redirect:/board/list";
		}
	}

}
