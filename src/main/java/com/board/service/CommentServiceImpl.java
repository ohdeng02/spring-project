package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.CommentDTO;
import com.board.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {//데이터가 저장되는 과정에서 추상화과정을 거치는 역할로 기본적으로 필히 사용되는 파일이라고 할 수 있습니다.
	//직접적으로 service를 구현하는 파일입니다. comment테이블과 연관됩니다.
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public boolean registerComment(CommentDTO params) {//게시판의 댓글을 생성하는 메서드로 mapper의 insertComment메서드를 불러와 실행합니다.
		// TODO Auto-generated method stub
		int queryResult = 0;
		if( params.getIdx() == null ) {
			queryResult = commentMapper.insertComment(params);
		} else {
			queryResult = commentMapper.updateComment(params);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteComment(long idx) {//해당게시판의 원하는 댓글을 삭제하는 메서드입니다.
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		CommentDTO comment = commentMapper.selectCommentDetail(idx);
		
		if( comment != null && "N".equals(comment.getDeleteYn())) {
			queryResult = commentMapper.deleteComment(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<CommentDTO> getCommentList(CommentDTO params) {//해당게시판의 댓글목록을 불러오는 메서드로 mapper의 selectCommentList메서드를 불러와 실행합니다.
		// TODO Auto-generated method stub
		List<CommentDTO> commentList = Collections.emptyList();
		
		int commentTotalCount = commentMapper.selectCommentTotalCount(params);
		if( commentTotalCount > 0 ) {
			commentList = commentMapper.selectCommentList(params);
		}
		return commentList;
	}
}
