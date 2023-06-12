package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.MovieCommentDTO;
import com.board.service.MovieCommentService;

@SpringBootTest
public class MovieCommentTests {
	@Autowired
	private MovieCommentService movieCommentService;
	
	@Test
	public void registerMovieComments() {
		int number = 20;
		
		for( int i = 1; i <= number; i++ ) {
			MovieCommentDTO params = new MovieCommentDTO();
			params.setMovieIdx((long) 1);
			params.setContent(i + "번 댓글을 추가합니다!");
			params.setWriter(i + "번 회원");
			params.setRating("8.8");
			movieCommentService.registerMovieComment(params);
		}
		System.out.println("댓글 " + number + "개가 등록되었습니다.");
	}
	
	@Test
	public void deleteMovieComment() {
		movieCommentService.deleteMovieComment((long) 12);
		getMovieCommentList();
	}
	
	@Test
	public void getMovieCommentList() {
		MovieCommentDTO params = new MovieCommentDTO();
		params.setMovieIdx((long) 1);
		
		for( MovieCommentDTO movieComment : movieCommentService.getMovieCommentList(params) ) {
			System.out.println("==================");
			System.out.println(movieComment.getMovieIdx());
			System.out.println(movieComment.getIdx());
			System.out.println(movieComment.getContent());
			System.out.println(movieComment.getWriter());
			System.out.println(movieComment.getRating());
			System.out.println("==================");
		}
	}
}
