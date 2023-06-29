package com.ssafy.util.model;

public class SearchCondition {
	
	public final int countPerPage = 10;
	private int totalCount; // 총 게시글 갯수
	private int naviSize; // 네비게이션 사이즈
	private int totalPageCount; // 총 페이지 갯수
	private boolean startRange; // 현재 페이지가 이전이 눌려지지 않는 범위의 페이지 체크
	private boolean endRange; // 현재 페이지가 다음이 눌려지지 않는 범위의 페이지 체크
	private String key = "none";
	private String word;
	private int currentPage = 1;
	private int startIndex;
	private String catOrRegion;
	private String userId;
	private String canceled;
	private String passed;

	public String getCatOrRegion() {
		return catOrRegion;
	}

	public void setCatOrRegion(String catOrRegion) {
		this.catOrRegion = catOrRegion;
	}

	public SearchCondition() {
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public boolean isStartRange() {
		return startRange;
	}

	public void setStartRange(boolean startRange) {
		this.startRange = startRange;
	}

	public boolean isEndRange() {
		return endRange;
	}

	public void setEndRange(boolean endRange) {
		this.endRange = endRange;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	@Override
	public String toString() {
		return "SearchCondition [countPerPage=" + countPerPage + ", totalCount=" + totalCount + ", naviSize=" + naviSize
				+ ", totalPageCount=" + totalPageCount + ", startRange=" + startRange + ", endRange=" + endRange
				+ ", key=" + key + ", word=" + word + ", currentPage=" + currentPage + ", startIndex=" + startIndex
				+ ", catOrRegion=" + catOrRegion + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	

}
