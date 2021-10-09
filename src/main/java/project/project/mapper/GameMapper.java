package project.project.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import project.project.dto.GameDto;
import project.project.dto.RankingDto;

@Mapper
public interface GameMapper {
	
	List<GameDto> showList() throws Exception;

	String searchByIdx(int idx) throws Exception;

	void saveRank(RankingDto rank) throws Exception;
	
	void hitCnt(int idx) throws Exception;

	List<RankingDto> makeRank(int idx) throws Exception;

	boolean isValid(int idx) throws Exception;

	List<HashMap<String, String>> getTitles() throws Exception;

	List<Integer> getIdxes() throws Exception;


}
