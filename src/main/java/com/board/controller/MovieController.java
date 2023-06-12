package com.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.domain.MovieDTO;
import com.board.service.MovieService;
import com.board.util.UiUtils;

@Controller
public class MovieController extends UiUtils{
	//사용자의 요청/응답을 처리하는 컨트롤러 클래스입니다.
		//선언해둔 value값이 mapping되는 경로이며 데이터를 불러올경우 get, 데이터를 전송할 경우 post를 사용합니다.
		//service파일의 메서드를 불러와서 db와 연결됩니다.
	//페이지가 넘어갈때 alert창에 뜨는 문구와 정보들을 설정하는데 사용하도록 UiUtils클래스를 상속받습니다
	@Autowired
	private MovieService movieService;
	
	@GetMapping(value = "/movie/write.do")
	public String openMovieWrite(@ModelAttribute("params") MovieDTO params, @RequestParam(value="idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("movie", new MovieDTO());
		} else {
			MovieDTO movie = movieService.getMovieDetail(idx);
			if(movie == null || "Y".equals(movie.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/movie/list.do", Method.GET, null, model);
			}
			model.addAttribute("movie", movie);
		}
		return "movie/write";
	}//영화정보를 작성하는 페이지로 매핑하는 메서드입니다. 
	
	@PostMapping(value = "/movie/register.do")//영화정보를 작성한 데이터를 서버로 넘겨서 저장하도록하는 메서드입니다.
	public String registerMovie(@ModelAttribute("params") final MovieDTO params, Model model) {
		params.setViewCnt(Integer.valueOf(params.viewCnt));
		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isRegistered = movieService.registerMovie(params);
			if( isRegistered == false ) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/movie/list.do", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			// TODO: handle exception
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/movie/list.do", Method.GET, pagingParams, model);
		} catch (Exception e) {
			// TODO: handle exception
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/movie/list.do", Method.GET, pagingParams, model);
		}
		
		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/movie/list.do", Method.GET, pagingParams, model);
	}
	
	@GetMapping(value = "/movie/list.do")//영화정보 전체 목록을 조회하는 메서드입니다.
	public String openMovieList(@ModelAttribute("params") MovieDTO params, Model model) {
		List<MovieDTO> movieList = movieService.getMovieList(params);
		model.addAttribute("movieList", movieList);
		//System.out.println(movieList);
		return "movie/list";
	}
	
	@GetMapping(value = "/movie/view.do")//각 영화정보의 자세한 정보를 조회하는 페이지로 매핑되는 메서드입니다.
	public String openMovieDetail(@ModelAttribute("params") MovieDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if( idx == null ) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/movie/list.do", Method.GET, null, model);
		}
		
		MovieDTO movie = movieService.getMovieDetail(idx);
		
		if( movie == null || "Y".equals(movie.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/movie/list.do", Method.GET, null, model);
		}
		model.addAttribute("movie", movie);
		
		return "movie/view";
	}
	
	@PostMapping(value = "/movie/delete.do")//원하는 영화정보를 삭제하였을때 해당 데이터를 서버로 넘겨서 db내용을 바꾸도록 하는 매핑 메서드입니다.
	public String deleteMovie(@ModelAttribute("params") MovieDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if( idx == null ) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/movie/list.do", Method.GET, null, model);
		}
		Map<String, Object> pagingParams = getPagingParams(params);
		
		try {
			boolean isDeleted = movieService.deleteMovie(idx);
			if( isDeleted == false ) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/movie/list.do", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			// TODO: handle exception
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/movie/list.do", Method.GET, pagingParams, model);
		} catch (Exception e) {
			// TODO: handle exception
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/movie/list.do", Method.GET, pagingParams, model);
		}
		
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/movie/list.do", Method.GET, pagingParams, model);
	}
	
	@GetMapping("/display")//로컬로부터 이미지를 불러와서 해당 경로로 매핑하여 이미지를 띄울 수 있도록 controller를 거치도록 설정한 것입니다.
	public ResponseEntity<Resource> display(@RequestParam("posterImg") String posterImg) {
		String path = "C:\\Temp\\images\\"; //로컬에 이미지가 있는 경로
		String folder = "";
		Resource resource = new FileSystemResource(path + folder + posterImg);
		if(!resource.exists()) 
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		try{
			filePath = Paths.get(path + folder + posterImg);
			header.add("Content-type", Files.probeContentType(filePath));//content-type을 사용하여 해당 객체의 형태 선언해줍니다.(파일)
		}catch(IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);//resource모듈을 사용해서 해당 이미지를 출력할 수 있도록 도와줍니다.
	}
	
}
