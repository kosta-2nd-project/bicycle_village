package group2.bicycle_village.common.dto;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.constant.CommonCode.reviewScore;

import java.util.Date;

public class ReviewEntity {
    private Long reviewSeq; //review_seq
    private UserEntity reviewing; //reviewing
    private UserEntity reviewer; //reviewer
    private BoardEntity board; //board_seq
    private String reviewContent; //review_content
    private reviewScore reviewScore;    //review_rate
    private Date reviewDate;    //review_date
    private Date modificationDate;  //MODIFICATION_DATE


    private ReviewEntity(Builder builder) {
        reviewSeq = builder.reviewSeq;
        reviewing = builder.reviewing;
        reviewer = builder.reviewer;
        board = builder.board;
        reviewContent = builder.reviewContent;
        reviewScore = builder.reviewScore;
        reviewDate = builder.reviewDate;
        modificationDate = builder.modificationDate;
    }

    public static class Builder {
        private Long reviewSeq; //review_seq
        private UserEntity reviewing; //reviewing
        private UserEntity reviewer; //reviewer
        private BoardEntity board; //board_seq
        private String reviewContent; //review_content
        private reviewScore reviewScore;    //review_rate
        private Date reviewDate;    //review_date
        private Date modificationDate;  //MODIFICATION_DATE

        public Builder reviewSeq(Long reviewSeq) {
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Builder reviewing(UserEntity reviewing) {
            this.reviewing = reviewing;
            return this;
        }

        public Builder reviewer(UserEntity reviewer) {
            this.reviewer = reviewer;
            return this;
        }

        public Builder board(BoardEntity board) {
            this.board = board;
            return this;
        }

        public Builder reviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
            return this;
        }

        public Builder reviewScore(reviewScore reviewScore) {
            this.reviewScore = reviewScore;
            return this;
        }

        public Builder reviewDate(Date reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder modificationDate(Date modificationDate) {
            this.modificationDate = modificationDate;
            return this;
        }

        public ReviewEntity build() {
            return new ReviewEntity(this);
        }
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public void setReviewSeq(Long reviewSeq) {
        this.reviewSeq = reviewSeq;
    }

    public UserEntity getReviewing() {
        return reviewing;
    }

    public void setReviewing(UserEntity reviewing) {
        this.reviewing = reviewing;
    }

    public UserEntity getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserEntity reviewer) {
        this.reviewer = reviewer;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public CommonCode.reviewScore getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(CommonCode.reviewScore reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}
