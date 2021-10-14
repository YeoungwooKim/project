package project.configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import project.project.dto.UserDto;
import project.project.service.UserService;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userservice;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		UserDto user = new UserDto();
		user.setUserId(request.getParameter("username"));
		String msg = "";

		if (exception instanceof BadCredentialsException) { // 아이디나 비밀번호 틀림
			try {
				if (!userservice.checkUser(user))
					userservice.disableCnt(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			msg += "wrong id or pw";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			// 내부 문제
			msg += "internal err";
		} else if (exception instanceof LockedException) {
			// 계정 잠김
			msg += "account is locked";
		}
		try {
			if (userservice.checkDisableCnt(user))
				userservice.disable(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/login");
	}

}
