package project.project.service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.project.dto.BoardDto;
import project.project.dto.MessageDto;
import project.project.dto.UserDto;
import project.project.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void saveUser(UserDto user) throws Exception {
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		//roles 세팅
		userMapper.saveUser(user);
		userMapper.saveRole(user);
	}

	@Override
	public String searchEmail(String username) throws Exception {
		return userMapper.searchEmail(username);
	}

	@Override
	public void editInfo(String username, UserDto postedUserValue) throws Exception {
		postedUserValue.setUserPw(passwordEncoder.encode(postedUserValue.getUserPw()));
		userMapper.editInfo(username, postedUserValue);
	}

	@Override
	public void editEmail(String username, String userEmail) throws Exception {
		userMapper.editEmail(username, userEmail);		
	}

	@Override
	public List<MessageDto> searchMailBox(String username, int cnt) throws Exception {
		return userMapper.searchMailBox(username, cnt);
	}

	@Override
	public int getMsgCount(String username) throws Exception {
		return userMapper.getMsgCount(username);
	}

	@Override
	public List<BoardDto> findMyBoard(String username) throws Exception {
		return userMapper.findMyBoard(username);
	}

}
