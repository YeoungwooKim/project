package project.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.UserDto;
import project.project.service.UserService;

@RestController
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public ModelAndView openLoginPage() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("project/index");
		return mv;
	}
 
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView login(@RequestBody UserDto user) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("project/index");
		if (userService.login(user) == null) {
			mv.addObject("loginTF", false);
		} else { // login success
			mv.addObject("loginTF", true);
		}
		return mv;
	}
}
