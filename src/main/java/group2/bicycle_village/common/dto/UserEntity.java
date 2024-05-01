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

	public long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(long userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
}
