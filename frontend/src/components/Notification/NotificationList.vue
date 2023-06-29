<template>
  <div class="notification-list">
    <div class="header-div">
      <div class="notification-title">공지사항</div>
      <div action="" class="search-form">
        <input
          class="word"
          type="text"
          v-model="inputData"
          placeholder="제목을 입력해주세요."
        />
        <button class="search-submit" @click="searchBtn()">검색</button>
      </div>
    </div>
    <div class="write-box">
      <button
        class="mv-write-btn"
        v-if="isAdmin"
        @click="mvNotificationWrite()"
      >
        공지작성
      </button>
    </div>
    <div class="cat-box">
      <button
        @click="catClick(index)"
        class="cat-btn"
        v-for="(navi, index) in navis"
        :key="index"
        :class="{ active: navi.active }"
      >
        {{ navi.menu }}
      </button>
    </div>
    <div class="notifcation-table-box">
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>구분</th>
            <th>제목</th>
            <th>등록일</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(notification, index) in notifications"
            :key="index"
            @click="rowClick(index)"
          >
            <td>{{ (page.page - 1) * 10 + index + 1 }}</td>
            <td>{{ notification.category }}</td>
            <td>{{ notification.title }}</td>
            <td>{{ notification.createTime }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="paging-box">
      <pagination
        :page="page"
        @onPage="onPage"
        v-if="this.notifications.length !== 0"
      ></pagination>
    </div>
  </div>
</template>

<script>
import pagination from "@/components/Element/PaginationEle.vue";
import httpCommon from "@/util/http-common";
import { mapState, mapActions } from "vuex";
export default {
  components: {
    pagination,
  },
  data() {
    return {
      isAdmin: false,
      navis: [
        { active: 1, menu: "전체", value: "" },
        { active: 0, menu: "일반", value: "일반" },
        { active: 0, menu: "서비스", value: "서비스" },
        { active: 0, menu: "당첨자발표", value: "당첨자발표" },
      ],
      notifications: [],
      page: {
        total: 0,
        page: 1,
        count: 10,
      },
      key: "title",
      inputData: "",
      word: "",
      isSearch: false,
      cat: "",
    };
  },

  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    catClick(val) {
      for (let i = 0; i < 4; i++) {
        if (i == val) {
          this.navis[i].active = 1;
        } else {
          this.navis[i].active = 0;
        }
      }
      this.cat = this.navis[val].value;
      this.page.page = 1;
      httpCommon
        .get("notification/list/count", { params: { catOrRegion: this.cat } })
        .then(({ data }) => {
          this.page.total = data;
        });
      httpCommon
        .get("notification/list", {
          params: { currentPage: this.page.page, catOrRegion: this.cat },
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            let date = new Date(data[i].createTime);
            data[i].createTime = this.formatDate(date);
          }
          this.notifications = data;
        });
    },
    rowClick(record) {
      this.$router.push({
        name: "notificationDetail",
        params: {
          notificationId: this.notifications[record].notificationId,
          isAdmin: this.isAdmin,
        },
      });
    },
    onPage(page) {
      this.page.page = page;
      httpCommon
        .get("notification/list", {
          params:
            this.isSearch == false
              ? { currentPage: this.page.page, catOrRegion: this.cat }
              : {
                  currentPage: this.page.page,
                  catOrRegion: this.cat,
                  key: this.key,
                  word: this.word,
                },
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            let date = new Date(data[i].createTime);
            data[i].createTime = this.formatDate(date);
          }
          this.notifications = data;
        });
    },
    mvNotificationWrite() {
      this.$router.push({ name: "notificationWrite" });
    },
    searchBtn() {
      if (this.inputData == "") return;
      this.isSearch = true;
      this.word = this.inputData;
      this.page.page = 1;
      httpCommon
        .get("notification/list/count", {
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
        .get("notification/list", {
          params: {
            currentPage: this.page.page,
            key: this.key,
            word: this.word,
            catOrRegion: this.cat,
          },
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            let date = new Date(data[i].createTime);
            data[i].createTime = this.formatDate(date);
          }
          this.notifications = data;
        });
    },
    formatDate(date) {
      return (
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1 > 9
          ? (date.getMonth() + 1).toString()
          : "0" + (date.getMonth() + 1)) +
        "-" +
        (date.getDate() > 9
          ? date.getDate().toString()
          : "0" + date.getDate().toString())
      );
    },
  },
  computed: {
    ...mapState("userStore", [
      "isLogin",
      "isLoginError",
      "userInfo",
      "errorType",
    ]),
  },
  created() {
    let token = sessionStorage.getItem("access-token");
    if (token != null && this.isLogin) {
      this.getUserInfo(token);
      if (this.userInfo.admin != 0) this.isAdmin = true;
    }
    httpCommon
      .get("notification/list/count", { params: { catOrRegion: this.cat } })
      .then(({ data }) => {
        this.page.total = data;
      });
    httpCommon
      .get("notification/list", {
        params: { currentPage: this.page.page, catOrRegion: this.cat },
      })
      .then(({ data }) => {
        for (let i = 0; i < data.length; i++) {
          let date = new Date(data[i].createTime);
          data[i].createTime = this.formatDate(date);
        }
        this.notifications = data;
      });
  },
};
</script>
<style scoped>
.card-view-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
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
.cat-box {
  display: flex;
  flex-direction: row;
  margin-top: 2rem;
}
.cat-btn {
  width: 25%;
  border: 1px solid lightgray;
  border-bottom: 1px solid black;
  background-color: white;
  padding: 1rem 3rem;
  font-size: 1rem;
  font-weight: bold;
}
.active {
  border: 1px solid black;
  border-bottom: 0px solid black;
  color: rgb(240, 108, 94);
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
.notifcation-table-box > table > thead {
  background-color: rgb(247, 247, 247);
}
th,
td {
  border-bottom: 1px solid rgb(221, 221, 221);
  border-left: 1px solid rgb(221, 221, 221);
  padding: 10px;
}
th:first-child,
td:first-child {
  border-left: none;
}
.notifcation-table-box > table > tbody > tr:hover {
  background-color: rgb(247, 247, 247);
}
.paging-box {
  display: flex;
  justify-content: center;
  margin-bottom: 5rem;
}
.write-box {
  display: flex;
  justify-content: flex-end;
  margin-top: 1rem;
}
.mv-write-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  border-radius: 5px;
  padding: 1rem 2rem;
  font-weight: bold;
  border: 0;
}
</style>
