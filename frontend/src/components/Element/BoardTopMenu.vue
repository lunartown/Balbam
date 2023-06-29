<template>
  <div class="top-menu">
    <div v-for="(title, index) in titles" :key="index" class="menu-box">
      <div
        class="menu-content"
        :class="{ active: selected == index ? true : false }"
        @click="catClick(index)"
      >
        {{ title }}
      </div>
      <div v-if="index != getLen" class="menu-dot">·</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    titles: [],
  },
  data() {
    return {
      selected: 0,
    };
  },
  methods: {
    catClick(idx) {
      this.selected = idx;
      this.$emit("catClick", idx);
    },
  },
  computed: {
    getLen() {
      return this.titles.length - 1;
    },
  },
};
</script>

<style scoped>
.top-menu {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin: 15px 0;
}
.menu-dot {
  color: gray;
  font-size: 1.5rem;
  padding: 0 1rem;
}
.menu-content:hover {
  color: rgb(240, 108, 94);
}
.menu-content {
  margin: auto 0;
  font-weight: bold;
  cursor: pointer;
}
.menu-box {
  display: flex;
  justify-content: space-evenly;
}
.active {
  color: rgb(240, 108, 94);
  text-decoration: underline 2px rgb(240, 108, 94);
  text-underline-position: under;
}
</style>
