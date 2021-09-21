package project.project.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.project.dto.CommentDto;
import project.project.service.BoardService;

@Controller
public class CommentController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/commentAJAX", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String commentPostAjax(@RequestBody Map<String, Object> param, Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		ObjectMapper objectMapper = new ObjectMapper();
		String url = param.get("url").toString();
		String dtoJson = objectMapper.writeValueAsString(param.get("commentDto"));
		CommentDto comment = objectMapper.readValue(dtoJson, CommentDto.class);
		comment.setCreatorId(user.getUsername());
        String[] boardIdx = url.split("=");
		
		boardService.addComment(comment);
		
		log.debug("url : " + url);
		log.debug("comment : " + comment.getBoardIdx() + " / " + comment.getContents());
		
		model.addAttribute("comment", boardService.searchComment(Integer.parseInt(boardIdx[boardIdx.length-1])));
		
		
		return url + " :: #resultDiv";
	}

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
