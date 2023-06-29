<template>
  <div class="home-content">
    <div class="container">
      <div class="package-box">
        <div class="box-desc">지금 놓치면 후회할 ✨완전특가✨ 모음집!!</div>
        <div class="package-card-box">
          <div v-for="(pack, index) in packages" :key="index" @click="packageClick(pack.packageId)">
            <main-card-view-ele :pack="pack"></main-card-view-ele>
          </div>
        </div>
      </div>
      <div class="region-box">
        <div class="region-left-box">
          <div class="region-desc">
            우리나라 <br />
            감성을 담은 <br />
            지역의 정석!
          </div>
          <div class="region-btn-box">
            <button
              class="region-btn"
              :class="{ active: btn.idx == regionSelected ? true : false }"
              v-for="btn in btns"
              :key="btn.idx"
              @click="regionBtnClick(btn.idx)"
            >
              {{ btn.text }}
            </button>
          </div>
        </div>
        <div class="region-card-box">
          <card-circle-view-ele
            v-for="(region, index) in regions[regionSelected]"
            :key="index"
            :region="region"
            class="region-card"
          ></card-circle-view-ele>
        </div>
      </div>
      <div class="img-box">
        <div class="box-desc">우리나라 구석구석✨✨</div>
        <div class="img-card-box">
          <div
            class="card-img-theme-box"
            v-for="(img, index) in imgs"
            @click="cardClick(img.articleId)"
            :key="index"
          >
            <card-img-view-ele :article="img"></card-img-view-ele>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainCardViewEle from "@/components/Element/MainCardViewEle.vue";
import CardImgViewEle from "@/components/Element/CardImgViewEle.vue";
import CardCircleViewEle from "../Element/CardCircleViewEle.vue";
import httpCommon from "@/util/http-common";
export default {
  components: { MainCardViewEle, CardImgViewEle, CardCircleViewEle },
  data() {
    return {
      packages: [],
      imgs: [],
      btns: [
        {
          text: "알수록 새로운 경기도🌈의 매력",
          idx: 0,
        },
        {
          text: "워터슬라이드🌊의 천국 부산",
          idx: 1,
        },
        {
          text: "여름을 더 여름답게!! 🌞대구",
          idx: 2,
        },
        {
          text: "색다른 매력이 넘치는 인천 🗽",
          idx: 3,
        },
      ],
      regionSelected: 0,
      regions: [
        [
          {
            title: "모두를 연결하는, 성남",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnvbUZ4LOa9ktSk3Fy03OAIh_wLXXc6pxUCA&usqp=CAU",
          },
          {
            title: "모두를 연결하는, 양평",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZZZKgUeMRBSP8GIppH3_AWeXD7Xex_AndJQ&usqp=CAU",
          },
          {
            title: "모두를 연결하는, 가평",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRg6pJGktjpCRfG8t2LV2JnOiMqbPXzN5vfA&usqp=CAU",
          },
        ],
        [
          {
            title: "모두를 연결하는, 송도",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://newsimg.sedaily.com/2020/09/04/1Z7Q4JK6X1_1.jpg",
          },
          {
            title: "모두를 연결하는, 해운대",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://i.namu.wiki/i/hkDOgJHC40yiIFKQDRz7YjHpzWrL9vCTT7mve4TF6Lj-GpGsBpvT8WlXbwOT_To1Ndl1zKrVLQ-SiwaGNFOgQA.webp",
          },
          {
            title: "모두를 연결하는, 광안리",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://www.visitbusan.net/uploadImgs/files/cntnts/20191229160530047_oen",
          },
        ],
        [
          {
            title: "모두를 연결하는, 83타워",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://res.klook.com/image/upload/c_fill,w_750,h_500/q_80/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/lbqcpieemzt3jhun9b9q.jpg",
          },
          {
            title: "모두를 연결하는, 동성로 스파크",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFPN8460kkIbtV6omZfD7wiCTLxbkenqnIFg&usqp=CAU",
          },
          {
            title: "모두를 연결하는, 앞산전망대",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ0BmfzHinjU12UrzLkM3sWIUwfNn0fwp4hA&usqp=CAU",
          },
        ],
        [
          {
            title: "모두를 연결하는, 인천 대공원",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/08/f0/9a/04/caption.jpg?w=300&h=300&s=1",
          },
          {
            title: "모두를 연결하는, 마시안 해변",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/8a/2e/54/photo7jpg.jpg?w=300&h=300&s=1",
          },
          {
            title: "모두를 연결하는, 월미도",
            content: "영화제, 해수욕장, 다양한 축제\n 대한민국 대표 항만도시🏆",
            img: "https://a.cdn-hotels.com/gdcs/production83/d1707/30fea6ff-a4f9-4327-89df-422de3214ff4.jpg?impolicy=fcrop&w=1600&h=1066&q=medium",
          },
        ],
      ],
    };
  },
  methods: {
    regionBtnClick(idx) {
      this.regionSelected = idx;
    },
    async cardClick(idx) {
      await httpCommon.post("img/article/plus/view", { articleId: idx }).then(() => {
        this.$router.push({
          name: "imgBoardDetail",
          params: { articleId: idx },
        });
      });
    },
    packageClick(idx) {
      this.$router.push({
        name: "packageDetail",
        params: { packageId: idx },
      });
    },
  },
  created() {
    httpCommon.get("/package/list/random").then(({ data }) => {
      this.packages = data;
    });
    httpCommon.get("/img/article/list/random").then(({ data }) => {
      this.imgs = data;
    });
  },
};
</script>

<style scoped>
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
.home-content {
  display: flex;
  justify-content: center;
  width: 100%;
}
.container {
  position: relative;
  width: 1280px;
}
.box-desc {
  padding-bottom: 30px;
  font-weight: bold;
  font-size: 26px;
}
.package-card-box {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.img-card-box {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.package-box,
.region-box,
.img-box {
  width: 100%;
  margin-bottom: 40px;
}
.region-box {
  display: flex;
  flex-direction: row;
  justify-content: start;
  overflow: hidden;
}
.region-left-box {
  width: 400px;
  background-image: url("@/assets/img/main-region.png");
  background-size: cover;
  background-repeat: no-repeat;
}
.region-desc {
  font-size: 26px;
  font-weight: bold;
  color: rgb(255, 255, 255);
  padding: 2rem;
}
.region-btn-box {
  margin: 4rem;
  display: flex;
  flex-direction: column;
}
.region-card-box {
  width: 980px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 4rem 2px 4rem -100px;
  background-color: white;
}
.region-btn {
  background-color: white;
  border-radius: 1rem;
  padding: 5px 1rem;
  margin: 5px 0;
  width: 80%;
}
.region-card {
  height: 410px;
  width: 280px;
  background-color: #fff;
  box-shadow: 0px 1px 10px 0px rgba(0, 0, 0, 0.06);
  border: 1px solid rgb(234, 234, 234);
}
.active {
  background: black;
  color: white;
}
.card-img-theme-box {
  min-height: 350px;
}
</style>
