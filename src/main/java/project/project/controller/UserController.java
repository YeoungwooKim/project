package project.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.UserDto;
import project.project.service.UserService;

@Controller
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public ModelAndView test() throws Exception {
		return new ModelAndView("project/test");
	}

	@GetMapping("/login")
	public ModelAndView login() throws Exception {
		ModelAndView mv = new ModelAndView("project/login");
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, res, auth);
			mv.setViewName("project/index");
		} else {
			mv.setViewName("project/login");
		}
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView openRegister() throws Exception {
		ModelAndView mv = new ModelAndView("project/register");
		mv.addObject("user", null);
		return mv;
	}

	@PostMapping("/register")
	public String register(UserDto user) throws Exception {
		ModelAndView mv = new ModelAndView("project/register");
		userService.saveUser(user);
		mv.addObject("user", user);
		return "redirect:/";
	}
}
