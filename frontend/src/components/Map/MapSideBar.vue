<template>
  <div class="sidebar" :style="cssProps">
    <div class="menu">
      <div class="menu__logo">
        <router-link :to="{ name: 'home' }" class="menu__logo--link"
          ><img src="@/assets/img/logo-small.png" class="menu__logo--img"
        /></router-link>
      </div>
      <side-bar-menu-item
        v-for="(item, index) in menu"
        :title="item.title"
        :icon="item.icon"
        :key="index"
      ></side-bar-menu-item>
    </div>
    <div class="content">
      <div class="title">
        <router-link :to="{ name: 'home' }">
          <div class="title__title">발밤발밤</div>
        </router-link>
        <div class="title__icon" @click="detailedSearch">
          <svg
            class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
            focusable="false"
            aria-hidden="true"
            viewBox="0 0 24 24"
            data-testid="ManageSearchOutlinedIcon"
          >
            <path
              d="M7 9H2V7h5v2zm0 3H2v2h5v-2zm13.59 7-3.83-3.83c-.8.52-1.74.83-2.76.83-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5c0 1.02-.31 1.96-.83 2.75L22 17.59 20.59 19zM17 11c0-1.65-1.35-3-3-3s-3 1.35-3 3 1.35 3 3 3 3-1.35 3-3zM2 19h10v-2H2v2z"
            ></path>
          </svg>
        </div>
      </div>
      <div class="search">
        <div class="search__area">
          <input
            class="search--input"
            @keyup.enter="keywordSearch"
            v-model="keyword"
            placeholder="어디로 떠나볼까요?"
          />
          <div class="search--icon" @click="keywordSearch">
            <svg
              class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
              focusable="false"
              aria-hidden="true"
              viewBox="0 0 24 24"
              data-testid="SearchOutlinedIcon"
            >
              <path
                d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
              ></path>
            </svg>
          </div>
        </div>
        <div class="search__region">
          <select
            class="region--sido"
            v-model="selectedSido"
            @change="getGugun($event.target.value)"
          >
            <option :value="index" :key="index" v-for="(item, index) in sido">
              {{ item.sidoName }}
            </option>
          </select>
          <select class="region--gugun" v-model="selectedGugun">
            <option :value="item.gugunCode" :key="index" v-for="(item, index) in gugun">
              {{ item.gugunName }}
            </option>
          </select>
        </div>
      </div>
      <div class="location">
        <div class="location__title">{{ location.area1 }} {{ location.area2 }}</div>
        <div class="location__left">
          <div class="location__weather">{{ location.weather }}</div>
          <div class="location__temperature">{{ location.temperature }}&#8451;</div>
        </div>
        <div class="location__right">
          <div class="location__dust">미세</div>
          <div class="location__dust--condition">{{ location.dust }}</div>
          <div class="location__dust">초미세</div>
          <div class="location__dust--condition">{{ location.dust2 }}</div>
        </div>
      </div>
      <attraction-list :attractions="attractions"></attraction-list>
    </div>
    <div class="button" @click="leftButton">
      <div class="arrow-prev"></div>
      <div class="arrow-next"></div>
    </div>
  </div>
</template>

<style scoped>
.sidebar {
  width: calc(var(--width) + 62px);
  transition: 1s;
  display: flex;
  height: 100vh;
  z-index: 1;
}

.menu {
  width: 62px;
  height: 100vh;
  border-right: 1px solid #dbdbdb;
  background-color: white;
  z-index: 2;
}

.menu__logo {
  display: flex;
  align-items: center;
  width: 62px;
  height: 80px;
  border-bottom: 1px solid #dbdbdb;
  margin-bottom: 15px;
}

.menu__logo--img {
  width: 62px;
  height: 62px;
}

.content {
  position: absolute;
  display: flex;
  flex-direction: column;
  width: 300px;
  height: 100vh;
  background-color: white;
  transition: 1s;
  box-shadow: 0 2px 2px 1px rgba(0, 0, 0, 0.3);
  left: calc(var(--width) + 62px - 300px);
}

.title {
  width: 100%;
  padding-inline: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 20px;
}

.title__logo {
  width: 35px;
  height: 35px;
}

.title__title {
  font-family: "EF_jejudoldam";
  font-size: 28px;
}

.title__icon {
  cursor: pointer;
  width: 31px;
  height: 31px;
}
.search {
  padding-top: 10px;
  transition: 0.5s;
  padding-bottom: calc(var(--searchHeight) / 3 + 5px);
  width: 100%;
  flex-direction: column;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #dbdbdb;
}

