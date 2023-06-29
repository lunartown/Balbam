package com.ssafy.notification.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "InquirytDto (문의  정보)", description = "문의에 대한 내용, 작성시간 등을 가진 Domain Class!!!!")
public class InquiryDto {
	@ApiModelProperty(value = "문의 구분자")
	private int inquiryId;
	@ApiModelProperty(value = "답변 구분자")
	private int answerId;
	@ApiModelProperty(value = "문의자 구분자")
	private int userId;
	@ApiModelProperty(value = "답변자 구분자")
	private int answerUserId;
	@ApiModelProperty(value = "답변자 닉네임")
	private String answerAuthor;
	@ApiModelProperty(value = "문의 제목")
	private String title;
	@ApiModelProperty(value = "문의 내용")
	private String content;
	@ApiModelProperty(value = "문의 답변")
	private String answerContent;
	@ApiModelProperty(value = "문의 작성일")
	private String createTime;
	@ApiModelProperty(value = "답변 작성일")
	private String answerTime;
	public String getAnswerAuthor() {
		return answerAuthor;
	}
	public void setAnswerAuthor(String answerAuthor) {
		this.answerAuthor = answerAuthor;
	}
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAnswerUserId() {
		return answerUserId;
	}
	public void setAnswerUserId(int answerUserId) {
		this.answerUserId = answerUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String create_time) {
		this.createTime = create_time;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	@Override
	public String toString() {
		return "InquiryDto [inquiryId=" + inquiryId + ", answerId=" + answerId + ", userId=" + userId
				+ ", answerUserId=" + answerUserId + ", answerAuthor=" + answerAuthor + ", title=" + title
				+ ", content=" + content + ", answerContent=" + answerContent + ", create_time=" + createTime
				+ ", answerTime=" + answerTime + "]";
	}
	
}
