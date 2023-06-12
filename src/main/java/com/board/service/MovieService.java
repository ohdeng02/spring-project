package com.board.service;

import java.util.List;

import com.board.domain.MovieDTO;

public interface MovieService {//데이터가 저장되는 과정에서 추상화과정을 거치는 역할로 기본적으로 필히 사용되는 파일이라고 할 수 있습니다.
	//직접 service를 구현하기 전에 함수들만 선언하여 두는 파일입니다.
	public boolean registerMovie(MovieDTO params);
	public MovieDTO getMovieDetail(long idx);
	public boolean deleteMovie(long idx);
	public List<MovieDTO> getMovieList(MovieDTO params);
	public int selectMovieTotalCount(MovieDTO params);
}
