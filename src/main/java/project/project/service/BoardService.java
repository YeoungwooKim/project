package project.project.service;

import java.util.List;

import project.project.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList() throws Exception;

	void insertBoardList(BoardDto board) throws Exception;

	BoardDto searchBoard(int boardIdx) throws Exception;

	void modifyBoardList(BoardDto board) throws Exception;

}
