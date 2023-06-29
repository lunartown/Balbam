package com.ssafy.notification.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NotificationDto (공지 정보)", description = "공지 하나의 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class NotificationDto {
	@ApiModelProperty(value = "공지 구분자")
	private int notificationId;
	@ApiModelProperty(value = "공지 제목")
	private String title;
	@ApiModelProperty(value = "공지 내용")
	private String content;
	@ApiModelProperty(value = "공지 작성시간")
	private String createTime;
	@ApiModelProperty(value = "공지 작성자 아이디")
	private int userId;
	@ApiModelProperty(value = "공지 종류")
	private String category;

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
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


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "NotificationDto [notificationId=" + notificationId + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + ", userId=" + userId + ", category=" + category + "]";
	}

}
