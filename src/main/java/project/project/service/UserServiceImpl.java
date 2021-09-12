package project.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.project.dto.UserDto;
import project.project.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public Boolean login(UserDto user) throws Exception {
		return userMapper.login(user);
	}

	@Override
	public void saveUser(UserDto user) throws Exception {
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		//roles 세팅
		userMapper.saveUser(user);
	}

}
