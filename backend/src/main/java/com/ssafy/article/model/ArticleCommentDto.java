package com.ssafy.article.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ArticleCommentDto (댓글 정보)", description = "게시글 댓글의 내용, 작성시간 등을 가진 Domain Class!!!!")
public class ArticleCommentDto {
	@ApiModelProperty(value = "댓글 구분자")
	private int commentId;
	@ApiModelProperty(value = "작성자 구분자")
	private int userId;
	@ApiModelProperty(value = "작성자 이름")
	private String name;
	@ApiModelProperty(value = "작성자 아바타")
	private String profile;
	@ApiModelProperty(value = "게시글 구분자")
	private int articleId;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "댓글 작성 시간")
	private String createTime;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "ArticleCommentDto{" +
				"commentId=" + commentId +
				", userId=" + userId +
				", name='" + name + '\'' +
				", profile='" + profile + '\'' +
				", articleId=" + articleId +
				", content='" + content + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
