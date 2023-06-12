package com.board.domain;

public class MovieCommentDTO { //database와 연결하여 저장할 변수들을 선언해놓은 클래스이입니다.
//moviecomment 테이블과 연결되며 영화세부페이지에서 해당 영화에 대한 평점이나 리뷰등의 댓글을 다는 데이터베이스입니다.
		private Long idx;
		private Long movieIdx;
		private String content;
		private String writer;
		private String deleteYn;
		private String rating;
		public Long getIdx() {
			return idx;
		}
		public void setIdx(Long idx) {
			this.idx = idx;
		}
		public Long getMovieIdx() {
			return movieIdx;
		}
		public void setMovieIdx(Long movieIdx) {
			this.movieIdx = movieIdx;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
		}
		public String getDeleteYn() {
			return deleteYn;
		}
		public void setDeleteYn(String deleteYn) {
			this.deleteYn = deleteYn;
		}
		public String getRating() {
			return rating;
		}
		public void setRating(String rating) {
			this.rating = rating;
		}
		@Override
		public String toString() {
			return "MovieCommentDTO [idx=" + idx + ", movieIdx=" + movieIdx + ", content=" + content + ", writer="
					+ writer + ", deleteYn=" + deleteYn + ", rating=" + rating + "]";
		}

	}

