<template>
  <div class="card-box">
    <div class="circle-box">
      <img :src="region.img" alt="" />
    </div>
    <div class="card-title">{{ region.title }}</div>
    <div class="card-content" v-html="setContent"></div>
  </div>
</template>

<script>
export default {
  props: {
    region: Object,
  },
  computed: {
    setContent() {
      return this.region.content.split("\n").join("<br>").split(" ").join(" ");
    },
  },
};
</script>

<style scoped>
.card-box {
  display: flex;
  flex-direction: column;
  padding-top:30px;
}
.circle-box {
  position: relative;
  width: 100%;
  height: 200px;
  overflow: hidden;
  display: flex;
  justify-content: center;
}
.card-box img {
  object-fit: cover;
  border-radius: 50%;
  width: 200px;
  height: 200px;
}

.card-content,
.card-title {
  padding-top: 1rem;
  font-weight: bold;
  text-align: center;
}
.card-title {
  font-size: 1.5rem;
}
.card-content {
  font-size: 0.8rem;
}
</style>
