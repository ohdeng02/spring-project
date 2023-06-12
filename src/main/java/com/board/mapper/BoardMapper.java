package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper { //데이터베이스와 통신하기위한 인터페이스입니다. 해당 인터페이스에
								//선언된 메서드들을 db sql문과 매핑하여 실행합니다.
	public int insertBoard(BoardDTO params);
	public BoardDTO selectBoardDetail(long idx);
	public int updateBoard(BoardDTO params);
	public int deleteBoard(long idx);
	public List<BoardDTO> selectBoardList(BoardDTO params);
	public int selectBoardTotalCount(BoardDTO params);
}
