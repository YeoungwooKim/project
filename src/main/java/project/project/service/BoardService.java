package project.project.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import project.project.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList(int currentPage, int size) throws Exception;

	void insertBoardList(BoardDto board) throws Exception;

	BoardDto searchBoard(int boardIdx) throws Exception;

	void modifyBoardList(@Valid BoardDto board, int parseInt) throws Exception;

	void delete(int boardIdx) throws Exception;

	List<BoardDto> findBoardByTitle(String title) throws Exception;
	
	int getTotalRecord() throws Exception;


}
