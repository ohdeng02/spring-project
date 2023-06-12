//package com.board;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.CollectionUtils;
//
//import com.board.domain.MovieDTO;
//import com.board.mapper.MovieMapper;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//@SpringBootTest
//public class MovieTests {
//	
//	@Autowired
//	private MovieMapper movieMapper;
//	
//	@Test
//	public void testOfInsert() {
//		MovieDTO params = new MovieDTO();
//		params.setTitle("블랙팬서");
//		params.setDescription("와칸다를 지켜라!");
//		params.setPosterImg("/images/black.jpg");
//		params.setDirector("라이언 쿠글러");
//		params.setViewCnt("800000");
//		params.setRating("7.37");
//		params.setPremierDate("2022.11.09");
//		params.setPremierYn("Y");		
//		int result = movieMapper.insertMovie(params);
//		System.out.println("결과는 " + result + "입니다.");
//	}
//	
//	@Test
//	public void testOfSelectDetail() {
//		MovieDTO movie = movieMapper.selectMovieDetail((long) 1);
//		try {
//			String movieJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(movie);
//			System.out.println("==========================");
//			System.out.println(movieJson);
//			System.out.println("==========================");
//		} catch (JsonProcessingException e) {
//			// TODO: handle exception
//		}
//	}
//	
//	@Test
//	public void testOfUpdate() {
//		MovieDTO params = new MovieDTO();
//		params.setTitle("1번 게시글 제목을 수정합니다.");
//		params.setDescription("수정함");
//		params.setPosterImg("/images/black.jpg");
//		params.setDirector("라이언 쿠글러");
//		params.setViewCnt(800000);
//		params.setRating(7.37);
//		params.setPremierYn("Y");	
//		params.setIdx((long)1);
//		
//		int result = movieMapper.updateMovie(params);
//		if (result == 1) {
//			MovieDTO movie = movieMapper.selectMovieDetail((long) 1);
//			try {
//				String movieJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(movie);
//				System.out.println("=============================");
//				System.out.println(movieJson);
//				System.out.println("=============================");
//			} catch (JsonProcessingException e) {
//				// TODO: handle exception
//			}
//		}
//	}
//	
//	@Test
//	public void testOfDelete() {
//		int result = movieMapper.deleteMovie((long) 1);
//		if (result == 1) {
//			MovieDTO movie = movieMapper.selectMovieDetail((long) 1);
//			try {
//				String movieJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(movie);
//				
//				System.out.println("=========================");
//				System.out.println(movieJson);
//				System.out.println("=========================");
//			} catch (JsonProcessingException e) {
//				// TODO: handle exception
//			}
//		}
//	}
//	
//	@Test
//	public void testSelectList() {
//		//MovieDTO params = new MovieDTO();
//		int movieTotalCount = movieMapper.selectMovieTotalCount();
//		if(movieTotalCount > 0) {
//			List<MovieDTO> movieList = movieMapper.selectMovieList();
//			if(CollectionUtils.isEmpty(movieList) == false) {
//				for(MovieDTO movie : movieList) {
//					System.out.println("=========================");
//					System.out.println(movie.getTitle());
//					System.out.println(movie.getDescription());
//					System.out.println(movie.getPosterImg());
//					System.out.println(movie.getDirector());
//					System.out.println(movie.getViewCnt());
//					System.out.println(movie.getRating());
//					System.out.println(movie.getPremierDate());
//					System.out.println(movie.getPremierYn());
//					System.out.println("=========================");
//				}
//			}
//		}
//	}
//}
