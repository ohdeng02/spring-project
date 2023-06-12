package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.MovieDTO;
import com.board.mapper.MovieMapper;
import com.board.paging.PaginationInfo;

@Service
public class MovieServiceImpl implements MovieService{
	//데이터가 저장되는 과정에서 추상화과정을 거치는 역할로 기본적으로 필히 사용되는 파일이라고 할 수 있습니다.
		//직접적으로 service를 구현하는 파일입니다. movie테이블과 연관됩니다.
	@Autowired
	private MovieMapper movieMapper;
	
	@Override
	public boolean registerMovie(MovieDTO params) {//영화정보를 생성하는 메서드로 mapper의 insertMovie메서드를 불러와 실행합니다.
		int queryResult = 0;
		
		if(params.getIdx() == null) {
			queryResult = movieMapper.insertMovie(params);
		} else {
			queryResult = movieMapper.updateMovie(params);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public MovieDTO getMovieDetail(long idx) {//영화정보를 자세히 볼때 불러오는 메서드로 mapper의 selectMovieDetail메서드를 불러와 실행합니다.
		return movieMapper.selectMovieDetail(idx);
	}

	@Override
	public boolean deleteMovie(long idx) {//게시판글을 삭제할때 사용하는 메서드로 mapper의 해당하는 메서드를 불러와 실행합니다.
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		MovieDTO movie = movieMapper.selectMovieDetail(idx);
		
		if (movie != null && "N".equals(movie.getDeleteYn())) {
			queryResult = movieMapper.deleteMovie(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<MovieDTO> getMovieList(MovieDTO params) {//영화 목록을 조회하는 메서드입니다. paginationInfo파일을 불러와서
		//페이징한 화면을 띄울 수 있도록 변수를 params에 담아 전달해줍니다.
		// TODO Auto-generated method stub
		List<MovieDTO> movieList = Collections.emptyList();
		
		int movieTotalCount = movieMapper.selectMovieTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(movieTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(movieTotalCount > 0) {
			movieList = movieMapper.selectMovieList(params);
		}
		return movieList;
	}
	
	@Override
	public int selectMovieTotalCount(MovieDTO params) {//총 등록된 영화수를 조회하는 메서드입니다.
		int movieTotalCount = movieMapper.selectMovieTotalCount(params);
		return movieTotalCount;
	}
}
