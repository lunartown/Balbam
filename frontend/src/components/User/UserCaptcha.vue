<template>
  <div class="user-captcha">
    <div class="safety-image">
      <img :src="`data:image/png;base64,` + safetyImage" alt="" />
    </div>
    <div class="safety-button">
      <button type="button" class="sound">
        <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
          <path d="M0 0h24v24H0z" fill="none" />
          <path
            d="M3 9v6h4l5 5V4L7 9H3zm13.5 3c0-1.77-1.02-3.29-2.5-4.03v8.05c1.48-.73 2.5-2.25 2.5-4.02zM14 3.23v2.06c2.89.86 5 3.54 5 6.71s-2.11 5.85-5 6.71v2.06c4.01-.91 7-4.49 7-8.77s-2.99-7.86-7-8.77z"
          />
        </svg>
      </button>
      <button type="button" class="refresh" @click="getCaptcha">
        <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
          <path d="M0 0h24v24H0z" fill="none" />
          <path
            d="M17.65 6.35C16.2 4.9 14.21 4 12 4c-4.42 0-7.99 3.58-7.99 8s3.57 8 7.99 8c3.73 0 6.84-2.55 7.73-6h-2.08c-.82 2.33-3.04 4-5.65 4-3.31 0-6-2.69-6-6s2.69-6 6-6c1.66 0 3.14.69 4.22 1.78L13 11h7V4l-2.35 2.35z"
          />
        </svg>
      </button>
    </div>
    <input
      type="text"
      class="captcha"
      @change="$emit('input-captcha', $event.target.value)"
      placeholder="보안문자를 입력하세요"
    />
    <div class="wrong-captcha" :class="{ 'wrong-captcha--view': wrongCaptcha }">
      보안문자가 일치하지 않습니다.
    </div>
  </div>
</template>
<style scoped>
.wrong-captcha {
  font-size: 13px;
  color: #ed1c24;
  margin-bottom: 5px;
  align-self: flex-start;
  opacity: 0;
}

.wrong-captcha--view {
  opacity: 1;
}

.user-captcha {
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
  border: 1px solid #767676;
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
  cursor: pointer;
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

.user-captcha > input {
  border: none;
  font-size: 16px;
  border-bottom: 1px solid rgb(192, 192, 192);
  width: 364px;
  height: 56px;
  margin-bottom: 4px;
}
</style>
<script>
import CommonAxios from "@/util/http-common.js";

export default {
  name: "UserCaptcha",
  props: {
    wrongCaptcha: Boolean,
  },
  data() {
    return {
      safetyKey: "",
      safetyImage: "",
    };
  },
  methods: {
    getCaptcha() {
      CommonAxios.get(`/user/getCaptcha`).then(({ data }) => {
        this.safetyKey = data[0];
        this.safetyImage = data[1];
        this.$emit("get-captcha", this.safetyKey);
      });
    },
  },
  created() {
    this.getCaptcha();
  },
};
</script>
