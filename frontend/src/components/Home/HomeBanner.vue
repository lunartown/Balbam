<template>
  <div class="banner">
    <div class="banner__banner">
      <div class="banner-filter"></div>
      <div class="banner__video-area">
        <div class="banner__carousel">
          <div class="banner--video">
            <video muted autoplay loop>
              <source src="@/assets/video/banner1.mp4" type="video/mp4" />
              <strong>Your browser does not support the video tag.</strong>
            </video>
          </div>
          <div class="banner--video">
            <video muted autoplay loop>
              <source src="@/assets/video/banner2.mp4" type="video/mp4" />
              <strong>Your browser does not support the video tag.</strong>
            </video>
          </div>
          <div class="banner--video">
            <video muted autoplay loop>
              <source src="@/assets/video/banner3.mp4" type="video/mp4" />
              <strong>Your browser does not support the video tag.</strong>
            </video>
          </div>
          <div class="banner--video">
            <video muted autoplay loop>
              <source src="@/assets/video/banner4.mp4" type="video/mp4" />
              <strong>Your browser does not support the video tag.</strong>
            </video>
          </div>
          <div class="banner--video">
            <video muted autoplay loop>
              <source src="@/assets/video/banner5.mp4" type="video/mp4" />
              <strong>Your browser does not support the video tag.</strong>
            </video>
          </div>
          <div class="banner--video">
            <video muted autoplay loop>
              <source src="@/assets/video/banner1.mp4" type="video/mp4" />
              <strong>Your browser does not support the video tag.</strong>
            </video>
          </div>
          <!--
          <div class="banner--video">
            <img src="@/assets/img/search/building-g4ab7726d6_1920.jpg" />
          </div>
          <div class="banner--video">
            <img src="@/assets/img/search/building-g4ab7726d6_1920.jpg" />
          </div>
          <div class="banner--video">
            <img src="@/assets/img/search/building-g4ab7726d6_1920.jpg" />
          </div>
          <div class="banner--video">
            <img src="@/assets/img/search/building-g4ab7726d6_1920.jpg" />
          </div>
          <div class="banner--video">
            <img src="@/assets/img/search/building-g4ab7726d6_1920.jpg" />
          </div>
          <div class="banner--video">
            <img src="@/assets/img/search/building-g4ab7726d6_1920.jpg" />
          </div>
          -->
        </div>
        <div class="banner__search">
          <div class="search-item">
            <div class="item--select">
              <div>연인</div>
              <div>부모님</div>
              <div>나</div>
              <div>친구</div>
              <div>가족</div>
            </div>
            <div class="item--name">
              <div>과</div>
              <div>과</div>
              <div>홀로</div>
              <div>와</div>
              <div>과</div>
            </div>
          </div>
          <div class="search-item">
            <div class="item--select">
              <div>이틀</div>
              <div>나흘</div>
              <div>한달</div>
              <div>당일</div>
              <div>일주일</div>
            </div>
            <div class="item--name">
              <div>동안</div>
              <div>동안</div>
              <div>동안</div>
              <div>치기</div>
              <div>동안</div>
            </div>
          </div>
          <div class="search-item">
            <div class="item--select">
              <div>양주</div>
              <div>속초</div>
              <div>안동</div>
              <div>해운대</div>
              <div>경주</div>
            </div>
            <div class="item--name">
              <div>의</div>
              <div>의</div>
              <div>의</div>
              <div>의</div>
              <div>의</div>
            </div>
          </div>
          <div class="search-item">
            <div class="item--select">
              <div>벚꽃길</div>
              <div>연금정</div>
              <div>오솔길</div>
              <div>바닷가</div>
              <div>문화재</div>
            </div>
            <div class="item--name">
              <div>로</div>
              <div>으로</div>
              <div>로</div>
              <div>로</div>
              <div>로</div>
            </div>
          </div>
        </div>
        <router-link :to="{ name: 'map' }" class="banner__click" onselectstart="return false">
          <div>
            떠나기&nbsp;
            <div class="arrow">></div>
          </div>
        </router-link>
      </div>
      <div class="banner__carousel--button">
        <div class="ㅡ">ㅡ</div>
        <div class="ㅣ">ㅣ</div>
      </div>
    </div>
  </div>
</template>

<style>
* {
  padding: 0;
  margin: 0;
}

ul {
  list-style-type: none;
}

