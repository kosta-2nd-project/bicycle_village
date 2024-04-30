package group2.bicycle_village.common.dto;

public class CommentsDTO {
   private Long commentSeq, boardSeq, userSeq, parentCommentSeq;
   private int isSeen;
   private String regDate, commentContent, corDate;
   
   public CommentsDTO() {}
   
   public CommentsDTO(Long commentSeq, Long parentCommentSeq, Long boardSeq, Long userSeq, 
		   String regDate, int isSeen, String commentContent, String corDate) {
	super();
	this.commentSeq = commentSeq;
	this.parentCommentSeq = parentCommentSeq;
	this.boardSeq = boardSeq;
	this.userSeq = userSeq;
	this.isSeen = isSeen;
	this.regDate = regDate;
	this.commentContent = commentContent;
	this.corDate = corDate;
   }

	public Long getCommentSeq() {
		return commentSeq;
	}
	
	public void setCommentSeq(Long commentSeq) {
		this.commentSeq = commentSeq;
	}
	
	public Long getBoardSeq() {
		return boardSeq;
	}
	
	public void setBoardSeq(Long boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public Long getUserSeq() {
		return userSeq;
	}
	
	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}
	
	public Long getParentCommentSeq() {
		return parentCommentSeq;
	}
	
	public void setParentCommentSeq(Long parentCommentSeq) {
		this.parentCommentSeq = parentCommentSeq;
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



