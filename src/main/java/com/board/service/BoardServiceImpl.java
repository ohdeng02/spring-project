package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

@Service
public class BoardServiceImpl implements BoardService {//데이터가 저장되는 과정에서 추상화과정을 거치는 역할로 기본적으로 필히 사용되는 파일이라고 할 수 있습니다.
	//직접적으로 service를 구현하는 파일입니다. board테이블과 연관됩니다.
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardDTO params) {//게시판글을 생성하는 메서드로 mapper의 insertBoard메서드를 불러와 실행합니다.
		int queryResult = 0;
		
		if(params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public BoardDTO getBoardDetail(long idx) {//게시판글을 자세히 볼때 불러오는 메서드로 mapper의 selectBoardDetail메서드를 불러와 실행합니다.
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(long idx) {//게시판글을 삭제할때 사용하는 메서드로 mapper의 해당하는 메서드를 불러와 실행합니다.
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		
		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {//게시판글 목록을 조회하는 메서드입니다. paginationInfo파일을 불러와서
		//페이징한 화면을 띄울 수 있도록 변수를 params에 담아 전달해줍니다.
		// TODO Auto-generated method stub
		List<BoardDTO> boardList = Collections.emptyList();
		
		int boardTotalCount = boardMapper.selectBoardTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(boardTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList(params);
		}
		return boardList;
	}
}
