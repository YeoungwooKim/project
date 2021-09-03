package project.project.service;

import project.project.dto.UserDto;

public interface UserService {

	Boolean login(UserDto user) throws Exception;

}
