package project.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.project.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception;

	void insertBoardList(BoardDto board) throws Exception;

	BoardDto searchBoard(int boardIdx) throws Exception;

	void modifyBoardList(BoardDto board) throws Exception;
}
