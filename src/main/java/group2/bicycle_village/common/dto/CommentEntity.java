package group2.bicycle_village.common.dto;

public class CommentEntity {
	private Long commentSeq, parentCommentSeq, boardSeq, userSeq;
	private int isSeen;
	private String regDate, commentContent, corDate;
	
	private CommentEntity(Builder builder) {
		this.commentSeq = builder.commentSeq;
		this.parentCommentSeq = builder.parentCommentSeq;
		this.boardSeq = builder.boardSeq;
		this.userSeq = builder.userSeq;
		this.isSeen = builder.isSeen;
		this.regDate = builder.regDate;
		this.commentContent = builder.commentContent;
		this.corDate = builder.corDate;
	}
	
	public static class Builder {
		
		private Long commentSeq, parentCommentSeq, boardSeq, userSeq;
		private int isSeen;
		private String regDate, commentContent, corDate;
		
		public Builder commentSeq(Long commentSeq) {
			this.commentSeq = commentSeq;
			return this;
		}
		public Builder parentCommentSeq(Long parentCommentSeq) {
			this.parentCommentSeq = parentCommentSeq;
			return this;
		}
		public Builder boardSeq(Long boardSeq) {
			this.boardSeq = boardSeq;
			return this;
		}
		public Builder userSeq(Long userSeq) {
			this.userSeq = userSeq;
			return this;
		}
		
		public Builder commentSeq(int isSeen) {
			this.isSeen = isSeen;
			return this;
		}
		public Builder regDate(String regDate) {
			this.regDate = regDate;
			return this;
		}
		public Builder commentContent(String commentContent) {
			this.commentContent = commentContent;
			return this;
		}
		public Builder corDate(String corDate) {
			this.corDate = corDate;
			return this;
		}
		
		// 대상 객체의 private 생성자를 호출하여 최종 인스턴스화 한다
        public CommentEntity build() {
            return new CommentEntity(this); // 빌더 객체 자신을 넘긴다.
        }
	}

	public Long getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(Long commentSeq) {
		this.commentSeq = commentSeq;
	}

	public Long getParentCommentSeq() {
		return parentCommentSeq;
	}

	public void setParentCommentSeq(Long parentCommentSeq) {
		this.parentCommentSeq = parentCommentSeq;
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
