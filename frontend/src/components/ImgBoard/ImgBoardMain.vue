<template>
  <div class="img-board-main-box">
    <div>
      <div class="img-board-desc-png">
        <img src="@/assets/img/imgBoardHead.png" alt="" />
      </div>
      <div class="img-board-desc-head">
        가장 사랑받은<br />
        사진들
      </div>
    </div>
    <board-top-menu :titles="menuTitles" @catClick="catclick"></board-top-menu>
    <div class="img-hot-board-box">
      <div
        class="card-img-theme-box"
        v-for="(article, index) in regionArticles"
        :key="index"
        @click="cardClick(article.articleId)"
      >
        <card-img-view-ele :article="article"></card-img-view-ele>
      </div>
    </div>
    <div>
      <div class="img-board-desc-head">
        사람들이 기억한<br />
        추억들
      </div>
      <form action="" class="img-board-search-form">
        <input
          type="text"
          placeholder="여행지, 장소를 검색해 보세요."
          class="search-keyword-input"
          v-model="inputData"
        />
        <div class="icon-box">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="36"
            viewBox="0 0 24 24"
            width="36"
            class="search-icon"
            @click="searchBtn"
          >
            <path d="M0 0h36v36H0z" fill="none" />
            <path
              d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
            />
          </svg>
        </div>
      </form>
    </div>
    <div class="img-hot-board-box">
      <div
        class="card-img-box"
        @click="cardClick(article.articleId)"
        v-for="(article, index) in articles"
        :key="index"
      >
        <card-img-view-ele :article="article"></card-img-view-ele>
      </div>
    </div>
    <div class="img-upload-box">
      <button class="img-upload-btn" @click="mvImgAritlceWrite()">
        사진 올리기
      </button>
    </div>
    <div class="page-box">
      <pagination
        :page="page"
        @onPage="onPage"
        v-if="articles.length !== 0"
      ></pagination>
    </div>
  </div>
</template>

<script>
import BoardTopMenu from "@/components/Element/BoardTopMenu.vue";
import CardImgViewEle from "@/components/Element/CardImgViewEle.vue";
import Pagination from "@/components/Element/PaginationEle.vue";
import httpCommon from "@/util/http-common";
export default {
  components: {
    BoardTopMenu,
    CardImgViewEle,
    Pagination,
  },
  data() {
    return {
      menuTitles: ["서울", "경기", "강원", "충청", "전라", "경상", "제주"],
      regionArticles: [],
      articles: [],
      page: {
        total: 0,
        page: 1,
        count: 10,
      },
      isSearch: false,
      inputData: "",
      word: "",
    };
  },
  methods: {
    async cardClick(idx) {
      await httpCommon
        .post("img/article/plus/view", { articleId: idx })
        .then(() => {
          this.$router.push({
            name: "imgBoardDetail",
            params: { articleId: idx },
          });
        });
    },
    async searchBtn() {
      if (this.inputData == "") return;
      this.isSearch = true;
      this.word = this.inputData;
      this.page.page = 1;
      await httpCommon
        .get("img/article/list/count", {
          params: {
            word: this.word,
          },
        })
        .then(({ data }) => {
          this.page.total = data;
        });
      await httpCommon
        .get("img/article/list", {
          params: {
            currentPage: this.page.page,
            word: this.word,
            totalCount: this.page.total,
          },
        })
        .then(({ data }) => {
          this.articles = data;
        });
    },
    onPage(page) {
      this.page.page = page;
      httpCommon
        .get("img/article/list", {
          params:
            this.isSearch == false
              ? {
                  currentPage: this.page.page,
                  totalCount: this.page.total,
                }
              : {
                  currentPage: this.page.page,
                  word: this.word,
                  totalCount: this.page.total,
                },
        })
        .then(({ data }) => {
          this.articles = data;
        });
    },
    mvImgAritlceWrite() {
      this.$router.push({ name: "imgBoardWrite" });
    },
    catclick(idx) {
      httpCommon
        .get("img/article/list/region", {
          params: { region: this.menuTitles[idx] },
        })
        .then(({ data }) => {
          this.regionArticles = data;
        });
    },
    async getArticle() {
      await httpCommon.get("img/article/list/count").then(({ data }) => {
        this.page.total = data;
      });
      await httpCommon
        .get("img/article/list", {
          params: {
            currentPage: this.page.page,
            totalCount: this.page.total,
          },
        })
        .then(({ data }) => {
          this.articles = data;
        });
    },
  },
  created() {
    httpCommon
      .get("img/article/list/region", { params: { region: "서울" } })
      .then(({ data }) => {
        this.regionArticles = data;
      });
    this.getArticle();
  },
};
</script>

<style scoped>
.img-board-main-box {
  margin: 45px 8%;
}
.img-board-desc-png {
  text-align: center;
}
.img-board-desc-head {
  text-align: center;
  font-weight: bold;
  font-size: 2.5rem;
}
.img-hot-board-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
  margin-bottom: 3rem;
}
.img-board-search-form {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin: 3rem 0;
}
.search-keyword-input {
  border-radius: 50px;
  border: 2px solid rgb(240, 108, 94);
  padding: 1rem;
  width: 30%;
  font-size: 1rem;
  text-align: center;
}
.icon-box {
  margin: auto 0 auto 1rem;
}
.search-icon {
  filter: invert(53%) sepia(86%) saturate(1808%) hue-rotate(324deg)
    brightness(100%) contrast(88%);
}
.page-box {
  margin-top: 3rem;
  display: flex;
  justify-content: center;
}
.card-img-theme-box {
  width: 25%;
  margin-top: 1rem;
  min-width: 300px;
  min-height: 350px;
}
.card-img-box {
  width: 20%;
  margin-top: 1rem;
  min-width: 250px;
  min-height: 300px;
}
.img-upload-box {
  text-align: right;
}
.img-upload-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  padding: 0.5rem 1rem;
  border-radius: 5px;
  font-weight: bold;
  font-size: 1rem;
  border: 0;
}
</style>
