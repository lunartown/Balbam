<template>
  <div class="mymenu-password">
    <div class="password--verify">
      <div class="password__title">비밀번호 변경</div>
      <div class="password__name">기존 비밀번호를 다시 한번 입력해주세요.</div>
      <div class="password__password">
        기존 비밀번호<br />
        <input
          type="password"
          class="password--input"
          v-model="pwd"
          placeholder="비밀번호 입력"
          @keyup.enter="submit"
        />
      </div>
      <div class="password__password">
        새 비밀번호<br />
        <input
          type="password"
          class="password--input"
          v-model="newPwd"
          placeholder="비밀번호 입력"
          @keyup.enter="submit"
        />
      </div>
      <div class="password__password">
        비밀번호 확인<br />
        <input
          type="password"
          class="password--input"
          v-model="newPwdCheck"
          placeholder="비밀번호 확인"
          @keyup.enter="submit"
        />
      </div>
      <br />
      <button class="password--button" @click="submit">확인</button>
    </div>
  </div>
</template>

<style scoped>
.mymenu-password {
  width: 100%;
}

.password__password {
  width: 400px;
  padding-top: 15px;
  font-size: 14px;
  margin-bottom: 10px;
}
.password--input {
  margin-top: 7px;
  padding-left: 15px;
  border-radius: 10px;
  width: 400px;
  height: 40px;
  background-color: #f7f7f7;
}
.password--button {
  cursor: pointer;
  position: relative;
  display: inline-block;
  height: 30px;
  left: 350px;
  line-height: 23px;
  font-size: 14px;
  padding: 0 10px;
  margin-top: 10px;
  color: #666;
  background-color: #fff;
  font-weight: 400;
  text-align: center;
  border-radius: 4px;
  border: solid 1px #c2c2c2;
}
.password__title {
  font-size: 22px;
  font-weight: bold;
  height: 55px;
}

.password__name {
  font-size: 17px;
  color: #111;
  font-weight: lighter;
  margin-bottom: 5px;
  padding-bottom: 15px;
  border-bottom: 1px solid #808080;
}
.password__table {
  font-size: 15px;
  border-collapse: separate;
  border-spacing: 0px;
  width: 100%;
  border-top: 1px solid #999;
}

.table--row {
}
.password__table td {
  min-height: 54px;
  padding: 15px 20px;
  border-bottom: 1px solid #ddd;
}

.table--category {
  width: 202px;
  background-color: #f7f7f7;
  font-size: 15px;
  font-weight: 600;
  color: #111;
}

.table--item {
  font-size: 15px;
  color: #333;
}
</style>

<script>
//   import CommonAxios from "@/util/http-common.js";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  data() {
    return {
      pwd: "",
      newPwd: "",
      newPwdCheck: "",
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
  },
  methods: {
    ...mapMutations("userStore", ["SET_ERROR_TYPE"]),
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    async submit() {
      if (!this.pwd) {
        alert("비밀번호를 입력해주세요.");
        return;
      }
      if (!this.userInfo) {
        alert("먼저 로그인하세요.");
        this.$router.push({ name: "login" });
      }
      let user = {
        id: this.userInfo.id,
        pwd: this.pwd,
      };
      this.SET_ERROR_TYPE(null);
      await this.userConfirm(user);
      if (this.errorType == null) {
        this.verified = true;
      } else if (this.errorType == "captcha" || (!isNaN(this.errorType) && this.errorType >= 5)) {
        alert("다시 로그인하세요.");
        this.$router.push({ name: "login" });
      } else {
        alert("다시 시도하세요.");
      }
    },
  },
};
</script>