.search__area {
  border-radius: 7px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 260px;
  height: 44px;
  background-color: #f1f1f5;
}

.search--input {
  border: none;
  margin-left: 20px;
  font-size: 14px;
  background-color: inherit;
}

.search--input::placeholder {
  color: #767676;
}

.search--icon {
  cursor: pointer;
  margin-right: 15px;
  width: 24px;
  height: 24px;
}

.search__region {
  margin-top: 10px;
  display: flex;
  transition: 0.5s;
  overflow: hidden;
  height: var(--searchHeight);
}
.region--sido {
  border-radius: 7px;
  border: none;
  background-color: #f1f1f5;
  width: 125px;
  height: 30px;
  margin-right: 10px;
  padding-left: 10px;
}
.region--gugun {
  padding-left: 10px;
  border-radius: 7px;
  border: none;
  height: 30px;
  background-color: #f1f1f5;
  width: 125px;
}
.location {
  padding-top: 15px;
  width: 100%;
  padding-inline: 20px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  padding-bottom: 15px;
  border-bottom: 1px solid #dbdbdb;
}

.location__title {
  font-weight: bold;
  width: 100%;
  font-size: 16px;
}

.location__left,
.location__right {
  align-items: end;
  display: flex;
}

.location__weather {
  font-size: 14px;
  margin-right: 5px;
}

.location__temperature {
  font-size: 14px;
  margin-right: 50px;
}

.location__dust {
  font-size: 12px;
  margin-left: 5px;
}

.location__dust--condition {
  font-size: 12px;
  margin-left: 5px;
  color: #2196f3;
}

.button {
  cursor: pointer;
  transition: 1s;
  position: absolute;
  display: flex;
  align-items: center;
  left: calc(var(--width) + 62px);
  top: calc(50% - 20px);
  width: 18px;
  height: 40px;
  background-color: white;
  box-shadow: 2px 0 2px 0px rgba(0, 0, 0, 0.3);
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}

.arrow-prev {
  position: absolute;
  left: 6px;
  top: 15px;
  width: 10px; /* 사이즈 */
  height: 10px; /* 사이즈 */
  border-top: 1px solid #000; /* 선 두께 */
  border-right: 1px solid #000; /* 선 두께 */
  transform: rotate(225deg); /* 각도 */
  display: var(--prev);
}

.arrow-next {
  position: absolute;
  left: 1px;
  top: 15px;
  width: 10px; /* 사이즈 */
  height: 10px; /* 사이즈 */
  border-top: 1px solid #000; /* 선 두께 */
  border-right: 1px solid #000; /* 선 두께 */
  transform: rotate(45deg); /* 각도 */
  display: var(--next);
}
</style>
<script>
import AttractionList from "@/components/Map/AttractionList.vue";
import SideBarMenuItem from "@/components/Map/SideBarMenuItem.vue";
import CommonAxios from "@/util/http-common.js";

