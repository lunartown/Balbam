<template>
  <div class="package-main-box">
    <div class="carousel-box">
      <package-banner-ele></package-banner-ele>
      <form action="" class="carousel-search-form">
        <div class="carousel-search-box">
          <input
            type="text"
            v-model="word"
            placeholder="여행지, 지역, 장소를 검색해 보세요."
            class="carousel-search-keyword-input"
          />
          <div class="carousel-icon-box" @click="search()">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="36"
              viewBox="0 0 24 24"
              width="36"
              class="search-icon"
            >
              <path d="M0 0h36v36H0z" fill="none" />
              <path
                d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
              />
            </svg>
          </div>
        </div>
      </form>
    </div>

    <div class="package-hot-board-main-box">
      <div>
        <div class="package-board-desc-png">
          <img src="@/assets/img/imgBoardHead.png" alt="" />
        </div>
        <div class="package-hot-board-desc-head">
          가장 사랑받은<br />
          여행들
        </div>
      </div>
      <board-top-menu :titles="menuTitles" @catClick="catclick"></board-top-menu>
      <div class="package-hot-board-box">
        <div
          class="card-package-box"
          @click="cardClick(pack.packageId)"
          v-for="(pack, index) in themePackages"
          :key="index"
        >
          <card-view-ele :pack="pack"></card-view-ele>
        </div>
      </div>
    </div>
    <div
      v-for="(idx, index) in themeIdx"
      :key="index"
      :class="{ evenTheme: index % 2 == 0 ? true : false }"
    >
      <package-theme :themeTitle="themeTitle[idx]" :idx="index"></package-theme>
    </div>
    <div class="package-write-box">
      <button class="package-write-btn" @click="mvPackageWrite()">패키지 만들기</button>
    </div>
  </div>
</template>

<script>
import BoardTopMenu from "@/components/Element/BoardTopMenu.vue";
import CardViewEle from "@/components/Element/CareViewEle.vue";
import PackageTheme from "@/components/Element/PackageTheme.vue";
import PackageBannerEle from "@/components/Element/PackageBannerEle.vue";
import httpCommon from "@/util/http-common";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
export default {
  components: {
    BoardTopMenu,
    CardViewEle,
    PackageTheme,
    PackageBannerEle,
  },
  data() {
    return {
      word: "",
      menuTitles: ["서울", "경기", "강원", "충청", "전라", "경상", "제주"],
      themePackages: [],
      themeIdx: [-1, -1],
      themeTitle: [
        { title: "뚜벅이여도 괜찮ㅇr..😎 <br />제주 투어", region: "제주" },
        { title: "뭘 고를지 몰라 준비해봤어❤️ 색다른 서울", region: "서울" },
        { title: "물놀이의 계절🌊다모여 경기에서", region: "경기" },
        { title: "다시 봐도 가고 싶은<br />그 곳, 충청😎", region: "충청" },
        { title: "꽃향기 물씬 풍기는, 전라남도🌷", region: "전라" },
        { title: "마! 봄에는 경상 아이가!😎", region: "경상" },
        { title: " 산 좋고 물 좋은, 사시사철 강원🌲", region: "강원" },
      ],
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    mvPackageWrite() {
      this.$router.push({ name: "packageWrite" });
    },
    cardClick(idx) {
      this.$router.push({ name: "packageDetail", params: { packageId: idx } });
    },
    search() {
      if (this.word == "" || this.word == null) {
        return;
      }

      this.$router.push({ name: "packageList", params: { word: this.word } });
    },
    catclick(idx) {
      var token = sessionStorage.getItem("access-token");
      var decodeToken;
      var userId = 0;
      if (token != null && this.isLogin) {
        decodeToken = jwtDecode(token);
        userId = decodeToken.userId;
      }
      httpCommon
        .get("package/list/region", {
          params: {
            region: this.menuTitles[idx],
            userId: userId,
          },
        })
        .then(({ data }) => {
          this.themePackages = data;
        });
    },
    getRandomIntInclusive(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
  },
  created() {
    let token = sessionStorage.getItem("access-token");
    let userId = 0;
    let decodeToken;
    if (token != null && this.isLogin) {
      decodeToken = jwtDecode(token);
      userId = decodeToken.userId;
    }

    httpCommon
      .get("package/list/region", {
        params: { region: "서울", userId: userId },
      })
      .then(({ data }) => {
        this.themePackages = data;
      });
    this.themeIdx[0] = this.getRandomIntInclusive(0, 6);
    this.themeIdx[1] = this.getRandomIntInclusive(0, 6);
    while (this.themeIdx[0] == this.themeIdx[1]) {
      this.themeIdx[1] = this.getRandomIntInclusive(0, 6);
    }
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
  },
};
</script>

<style scoped>
.package-main-box {
  margin-bottom: 4rem;
}
.carousel-box {
  position: relative;
}
.carousel-search-form {
  position: absolute;
  bottom: 5%;
  right: 5%;
  z-index: 1;
}
.carousel-search-box {
  display: flex;
  flex-direction: row;
  justify-content: right;
  border-radius: 50px;
  border: 2px solid rgb(240, 108, 94);
  overflow: hidden;
}
.carousel-search-keyword-input {
  width: 25rem;
  text-align: center;
  font-weight: bold;
}
.carousel-icon-box {
  background-color: white;
  padding: 5px 1rem;
}
.package-hot-board-main-box {
  margin: 90px 8%;
}
.package-board-desc-png {
  margin-top: 90px;
  text-align: center;
}
.package-hot-board-desc-head {
  text-align: center;
  font-weight: bold;
  font-size: 2.5rem;
}
.package-hot-board-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

.search-icon {
  filter: invert(53%) sepia(86%) saturate(1808%) hue-rotate(324deg) brightness(100%) contrast(88%);
}
.evenTheme {
  background-color: rgba(211, 211, 211, 0.18);
}
.card-package-box {
  width: 25%;
  margin-top: 1rem;
  min-width: 300px;
  min-height: 350px;
  cursor: pointer;
}
.package-write-box {
  text-align: right;
  margin-right: 8%;
}
.package-write-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  padding: 0.5rem 1rem;
  border-radius: 5px;
  font-weight: bold;
  font-size: 1rem;
  border: 0;
}
</style>
