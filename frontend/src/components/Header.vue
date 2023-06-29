<template>
  <div class="header">
    <div class="header__empty"></div>
    <div class="header__head">
      <div class="header__area">
        <div class="header__left">
          <router-link :to="{ name: 'home' }" class="header__logo">
            <img src="../assets/img/logo-small.png" class="header__logo--img" />
            발밤발밤
          </router-link>
          <fieldset class="header__search">
            <input class="header__search--search" placeholder="검색어를 입력해주세요" />
            <div class="header__search--icon">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="100%"
                viewBox="0 0 24 24"
                width="100%"
              >
                <path d="M0 0h24v24H0z" fill="none" />
                <path
                  d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
                  fill="#767676"
                />
              </svg>
            </div>
          </fieldset>
        </div>
        <div class="header__right">
          <ul class="header__info">
            <li class="header__info--item">
              <router-link :to="{ name: 'mymenuBasket' }">
                <div>
                  <img class="header__info--icon" src="@/assets/img/icon/ico-mymenu.png" />
                </div>
                마이메뉴
              </router-link>
            </li>
            <li class="header__info--item">
              <router-link :to="{ name: 'mymenuHistory' }">
                <div>
                  <img
                    class="header__info--icon"
                    src="@/assets/img/icon/ico-reservationhistory.png"
                  />
                </div>
                히스토리
              </router-link>
            </li>
            <li class="header__info--item">
              <router-link :to="{ name: 'mymenuHeart' }">
                <div>
                  <img class="header__info--icon" src="@/assets/img/icon/ico-haeder-choice.png" />
                </div>
                좋아요
              </router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="header__nav">
      <div class="header__area">
        <div class="header__left">
          <div class="header__menu">
            <div class="header__menu--icon">
              <svg
                class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
                focusable="false"
                aria-hidden="true"
                viewBox="0 0 24 24"
                data-testid="MapOutlinedIcon"
              >
                <path
                  d="m20.5 3-.16.03L15 5.1 9 3 3.36 4.9c-.21.07-.36.25-.36.48V20.5c0 .28.22.5.5.5l.16-.03L9 18.9l6 2.1 5.64-1.9c.21-.07.36-.25.36-.48V3.5c0-.28-.22-.5-.5-.5zM10 5.47l4 1.4v11.66l-4-1.4V5.47zm-5 .99 3-1.01v11.7l-3 1.16V6.46zm14 11.08-3 1.01V6.86l3-1.16v11.84z"
                ></path>
              </svg>
            </div>
            <div class="header__menu--title">
              <router-link :to="{ name: 'map' }">여행지도</router-link>
            </div>
          </div>
          <ul class="header__list">
            <li class="header__list--item">
              <router-link :to="{ name: 'packageMain' }">테마</router-link>
            </li>
            <li class="header__list--item">
              <router-link :to="{ name: 'imgBoardMain' }">사진</router-link>
            </li>
            <li class="header__list--item">
              <router-link :to="{ name: 'boardMain' }">커뮤니티</router-link>
            </li>
          </ul>
        </div>
        <div class="header__right">
          <ul class="header__my">
            <li v-if="!userInfo" class="header__my--item">
              <router-link :to="{ name: 'login' }">로그인</router-link>
            </li>
            <li v-if="!userInfo" class="header__my--item">
              <router-link :to="{ name: 'signup' }">회원가입</router-link>
            </li>
            <li v-if="userInfo" class="header__my--item" @click="onClickLogout">로그아웃</li>
            <li class="header__my--item">
              <router-link :to="{ name: 'notification' }">고객센터</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
@font-face {
  font-family: "EF_jejudoldam";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-EF@1.0/EF_jejudoldam.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}

@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@700&family=Roboto&display=swap");

* {
  padding: 0;
  margin: 0;
  border: none;
  box-sizing: border-box;
}

ul {
  list-style-type: none;
}

a {
  text-decoration: none;
}

.header {
  font-family: "Noto Sans KR";
  display: flex;
  font-weight: 500;
  flex-direction: column;
  border-bottom: 1.5px solid rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.header__empty {
  height: 20px;
}
.header__head {
  display: flex;
  justify-content: center;
  height: 80px;
  border-bottom: 1px solid #dbdbdb;
}

.header__area {
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  height: 100%;
  width: 1280px;
}

.header__left {
  display: flex;
  align-items: center;
  height: 100%;
}

.header__logo {
  font-family: "EF_jejudoldam";
  display: flex;
  align-items: center;
  font-size: 28px;
  margin-right: 50px;
}

.header__logo--img {
  width: 35px;
  height: 35px;
}

.header__search {
  background-color: #f1f1f5;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 44px;
  width: 420px;
  border-radius: 22px;
  padding-left: 24px;
  padding-right: 24px;
}

.header__search--search {
  background-color: inherit;
  font-size: 14px;
  color: #767676;
}

.header__search--icon {
  width: 24px;
  height: 24px;
}

.header__info {
  position: relative;
  top: 5px;
  display: flex;
}

.header__info--item {
  text-align: center;
  font-size: 11px;
  width: 44px;
  height: 44px;
  margin-left: 24px;
}

/* nav 바 */
.header__nav {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 15px;
  letter-spacing: -0.05em;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 54px;
}

.header__menu {
  height: 100%;
  display: flex;
  align-items: center;
  margin-right: 30px;
  border-inline: 1px solid #dbdbdb;
  padding-inline: 25px;
}

.header__menu--icon {
  margin-right: 6px;
  width: 24px;
  height: 24px;
}

.header__menu--title {
  display: flex;
  align-items: center;
}

.header__list {
  display: flex;
}

.header__list--item {
  margin-right: 24px;
}

.header__my {
  display: flex;
}

.header__my--item {
  cursor: pointer;
  color: #767676;
  margin-left: 24px;
}
</style>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "HeaderHeader",
  data() {
    return {};
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    onClickLogout() {
      this.userLogout(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path !== "/home") {
        this.$router.push({ name: "home" });
      }
    },
  },
  created() {},
};
</script>
