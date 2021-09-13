package project.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.project.dto.UserDto;

@Mapper
public interface UserMapper {
	Boolean login(UserDto user) throws Exception;

	void saveUser(UserDto user) throws Exception;
	
	void saveRole(UserDto user) throws Exception;
}
