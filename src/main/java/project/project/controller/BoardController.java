package project.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.micrometer.core.instrument.util.StringUtils;
import project.project.dto.BoardDto;
import project.project.dto.UserDto;
import project.project.page.Pagination;
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
	public ModelAndView list(@RequestParam(value = "currentPage", required = false) String currentPage)
			throws Exception {
		Pagination pagination = new Pagination(boardService.getTotalRecord());
		ModelAndView mv = new ModelAndView("project/list");
		int currPage;
		if (pagination.chkCurrentPage(currentPage)) {
			currPage = Integer.parseInt(currentPage);
		} else {
			currPage = 0;
		}
		List<BoardDto> list = boardService.selectBoardList(currPage, pagination.getSize());
		mv.addObject("pagination", pagination);
		mv.addObject("list", list);
		mv.addObject("title", "Board");

		return mv;
	}
	
	@GetMapping("/list/creator/{creator}")
	public ModelAndView searchByCreator(@PathVariable("creator") String creatorId) throws Exception {
		ModelAndView mv = new ModelAndView("project/list");
		List<BoardDto> list = boardService.searchByCreator(creatorId);
		mv.addObject("list", list);
		mv.addObject("title","creator");
		return mv;
	}
	

	@GetMapping("/write")
	public ModelAndView write(HttpServletRequest request) throws Exception {
		String boardIdx = request.getParameter("id");
		ModelAndView mv = new ModelAndView("project/write");
		if (boardIdx != null) { // 파라미터값이 존재, 상세열람
			mv.addObject("board", boardService.searchBoard(Integer.parseInt(boardIdx)));
			boardService.addHitCnt(Integer.parseInt(boardIdx));
		} else { // 파라미터값이 존재 X, 글쓰기
			mv.addObject("board", new BoardDto());
		}
		return mv;
	}

	@PostMapping("/write")
	public String save(@Valid BoardDto board, BindingResult bindingResult) throws Exception {
		// boardValidator.validate(board, bindingResult);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();

		if (bindingResult.hasErrors()) {
			log.debug(bindingResult.getFieldError() + "  dto 어노테이션서 에러 출현");
			if (board.getBoardIdx() > 0)
				return "redirect:/board/write?id=" + board.getBoardIdx();
			else
				return "redirect:/board/write";
		} else {
			if (board.getBoardIdx() > 0) { // 파라미터값이 존재, 게시글 수정
				if(isEqualUser(boardService.findCreator(board.getBoardIdx()), user.getUsername())) {
					boardService.modifyBoardList(board, board.getBoardIdx());
				} else if (isProperAuth(user.getAuthorities().toString())) {
					boardService.modifyBoardList(board, board.getBoardIdx());
				}
			} else { // 파라미터값이 존재X, 게시글 생성
				board.setCreatorId(user.getUsername());
				boardService.insertBoardList(board);
			}
			return "redirect:/board/list";
		}
	}

	@DeleteMapping("/write")
	public String delete(@RequestParam int boardIdx) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		if (isEqualUser(boardService.findCreator(boardIdx), user.getUsername())) {
			boardService.delete(boardIdx);
		} else if (isProperAuth(user.getAuthorities().toString())) {
			boardService.delete(boardIdx);
		}
		return "redirect:/board/list";
	}
	
	public Boolean isEqualUser(String creatorId, String currentUser) {
		if(creatorId.equals(currentUser)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isProperAuth(String currentAuth) {
		if(currentAuth.equals("[ROLE_ADMIN]")) {
			return true;
		} else {
			return false;
		}
	}
}
