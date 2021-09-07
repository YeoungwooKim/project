package project.project.mapper;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.project.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception;

	void insertBoardList(BoardDto board) throws Exception;

	BoardDto searchBoard(int boardIdx) throws Exception;

	void modifyBoardList(@Param("board") @Valid BoardDto board, @Param("boardIdx") int boardIdx) throws Exception;

	void delete(@Param("boardIdx") int boardIdx) throws Exception;
}
