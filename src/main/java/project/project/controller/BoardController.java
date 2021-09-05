package project.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.BoardDto;
import project.project.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
 
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
	public ModelAndView write() throws Exception {
		ModelAndView mv = new ModelAndView("project/write");
		mv.addObject("board", new BoardDto());
		return mv;
	}
	
	@PostMapping("/write")
	public String save(@ModelAttribute BoardDto board) throws Exception {		
		//save action
		boardService.insertBoardList(board);
		//board.printAll();
		//returning to list page
		return "redirect:/board/list"; 
	}
}
