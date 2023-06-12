package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.MovieCommentDTO;

@Mapper
public interface MovieCommentMapper {//데이터베이스와 통신하기위한 인터페이스입니다. 해당 인터페이스에
	//선언된 메서드들을 db sql문과 매핑하여 실행합니다.
	public int insertMovieComment(MovieCommentDTO params);
	public MovieCommentDTO selectMovieCommentDetail(Long idx);
	public int updateMovieComment(MovieCommentDTO params);
	public int deleteMovieComment(Long idx);
	public List<MovieCommentDTO> selectMovieCommentList(MovieCommentDTO params);
	public int selectMovieCommentTotalCount(MovieCommentDTO params);
}

