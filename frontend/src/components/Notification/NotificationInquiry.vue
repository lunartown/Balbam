<template>
  <div class="notification-list">
    <div class="header-div">
      <div class="notification-title">자주 찾는 질문</div>
      <div class="search-form">
        <input class="word" type="text" v-model="inputData" placeholder="제목를 입력해주세요." />
        <button class="search-submit" @click="searchBtn()">검색</button>
      </div>
    </div>
    <div class="notifcation-table-box">
      <table>
        <tbody>
          <div
            class="inquiry-box"
            :class="[{ active: selected == index ? true : false }]"
            v-for="(question, index) in questions"
            :key="index"
            @click="rowClick(index)"
          >
            <tr>
              <td class="inquiry-icon-first">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="24"
                  viewBox="0 0 24 24"
                  width="24"
                  class="not-active-icon"
                >
                  <path d="M0 0h24v24H0z" fill="none" />
                  <path
                    d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 17h-2v-2h2v2zm2.07-7.75l-.9.92C13.45 12.9 13 13.5 13 15h-2v-.5c0-1.1.45-2.1 1.17-2.83l1.24-1.26c.37-.36.59-.86.59-1.41 0-1.1-.9-2-2-2s-2 .9-2 2H8c0-2.21 1.79-4 4-4s4 1.79 4 4c0 .88-.36 1.68-.93 2.25z"
                  />
                </svg>
              </td>
              <td class="inquiry-title">
                <div>{{ question.title }}</div>
              </td>
              <td class="inquiry-arrow-icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="36"
                  viewBox="0 0 24 24"
                  width="36"
                  class="not-active-icon"
                >
                  <path d="M0 0h36v36H0z" fill="none" />
                  <path d="M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6z" />
                </svg>
              </td>
            </tr>
            <div class="inquiry-answer" v-if="selected == index ? true : false">
              <div>
                <svg xmlns="http://www.w3.org/2000/svg" height="36" viewBox="0 0 24 24" width="36">
                  <path d="M0 0h36v36H0z" fill="none" />
                  <path
                    d="M18 11v2h4v-2h-4zm-2 6.61c.96.71 2.21 1.65 3.2 2.39.4-.53.8-1.07 1.2-1.6-.99-.74-2.24-1.68-3.2-2.4-.4.54-.8 1.08-1.2 1.61zM20.4 5.6c-.4-.53-.8-1.07-1.2-1.6-.99.74-2.24 1.68-3.2 2.4.4.53.8 1.07 1.2 1.6.96-.72 2.21-1.65 3.2-2.4zM4 9c-1.1 0-2 .9-2 2v2c0 1.1.9 2 2 2h1v4h2v-4h1l5 3V6L8 9H4zm11.5 3c0-1.33-.58-2.53-1.5-3.35v6.69c.92-.81 1.5-2.01 1.5-3.34z"
                  />
                </svg>
              </div>
              <div class="inquriy-answer-content" v-html="question.content"></div>
            </div>
          </div>
        </tbody>
      </table>
    </div>
    <div class="paging-box">
      <pagination :page="page" @onPage="onPage" v-if="this.questions.length !== 0"></pagination>
    </div>
  </div>
</template>

<script>
import pagination from "@/components/Element/PaginationEle.vue";
import httpCommon from "@/util/http-common";
export default {
  components: { pagination },
  data() {
    return {
      questions: [],
      page: {
        total: 0,
        page: 1,
        count: 10,
      },
      selected: -1,
      key: "title",
      inputData: "",
      word: "",
      isSearch: false,
    };
  },
  methods: {
    rowClick(index) {
      if (this.selected == index) {
        this.selected = -1;
      } else {
        this.selected = index;
      }
    },
    onPage(page) {
      this.page.page = page;
      httpCommon
        .get("notification/question", {
          params:
            this.isSearch == false
              ? { currentPage: this.page.page }
              : {
                  currentPage: this.page.page,
                  key: this.key,
                  word: this.word,
                },
        })
        .then(({ data }) => {
          this.questions = data;
        });
      this.selected = -1;
    },
    mvInquiryWrite() {
      this.$router.push({ name: "notificationInquiryWrite" });
    },
    searchBtn() {
      if (this.inputData == "") return;
      this.isSearch = true;
      this.word = this.inputData;
      this.page.page = 1;
      httpCommon
        .get("notification/question/count", {
          params: {
            key: this.key,
            word: this.word,
          },
        })
        .then(({ data }) => {
          this.page.total = data;
        });
      httpCommon
        .get("notification/question", {
          params: {
            currentPage: this.page.page,
            key: this.key,
            word: this.word,
          },
        })
        .then(({ data }) => {
          this.questions = data;
        });
    },
  },
  created() {
    httpCommon.get("notification/question/count").then(({ data }) => {
      this.page.total = data;
    });
    httpCommon
      .get("notification/question", { params: { currentPage: this.page.page } })
      .then(({ data }) => {
        this.questions = data;
      });
  },
};
</script>

<style scoped>
.inquiry-table-box {
  text-align: left;
}
.notification-list {
  padding-left: 50px;
  width: 1000px;
  font-family: "Noto Sans KR", sans-serif;
}
.header-div {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.notification-title {
  color: black;
  font-weight: bold;
  font-size: 1.3rem;
}
.search-form {
  display: flex;
  flex-direction: row;
}
.word {
  border-radius: 5px;
  border: 1px solid lightgray;
  padding: 5px;
  margin-right: 0.5rem;
}
.search-submit {
  border-radius: 5px;
  border: 1px solid lightgray;
  color: rgb(240, 108, 94);
  font-weight: bold;
  background-color: white;
  padding: 0 1rem;
}
.notifcation-table-box {
  margin: 30px 0px;
  text-align: center;
}
.notifcation-table-box > table {
  width: 100%;
  border-top: 1px solid rgb(0, 0, 0);
  border-collapse: collapse;
}
.notifcation-table-box > table > tbody > tr:hover {
  background-color: rgb(247, 247, 247);
}
td {
  border-bottom: 1px solid rgb(221, 221, 221);
  padding: 1rem;
  vertical-align: middle;
  border-left: none;
}
.not-active-icon {
  filter: invert(83%) sepia(0%) saturate(3657%) hue-rotate(29deg) brightness(140%) contrast(73%);
}
.inquiry-box {
  display: flex;
  flex-direction: column;
}
.inquiry-title {
  text-align: left;
  width: 100%;
}

.active svg {
  filter: invert(0%) sepia(0%) saturate(7475%) hue-rotate(302deg) brightness(97%) contrast(98%);
}
.active td {
  border-bottom: none;
}
.active {
  border-bottom: 1px solid rgb(221, 221, 221);
}
.inquiry-answer {
  display: flex;
  flex-direction: row;
  padding: 2rem;
}
.inquiry-answer svg {
  filter: invert(20%) sepia(32%) saturate(6712%) hue-rotate(255deg) brightness(84%) contrast(96%);
}
.inquriy-answer-content {
  margin-left: 2.5rem;
  text-align: left;
}
.paging-box {
  display: flex;
  justify-content: center;
  margin-bottom: 5rem;
}
.inquiry-arrow-icon {
  width: 25%;
}
.inquiry-icon-first {
  width: 10%;
}
</style>
