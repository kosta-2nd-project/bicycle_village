package group2.bicycle_village.common.dto;

public class CommentsDTO {
   private int commentSeq, parentComment, boardSeq, userSeq, isSeen;
   private String regDate, commentContent, corDate;
   
   public CommentsDTO() {}
   
   public CommentsDTO(int commentSeq, int parentComment, int boardSeq, int userSeq, 
		   String regDate, int isSeen, String commentContent, String corDate) {
	super();
	this.commentSeq = commentSeq;
	this.parentComment = parentComment;
	this.boardSeq = boardSeq;
	this.userSeq = userSeq;
	this.isSeen = isSeen;
	this.regDate = regDate;
	this.commentContent = commentContent;
	this.corDate = corDate;
   }
	
	public int getCommentSeq() {
		return commentSeq;
	}
	
	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}
	
	public int getParentComment() {
		return parentComment;
	}
	
	public void setParentComment(int parentComment) {
		this.parentComment = parentComment;
	}
	
	public int getBoardSeq() {
		return boardSeq;
	}
	
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public int getUserSeq() {
		return userSeq;
	}
	
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
	public int getIsSeen() {
		return isSeen;
	}
	
	public void setIsSeen(int isSeen) {
		this.isSeen = isSeen;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public String getCorDate() {
		return corDate;
	}
	
	public void setCorDate(String corDate) {
		this.corDate = corDate;
	}
   
}



