package project.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/rank")
	public ModelAndView rankList() throws Exception {
		ModelAndView mv = new ModelAndView("/project/ranklist");
		mv.addObject("title", "Rank");
		List<GameDto> list = gameService.showList();
		mv.addObject("list", list);

		return mv;
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
