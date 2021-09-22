package project.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.project.dto.GameDto;
import project.project.mapper.GameMapper;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameMapper gameMapper;
	
	@Override
	public List<GameDto> showList() throws Exception {
		return gameMapper.showList();
	}

	@Override
	public String searchByIdx(int idx) throws Exception {
		return gameMapper.searchByIdx(idx);
	}

}
