package project.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.GameDto;
import project.project.dto.RankingDto;
import project.project.service.GameService;

@Controller
public class GameController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	GameService gameService;
	

	@GetMapping("/games")
	public ModelAndView gamelist() throws Exception {
		ModelAndView mv = new ModelAndView("/project/gamelist");
		mv.addObject("title", "Games");
		List<GameDto> list = gameService.showList();
		mv.addObject("list", list);

		return mv;
	}

	@GetMapping("/rank/{idx}")
	public ModelAndView rankList(@PathVariable int idx) throws Exception {
		ModelAndView mv = new ModelAndView("/project/ranklist");
		mv.addObject("title", "Rank");
		if(gameService.isValid(idx) != true) {
			idx=1;
		}
		List<HashMap<Integer, String>> titles = gameService.getTitles();
		//[{played_cnt=3, game_title=brick, idx=1}, {played_cnt=2, game_title=tetris, idx=4}]
		mv.addObject("titles", titles);

		List<RankingDto> list = gameService.makeRank(idx);
		mv.addObject("list", list);

		return mv;
	}

	@ExceptionHandler(NumberFormatException.class)
	public String handleTypeMismatchException() {
		return "redirect:/rank/1";
	}

	@GetMapping("/rank")
	public String catchUrl() throws Exception {
		return "redirect:/rank/1";
	}

	@GetMapping("/games/{idx}")
	public ModelAndView gameDetail(@PathVariable int idx) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/project/game/detail");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();

		mv.addObject("user", user.getUsername());
		mv.addObject("gameTitle", gameService.searchByIdx(idx));
		return mv;
	}

	@RequestMapping(value = "/games/{idx}", method = RequestMethod.POST)
	public String saveScore(@PathVariable int idx, RankingDto rank) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		rank.setUserId(user.getUsername());

		gameService.hitCnt(idx);
		gameService.saveRank(rank);

		return "redirect:/games/" + idx;
	}
}
