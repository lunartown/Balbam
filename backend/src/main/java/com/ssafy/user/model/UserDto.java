package com.ssafy.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDto (회원정보)", description = "회원 한 명의 아이디, 비번, 이름, 이메일을 가진 Domain Class")
public class UserDto {
	@ApiModelProperty(value = "회원 고유 아이디")
	private int userId;
	@ApiModelProperty(value = "회원 아이디")
	private String id;
	@ApiModelProperty(value = "회원 비밀번호")
	private String pwd;
	@ApiModelProperty(value = "회원 이메일")
	private String email;
	@ApiModelProperty(value = "회원 이름")
	private String name;
	@ApiModelProperty(value = "회원 성별")
	private char gender;
	@ApiModelProperty(value = "회원 생년월일")
	private String birth;
	@ApiModelProperty(value = "회원 프로필 이미지")
	private String profileImg;
	@ApiModelProperty(value = "회원 전화번호")
	private String tel;
	@ApiModelProperty(value = "관리자 권한")
	private int admin;
	@ApiModelProperty(value = "비밀번호 오류 횟수")
	private int captcha;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getCaptcha() {
		return captcha;
	}

	public void setCaptcha(int captcha) {
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", id=" + id + ", pwd=" + pwd + ", email=" + email + ", name=" + name
				+ ", gender=" + gender + ", birth=" + birth + ", profileImg=" + profileImg + ", tel=" + tel + ", admin="
				+ admin + ", captcha=" + captcha + "]";
	}
}
