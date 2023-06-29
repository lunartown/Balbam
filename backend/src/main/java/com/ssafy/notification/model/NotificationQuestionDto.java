package com.ssafy.notification.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "QuestionDto (자주 찾는 질문 정보)", description = "자주 찾는 질문에 대한 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class NotificationQuestionDto {
	private int questionId;
	private String title;
	private String content;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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
	@Override
	public String toString() {
		return "NotificationQuestionDto [questionId=" + questionId + ", title=" + title + ", content=" + content + "]";
	}
	
	

}
