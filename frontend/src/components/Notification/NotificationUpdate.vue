<template>
  <div class="board-detail-box">
    <div class="board-detail-topic">공지 수정</div>
    <hr class="hr-bold" />
    <div class="selectBox">
      <select
        name=""
        class="select"
        v-model="notification.category"
        ref="select"
      >
        <option :value="option" v-for="(option, index) in options" :key="index">
          {{ option }}
        </option>
      </select>
      <span class="icoArrow"
        ><img src="@/assets/img/icon/arrow-down-icon.png"
      /></span>
    </div>
    <div class="board-detail-header-box">
      <div>
        <input
          class="board-write-title"
          placeholder="제목을 입력해주세요."
          v-model="notification.title"
          ref="title"
        />
      </div>
    </div>
    <hr class="hr-lighter" />
    <div ref="content">
      <tip-tap-ele
        @editorContent="setContent"
        :read-only="false"
        :description="desc"
        :menubar="true"
      ></tip-tap-ele>
    </div>
    <hr class="hr-bold" />
    <div class="write-btn-box">
      <button @click="mvBack" class="write-btn">취소</button>
      <button @click="write" class="write-btn">수정</button>
    </div>
  </div>
</template>

<script>
import TipTapEle from "@/components/Element/TipTapEle.vue";
import httpCommon from "@/util/http-common";
import { mapState, mapActions } from "vuex";
const Swal = require("sweetalert2");
export default {
  components: { TipTapEle },
  data() {
    return {
      desc: "",
      options: ["일반", "서비스", "당첨자발표"],
      notification: {},
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    async write() {
      if (this.notification.category == "") {
        this.$refs.select.focus();
        this.showAlter("카테고리를 입력해주세요!");
        return;
      }
      if (this.notification.title == "") {
        this.$refs.title.focus();
        this.showAlter("제목을 입력해주세요!");
        return;
      }
      if (this.notification.content == "") {
        this.$refs.content.focus();
        this.showAlter("내용을 입력해주세요!");
        return;
      }
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        this.getUserInfo(token);
        if (this.userInfo.admin != 0) {
          await httpCommon.post("notification/update", {
            title: this.notification.title,
            content: this.notification.content,
            category: this.notification.category,
            notificationId: this.notification.notificationId,
          });
          this.mvBack();
        }
      }
    },
    setContent(val) {
      this.notification.content = val.val;
    },
    showAlter(msg) {
      Swal.fire({
        icon: "error",
        title: "수정 실패",
        text: msg,
      });
    },
    mvBack() {
      let token = sessionStorage.getItem("access-token");
      let isAdmin = false;
      if (this.isLogin) {
        this.getUserInfo(token);
        if (this.userInfo.admin != 0) {
          isAdmin = true;
        }
      }
      this.$router.push({
        name: "notificationDetail",
        params: {
          notificationId: this.notification.notificationId,
          isAdmin: isAdmin,
        },
      });
    },
  },
  created() {
    this.notification = this.$route.params.notification;
    this.desc = this.notification.content;
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
select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  font-weight: bold;
  font-size: 1rem;
}
option {
  font-weight: bold;
  font-size: 1rem;
}
.selectBox {
  position: relative;
  width: 150px;
  height: 35px;
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
  position: relative;
  z-index: 3;
}
.select {
  padding-left: 3rem;
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
.board-detail-box {
  display: flex;
  flex-direction: column;
  margin: 90px 160px;
  min-width: 500px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: bold;
}
.write-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  border-radius: 5px;
  padding: 1rem 2rem;
  font-weight: bold;
  margin: 0 1rem;
  border: 0;
}
.write-btn-box {
  display: flex;
  justify-content: right;
  margin-top: 2rem;
}
.board-detail-header-box {
  display: flex;
  padding: 1rem 0;
  font-weight: bold;
}
.board-detail-title-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}
.board-write-title {
  margin-left: 1rem;
  font-size: 2rem;
  border: 0;
}
.board-detail-topic {
  font-size: 1.3rem;
  padding-bottom: 2rem;
}
.board-detial-cat {
  color: rgb(240, 108, 94);
}
</style>
