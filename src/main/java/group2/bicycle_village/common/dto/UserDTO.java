package group2.bicycle_village.common.dto;

import java.util.Date;

public class UserDTO {
	   private int user_seq;
	   private String userId;
	   private String pwd;
	   private String name;
	   private String nickName;
	   private String tel;
	   private String birth;
	   private String email;
	   private String gender;
	   private int status;
	   private Date reg_date;
	   
	   
	   public UserDTO() {}
	   public UserDTO(String userId, String pwd) {
			super();
			this.userId = userId;
			this.pwd = pwd;
			
		}
		public UserDTO(String userId, String pwd, String name) {
		super();
		this.userId = userId;
		this.pwd = pwd;
		this.name = name;
		
		
	}
		
		
		public UserDTO(String userId, String pwd, String name, String nickName, String tel, String birth, String email,
				String gender, int status) {
			super();
			this.userId = userId;
			this.pwd = pwd;
			this.name = name;
			this.nickName = nickName;
			this.tel = tel;
			this.birth = birth;
			this.email = email;
			this.gender = gender;
			this.status = status;
		}
		public UserDTO(String userId, String pwd, String name, String nickName, String tel, String birth, String email,
				String gender, int status, Date reg_date) {
			super();
			this.reg_date = reg_date;
		}
		
		public UserDTO(int user_seq, String userId, String pwd, String name, String nickName, String tel, String birth,
				String email, String gender, int status) {
			super();
			this.user_seq = user_seq;
			this.userId = userId;
			this.pwd = pwd;
			this.name = name;
			this.nickName = nickName;
			this.tel = tel;
			this.birth = birth;
			this.email = email;
			this.gender = gender;
			this.status = status;
		}
		
		public UserDTO(int user_seq, String userId, String pwd, String name, String nickName, String tel, String birth,
				String email, String gender, int status, Date reg_date) {
			super();
			this.user_seq = user_seq;
			this.userId = userId;
			this.pwd = pwd;
			this.name = name;
			this.nickName = nickName;
			this.tel = tel;
			this.birth = birth;
			this.email = email;
			this.gender = gender;
			this.status = status;
			this.reg_date = reg_date;
		}
		
		public int getUser_seq() {
			return user_seq;
		}
		public void setUser_seq(int user_seq) {
			this.user_seq = user_seq;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNickName() {
			return nickName;
		}
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getBirth() {
			return birth;
		}
		public void setBirth(String birth) {
			this.birth = birth;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getReg_date() {
			return reg_date;
		}
		public void setReg_date(Date reg_date) {
			this.reg_date = reg_date;
		}
}
