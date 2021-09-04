package project.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.UserDto;
import project.project.service.UserService;

@Controller
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	private UserService userService;
//
//	@GetMapping("/")
//	public ModelAndView openLoginPage() throws Exception {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("project/index");
//		return mv;
//	}
//	
//	@PostMapping("/")
//	public ModelAndView login(@RequestBody UserDto user) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		if (userService.login(user) != null ) { // login success
//			mv.setViewName("project/board");
//			mv.addObject("tf", true);
//		} else { 
//			mv.setViewName("project/index");
//			mv.addObject("tf", false);
//		}
//		return mv;
//	}
}
