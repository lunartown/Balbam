package com.ssafy.article.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "ArticleDto (게시글 정보)", description = "게시글 하나의 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class ArticleDto {
	@ApiModelProperty(value = "게시글 구분자")
	private int articleId;
	@ApiModelProperty(value = "게시글 제목")
	private String title;
	@ApiModelProperty(value = "게시글 내용")
	private String content;
	@ApiModelProperty(value = "게시글 작성시간")
	private String createTime;
	@ApiModelProperty(value = "게시글 작성자 아이디")
	private int userId;
	@ApiModelProperty(value = "게시글 작성자 이름")
	private String userName;
	@ApiModelProperty(value = "게시글 카테고리")
	private String category;
	@ApiModelProperty(value = "게시글 조회수")
	private int view;
	@ApiModelProperty(value = "게시글 좋아요 여부")
	private boolean heart;
	@ApiModelProperty(value = "게시글 좋아요 개수")
	private int heartCnt;

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public boolean isHeart() {
		return heart;
	}
	public void setHeart(boolean heart) {
		this.heart = heart;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getHeartCnt() {
		return heartCnt;
	}

	public void setHeartCnt(int heartCnt) {
		this.heartCnt = heartCnt;
	}

	@Override
	public String toString() {
		return "ArticleDto{" +
				"articleId=" + articleId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", createTime='" + createTime + '\'' +
				", userId=" + userId +
				", userName='" + userName + '\'' +
				", category='" + category + '\'' +
				", view=" + view +
				", heart=" + heart +
				", heartCnt=" + heartCnt +
				'}';
	}
}
