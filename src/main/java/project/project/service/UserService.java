package project.project.service;

import java.util.List;

import project.project.dto.BoardDto;
import project.project.dto.MessageDto;
import project.project.dto.UserDto;

public interface UserService {

	void saveUser(UserDto user) throws Exception;

	String searchEmail(String username) throws Exception;

	void editInfo(String username, UserDto postedUserValue) throws Exception;

	void editEmail(String username, String userEmail) throws Exception;

	List<MessageDto> searchMailBox(String username, int cnt) throws Exception;

	int getMsgCount(String username) throws Exception;

	List<BoardDto> findMyBoard(String username) throws Exception;

}
