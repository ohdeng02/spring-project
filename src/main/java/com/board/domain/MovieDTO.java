package com.board.domain;

import java.time.LocalDateTime;

public class MovieDTO extends CommonDTO{//paging을 위해서 commonDTO을 상속받았으며 database와 연결하여 저장할 변수들을 선언해놓은 클래스이입니다.
	//movie테이블과 연결되며 메인페이지에 쓰이는 영화들에 대한 정보를 담고 있는 데이터베이스입니다.
	private Long idx;
	private String title;
	private String posterImg;
	private String description;
	private String director;
	public int viewCnt;
	private String rating;
	private String premierDate;
	private String premierYn;
	private String deleteYn;
	private LocalDateTime insertTime;
	private LocalDateTime updateTime;
	private LocalDateTime deleteTime;
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterImg() {
		return posterImg;
	}
	public void setPosterImg(String posterImg) {
		this.posterImg = posterImg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getPremierDate() {
		return premierDate;
	}
	public void setPremierDate(String premierDate) {
		this.premierDate = premierDate;
	}
	public String getPremierYn() {
		return premierYn;
	}
	public void setPremierYn(String premierYn) {
		this.premierYn = premierYn;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public LocalDateTime getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}
	@Override
	public String toString() {
		return "MovieDTO [idx=" + idx + ", title=" + title + ", posterImg=" + posterImg + ", description=" + description
				+ ", director=" + director + ", viewCnt=" + viewCnt + ", rating=" + rating + ", premierDate="
				+ premierDate + ", premierYn=" + premierYn + ", deleteYn=" + deleteYn + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", deleteTime=" + deleteTime + "]";
	}
	
}
