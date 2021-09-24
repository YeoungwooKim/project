package project.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.project.dto.GameDto;
import project.project.dto.RankingDto;
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

	@Override
	public void saveRank(RankingDto rank) throws Exception {
		gameMapper.saveRank(rank);
	}

	@Override
	public void hitCnt(int idx) throws Exception {
		gameMapper.hitCnt(idx);
	}

	@Override
	public List<RankingDto> makeRank(int idx) throws Exception {
		return gameMapper.makeRank(idx);
	}

	@Override
	public boolean isValid(int idx) throws Exception {
		return gameMapper.isValid(idx);
	}

	@Override
	public List<HashMap<Integer, String>> getTitles() throws Exception {
		return gameMapper.getTitles();
	}


}
