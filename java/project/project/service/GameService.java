package project.project.service;

import java.util.List;

import project.project.dto.GameDto;

public interface GameService {
	List<GameDto> showList() throws Exception;

	String searchByIdx(int idx) throws Exception;
	
}
