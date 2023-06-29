<template>
  <div class="mymenu-user-info">
    <div class="user-info--verify" v-if="!verified">
      <div class="user-info__title">비밀번호 확인</div>
      <div class="user-info__explain">개인정보 조회를 위해 비밀번호를 다시 한번 입력해주세요.</div>
      <div class="user-info__password">
        비밀번호 입력<br />
        <input
          type="password"
          class="password--input"
          v-model="pwd"
          placeholder="비밀번호 입력"
          @keyup.enter="submit"
        />
        <br />
        <button class="password--button" @click="submit">확인</button>
      </div>
    </div>
    <div class="user-info-view" v-if="verified">
      <div class="user-info__title">회원정보 수정</div>
      <div class="user-info__name">기본 정보 입력</div>
      <table class="user-info__table">
        <tbody>
          <tr class="table--row">
            <td class="table--category">프로필 사진</td>
            <td class="table--item"><input type="file" /></td>
          </tr>
          <tr class="table--row">
            <td class="table--category">이름</td>
            <td class="table--item">{{ userInfo.name | nameFilter }}</td>
          </tr>
          <tr class="table--row">
            <td class="table--category">아이디</td>
            <td class="table--item">{{ userInfo.id | idFilter }}</td>
          </tr>
          <tr class="table--row">
            <td class="table--category">이메일</td>
            <td class="table--item">{{ userInfo.email | emailFilter }}</td>
          </tr>
          <tr class="table--row">
            <td class="table--category">생년월일</td>
            <td class="table--item">{{ userInfo.birth }}</td>
          </tr>
          <tr class="table--row">
            <td class="table--category">성별</td>
            <td class="table--item">{{ userInfo.gender | genderFilter }}</td>
          </tr>
          <tr class="table--row">
            <td class="table--category">휴대폰 번호</td>
            <td class="table--item">{{ userInfo.tel | telFilter }}</td>
          </tr>
        </tbody>
      </table>
      <button class="user-info--button">수정</button>
    </div>
  </div>
</template>

<style scoped>
.mymenu-user-info {
  width: 100%;
}
.user-info__explain {
  font-size: 17px;
  color: #111;
  font-weight: lighter;
  margin-bottom: 5px;
  padding-bottom: 15px;
  border-bottom: 1px solid #808080;
}
.user-info__password {
  width: 400px;
  padding-top: 15px;
  font-size: 14px;
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
.user-info__title {
  font-size: 22px;
  font-weight: bold;
  height: 55px;
}

.user-info__name {
  font-size: 17px;
  color: #111;
  font-weight: lighter;
  margin-bottom: 22px;
}
.user-info__table {
  font-size: 15px;
  border-collapse: separate;
  border-spacing: 0px;
  width: 100%;
  border-top: 1px solid #999;
}

.user-info__table td {
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

.user-info--button {
  cursor: pointer;
  position: relative;
  display: inline-block;
  height: 30px;
  left: 0px;
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
</style>

<script>
//   import CommonAxios from "@/util/http-common.js";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  data() {
    return {
      pwd: "",
      verified: false,
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
  components: {},
  // async created() {
  //   if (sessionStorage.getItem("access-token")) {
  //     let token = sessionStorage.getItem("access-token");
  //     if (this.isLogin) {
  //       await this.getUserInfo(token);
  //       console.log("4. confirm() userInfo :: ", this.userInfo);
  //       return;
  //     }
  //   }
  //   alert("먼저 로그인하세요.");
  //   this.$router.push({ name: "login" });
  // },
  filters: {
    nameFilter(value) {
      return value.replaceAll(/(?<=^.+).(?=.+$)/gm, "*");
    },
    idFilter(value) {
      return value.replaceAll(/(?<=^.{2,}).(?=.+$)/gm, "*");
    },
    emailFilter(value) {
      // eslint-disable-next-line
      return value.toString().replaceAll(/(?<=^.{2,}).(?=.+@)/gm, "*");
    },
    genderFilter(value) {
      if (value == "M") {
        return "남성";
      } else if (value == "W") {
        return "여성";
      } else return "기타";
    },

    telFilter(value) {
      return value.toString().replace(/-[0-9]{3,4}-/, "-****-");
    },
  },
};
</script>
