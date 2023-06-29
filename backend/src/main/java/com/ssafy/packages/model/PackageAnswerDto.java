package com.ssafy.packages.model;

public class PackageAnswerDto {
	private int answerId;
	private int inquiryId;
	private String content;
	private String createTime;

	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getInquiryId() {
		return inquiryId;
	}
	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "PackageAnswerDto [answerId=" + answerId + ", inquiryId=" + inquiryId + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
	
}
