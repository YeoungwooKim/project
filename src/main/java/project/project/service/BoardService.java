package project.project.service;

import java.util.List;

import project.project.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList() throws Exception;

}
