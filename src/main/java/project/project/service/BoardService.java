package project.project.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import project.project.dto.BoardDto;
import project.project.dto.CommentDto;

public interface BoardService {

	List<BoardDto> selectBoardList(int currentPage, int size) throws Exception;

	void insertBoardList(BoardDto board) throws Exception;

	BoardDto searchBoard(int boardIdx) throws Exception;

	void modifyBoardList(@Valid BoardDto board, int parseInt) throws Exception;

	void delete(int boardIdx) throws Exception;

	int getTotalRecord() throws Exception;

	List<BoardDto> findBoardByTitle(String title) throws Exception;

	String findCreator(int boardIdx) throws Exception;

	void addHitCnt(int boardIdx) throws Exception;

	List<BoardDto> searchByCreator(String creatorId) throws Exception;

	void addComment(CommentDto comment) throws Exception;
	
	List<CommentDto> searchComment(int boardIdx) throws Exception;
}
