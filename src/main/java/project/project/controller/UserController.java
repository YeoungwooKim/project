package project.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.micrometer.core.instrument.util.StringUtils;
import project.project.dto.MailDto;
import project.project.dto.MessageDto;
import project.project.dto.UserDto;
import project.project.service.MailService;
import project.project.service.UserService;

@Controller
public class UserController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@GetMapping("/adminPage")
	public ModelAndView showAdminPage() throws Exception {
		ModelAndView mv = new ModelAndView("project/adminPage");
		mv.addObject("title", "adminPage");
		List<UserDto> list = userService.getUsers();
		mv.addObject("users", list);
		return mv;
	}

	@PostMapping("/disableUser")
	public @ResponseBody String disableUsers(@RequestBody List<String> userList) throws Exception {
		userService.disableUsers(userList);
		return "";
	}

	@PostMapping("/enableUser")
	public @ResponseBody String enableUsers(@RequestBody List<String> userList) throws Exception {
		userService.enableUsers(userList);
		return "";
	}

	@GetMapping("/myMessage")
	public ModelAndView showAllMessage() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		ModelAndView mv = new ModelAndView("project/myMessageHome");
		mv.addObject("msg",
				userService.searchMailBox(false, user.getUsername(), userService.getMsgCount(user.getUsername())));
		return mv;
	}

	@GetMapping("/message")
	public ModelAndView openMessage(@RequestParam(value = "recipient", required = false) String recipient,
			@RequestParam(value = "idx", required = false) String idx) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView("project/message");
		User user = (User) auth.getPrincipal();
		if (StringUtils.isEmpty(recipient) && StringUtils.isEmpty(idx)) {
			mv.addObject("title", "new Message");
		} else if (!StringUtils.isEmpty(recipient) && StringUtils.isEmpty(idx)) {
			mv.addObject("title", "To Msg");
		} else {
			// ?????? ?????????, ?????? ????????? ????????? ?????? ??????
			log.debug(recipient + " : " + user.getUsername());
			if (recipient.equals(user.getUsername())) {
				userService.checkMessage(Integer.parseInt(idx));
				mv.addObject("msg", userService.searchBySenderAndIdx(recipient, Integer.parseInt(idx)));
				mv.addObject("title", "Detail ");
			} else {
				mv.addObject("title", false);
				mv.setViewName("project/msgResult");
			}
		}
		return mv;
	}

	@PostMapping("/emailCheck")
	public @ResponseBody String emailChk(UserDto user) throws Exception {
		ModelAndView mv = new ModelAndView("project/register");
		if (user.getUserEmail().length() > 5) {
			if (userService.isValid(user.getUserEmail())) {
				if (userService.chechkEmailValidation(user.getUserEmail())) {
					MailDto mail = new MailDto(user.getUserEmail());

					if (mailService.mailSend(mail)) { // ?????? ????????? ??????.
						userService.changePassword(user.getUserEmail(), mail.getGeneratedPass());
						userService.addEmailValidationCnt(user.getUserEmail());
						return "i send mail to you. check your emails";
					} else { // ??????????????? ??????
						return "error";
					}
				} else {
					return "?????? ???????????? ??????!!";
				}
			}
		}
		return "invalid email !";
	}

	@PostMapping("/message")
	public ModelAndView postMessage(@Valid MessageDto msg, BindingResult bindingResult) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView();
		User user = (User) auth.getPrincipal();

		if (bindingResult.hasErrors()) {
			// log.debug( " >>>>>> " + bindingResult.getAllErrors());
			mv.setViewName("project/msgResult");
			mv.addObject("title", false);
			throw new Exception(bindingResult.getAllErrors().toString());
		} else {
			userService.postMessage(msg, user.getUsername());
			mv.setViewName("project/msgResult");
			mv.addObject("title", true);
		}

		return mv;
	}

	@DeleteMapping("/message")
	public String deleteMessage(int idx) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();

		userService.deleteMsg(idx, user.getUsername());
		return "redirect:/myMessage";
	}

	@GetMapping("/mypage")
	public ModelAndView myPage() throws Exception {
		ModelAndView mv = new ModelAndView("project/mypage");
		mv.addObject("title", "mypage");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		mv.addObject("email", userService.searchEmail(user.getUsername()));

		mv.addObject("receiveMail", userService.searchMailBox(true, user.getUsername(), 2));
		mv.addObject("msgCount", userService.getMsgCount(user.getUsername()));

		mv.addObject("myBoard", userService.findMyBoard(user.getUsername()));
		// ?????? ????????? ?????? ????????????.
		return mv;
	}

	@PutMapping("/editMyInfo")
	public String editMyInfo(UserDto postedUserValue) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();

		if (postedUserValue.getUserPw().isEmpty()) {
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

	@PostMapping("/isValidUser")
	public @ResponseBody String isValidUser(UserDto user) throws Exception {
		String msg = "";
		if (userService.isDisabled(user).equals("??????????????????")) {
			user = userService.getUser(user);
			if (user.getEmailValidationCnt() > 3)
				msg += " ????????? ?????? ?????? ?????? \n";
			if (user.getEnabled() == 0)
				msg += " ????????? ?????? ?????? ??????.\n";
			msg += " ?????? ?????? ?????? ?????? : " + user.getDisabledDate() + "\n";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date disabledDate = transFormat.parse(user.getDisabledDate());
			cal.setTime(disabledDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			cal.add(Calendar.DATE, +3);
			msg += " ?????? ?????? ?????? ?????? ?????? : " + df.format(cal.getTime()) + "\n";
			// ????????? ??????????????? ?????? ????????? disable????????? ?????? ????????????. 3??? ????????? ?????? ??????. [??????]
		}
		return msg;
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

	@PostMapping("/idCheck")
	public @ResponseBody String idCheck(UserDto user) throws Exception {
		if (userService.checkUser(user)) {
			return "you can use it!";
		} else {
			return "you can't use it!";
		}
	}

	@PostMapping("/register")
	public String register(UserDto user) throws Exception {
		ModelAndView mv = new ModelAndView("project/register");
		if (userService.checkUser(user)) {
			userService.saveUser(user);
			mv.addObject("user", user);
			return "redirect:/";
		} else {
			return "redirect:/register";
		}

	}
}
