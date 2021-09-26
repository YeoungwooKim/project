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

	List<MessageDto> searchMailBox(boolean tf, String username, int cnt) throws Exception;

	int getMsgCount(String username) throws Exception;

	List<BoardDto> findMyBoard(String username) throws Exception;

	void checkMessage(int idx) throws Exception;

	MessageDto searchBySenderAndIdx(String recipient, int idx) throws Exception;

	void postMessage(MessageDto msg, String username) throws Exception;

	void deleteMsg(int idx, String username) throws Exception;

	boolean checkUser(UserDto user) throws Exception;

	boolean isValid(String email) throws Exception;

	void changePassword(String userEmail, String generatedPass) throws Exception;

	boolean chechkEmailValidation(String userEmail) throws Exception;

	void addEmailValidationCnt(String userEmail) throws Exception;

}
