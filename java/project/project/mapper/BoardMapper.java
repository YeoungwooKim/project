package project.project.mapper;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.project.dto.BoardDto;
import project.project.dto.CommentDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList(@Param("currentPage") int currentPage, @Param("size") int size) throws Exception;

	void insertBoardList(BoardDto board) throws Exception;

	BoardDto searchBoard(int boardIdx) throws Exception;

	void modifyBoardList(@Param("board") @Valid BoardDto board, @Param("boardIdx") int boardIdx) throws Exception;

	void delete(@Param("boardIdx") int boardIdx) throws Exception;

	int getTotalRecord() throws Exception;

	List<BoardDto> findBoardByTitle(@Param("title") String title) throws Exception;

	String findCreator(@Param("boardIdx") int boardIdx) throws Exception;

	void addHitCnt(int boardIdx) throws Exception;

	List<BoardDto> searchByCreator(String creatorId) throws Exception;

	void addComment(CommentDto comment) throws Exception;
	
	List<CommentDto> searchComment(int boardIdx) throws Exception;

	void deleteComment(CommentDto comment) throws Exception;
}
