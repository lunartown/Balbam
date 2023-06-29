<template>
  <div>
    <Flicking :options="option" :plugins="plugins" ref="flicking" class="temp">
      <div class="flicking-panel panel" v-for="(img, index) in imgs" :key="index">
        <img class="img" :src="img" />
      </div>
      <div slot="viewport" class="carouselPage">
        <div class="flicking-arrow-prev arrow" @click="preClick()">
          <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
            <path d="M0 0h24v24H0z" fill="none" />
            <path d="M11.67 3.87L9.9 2.1 0 12l9.9 9.9 1.77-1.77L3.54 12z" />
          </svg>
        </div>
        <div class="carousel-text">{{ slide + 1 }} / {{ getMax }}</div>
        <div class="flicking-arrow-next arrow" @click="nxtClick()">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            enable-background="new 0 0 24 24"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <g><path d="M0,0h24v24H0V0z" fill="none" /></g>
            <g>
              <polygon points="6.23,20.23 8,22 18,12 8,2 6.23,3.77 14.46,12" />
            </g>
          </svg>
        </div>
      </div>
    </Flicking>
  </div>
</template>
<script>
import { Flicking } from "@egjs/vue-flicking";
import { Arrow } from "@egjs/flicking-plugins";
import "@egjs/flicking-plugins/dist/arrow.css";

export default {
  props: {
    imgs: Array,
  },
  components: {
    Flicking: Flicking,
  },
  data() {
    return {
      slide: 0,
      option: {
        align: "prev",
        resizeOnContentsReady: true,
        disableOnInit: true,
      },
      plugins: [new Arrow()],
    };
  },
  methods: {
    nxtClick() {
      if (this.slide != this.imgs.length - 1) this.slide = this.$refs.flicking.index + 1;
    },
    preClick() {
      if (this.slide != 0) this.slide = this.$refs.flicking.index - 1;
    },
  },
  computed: {
    getMax() {
      if (!this.imgs) return 0;
      let result = this.imgs.length;
      return result;
    },
  },
};
</script>
<style scoped>
.img {
  width: 100%;
  height: 700px;
  object-fit: cover;
}
.panel {
  width: inherit;
}
.carouselPage {
  right: 40px;
  color: white;
  position: absolute;
  display: flex;
  flex-direction: row;
  z-index: 1;
  bottom: 20px;
  border-radius: 4px;
  background: gray;
  padding: 0 6px;
}
.arrow {
  margin-top: 5px;
  filter: invert(100%) sepia(3%) saturate(0%) hue-rotate(215deg) brightness(104%) contrast(103%);
}
.carousel-text {
  margin: auto 0;
}
</style>
