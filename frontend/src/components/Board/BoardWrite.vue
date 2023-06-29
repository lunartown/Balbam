<template>
  <div class="board-detail-box">
    <div class="board-detail-topic">글작성</div>
    <hr class="hr-bold" />
    <div class="selectBox">
      <select name="" class="select" v-model="curOption" ref="select">
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
          v-model="inputTitle"
          ref="title"
        />
      </div>
    </div>
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
      <button @click="write" class="write-btn">작성</button>
    </div>
  </div>
</template>

<script>
import TipTapEle from "@/components/Element/TipTapEle.vue";
import httpCommon from "@/util/http-common";
import { mapState, mapActions } from "vuex";
import jwtDecode from "jwt-decode";
const Swal = require("sweetalert2");
export default {
  components: { TipTapEle },
  data() {
    return {
      desc: "",
      options: ["잡담", "후기", "추천", "질문"],
      curOption: "",
      inputContent: "",
      inputTitle: "",
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    async write() {
      if (this.curOption == "") {
        this.$refs.select.focus();
        this.showAlter("카테고리를 입력해주세요!");
        return;
      }
      if (this.inputTitle == "") {
        this.$refs.title.focus();
        this.showAlter("제목을 입력해주세요!");
        return;
      }
      if (this.inputContent == "") {
        this.$refs.content.focus();
        this.showAlter("내용을 입력해주세요!");
        return;
      }
      let token = sessionStorage.getItem("access-token");
      let decodeToken = jwtDecode(token);
      if (this.isLogin) {
        await httpCommon.post("article", {
          title: this.inputTitle,
          content: this.inputContent,
          category: this.curOption,
          userId: decodeToken.userId,
        });
        this.$router.push({ name: "boardMain" });
      } else {
        alert("로그인을 확인해주세요.");
      }
    },
    setContent(val) {
      this.inputContent = val.val;
    },
    showAlter(msg) {
      Swal.fire({
        icon: "error",
        title: "공지 작성 실패",
        text: msg,
      });
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
  width: 100%;
}
.board-detail-topic {
  font-size: 1.3rem;
  padding-bottom: 2rem;
}
.board-detial-cat {
  color: rgb(240, 108, 94);
}
</style>
