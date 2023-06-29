<template>
  <div class="login">
    <div class="login__container">
      <div class="login__title">로그인</div>
      <div class="login__input">
        <div class="id-area">
          <input type="text" v-model="id" placeholder="아이디를 입력하세요" />
        </div>
        <div class="wrong-id" :class="{ 'wrong-id--view': wrongID }">
          존재하지 않는 아이디입니다.
        </div>
        <div class="pw-area">
          <input
            :type="pwdType"
            v-model="pwd"
            @keyup.enter="confirm"
            placeholder="비밀번호를 입력하세요"
          />
          <button @mouseover="showPwd()" @mouseout="hidePwd()">
            <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
              <path d="M0 0h24v24H0z" fill="none" />
              <path
                d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z"
              />
            </svg>
          </button>
        </div>
        <div class="wrong-password" :class="{ 'wrong-password--view': wrongPWD }">
          비밀번호가 일치하지 않습니다.
        </div>
      </div>
      <user-captcha
        v-if="captcha"
        :wrongCaptcha="wrongCaptcha"
        @input-captcha="inputCaptcha"
        @get-captcha="getCaptcha"
      ></user-captcha>
      <div class="login__submit">
        <button @click="confirm">로그인</button>
      </div>
      <div class="button-area">
        <div class="stay">
          <input type="checkbox" name="stay-loggedin" id="" />
          <div>로그인 상태 유지</div>
        </div>
        <div class="find">
          <router-link :to="{ name: 'pwFind' }">아이디/비밀번호 찾기</router-link>
        </div>
      </div>
      <div class="login__easy-login">
        <div class="text">SNS로 간편하게 시작하기</div>
        <div class="image-area">
          <div class="image"><img src="../../assets/img/account/kakao logo.png" alt="kakao" /></div>
          <div class="image"><img src="../../assets/img/account/naver logo.png" alt="naver" /></div>
          <div class="image">
            <img src="../../assets/img/account/google logo.png" alt="google" />
          </div>
          <div class="image"><img src="../../assets/img/account/apple logo.png" alt="apple" /></div>
        </div>
      </div>
      <div class="login__signup"><router-link :to="{ name: 'signup' }">회원가입</router-link></div>
    </div>
  </div>
</template>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: #f1f1f5;
}

.login__container {
  padding-top: 40px;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: white;
  align-items: center;
  width: 460px;
  margin-bottom: 50px;
}

.login__title {
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
}

.login__input {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.id-area {
  margin-bottom: 4px;
}

.wrong-id {
  font-size: 13px;
  color: #ed1c24;
  margin-bottom: 20px;
  align-self: flex-start;
  opacity: 0;
}

.wrong-id--view {
  opacity: 1;
}

.pw-area {
  position: relative;
  width: 364px;
  margin-bottom: 4px;
}

.pw-area > button {
  display: inline-flex;
  align-items: center;
  position: absolute;
  right: 5px;
  transform: translate(0, 16px);
  width: 24px;
  height: 24px;
  background: none;
  border: none;
  cursor: pointer;
}

.pw-area > button > svg {
  filter: invert(59%) sepia(0%) saturate(2%) hue-rotate(123deg) brightness(101%) contrast(92%);
  width: 100%;
}

.pw-area > button > svg:hover {
  filter: none;
}

.login__input input {
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  width: 364px;
  height: 56px;
}

.wrong-password {
  font-size: 13px;
  color: #ed1c24;
  margin-bottom: 20px;
  align-self: flex-start;
  opacity: 0;
}

.wrong-password--view {
  opacity: 1;
}

.safety-text {
  width: 364px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

.safety-image {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 307px;
  height: 76px;
  border: 1px solid #767676;
  overflow: hidden;
}

.safety-image > img {
  width: 100%;
}

.safety-button {
  height: 76px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.safety-button > button {
  width: 36px;
  height: 36px;
  border: 1px solid #999;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}

.safety-button > button > svg {
  width: 100%;
}

.safety-text > input {
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  width: 364px;
  height: 56px;
  margin-bottom: 20px;
}

.button-area {
  width: 364px;
  display: flex;
  justify-content: space-between;
  font-size: 15px;
  color: #767676;
  margin-bottom: 40px;
}

.button-area > .stay {
  color: black;
  display: flex;
  align-items: center;
}

.stay > input {
  width: 22px;
  height: 22px;
  border-radius: 50%;
  border: 1px solid #999;
  cursor: pointer;
  margin-right: 6px;
}

.stay > input:checked {
  border: 5px double #999;
  background: white;
}

.find {
  cursor: pointer;
}

.login__submit button {
  font-size: 16px;
  width: 364px;
  height: 56px;
  color: white;
  background-color: #444939;
  border: 0;
  margin-bottom: 20px;
}

.login__submit button:hover {
  cursor: pointer;
  transform: translate(0, 1px);
  background-color: #7d8669;
  transition: all 0.3s;
}

.login__easy-login {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 16;
  font-weight: medium;
  color: #767676;
}

.login__easy-login > .text {
  margin-bottom: 16px;
}

.login__easy-login > .image-area {
  display: flex;
  justify-content: space-between;
  margin-bottom: 40px;
}

.image-area > .image {
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #999;
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-inline: 10px;
}

.image-area > .image > img {
  width: 40%;
}

.login__signup {
  cursor: pointer;
  width: 364px;
  height: 56px;
  font-size: 15px;
  border: 1px solid #dbdbdb;
  line-height: 56px;
  text-align: center;
  color: black;
  margin-bottom: 40px;
}

.login__signup > a {
  font-size: 15px;
  text-align: center;
  color: black !important;
}
</style>

<script>
import UserCaptcha from "@/components/User/UserCaptcha.vue";
// import CommonAxios from "@/util/http-common.js";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "UserLogin",
  components: {
    UserCaptcha,
  },
  data() {
    return {
      wrongID: false,
      wrongPWD: false,
      wrongCaptcha: false,
      pwdType: "password",
      id: "",
      pwd: "",
      captcha: false,
      value: null,
      key: "",
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    ...mapMutations("userStore", ["SET_ERROR_TYPE"]),

    async confirm() {
      if (!this.id) {
        alert("아이디를 입력해주세요.");
        return;
      }

      if (!this.pwd) {
        alert("비밀번호를 입력해주세요.");
        return;
      }

      let user = {
        id: this.id,
        pwd: this.pwd,
        key: this.key,
        value: this.value,
      };

      let captcha = this.captcha;
      this.captcha = false;

      this.SET_ERROR_TYPE(null);
      await this.userConfirm(user);
      if (this.errorType == "id") {
        this.wrongID = true;
        this.wrongPWD = false;
        this.wrongCaptcha = false;
        return;
        // 비밀번호가 틀렸을 때
      } else if (!isNaN(this.errorType) && this.errorType) {
        console.log(this.errorType);
        this.wrongID = false;
        this.wrongPWD = true;
        this.wrongCaptcha = false;

        if (this.errorType >= 5) {
          this.captcha = true;
        }
        return;
      } else if (this.errorType == "captcha") {
        this.wrongID = false;
        this.wrongPWD = false;
        this.wrongCaptcha = true;
        return;
      } 

      if (captcha) {
        this.captcha = true;
      }

      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },

    showPwd() {
      this.pwdType = "text";
    },
    hidePwd() {
      this.pwdType = "password";
    },
    inputCaptcha(value) {
      this.value = value;
    },
    getCaptcha(key) {
      this.key = key;
    },
  },
};
</script>
