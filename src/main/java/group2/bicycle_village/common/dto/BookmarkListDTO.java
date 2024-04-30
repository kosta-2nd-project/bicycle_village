package group2.bicycle_village.common.dto;

import java.util.Date;

public class BookmarkListDTO {
	private int boardSeq;
	private String boardName;
	private String nickname;
	private Date regDate;
	private String goodsPrice;
	private String boardAddr;
	
	public BookmarkListDTO(int boardSeq, String boardName, String nickname, Date regDate, String goodsPrice, String boardAddr) {
		super();
		this.boardSeq = boardSeq;
		this.boardName = boardName;
		this.nickname = nickname;
		this.regDate = regDate;
		this.goodsPrice = goodsPrice;
		this.boardAddr = boardAddr;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getBoardAddr() {
		return boardAddr;
	}

	public void setBoardAddr(String boardAddr) {
		this.boardAddr = boardAddr;
	}
	
	
}