a {
  color: inherit;
  text-decoration: none;
}
/* banner */
.banner {
  width: 100%;
  height: 70vh;
  margin-bottom: 100px;
  position: relative;
}

.banner__line {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
}

.banner__line > .line1 {
  position: relative;
  height: 100%;
  width: 3px;
  z-index: 100;
  background-color: rgba(255, 255, 255, 0.5);
}

.banner__window {
  position: relative;
  width: 100%;
  height: 120px;
  z-index: 100;
}

.banner__layout {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1712px;
  padding: 0 40px;
  height: 120px;
  margin: 0 auto;
}

.banner__logo-area {
  display: flex;
  align-items: center;
}

.banner__logo-area > img {
  filter: invert(96%) sepia(100%) saturate(12%) hue-rotate(237deg) brightness(103%) contrast(103%);
  width: 80px;
  height: 80px;
}

.banner__logo-area > div {
  color: white;
  font-family: "EF_jejudoldam", sans-serif;
  font-size: 50px;
}

.banner__gnb {
  display: flex;
}

.banner__info {
  color: white;
  display: flex;
  justify-content: space-between;
  width: 600px;
  letter-spacing: -1px;
}

.banner__info > .item--login {
  cursor: pointer;
}

.banner__info > .line2 {
  position: absolute;
  width: calc((100% - 1712px) / 2 + 640px);
  min-width: 640px;
  height: 1px;
  background-color: rgba(255, 255, 255, 0.5);
  right: 0px;
  top: 97px;
}

.info--item:hover {
  color: aliceblue;
}

.banner__banner {
  position: absolute;
  top: 0;
  width: 100%;
  height: 70vh;
  z-index: 0;
  overflow: hidden;
}

.banner__gradation {
  position: relative;
  height: 200px;
  background: linear-gradient(to top, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3));
  z-index: 1;
}

@keyframes display {
  0% {
    transform: translateX(0);
  }
  5% {
    transform: translateX(-16.6666667%);
  }
  20% {
    transform: translateX(-16.6666667%);
  }
  25% {
    transform: translateX(-33.3333333%);
  }
  40% {
    transform: translateX(-33.3333333%);
  }
  45% {
    transform: translateX(-50%);
  }
  60% {
    transform: translateX(-50%);
  }
  65% {
    transform: translateX(-66.6666667%);
  }
  80% {
    transform: translateX(-66.6666667%);
  }
  85% {
    transform: translateX(-83.3333333%);
  }
  100% {
    transform: translateX(-83.3333333%);
  }
}

.banner__video-area {
  width: 600%;
  height: 100%;
  position: absolute;
  top: 0;
}

.banner__carousel {
  display: flex;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  animation: display 60s infinite;
  animation-delay: 12s;
}

.banner__carousel > .banner--video {
  position: relative;
  width: 16.6666667%;
  height: 100%;
  bottom: 0;
}

.banner--video > video {
  width: 100%;
  min-width: 180vh;
}

