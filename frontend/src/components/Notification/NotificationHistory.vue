<template>
  <div class="notification-list">
    <div class="header-div">
      <div class="notification-title">문의내역</div>
      <div class="mv-write-box">
        <button class="write-btn" @click="mvInquiryWrite()" v-if="!isAdmin">
          문의하기
        </button>
      </div>
    </div>
    <div class="notifcation-table-box">
      <table>
        <tbody>
          <div
            class="inquiry-box"
            :class="[{ active: selected == index ? true : false }]"
            v-for="(inquiry, index) in inquirys"
            :key="index"
            @click="rowClick(index)"
          >
            <tr class="history-tr">
              <td class="inquiry-icon-first">
                <div
                  v-if="inquiry.answerContent == null ? true : false"
                  class="inquiry-process process-wait"
                >
                  답변대기
                </div>
                <div
                  v-if="inquiry.answerContent == null ? false : true"
                  class="inquiry-process process-complete"
                >
                  답변완료
                </div>
              </td>
              <td class="inquiry-title">
                <div>{{ inquiry.title }}</div>
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
            <div v-if="selected == index ? true : false">
              <div class="inquiry-content-box">
                <div class="inquiry-date">{{ inquiry.createTime }}</div>
                <tip-tap-content-ele
                  :description="inquiry.content"
                ></tip-tap-content-ele>
                <div style="text-align: right">
                  <button class="inquiry-btn" @click="mvInquiryUpdate(index)">
                    수정하기
                  </button>
                  <button
                    class="inquiry-btn"
                    @click="mvAnswerWrite(inquiry.inquiryId)"
                    v-if="inquiry.answerContent == null && isAdmin"
                  >
                    답변하기
                  </button>
                </div>
              </div>
              <div
                class="inquiry-answer-box"
                v-if="inquiry.answerContent == null ? false : true"
              >
                <hr class="hr-dot" />
                <div class="inquiry-author-box">
                  <div class="inquiry-answer-author">
                    {{ inquiry.answerAuthor }}
                  </div>
                  <div class="inquiry-date">{{ inquiry.answerTime }}</div>
                </div>

                <tip-tap-content-ele
                  :description="inquiry.answerContent"
                ></tip-tap-content-ele>
                <div style="text-align: right">
                  <button class="inquiry-btn" @click="mvAnswerUpdate(index)">
                    수정하기
                  </button>
                </div>
              </div>
            </div>
          </div>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import httpCommon from "@/util/http-common";
import TipTapContentEle from "@/components/Element/TopTapContentEle.vue";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
export default {
  components: {
    TipTapContentEle,
  },
  data() {
    return {
      inquirys: [],
      selected: -1,
      isAdmin: false,
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    rowClick(index) {
      if (this.selected == index) {
        this.selected = -1;
      } else {
        this.selected = index;
      }
    },
    mvInquiryWrite() {
      this.$router.push({ name: "notificationInquiryWrite" });
    },
    mvAnswerWrite(idx) {
      this.$router.push({
        name: "notificationAnswerWrite",
        params: { inquiryId: idx },
      });
    },
    mvInquiryUpdate(idx) {
      this.$router.push({
        name: "notificationInquiryUpdate",
        params: { inquiry: this.inquirys[idx] },
      });
    },
    mvAnswerUpdate(idx) {
      this.$router.push({
        name: "notificationInquiryAnswerUpdate",
        params: { answer: this.inquirys[idx] },
      });
    },
  },
  created() {
    let token = sessionStorage.getItem("access-token");
    let decodeToken = jwtDecode(token);
    if (this.isLogin) {
      this.getUserInfo(token);
      if (this.userInfo.admin != 0) {
        this.isAdmin = true;
        httpCommon
          .get("notification/inquiry", {
            params: { userId: 0 },
          })
          .then(({ data }) => {
            this.inquirys = data;
          });
      } else {
        httpCommon
          .get("notification/inquiry", {
            params: { userId: decodeToken.userId },
          })
          .then(({ data }) => {
            this.inquirys = data;
          });
      }
    }
  },
  computed: {
    ...mapState("userStore", [
      "isLogin",
      "isLoginError",
      "userInfo",
      "errorType",
    ]),
  },
};
</script>

<style scoped>
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
.mv-write-box {
  display: flex;
  justify-content: flex-end;
  margin-top: 1rem;
}
.write-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  border-radius: 5px;
  padding: 1rem 2rem;
  font-weight: bold;
  border: 0px;
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
  filter: invert(83%) sepia(0%) saturate(3657%) hue-rotate(29deg)
    brightness(140%) contrast(73%);
}
.inquiry-box {
  display: flex;
  flex-direction: column;
}
.inquiry-title {
  text-align: left;
  width: 100%;
}
.inquiry-process {
  border-radius: 1rem;
  padding: 5px 10px;
  color: white;
}
.process-wait {
  background-color: gray;
}
.process-complete {
  background-color: rgb(240, 108, 94);
}

.active svg {
  filter: invert(0%) sepia(0%) saturate(7475%) hue-rotate(302deg)
    brightness(97%) contrast(98%);
}
.active td {
  border-bottom: none;
}
.active {
  border-bottom: 1px solid rgb(221, 221, 221);
}
.inquiry-content-box {
  text-align: left;
  padding: 1rem 2rem;
}
.inquiry-answer svg {
  filter: invert(20%) sepia(32%) saturate(6712%) hue-rotate(255deg)
    brightness(84%) contrast(96%);
}
.inquiry-answer-content {
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
  width: 15%;
}
.inquiry-author-box {
  display: flex;
  flex-direction: row;
}
.inquiry-author-box > * {
  margin: auto 1rem auto 0;
  font-weight: bold;
}
.inquiry-inquiry-content {
  padding-top: 1rem;
}
.inquiry-answer-author {
  color: rgb(240, 108, 94);
}
.inquiry-content {
  padding-top: 1rem;
  text-align: left;
}
.inquiry-answer-box {
  padding: 0 2rem 2rem 2rem;
}
.hr-dot {
  border: 1px dashed gray;
  margin: 1rem 0;
}
.inquiry-date {
  color: gray;
}
.inquiry-btn {
  padding: 0.5rem 1rem;
  margin-left: 1rem;
  margin-bottom: 2px;
  border: 0;
  border-radius: 5px;
  font-weight: bold;
  background-color: rgb(240, 108, 94);
  color: white;
}
.history-tr {
  border: 0;
}
.editor {
  text-align: left;
}
</style>
