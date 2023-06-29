package com.ssafy.imgarticle.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "ImgArticleDto (게시글 정보)", description = "사진 게시글 하나의 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class ImgArticleDto {
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
	private String name;
	@ApiModelProperty(value = "게시글 지역")
	private String region;
	@ApiModelProperty(value = "게시글 조회수")
	private int view;
	@ApiModelProperty(value = "게시글 이미지")
	private List<String> img;
	@ApiModelProperty(value = "게시글 메인 이미지")
	private String mainImg;
	@ApiModelProperty(value = "게시글 작성자 프로필 이미지")
	private String profileImg;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getHeartCnt() {
		return heartCnt;
	}

	public void setHeartCnt(int heartCnt) {
		this.heartCnt = heartCnt;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "ImgArticleDto{" +
				"articleId=" + articleId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", createTime='" + createTime + '\'' +
				", userId=" + userId +
				", name='" + name + '\'' +
				", region='" + region + '\'' +
				", view=" + view +
				", img=" + img +
				", mainImg='" + mainImg + '\'' +
				", profileImg='" + profileImg + '\'' +
				", heart=" + heart +
				", heartCnt=" + heartCnt +
				'}';
	}
}