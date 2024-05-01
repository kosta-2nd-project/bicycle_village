package group2.bicycle_village.common.dto;

import java.util.Date;

public class PostDTO {
	private int boardSeq; 
	private String boardName; //게시글 제목
	private String category; //카테고리
	private Date regDate; //작성일
	private int boardCount; //조회수
	private int commentCount; //댓글수
	private int userSeq;
	private String imageName; //이미지 파일 이름
	private int saveNumber;
	private int goodsPrice;
	
	public PostDTO(int boardSeq, String boardName, String category, Date regDate, int boardCount, int commentCount
			,String imageName, int saveNumber) {
		super();
		this.boardSeq = boardSeq;
		this.boardName = boardName;
		this.category = category;
		this.regDate = regDate;
		this.boardCount = boardCount;
		this.commentCount = commentCount;
		this.imageName = imageName;
		this.saveNumber = saveNumber;
	}
	
	public PostDTO(int boardSeq, String boardName, int goodsPrice, String imageName) {
		this.boardSeq = boardSeq;
		this.boardName = boardName;
		this.goodsPrice = goodsPrice;
		this.imageName = imageName;
	}
	
	public PostDTO(int boardSeq, int userSeq) {
		this.boardSeq = boardSeq;
		this.userSeq = userSeq;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
	
}
