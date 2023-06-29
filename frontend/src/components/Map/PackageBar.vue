<template>
  <div class="package-bar" :style="rightSide">
    <div class="button" @click="rightButton">
      <div class="arrow-prev"></div>
      <div class="arrow-next"></div>
    </div>
    <div class="content">
      <div class="title">
        <div class="title--icon">
          <svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="EventIcon"><path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path></svg>
        </div>
        이런 여행상품은 어때요?</div>
      <package-list></package-list>
    </div>
  </div>
</template>

<style scoped>
.package-bar {
  display: flex;
  justify-content: end;
  z-index: 2;
  transition: 1s;
  width: var(--width);
  height: 100vh;
  right: 0px;
}
.content {
  right: calc(var(--width) - 300px);
  position: absolute;
  transition: 1s;
  width: 300px;
  height: 100%;
  background-color: white;
  box-shadow: 0 2px 2px 1px rgba(0, 0, 0, 0.3);
  z-index: 1;
}
.title {
  display: flex;
  padding-inline: 20px;
  border-bottom: 1px solid #dbdbdb;
  height: 65px;
  font-size: 18px;
  padding-top: 30px;
  font-weight: bold;
}

.title--icon {
  width: 24px;
  height: 24px;
  margin-right: 5px;
}
.button {
  cursor: pointer;
  transition: 1s;
  position: absolute;
  display: flex;
  align-items: center;
  right: var(--width);
  top: calc(50% - 20px);
  width: 18px;
  height: 40px;
  background-color: white;
  box-shadow: -2px 0 2px 0px rgba(0, 0, 0, 0.3);
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  z-index: 2;
}

.arrow-prev {
  position: absolute;
  left: 6px;
  top: 15px;
  width: 10px; /* 사이즈 */
  height: 10px; /* 사이즈 */
  border-top: 1px solid #000; /* 선 두께 */
  border-right: 1px solid #000; /* 선 두께 */
  transform: rotate(225deg); /* 각도 */
  display: var(--prev);
}

.arrow-next {
  position: absolute;
  left: 1px;
  top: 15px;
  width: 10px; /* 사이즈 */
  height: 10px; /* 사이즈 */
  border-top: 1px solid #000; /* 선 두께 */
  border-right: 1px solid #000; /* 선 두께 */
  transform: rotate(45deg); /* 각도 */
  display: var(--next);
}
</style>

<script>
import PackageList from "@/components/Map/PackageList.vue";
export default {
  data() {
    return {
      showRight: false,
    };
  },
  components: {
    PackageList,
  }, 
  methods: {
    rightButton() {
      this.showRight = !this.showRight;
    },
  },

  computed: {
    rightSide() {
      if (this.showRight) {
        return {
          "--width": "300px",
          "--prev": "none",
          "--next": "block",
        };
      } else {
        return {
          "--width": "0px",
          "--prev": "block",
          "--next": "none",
        };
      }
    },
  },
};
</script>
