package project.project.controller;

import java.net.BindException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.project.dto.CommentDto;
import project.project.dto.MessageDto;
import project.project.service.BoardService;

@Controller
public class CommentController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;

	@PostMapping("/comment")
	public String commentPost(@Valid CommentDto comment, BindingResult bindingResult) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		comment.setCreatorId(user.getUsername());

		if (bindingResult.hasErrors()) { 
			throw new Exception(bindingResult.getAllErrors().toString());
		} else {
			boardService.addComment(comment); 
		}

		return "redirect:/board/write?id=" + comment.getBoardIdx();
	}

	@DeleteMapping("/comment")
	public String deleteMsg(CommentDto comment) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		if (comment.getCreatorId().equals(user.getUsername())) {
			boardService.deleteComment(comment);
		} else if (user.getUsername().equals("root")) {
			boardService.deleteComment(comment);
		}
		return "redirect:/board/write?id=" + comment.getBoardIdx();
	}
}
