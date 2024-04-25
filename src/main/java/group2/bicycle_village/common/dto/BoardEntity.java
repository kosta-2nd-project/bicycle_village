package group2.bicycle_village.common.dto;

import group2.bicycle_village.common.constant.CommonCode;

import java.util.Date;

import static group2.bicycle_village.common.constant.CommonCode.*;

public class BoardEntity {
    private long boardSeq;
    private String boardName;
    private Date regDate;
    private BoardCategory category;
    private BoardStatus isSeen;
    private String content;
    private String addr;
    private Date editDay;
    private Integer boardCount;
    private Integer price;
    private Long productSeq;
    private Long userSeq;

    private BoardEntity(Builder builder){
        this.boardSeq = builder.boardSeq;
        this.boardName = builder.boardName;
        this.regDate = builder.regDate;
        this.category = builder.category;
        this.isSeen = builder.isSeen;
        this.content = builder.content;
        this.addr = builder.addr;
        this.editDay = builder.editDay;
        this.boardCount = builder.boardCount;
        this.price = builder.price;
        this.productSeq = builder.productSeq;
        this.userSeq = builder.userSeq;
    }

    public static class Builder {

        private Long boardSeq;
        private String boardName;
        private Date regDate;
        private BoardCategory category;
        private BoardStatus isSeen;
        private String content;
        private String addr;
        private Date editDay;
        private Integer boardCount;
        private Integer price;
        private Long productSeq;
        private Long userSeq;

        public Builder boardSeq(Long boardSeq) {
            this.boardSeq = boardSeq;
            return this;
        }
        public Builder boardName(String boardName) {
            this.boardName = boardName;
            return this;
        }
        public Builder regDate(Date regDate) {
            this.regDate = regDate;
            return this;
        }
        public Builder category(BoardCategory category) {
            this.category = category;
            return this;
        }
        public Builder isSeen(BoardStatus isSeen) {
            this.isSeen = isSeen;
            return this;
        }
        public Builder content(String content) {
            this.content = content;
            return this;
        }
        public Builder addr(String addr) {
            this.addr = addr;
            return this;
        }
        public Builder editDay(Date editDay) {
            this.editDay = editDay;
            return this;
        }
        public Builder boardCount(Integer boardCount) {
            this.boardCount = boardCount;
            return this;
        }
        public Builder price(Integer price) {
            this.price = price;
            return this;
        }
        public Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }
        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        // 대상 객체의 private 생성자를 호출하여 최종 인스턴스화 한다
        public BoardEntity build() {
            return new BoardEntity(this); // 빌더 객체 자신을 넘긴다.
        }
    }

}
