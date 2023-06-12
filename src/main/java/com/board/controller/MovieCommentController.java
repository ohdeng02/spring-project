package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.MovieCommentDTO;
import com.board.service.MovieCommentService;
import com.google.gson.JsonObject;

@RestController
public class MovieCommentController {//사용자의 요청/응답을 처리하는 컨트롤러 클래스입니다.
	//선언해둔 value값이 mapping되는 경로이며 데이터를 불러올경우 get, 데이터를 전송할 경우 post를 사용합니다.
	//service파일의 메서드를 불러와서 db와 연결됩니다.

	@Autowired
	private MovieCommentService movieCommentService;
	
	@RequestMapping(value = { "/movieComments", "/movieComments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final MovieCommentDTO params) {
		JsonObject jsonObj = new JsonObject();
		
		try {
			boolean isRegistered = movieCommentService.registerMovieComment(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}//영화의 댓글을 생성하는 페이지와 매핑되는 메서드입니다.

	@GetMapping(value = "/movieComments/{movieIdx}")
	public List<MovieCommentDTO> getMovieCommentList(@PathVariable("movieIdx") Long movieIdx, @ModelAttribute("params") MovieCommentDTO params) {
		List<MovieCommentDTO> movieCommentList = movieCommentService.getMovieCommentList(params);
		return movieCommentList;
	}//영화의 댓글을 조회하는 페이지와 매핑되는 메서드입니다.

}