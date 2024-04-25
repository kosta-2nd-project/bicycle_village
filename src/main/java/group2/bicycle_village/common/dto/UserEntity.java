package group2.bicycle_village.common.dto;

import java.util.Date;

import group2.bicycle_village.common.constant.CommonCode.UserStatus;

public class UserEntity {
	private long userSeq;
	private String userId;
	private String userPwd;
	private String nickname;
	private String userEmail;
	private String userGender;
	private String userTel;
	private Date regDate;
	private Date editDate;
	private UserStatus status;
	private Date userBirth;
	
	private UserEntity(Builder builder) {
		this.userSeq = builder.userSeq;
		this.userId = builder.userId;
		this.userPwd = builder.userPwd;
		this.nickname = builder.nickname;
		this.userEmail = builder.userEmail;
		this.userGender = builder.userGender;
		this.userTel = builder.userTel;
		this.regDate = builder.regDate;
		this.editDate = builder.editDate;
		this.status = builder.status;
		this.userBirth = builder.userBirth;
		
	}
	
	public static class Builder{
		private long userSeq;
		private String userId;
		private String userPwd;
		private String nickname;
		private String userEmail;
		private String userGender;
		private String userTel;
		private Date regDate;
		private Date editDate;
		private UserStatus status;
		private Date userBirth;
		
		public Builder userSeq(long userSeq) {
			this.userSeq = userSeq;
			return this;
		}
		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}
		public Builder userPwd(String userPwd) {
			this.userPwd = userPwd;
			return this;
		}
		public Builder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}
		public Builder userEmail(String userEmail) {
			this.userEmail = userEmail;
			return this;
		}
		public Builder userGener(String userGender) {
			this.userGender = userGender;
			return this;
		}
		public Builder userTel(String userTel) {
			this.userTel = userTel;
			return this;
		}
		public Builder regDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public Builder editDate(Date editDate) {
			this.editDate = editDate;
			return this;
		}
		public Builder status(UserStatus status) {
			this.status = status;
			return this;
		}
		public Builder userBirth(Date userBirth) {
			this.userBirth = userBirth;
			return this;
		}
		
		 // 대상 객체의 private 생성자를 호출하여 최종 인스턴스화 한다
        public UserEntity build() {
            return new UserEntity(this); // 빌더 객체 자신을 넘긴다.
        }
	}
}
