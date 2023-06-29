<template>
  <div class="board-main-box">
    <div class="container">
      <div class="sel-cat-text">{{ selectedCat }}</div>
      <board-top-menu :titles="menus" @catClick="catClick"></board-top-menu>
      <form action="" class="search-form">
        <div class="selectBox">
          <select name="" class="select" v-model="key">
            <option :value="option.value" v-for="(option, index) in options" :key="index">
              {{ option.name }}
            </option>
          </select>
          <span class="icoArrow"><img src="@/assets/img/icon/arrow-down-icon.png" /></span>
        </div>
        <input class="word" type="text" placeholder="검색어를 입력해주세요." v-model="inputData" />
        <div class="icon-box" @click="searchBtn()">
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
      </form>
      <table>
        <thead>
          <tr>
            <th>No.</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>작성시간</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr
            @click="rowClick(article.articleId)"
            v-for="(article, index) in articles"
            :key="index"
          >
            <td class="article-no">{{ (page.page - 1) * 10 + index + 1 }}</td>
            <td class="article-category">{{ article.category }}</td>
            <td>{{ article.title }}</td>
            <td class="article-name">{{ article.userName }}</td>
            <td class="article-date">{{ article.createTime }}</td>
            <td class="article-view">{{ article.view }}</td>
          </tr>
        </tbody>
      </table>
      <div class="write-btn-box">
        <button class="write-btn" @click="mvWrite()">글쓰기</button>
      </div>
      <div class="paging-box">
        <pagination :page="page" @onPage="onPage" v-if="this.articles.length !== 0"></pagination>
      </div>
    </div>
  </div>
</template>

<script>
import BoardTopMenu from "@/components/Element/BoardTopMenu.vue";
import Pagination from "@/components/Element/PaginationEle.vue";
import httpCommon from "@/util/http-common";
export default {
  components: {
    BoardTopMenu,
    Pagination,
  },
  data() {
    return {
      menus: ["전체", "잡담", "후기", "추천", "질문"],
      selectedCat: "전체",
      options: [
        { value: "title", name: "제목" },
        { value: "name", name: "작성자" },
        { value: "content", name: "내용" },
      ],
      articles: [],
      page: {
        total: 0,
        page: 1,
        count: 10,
      },
      key: "",
      inputData: "",
      word: "",
      isSearch: false,
      cat: "",
    };
  },
  methods: {
    catClick(idx) {
      this.key = "";
      this.inputData = "";
      this.selectedCat = this.menus[idx];
      if (idx == 0) this.cat = "";
      else this.cat = this.menus[idx];
      this.page.page = 1;
      this.getArticle();
    },
    searchBtn() {
      if (this.inputData == "") return;
      this.isSearch = true;
      this.word = this.inputData;
      this.page.page = 1;
      httpCommon
        .get("article/list/count", {
          params: {
            key: this.key,
            word: this.word,
            catOrRegion: this.cat,
          },
        })
        .then(({ data }) => {
          this.page.total = data;
        });
      httpCommon
        .get("article/list", {
          params: {
            currentPage: this.page.page,
            key: this.key,
            word: this.word,
            catOrRegion: this.cat,
            totalCount: this.page.total,
          },
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            let date = new Date(data[i].createTime);
            data[i].createTime = this.formatDate(date);
          }
          this.articles = data;
        });
    },
    async rowClick(id) {
      await httpCommon.post("article/plus/view", { articleId: id }).then(() => {
        this.$router.push({ name: "boardDetail", params: { articleId: id } });
      });
    },
    mvWrite() {
      this.$router.push({ name: "boardWrite" });
    },
    onPage(page) {
      this.page.page = page;
      httpCommon
        .get("article/list", {
          params:
            this.isSearch == false
              ? {
                  currentPage: this.page.page,
                  catOrRegion: this.cat,
                  totalCount: this.page.total,
                }
              : {
                  currentPage: this.page.page,
                  catOrRegion: this.cat,
                  key: this.key,
                  word: this.word,
                  totalCount: this.page.total,
                },
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            let date = new Date(data[i].createTime);
            data[i].createTime = this.formatDate(date);
          }
          this.articles = data;
        });
    },
    async getArticle() {
      await httpCommon
        .get("article/list/count", { params: { catOrRegion: this.cat } })
        .then(({ data }) => {
          this.page.total = data;
        });
      await httpCommon
        .get("article/list", {
          params: {
            currentPage: this.page.page,
            catOrRegion: this.cat,
            totalCount: this.page.total,
          },
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            let date = new Date(data[i].createTime);
            data[i].createTime = this.formatDate(date);
          }
          this.articles = data;
        });
    },
    formatDate(date) {
      return (
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1 > 9 ? (date.getMonth() + 1).toString() : "0" + (date.getMonth() + 1)) +
        "-" +
        (date.getDate() > 9 ? date.getDate().toString() : "0" + date.getDate().toString())
      );
    },
  },
  created() {
    this.getArticle();
  },
};
</script>

<style scoped>
.board-main-box {
  display: flex;
  justify-content: center;
  width: 100%;
}
.container {
  margin-top: 40px;
  display: flex;
  width: 1280px;
  flex-direction: column;
}
.sel-cat-text {
  font-weight: bold;
  text-align: center;
  font-size: 3rem;
  text-decoration: underline 4px rgb(240, 108, 94);
  text-underline-position: under;
}
table {
  border-collapse: collapse;
}
th,
td {
  text-align: center;
  padding: 1rem;
}
th {
  border-top: 2px solid black;
  border-bottom: 1px solid lightgray;
  font-weight: bold;
}
tr {
  border-bottom: 1px solid lightgray;
}
.board-search-form {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin: 3rem 0;
}
.icon-box {
  margin: auto 0 auto 1rem;
}
.search-icon {
  filter: invert(53%) sepia(86%) saturate(1808%) hue-rotate(324deg) brightness(100%) contrast(88%);
}
select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  font-weight: bold;
}
.selectBox {
  position: relative;
  width: 150px;
  height: 41px;
  border-radius: 5px;
  border: 1px solid lightgray;
  margin-right: 0.5rem;
}
.selectBox .select {
  width: inherit;
  height: inherit;
  background: transparent;
  border: 0 none;
  outline: 0 none;
  padding: 0 5px;
  position: relative;
  z-index: 3;
}
.selectBox .icoArrow {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 1;
  width: 35px;
  height: inherit;
  display: flex;
  justify-content: center;
  align-items: center;
}

.selectBox .icoArrow img {
  width: 50%;
  transition: 0.3s;
}

.selectBox .select:focus + .icoArrow img {
  transform: rotate(180deg);
}
.select:focus {
  border: 1px solid rgb(240, 108, 94);
}
.search-form {
  display: flex;
  flex-direction: row;
  justify-content: right;
  margin: 20px 0;
}
.word {
  border-radius: 5px;
  border: 1px solid lightgray;
  padding: 5px;
  margin-right: 0.5rem;
  font-weight: bold;
}
.write-btn-box {
  display: flex;
  justify-content: right;
  margin-top: 2rem;
}
.write-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  border-radius: 5px;
  padding: 1rem 2rem;
  font-weight: bold;
  border: 0;
}
.paging-box {
  display: flex;
  justify-content: center;
}
.article-no {
  width: 5%;
}
.article-category,
.article-view,
.article-name {
  width: 10%;
}
.article-date {
  width: 15%;
}
</style>
