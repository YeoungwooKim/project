package project.project.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.project.dto.BoardDto;
import project.project.dto.CommentDto;
import project.project.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> selectBoardList(int currentPage, int size) throws Exception {
		return boardMapper.selectBoardList(currentPage, size);
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
	public void modifyBoardList(@Valid BoardDto board, int boardIdx) throws Exception {
		boardMapper.modifyBoardList(board, boardIdx);
	}

	@Override
	public void delete(int boardIdx) throws Exception {
		boardMapper.delete(boardIdx);
	}

	@Override
	public int getTotalRecord() throws Exception {
		return boardMapper.getTotalRecord();
	}

	@Override
	public List<BoardDto> findBoardByTitle(String title) throws Exception {
		return boardMapper.findBoardByTitle(title);
	}

	@Override
	public String findCreator(int boardIdx) throws Exception {
		return boardMapper.findCreator(boardIdx);
	}

	@Override
	public void addHitCnt(int boardIdx) throws Exception {
		boardMapper.addHitCnt(boardIdx);
	}

	@Override
	public List<BoardDto> searchByCreator(String creatorId) throws Exception {
		return boardMapper.searchByCreator(creatorId);
	}

	@Override
	public void addComment(CommentDto comment) throws Exception {
		boardMapper.addComment(comment);
	}

	@Override
	public List<CommentDto> searchComment(int boardIdx) throws Exception {
		return boardMapper.searchComment(boardIdx);
	}
}
