<template>
  <div class="pw-find">
    <div class="pw-find__container">
      <div class="pw-find__title">비밀번호 찾기</div>
      <form class="pw-find__input" method="post">
        <input type="hidden" name="action" value="pwfind" />
        <div class="id">
          <div>아이디</div>
          <input type="text" name="id" placeholder="아이디를 입력하세요" required />
        </div>
        <div class="name-area">
          <div>이름</div>
          <input type="text" name="name" placeholder="이름을 입력하세요" required />
        </div>
        <div class="email-area">
          <div>이메일</div>
          <input type="email" name="email" placeholder="이메일을 입력하세요" required />
        </div>
        <div class="pw-find__submit">
          <button id="next-btn" type="button">비밀번호 찾기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.pw-find {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: #f1f1f5;
}

.pw-find__container {
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

.pw-find__title {
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
}

.pw-find__input {
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.pw-find__input > div {
  margin-bottom: 10px;
}

.pw-find__input input {
  padding-left: 5px;
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  width: 364px;
  height: 56px;
}

.pw-find__submit button {
  margin-top: 20px;
  font-size: 16px;
  width: 364px;
  height: 56px;
  color: white;
  background-color: #444939;
  border: 0;
  margin-bottom: 20px;
}

.pw-find__submit button:hover {
  cursor: pointer;
  transform: translate(0, 1px);
  background-color: rgb(82, 168, 147);
  transition: all 0.3s;
}

.pw-find__easy-login {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 16;
  font-weight: medium;
  color: #767676;
}

.pw-find__easy-login > .text {
  margin-bottom: 16px;
}

.pw-find__easy-login > .pw-find__easy-image-area {
  display: flex;
  justify-content: space-between;
  margin-bottom: 40px;
}

.pw-find__easy-image-area > .image {
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

.pw-find__easy-image-area > .image > img {
  width: 40%;
}

.login__pw-find {
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
</style>

<script>
export default {
  name: "UserPwFind",
};
</script>
