package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.CommentDTO;

@Mapper
public interface CommentMapper { //데이터베이스와 통신하기위한 인터페이스입니다. 해당 인터페이스에
	//선언된 메서드들을 db sql문과 매핑하여 실행합니다.
	public int insertComment(CommentDTO params);
	public CommentDTO selectCommentDetail(Long idx);
	public int updateComment(CommentDTO params);
	public int deleteComment(Long idx);
	public List<CommentDTO> selectCommentList(CommentDTO params);
	public int selectCommentTotalCount(CommentDTO params);
}
