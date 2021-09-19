package project.aop;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.MessageDto;
import project.project.service.UserService;

@ControllerAdvice
public class ExceptionHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ModelAndView defaultExceptionHandler(HttpServletRequest req, Exception exception) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		String currUser = user.getUsername();
		ModelAndView mv = new ModelAndView();

		if (currUser.equals("root")) {
			mv.setViewName("error/error_default");
			mv.addObject("errs", exception);
		} else {
			mv.setViewName("project/index");
			mv.addObject("err", true);
			//pushMsg("root", exception);
		}
		pushMsg("root", exception);
		
		return mv;
	}
	
	
	public void pushMsg(String target, Exception exception) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		String currUser =  "(" + user.getUsername() + ")";
		String err = currUser + " \n" + exception.toString();
		// root에게 메세징
		MessageDto errMsg = new MessageDto("root", target, currUser, err);
		userService.postMessage(errMsg, "root");
	}
	
}