.banner-filter {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.banner__search {
  display: flex;
  flex-wrap: wrap;
  position: absolute;
  top: 10%;
  left: 2%;
  width: 700px;
  height: 220px;
  padding: 0 20px;
  z-index: 100;
}

.banner__search > .search-item {
  color: #eaf6cf;
  width: 330px;
  height: 110px;
  display: flex;
  align-items: center;
}

.search-item > .item--select {
  position: relative;
  width: 190px;
  height: 80px;
  line-height: 80px;
  font-family: "Noto Serif KR", serif;
  margin: 0 10px 0 10px;
  padding-left: 10px;
  border-left: 3px solid rgba(255, 255, 255, 0.5);
}

@keyframes titleAnimation {
  0% {
    transform: translateX(50px);
    opacity: 0;
    -webkit-clip-path: polygon(100% 80%, 100% 100%, 0 20%, 0 0);
    clip-path: polygon(100% 80%, 100% 100%, 0 20%, 0 0);
  }
  5% {
    transform: translateX(0);
    opacity: 1;
    -webkit-clip-path: polygon(100% 15%, 100% 100%, 0 100%, 0 0);
    clip-path: polygon(100% 15%, 100% 100%, 0 100%, 0 0);
  }
  19% {
    transform: translateX(0);
    opacity: 1;
    -webkit-clip-path: polygon(100% 15%, 100% 100%, 0 100%, 0 0);
    clip-path: polygon(100% 15%, 100% 100%, 0 100%, 0 0);
  }
  23% {
    transform: translateX(-50px);
    opacity: 0;
    -webkit-clip-path: polygon(100% 80%, 100% 100%, 0 100%, 0 0);
    clip-path: polygon(100% 80%, 100% 100%, 0 100%, 0 0);
  }
}

@keyframes titleAnimation2 {
  0% {
    opacity: 0;
    -webkit-clip-path: polygon(100% 80%, 100% 100%, 0 100%, 0 0);
    clip-path: polygon(100% 80%, 100% 100%, 0 100%, 0 0);
  }
  5% {
    opacity: 1;
    -webkit-clip-path: polygon(100% 15%, 100% 100%, 0 100%, 0 0);
    clip-path: polygon(100% 15%, 100% 100%, 0 100%, 0 0);
  }
  18% {
    opacity: 1;
  }
  20% {
    opacity: 0;
  }
}

.item--select > div {
  position: absolute;
  font-family: "Noto Serif KR", serif;
  font-size: 56px;
  font-weight: bold;
  color: white;
  -webkit-clip-path: polygon(100% 0, 100% 100%, 0 100%, 0 80%);
  clip-path: polygon(100% 0, 100% 100%, 0 100%, 0 80%);
  transform: translateX(50px);
  opacity: 0;
  animation-name: titleAnimation;
  animation-timing-function: ease;
  animation-duration: 60s;
  animation-iteration-count: infinite;
}

.item--select > div:hover {
  cursor: pointer;
  line-height: 76px;
  transition: all 0.5s;
  text-shadow: 0 5px 5px rgba(0, 0, 0, 0.5);
}

.item--select > div:nth-child(2) {
  animation-delay: 12s;
}
.item--select > div:nth-child(3) {
  animation-delay: 24s;
}
.item--select > div:nth-child(4) {
  animation-delay: 36s;
}
.item--select > div:nth-child(5) {
  animation-delay: 48s;
}

.search-item > .item--name {
  width: 100px;
  display: flex;
  align-items: center;
  position: relative;
}

.item--name > div {
  transform: translate(0, 7.5px);
  position: absolute;
  font-family: "Noto Serif KR", serif;
  font-size: 50px;
  font-weight: medium;
  opacity: 0;
  animation-name: titleAnimation2;
  animation-timing-function: ease;
  animation-duration: 60s;
  animation-iteration-count: infinite;
}

.item--name > div:nth-child(2) {
  animation-delay: 12s;
}
.item--name > div:nth-child(3) {
  animation-delay: 24s;
}
.item--name > div:nth-child(4) {
  animation-delay: 36s;
}
.item--name > div:nth-child(5) {
  animation-delay: 48s;
}

@keyframes bounce_frames {
  from {
    transform: translate3d(0, 0, 0);
  }
  to {
    transform: translate3d(0, 10px, 0);
  }
}

.banner__click {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  position: absolute;
  width: 500px;
  height: 130px;
  line-height: 130px;
  top: calc(20.63% + 260px);
  left: calc(2% + 35px);
  color: white;
  text-shadow: 3px 0px 3px rgb(80, 80, 80);
  font-size: 100px;
  font-family: "Noto Serif KR", sans-serif;
  font-weight: bold;
  z-index: 1;
  border-bottom: 2px solid rgba(255, 255, 255, 0.5);
}

.banner__click:hover {
  cursor: pointer;
  padding-bottom: 10px;
  text-shadow: 0px 0px 5px black;
  transition: all 0.5s;
}

.banner__click > div {
  width: 500px;
  display: flex;
  justify-content: space-between;
  font-size: 100px;
  font-family: "Noto Serif KR", serif;
}

.banner__click > div > div {
  font-size: 60px;
  font-family: "Noto Serif KR", serif;
}

.banner__carousel--button {
  position: absolute;
  top: 45%;
  right: 50px;
  font-size: 100px;
  color: white;
}

.banner__carousel--button:hover {
  cursor: pointer;
}

.banner__carousel--button > .ㅡ {
  font-family: "EF_jejudoldam", sans-serif;
  transform: rotate(45deg);
}

.banner__carousel--button > .ㅣ {
  font-family: "EF_jejudoldam", sans-serif;
  transform: rotate(45deg) translate(-44px, -44px);
}
</style>

<script>
export default {
  name: "HomeBanner",
};
</script>
