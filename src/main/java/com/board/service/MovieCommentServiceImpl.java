package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.MovieCommentDTO;
import com.board.mapper.MovieCommentMapper;

@Service
public class MovieCommentServiceImpl implements MovieCommentService {//데이터가 저장되는 과정에서 추상화과정을 거치는 역할로 기본적으로 필히 사용되는 파일이라고 할 수 있습니다.
	//직접적으로 service를 구현하는 파일입니다. moviecomment테이블과 연관됩니다.
	
	@Autowired
	private MovieCommentMapper movieCommentMapper;
	
	@Override
	public boolean registerMovieComment(MovieCommentDTO params) {//영화의 댓글을 생성하는 메서드로 mapper의 insertMovieComment메서드를 불러와 실행합니다.
		// TODO Auto-generated method stub
		int queryResult = 0;
		if( params.getIdx() == null ) {
			queryResult = movieCommentMapper.insertMovieComment(params);
		} else {
			queryResult = movieCommentMapper.updateMovieComment(params);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteMovieComment(long idx) {//해당영화의 원하는 댓글을 삭제하는 메서드입니다.
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		MovieCommentDTO movieComment = movieCommentMapper.selectMovieCommentDetail(idx);
		
		if( movieComment != null && "N".equals(movieComment.getDeleteYn())) {
			queryResult = movieCommentMapper.deleteMovieComment(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<MovieCommentDTO> getMovieCommentList(MovieCommentDTO params) {//해당영화의 댓글목록을 불러오는 메서드로 mapper의 selectMovieCommentList메서드를 불러와 실행합니다.
		// TODO Auto-generated method stub
		List<MovieCommentDTO> movieCommentList = Collections.emptyList();
		
		int movieCommentTotalCount = movieCommentMapper.selectMovieCommentTotalCount(params);
		if( movieCommentTotalCount > 0 ) {
			movieCommentList = movieCommentMapper.selectMovieCommentList(params);
		}
		return movieCommentList;
	}
}

