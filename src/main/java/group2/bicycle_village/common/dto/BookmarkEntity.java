package group2.bicycle_village.common.dto;


public class BookmarkEntity {
	private long bookmarkSeq;
	private long userSeq;
	private long boardSeq;
	
	private BookmarkEntity(Builder builder) {
		this.bookmarkSeq = builder.bookmarkSeq;
		this.userSeq = builder.userSeq;
		this.boardSeq = builder.boardSeq;
	}
	
	public static class Builder{
		private long bookmarkSeq;
		private long userSeq;
		private long boardSeq;
		
		public Builder bookmarkSeq(long bookmarkSeq) {
			this.bookmarkSeq = bookmarkSeq;
			return this;
		}
		public Builder userSeq(long userSeq) {
			this.userSeq = userSeq;
			return this;
		}
		public Builder boardSeq(long boardSeq) {
			this.boardSeq = boardSeq;
			return this;
		}
		
		// 대상 객체의 private 생성자를 호출하여 최종 인스턴스화 한다
	    public BookmarkEntity build() {
	        return new BookmarkEntity(this); // 빌더 객체 자신을 넘긴다.
	    }
	}
	

	public long getBookmarkSeq() {
		return bookmarkSeq;
	}

	public long getUserSeq() {
		return userSeq;
	}

	public long getBoardSeq() {
		return boardSeq;
	}
	
	
}
