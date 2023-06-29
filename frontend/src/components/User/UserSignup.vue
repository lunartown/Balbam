<template>
  <div class="signup">
    <div class="signup__container">
      <div class="signup__title">회원가입</div>
      <div class="signup__input">
        <div class="id">
          <div>아이디</div>
          <input type="text" v-model="user.id" placeholder="아이디를 입력하세요" />
        </div>
        <div class="pw">
          <div>비밀번호</div>
          <input type="password" v-model="user.pwd" placeholder="비밀번호를 입력하세요" />
          <button>
            <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
              <path d="M0 0h24v24H0z" fill="none" />
              <path
                d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z"
              />
            </svg>
          </button>
        </div>
        <div class="pw">
          <div>비밀번호 확인</div>
          <input type="password" name="pw-check" placeholder="비밀번호를 다시 입력하세요" />
          <button>
            <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
              <path d="M0 0h24v24H0z" fill="none" />
              <path
                d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z"
              />
            </svg>
          </button>
        </div>
        <div class="wrong-password">비밀번호가 일치하지 않습니다.</div>
        <div class="name-area">
          <div>이름</div>
          <input type="text" v-model="user.name" placeholder="이름을 입력하세요" />
        </div>
        <div class="birth-area">
          <div>생년월일</div>
          <input type="text" v-model="user.year" placeholder="년(4자)" />
          <select v-model="user.month" id="month">
            <option value="none">월</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
          </select>
          <input type="text" v-model="user.date" placeholder="일" />
        </div>
        <div class="sex-area">
          <div>성별</div>
          <select v-model="user.gender" id="gender">
            <option value="N">성별</option>
            <option value="M">남성</option>
            <option value="F">여성</option>
            <option value="O">기타</option>
          </select>
        </div>
        <div class="email-area">
          <div>이메일</div>
          <input type="email" v-model="user.email" placeholder="이메일을 입력하세요" />
        </div>
        <div class="tel-area">
          <div>휴대전화</div>
          <input type="tel" v-model="user.tel" placeholder="전화번호 입력" />
          <button>인증번호 전송</button>
          <input type="text" name="tel-certi" placeholder="인증번호를 입력하세요" />
        </div>
      </div>
      <div class="safety-text"></div>
      <div class="signup__submit">
        <button id="next-btn" @click="signup">회원가입</button>
      </div>
      <div class="signup__easy-login">
        <div class="text">SNS로 간편하게 시작하기</div>
        <div class="signup__easy-image-area">
          <div class="image">
            <img src="../../assets/img/account/kakao logo.png" alt="kakao" />
          </div>
          <div class="image">
            <img src="../../assets/img/account/naver logo.png" alt="naver" />
          </div>
          <div class="image">
            <img src="../../assets/img/account/google logo.png" alt="google" />
          </div>
          <div class="image">
            <img src="../../assets/img/account/apple logo.png" alt="apple" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.signup {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: #f1f1f5;
}

.signup__container {
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

.signup__title {
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
}

.signup__input {
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.signup__input > div {
  margin-bottom: 10px;
}

.pw {
  width: 364px;
}

.pw > button {
  margin-right: 20px;
  display: inline-flex;
  align-items: center;
  position: absolute;
  transform: translate(-30px, 16px);
  width: 24px;
  height: 24px;
  background-color: white;
  border: none;
  cursor: pointer;
}

.pw > button > svg {
  filter: invert(59%) sepia(0%) saturate(2%) hue-rotate(123deg) brightness(101%) contrast(92%);
  width: 100%;
}

.pw > button > svg:hover {
  filter: none;
}

.signup__input input {
  padding-left: 5px;
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  width: 364px;
  height: 56px;
}

.birth-area > input {
  width: 110px;
  margin: 0;
}

.birth-area > select {
  margin-inline: 13px;
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  height: 56px;
  width: 110px;
}

.sex-area > select {
  padding-left: 5px;
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  width: 364px;
  height: 56px;
}

.tel-area {
  width: 364px;
}

.tel-area > input:nth-child(2) {
  width: 245px;
}

.tel-area > button {
  margin-left: 5px;
  border: none;
  background-color: #444939;
  font-size: 16px;
  width: 110px;
  height: 56px;
  color: rgb(240, 240, 240);
}

.tel-area > button:hover {
  cursor: pointer;
  background-color: #7d8669;
}

.wrong-password {
  font-size: 13px;
  color: #ed1c24;
  margin-bottom: 20px;
  align-self: flex-start;
  display: none;
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
  border: 1px solid black;
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

.signup__submit button {
  margin-top: 20px;
  font-size: 16px;
  width: 364px;
  height: 56px;
  color: white;
  background-color: #444939;
  border: 0;
  margin-bottom: 20px;
}

.signup__submit button:hover {
  cursor: pointer;
  transform: translate(0, 1px);
  background-color: #7d8669;
  transition: all 0.3s;
}

.signup__easy-login {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 16;
  font-weight: medium;
  color: #767676;
}

.signup__easy-login > .text {
  margin-bottom: 16px;
}

.signup__easy-login > .signup__easy-image-area {
  display: flex;
  justify-content: space-between;
  margin-bottom: 40px;
}

.signup__easy-image-area > .image {
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

.signup__easy-image-area > .image > img {
  width: 40%;
}
</style>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "UserSignup",
  data() {
    return {
      user: {
        id: "",
        pwd: "",
        name: "",
        birth: "",
        year: "",
        month: "",
        date: "",
        gender: "",
        email: "",
        tel: ""
      }
    }
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo", "userSignup"]),
    // ...mapMutations("userStore", ["userSignup"]),
    async signup() {
      this.user.birth = this.user.year + "-" + this.user.month + "-" + this.user.date;

      await this.userSignup(this.user);
      
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    }
  }
};
</script>
