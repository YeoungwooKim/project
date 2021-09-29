package project.project.service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.project.dto.BoardDto;
import project.project.dto.MessageDto;
import project.project.dto.UserDto;
import project.project.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	@Override
	public void saveUser(UserDto user) throws Exception {
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		// roles 세팅
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
	public List<MessageDto> searchMailBox(boolean tf, String username, int cnt) throws Exception {
		return userMapper.searchMailBox(tf, username, cnt);
	}

	@Override
	public int getMsgCount(String username) throws Exception {
		return userMapper.getMsgCount(username);
	}

	@Override
	public List<BoardDto> findMyBoard(String username) throws Exception {
		return userMapper.findMyBoard(username);
	}

	@Override
	public void checkMessage(int idx) throws Exception {
		userMapper.checkMessage(idx);
	}

	@Override
	public MessageDto searchBySenderAndIdx(String recipient, int idx) throws Exception {
		return userMapper.searchBySenderAndIdx(recipient, idx);
	}

	@Override
	public void postMessage(MessageDto msg, String username) throws Exception {
		userMapper.postMessage(msg, username);
	}

	@Override
	public void deleteMsg(int idx, String username) throws Exception {
		userMapper.deleteMsg(idx, username);
	}

	@Override
	public boolean checkUser(UserDto user) throws Exception {
		return userMapper.checkUser(user);
	}

	@Override
	public boolean isValid(String email) throws Exception {
		return userMapper.isValid(email);
	}

	@Override
	public void changePassword(String userEmail, String generatedPass) throws Exception {
		userMapper.changePassword(userEmail, passwordEncoder.encode(generatedPass));
	}

	@Override
	public boolean chechkEmailValidation(String userEmail) throws Exception {
		return userMapper.chechkEmailValidation(userEmail);
	}

	@Override
	public void addEmailValidationCnt(String userEmail) throws Exception {
		userMapper.addEmailValidationCnt(userEmail);
	}

	@Override
	public void disableCnt(UserDto user) throws Exception {
		userMapper.disableCnt(user);
	}

	@Override
	public boolean checkDisableCnt(UserDto user) throws Exception {
		return userMapper.checkDisableCnt(user);
	}

	@Override
	public void disable(UserDto user) throws Exception {
		userMapper.disable(user);
	}

	@Override
	public boolean isDisabled(UserDto user) throws Exception {
		return userMapper.isDisabled(user);
	}

}
