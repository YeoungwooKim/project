package project.project.service;

import java.util.List;

import project.project.dto.GameDto;
import project.project.dto.RankingDto;

public interface GameService {
	List<GameDto> showList() throws Exception;

	String searchByIdx(int idx) throws Exception;

	void saveRank(RankingDto rank) throws Exception;

	void hitCnt(int idx) throws Exception;
	
}
