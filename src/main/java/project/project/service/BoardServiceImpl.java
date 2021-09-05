package project.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.project.dto.BoardDto;
import project.project.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoardList(BoardDto board) throws Exception {
		boardMapper.insertBoardList(board);
	}

	@Override
	public BoardDto searchBoard(int boardIdx) throws Exception {
		return boardMapper.searchBoard(boardIdx);
	}

	@Override
	public void modifyBoardList(BoardDto board) throws Exception {
		boardMapper.modifyBoardList(board);
	}

}
