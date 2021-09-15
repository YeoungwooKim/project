package project.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/myMessage")
	public ModelAndView showAllMessage() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		ModelAndView mv = new ModelAndView("project/myMessage");
		mv.addObject("msg", userService.searchMailBox(user.getUsername(), userService.getMsgCount(user.getUsername())));
		return mv;
	}
	
	@GetMapping("/mypage")
	public ModelAndView myPage() throws Exception {
		ModelAndView mv = new ModelAndView("project/mypage");
		mv.addObject("title", "mypage");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		mv.addObject("email", userService.searchEmail(user.getUsername()));
		
		mv.addObject("receiveMail", userService.searchMailBox(user.getUsername(), 2));
		mv.addObject("msgCount", userService.getMsgCount(user.getUsername()));
		
		mv.addObject("myBoard", userService.findMyBoard(user.getUsername()));
		//유저 이메일 정보 넘겨주기.
		return mv;
	}	
	
	@PutMapping("/editMyInfo")
	public String editMyInfo(UserDto postedUserValue) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();

		if(postedUserValue.getUserPw().isEmpty()) {
			userService.editEmail(user.getUsername(), postedUserValue.getUserEmail());
		} else {
			userService.editInfo(user.getUsername(), postedUserValue);
		}
		return "redirect:/mypage";
	} 
	
	@GetMapping("/login")
	public ModelAndView login() throws Exception {
		ModelAndView mv = new ModelAndView("project/login");
		return mv;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) 
			new SecurityContextLogoutHandler().logout(req, res, auth);
		return "redirect:/";
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
