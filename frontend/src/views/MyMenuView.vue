<template>
  <div class="mymenu-main">
    <div class="mymenu-container">
      <my-menu-nav></my-menu-nav>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import MyMenuNav from "@/components/MyMenu/MyMenuNav.vue";
export default {
  data() {
    return {};
  },
  components: {
    MyMenuNav,
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["getUserInfo"]),
  },
  async created() {
    if (sessionStorage.getItem("access-token")) {
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        return;
      }
    }
    alert("먼저 로그인하세요.");
    this.$router.push({ name: "login" });
  },
};
</script>

<style scoped>
.mymenu-main {
  width: 100%;
  display: flex;
  justify-content: center;
}
.mymenu-container {
  width: 1280px;
  display: flex;
  justify-content: start;
  font-family: "Noto Sans KR", sans-serif;
  margin-top: 40px;
  margin-bottom: 80px;
}
</style>
<style>
.hr-bold {
  border: 0;
  height: 2px;
  background-color: black;
  opacity: 1;
  margin: 0px;
}
.hr-lighter {
  border: 0;
  height: 1px;
  background-color: lightgray;
  margin: 0px;
}

* {
  padding: 0;
  margin: 0;
  border: none;
  box-sizing: border-box;
  letter-spacing: -.4px;
}

ul {
  list-style-type: none;
}

a {
  text-decoration: none;
}
</style>
