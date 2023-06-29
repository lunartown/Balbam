<template>
  <div class="board-detail-box">
    <div class="board-detail-topic">답변작성</div>
    <hr class="hr-lighter" />
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
      <button @click="write" class="write-btn">작성</button>
    </div>
  </div>
</template>

<script>
import TipTapEle from "@/components/Element/TipTapEle.vue";
import httpCommon from "@/util/http-common";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
const Swal = require("sweetalert2");
export default {
  components: { TipTapEle },
  data() {
    return {
      desc: "",
      inputContent: "",
      inquiryId: "",
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    setContent(val) {
      this.inputContent = val.val;
    },
    async write() {
      if (this.inputContent == "") {
        this.$refs.content.focus();
        this.showAlter("내용을 입력해주세요!");
        return;
      }
      let token = sessionStorage.getItem("access-token");
      let decodeToken = jwtDecode(token);
      if (this.isLogin) {
        await httpCommon.post("notification/inquiry/answer", {
          content: this.inputContent,
          inquiryId: this.inquiryId,
          userId: decodeToken.userId,
        });
        this.$router.push({ name: "notificationHistory" });
      } else {
        alert("로그인을 확인해주세요.");
      }
    },
    showAlter(msg) {
      Swal.fire({
        icon: "error",
        title: "문의 답글 작성 실패",
        text: msg,
      });
    },
  },
  created() {
    this.inquiryId = this.$route.params.inquiryId;
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
.board-detail-topic {
  font-size: 1.3rem;
  padding-bottom: 2rem;
}
</style>
