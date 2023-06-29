<template>
  <div class="board-detail-box">
    <div class="board-detail-topic">문의 수정</div>
    <hr class="hr-bold" />
    <div ref="content" class="tip-tap-box">
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
      answer: {},
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    async write() {
      if (this.answer.answerContent == "") {
        this.$refs.content.focus();
        this.showAlter("내용을 입력해주세요!");
        return;
      }
      if (this.isLogin) {
        await httpCommon.post("notification/inquiry/answer/update", {
          content: this.answer.answerContent,
          answerId: this.answer.answerId,
        });
        this.mvBack();
      } else {
        alert("로그인을 확인하세요.");
      }
    },
    setContent(val) {
      this.answer.answerContent = val.val;
    },
    showAlter(msg) {
      Swal.fire({
        icon: "error",
        title: "수정 실패",
        text: msg,
      });
    },
    mvBack() {
      this.$router.push({
        name: "notification",
      });
    },
  },
  created() {
    this.answer = this.$route.params.answer;
    this.desc = this.answer.answerContent;
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
