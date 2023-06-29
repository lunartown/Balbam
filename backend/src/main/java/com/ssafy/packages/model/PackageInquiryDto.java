package com.ssafy.packages.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PackageInquiryDto (패키지 문의 정보)", description = "패키지 문의 하나의 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class PackageInquiryDto {
	@ApiModelProperty(value = "문의 구분자")
	private int inquiryId;
	@ApiModelProperty(value = "답변 구분자")
	private int answerId;
	@ApiModelProperty(value = "패키지 구분자")
	private int packageId;
	@ApiModelProperty(value = "유저 구분자")
	private int userId;
	@ApiModelProperty(value = "가이드 구분자")
	private int guideId;
	@ApiModelProperty(value = "문의 유저 이름")
	private String userName;
	@ApiModelProperty(value = "가이드 답변")
	private String guideName;
	@ApiModelProperty(value = "문의 내용")
	private String content;
	@ApiModelProperty(value = "문의 답변")
	private String answer;
	@ApiModelProperty(value = "문의 시간")
	private String createTime;
	@ApiModelProperty(value = "답변 시간")
	private String answerTime;

	public int getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGuideId() {
		return guideId;
	}

	public void setGuideId(int guideId) {
		this.guideId = guideId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}


	@Override
	public String toString() {
		return "PackageInquiryDto{" +
				"inquiryId=" + inquiryId +
				", answerId=" + answerId +
				", packageId=" + packageId +
				", userId=" + userId +
				", guideId=" + guideId +
				", userName='" + userName + '\'' +
				", guideName='" + guideName + '\'' +
				", content='" + content + '\'' +
				", answer='" + answer + '\'' +
				", createTime='" + createTime + '\'' +
				", answerTime='" + answerTime + '\'' +
				'}';
	}
}
