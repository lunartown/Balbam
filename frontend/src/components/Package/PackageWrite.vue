<template>
  <div class="board-detail-box">
    <div class="board-detail-topic">패키지 만들기</div>
    <div>
      <div class="selectBox">
        <select name="" class="select" v-model="curOption" ref="select">
          <option :value="option" v-for="(option, index) in options" :key="index">
            {{ option }}
          </option>
        </select>
        <span class="icoArrow"><img src="@/assets/img/icon/arrow-down-icon.png" /></span>
      </div>
      <hr class="hr-bold" />
      <div class="package-info-box">
        <div class="input-date">
          <label for="startDate"
            >시작 날짜:
            <input
              type="date"
              id="startDate"
              max="2077-06-20"
              min="2023-05-25"
              v-model="startDate"
            />
          </label>
        </div>
        <div class="input-date">
          <label for="endDate"
            >종료 날짜:
            <input type="date" id="endDate" max="2077-06-20" min="2023-05-25" v-model="endDate" />
          </label>
        </div>
        <div class="input-date">
          <label for="price"
            >가격:
            <input type="text" pattern="[0-9]+" id="price" v-model="price" />
          </label>
        </div>
        <div class="input-date">
          <label for="headcount"
            >최대 인원:
            <input type="range" id="headcount" min="1" max="15" v-model="headcount" />
          </label>
          <span style="margin-left: 1rem">{{ headcount }}</span>
        </div>
      </div>
    </div>
    <hr class="hr-bold" />
    <div class="board-detail-header-box">
      <div>
        <input
          class="board-write-title"
          placeholder="패키지 제목을 입력해주세요."
          v-model="inputTitle"
          ref="title"
        />
      </div>
    </div>
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
    <h2 class="upload-desc">안내사진 업로더</h2>
    <file-uploader-ele :dir="descDir" :idx="idx" @pushEvent="uploadImg"></file-uploader-ele>
    <hr class="hr-bold" />
    <hr class="hr-bold" />
    <h2 class="upload-desc">기본정보 사진 업로더</h2>
    <file-uploader-ele :dir="imgDir" :idx="idx" @pushEvent="uploadDescImg"></file-uploader-ele>
    <hr class="hr-bold" />
    <div class="write-btn-box">
      <button @click="write()" class="write-btn">작성</button>
    </div>
  </div>
</template>

<script>
import TipTapEle from "@/components/Element/TipTapEle.vue";
import FileUploaderEle from "@/components/Element/FileUploaderEle.vue";
import httpCommon from "@/util/http-common";
import { mapState, mapActions } from "vuex";
const Swal = require("sweetalert2");
export default {
  components: { TipTapEle, FileUploaderEle },
  data() {
    return {
      desc: "",
      options: ["서울", "경기", "강원", "충청", "전라", "경상", "제주"],
      curOption: "",
      inputContent: "",
      dir: "imgBoard",
      inputTitle: "",
      idx: 0,
      startDate: "2023-05-25",
      endDate: "2023-05-25",
      headcount: 1,
      price: 0,
      descDir: "descImg",
      imgDir: "packageImg",
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    write() {
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
      if (token == null || !this.isLogin) return;
      if (this.isLogin) {
        httpCommon
          .post("package", {
            title: this.inputTitle,
            content: this.inputContent,
            region: this.curOption,
            price: this.price,
            headcount: this.headcount,
            startDate: this.startDate,
            endDate: this.endDate,
            guideId: 1,
          })
          .then(({ data }) => {
            this.idx = data;
          });
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
    uploadImg(url) {
      for (let i = 0; i < url.length; i++) {
        httpCommon.post("package/img", {
          url: url[i],
          packageId: this.idx,
        });
      }
    },
    uploadDescImg(url) {
      for (let i = 0; i < url.length; i++) {
        httpCommon.post("package/desc/img", {
          url: url[i],
          packageId: this.idx,
        });
      }
      this.$router.push({ name: "PackageMain" });
    },
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
  },
};
</script>

<style scoped>
#price {
  text-align: left;
  padding-left: 0.5rem;
  font-weight: bold;
}
.package-info-box {
  display: flex;
  flex-direction: row;
}
.input-date {
  margin: 1rem;
  font-weight: bold;
}
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
  margin-bottom: 1rem;
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
  margin: 0 1rem;
}
.upload-desc {
  padding: 1rem 0;
  color: rgb(240, 108, 94);
}
</style>
