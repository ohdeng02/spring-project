package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.MovieDTO;

@Mapper
public interface MovieMapper {//데이터베이스와 통신하기위한 인터페이스입니다. 해당 인터페이스에
	//선언된 메서드들을 db sql문과 매핑하여 실행합니다.
	
	public int insertMovie(MovieDTO params);
	public MovieDTO selectMovieDetail(long idx);
	public int updateMovie(MovieDTO params);
	public int deleteMovie(long idx);
	public List<MovieDTO> selectMovieList(MovieDTO params);
	public int selectMovieTotalCount(MovieDTO params);
}
