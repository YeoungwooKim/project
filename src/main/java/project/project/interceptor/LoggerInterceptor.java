package project.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggerInterceptor implements HandlerInterceptor {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static final String HIERACHY_MSG = " Can't access that url because of Hierachy : ROLE_USER";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug(
				"======================================          START         ======================================");
		log.debug(" Request URI \t:  " + request.getRequestURI());
		if(request.getRequestURI().contains("swagger")) {
			if(request.isUserInRole("ROLE_ADMIN")) {
				return true;
			} else {
				log.debug(HIERACHY_MSG);
				response.sendRedirect("/");
				return false;
			}
		}
		
		if(request.getRequestURI().contains("adminPage")) {
			if(request.isUserInRole("ROLE_ADMIN")) {
				return true;
			} else {
				log.debug(HIERACHY_MSG);
				response.sendRedirect("/");
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug(
				"======================================           END          ======================================\n");
	}
}
