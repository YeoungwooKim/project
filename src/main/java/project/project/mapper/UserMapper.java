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

	void editEmail(@Param("username") String username, @Param("userEmail")  String userEmail) throws Exception;

	List<MessageDto> searchMailBox(@Param("username") String username, @Param("cnt") int cnt) throws Exception;

	int getMsgCount(String username) throws Exception;

	List<BoardDto> findMyBoard(String username) throws Exception;
}