export default {
  name: "MapSideBar",
  data() {
    return {
      menu: [
        {
          title: "마이메뉴",
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="PersonOutlineOutlinedIcon"><path d="M12 5.9c1.16 0 2.1.94 2.1 2.1s-.94 2.1-2.1 2.1S9.9 9.16 9.9 8s.94-2.1 2.1-2.1m0 9c2.97 0 6.1 1.46 6.1 2.1v1.1H5.9V17c0-.64 3.13-2.1 6.1-2.1M12 4C9.79 4 8 5.79 8 8s1.79 4 4 4 4-1.79 4-4-1.79-4-4-4zm0 9c-2.67 0-8 1.34-8 4v3h16v-3c0-2.66-5.33-4-8-4z"></path></svg>`,
        },
        {
          title: "테마",
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="DescriptionOutlinedIcon"><path d="M8 16h8v2H8zm0-4h8v2H8zm6-10H6c-1.1 0-2 .9-2 2v16c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm4 18H6V4h7v5h5v11z"></path></svg>`,
        },
        {
          title: "지역",
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="LocationOnOutlinedIcon"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zM7 9c0-2.76 2.24-5 5-5s5 2.24 5 5c0 2.88-2.88 7.19-5 9.88C9.92 16.21 7 11.85 7 9z"></path><circle cx="12" cy="9" r="2.5"></circle></svg>`,
        },
        {
          title: "사진",
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="CameraAltOutlinedIcon"><path d="M20 4h-3.17L15 2H9L7.17 4H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 14H4V6h4.05l1.83-2h4.24l1.83 2H20v12zM12 7c-2.76 0-5 2.24-5 5s2.24 5 5 5 5-2.24 5-5-2.24-5-5-5zm0 8c-1.65 0-3-1.35-3-3s1.35-3 3-3 3 1.35 3 3-1.35 3-3 3z"></path></svg>`,
        },
        {
          title: "커뮤니티",
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="ForumOutlinedIcon"><path d="M15 4v7H5.17L4 12.17V4h11m1-2H3c-.55 0-1 .45-1 1v14l4-4h10c.55 0 1-.45 1-1V3c0-.55-.45-1-1-1zm5 4h-2v9H6v2c0 .55.45 1 1 1h11l4 4V7c0-.55-.45-1-1-1z"></path></svg>`,
        },
      ],
      location: {
        area1: "경기도",
        area2: "성남시",
        weather: "맑음",
        temperature: 25,
        dust: "좋음",
        dust2: "좋음",
      },
      keyword: "",
      selectedSido: null,
      selectedGugun: null,
      showLeft: true,
      showSearch: false,
      sido: [
        {
          sidoName: "지역 전체",
          sidoCode: "0",
        },
      ],
      gugun: [
        {
          gugunName: "지역 상세",
          gugunCode: "",
        },
      ],
    };
  },
  props: {
    attractions: {
      type: Array,
      default: () => [],
    },
  },
  components: {
    AttractionList,
    SideBarMenuItem,
  },
  methods: {
    leftButton() {
      this.showLeft = !this.showLeft;
    },
    detailedSearch() {
      this.showSearch = !this.showSearch;
    },
    getGugun(index) {
      let code = this.sido[index].sidoCode;
      let sidoName = this.sido[index].sidoName;
      if (code == 0) {
        this.gugun = [
          {
            gugunName: "지역 상세",
            gugunCode: "",
          },
        ];
        return;
      }
      CommonAxios.get(`/attraction/gugun/${code}`).then(({ data }) => {
        let gugun = [
          {
            gugunCode: 0,
            gugunName: `${sidoName} 전체`,
            sidoCode: code,
          },
        ];
        this.gugun = gugun.concat(data);
      });
    },
    keywordSearch() {
      let sidoCode = 0;
      if (this.selectedSido !== null) {
        sidoCode = this.sido[this.selectedSido].sidoCode;
      }

      this.$emit("keywordSearch", this.keyword, sidoCode, this.selectedGugun);
    },
  },
  computed: {
    cssProps() {
      let css = {};
      if (this.showLeft) {
        css["--width"] = "300px";
        css["--prev"] = "block";
        css["--next"] = "none";
      } else {
        css["--width"] = "0px";
        css["--prev"] = "none";
        css["--next"] = "block";
      }

      if (this.showSearch) {
        css["--searchHeight"] = "30px";
      } else {
        css["--searchHeight"] = "0px";
      }
      return css;
    },
  },
  created() {
    navigator.geolocation.getCurrentPosition(({ coords }) => {
      CommonAxios.get(
        `/attraction/address?longitude=${coords.longitude}&latitude=${coords.latitude}`
      ).then(({ data }) => {
        this.location.area1 = data.results[0].region.area1.name;
        this.location.area2 = data.results[0].region.area2.name;
      });
    });
    // const CLIENT_ID = "v8fa47ot12";
    // const CLIENT_SECRET = "uIS6KyFlILy04F0OQsyXgbmN0PN3OensM1Bk4nEF";

    // navigator.geolocation
    //   .getCurrentPosition(({ coords }) => {
    //     console.log(coords);
    //     CommonAxios.get(`https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc`, {
    //       params: {
    //         coords: coords.longitude + "," + coords.latitude,
    //         sourcecrs: "default",
    //         orders: "legalcode",
    //         output: "json",
    //       },
    //       headers: {
    //         "X-NCP-APIGW-API-KEY-ID": CLIENT_ID,
    //         "X-NCP-APIGW-API-KEY": CLIENT_SECRET,
    //       },
    //     });
    //   })
    //   .then((response) => {
    //     console.log(response);
    //   }).catch((error) => {
    //     console.log(error.response);
    //   });

    CommonAxios.get(`/attraction/sido`).then(({ data }) => {
      this.sido = this.sido.concat(data);
    });
  },
};
</script>
