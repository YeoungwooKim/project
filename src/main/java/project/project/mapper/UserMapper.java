package project.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.project.dto.BoardDto;
import project.project.dto.MessageDto;
import project.project.dto.UserDto;

@Mapper
public interface UserMapper {
	void saveUser(UserDto user) throws Exception;

	void saveRole(UserDto user) throws Exception;

	String searchEmail(String username) throws Exception;

	void editInfo(@Param("username") String username, @Param("user") UserDto postedUserValue) throws Exception;

	void editEmail(@Param("username") String username, @Param("userEmail") String userEmail) throws Exception;

	List<MessageDto> searchMailBox(@Param("tf") boolean tf, @Param("username") String username, @Param("cnt") int cnt)
			throws Exception;

	int getMsgCount(String username) throws Exception;

	List<BoardDto> findMyBoard(String username) throws Exception;

	void checkMessage(@Param("idx") int idx) throws Exception;

	MessageDto searchBySenderAndIdx(@Param("recipient") String recipient, @Param("idx") int idx) throws Exception;

	void postMessage(@Param("msg") MessageDto msg, @Param("username") String username) throws Exception;

	void deleteMsg(@Param("idx") int idx, @Param("username") String username) throws Exception;

	boolean checkUser(UserDto user) throws Exception;

	boolean isValid(String email) throws Exception;

	void changePassword(@Param("email") String userEmail, @Param("pass") String generatedPass) throws Exception;

	boolean chechkEmailValidation(String userEmail) throws Exception;

	void addEmailValidationCnt(String userEmail) throws Exception;

	void disableCnt(UserDto user) throws Exception;

	boolean checkDisableCnt(UserDto user) throws Exception;

	void disable(UserDto user) throws Exception;

	String isDisabled(UserDto user) throws Exception;

	String getEmail(String userEmail) throws Exception;

	UserDto getUser(UserDto user) throws Exception;

	List<UserDto> getUsers() throws Exception;

	void disableUsers(List<String> userList) throws Exception;

	void enableUsers(List<String> userList) throws Exception;

}
