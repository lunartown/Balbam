package com.ssafy.guide.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "GuideDto (가이드정보)", description = "가이드 한 명의 아이디, 비번, 이름, 이메일을 가진 Domain Class")
public class GuideDto {
	@ApiModelProperty(value = "가이드 고유 아이디")
	private int guideId;
	@ApiModelProperty(value = "가이드 아이디")
	private String id;
	@ApiModelProperty(value = "가이드 비밀번호")
	private String pwd;
	@ApiModelProperty(value = "가이드 이메일")
	private String email;
	@ApiModelProperty(value = "가이드 이름")
	private String name;
	@ApiModelProperty(value = "가이드 프로필 이미지")
	private String profileImg;
	@ApiModelProperty(value = "가이드 전화번호")
	private String tel;
	@ApiModelProperty(value = "가이드 자격 종류")
	private String license;
	@ApiModelProperty(value = "가이드 자격증 이미지")
	private String licenseImg;
	@ApiModelProperty(value = "가이드 계좌번호")
	private String accountNum;
	@ApiModelProperty(value = "가이드 계좌 은행")
	private String accountBank;
	@ApiModelProperty(value = "가이드 계좌 예금주")
	private String accountHolder;
	@ApiModelProperty(value = "가이드 통장 사본")
	private String accountImg;

	public int getguideId() {
		return guideId;
	}

	public void setguideId(int guideId) {
		this.guideId = guideId;
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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseImg() {
		return licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountImg() {
		return accountImg;
	}

	public void setAccountImg(String accountImg) {
		this.accountImg = accountImg;
	}

	@Override
	public String toString() {
		return "GuideDto [guideId=" + guideId + ", id=" + id + ", pwd=" + pwd + ", email=" + email + ", name=" + name
				+ ", profileImg=" + profileImg + ", tel=" + tel + ", license=" + license + ", licenseImg=" + licenseImg
				+ ", accountNum=" + accountNum + ", accountBank=" + accountBank + ", accountHolder=" + accountHolder
				+ ", accountImg=" + accountImg + "]";
	}

}
