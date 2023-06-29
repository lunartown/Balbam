<template>
  <div class="package-theme-main-box">
    <div class="package-theme-box">
      <div>
        <div>
          <img
            v-if="idx % 2 == 0"
            src="@/assets/img/packageThemeGray.png"
            alt=""
          />
          <img v-else src="@/assets/img/packageTheme.png" />
        </div>
        <div class="package-board-desc-head" v-html="themeTitle.title"></div>
      </div>
      <div class="img-package-board-box">
        <div
          class="card-package-box"
          @click="cardClick(pack.packageId)"
          v-for="(pack, index) in packages"
          :key="index"
        >
          <card-view-ele :pack="pack"></card-view-ele>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CardViewEle from "@/components/Element/CareViewEle.vue";
import httpCommon from "@/util/http-common";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
export default {
  props: {
    themeTitle: Object,
    idx: Number,
  },
  data() {
    return {
      packages: {},
    };
  },
  components: { CardViewEle },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    cardClick(packageId) {
      this.$router.push({
        name: "packageDetail",
        params: { packageId: packageId },
      });
    },
  },
  created() {
    var userId = 0;
    var decodeToken;
    var token = sessionStorage.getItem("access-token");
    if (token != null) {
      decodeToken = jwtDecode(token);
      userId = decodeToken.userId;
    }
    httpCommon
      .get("package/list/region/random", {
        params: { region: this.themeTitle.region, userId: userId },
      })
      .then(({ data }) => {
        this.packages = data;
      });
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
.package-theme-main-box {
  margin-top: 90px;
  padding: 2rem 0;
}
.package-theme-box {
  margin: 0 8%;
}
.package-board-desc-head {
  text-align: left;
  font-weight: bold;
  font-size: 2.5rem;
  margin-bottom: 1rem;
}
.img-package-board-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
.card-package-box {
  width: 25%;
  min-width: 250px;
  min-height: 300px;
  margin-top: 1rem;
}
</style>
