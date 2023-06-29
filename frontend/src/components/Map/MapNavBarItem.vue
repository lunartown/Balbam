<template>
  <div class="map-nav-bar-item" :class="{ 'map-nav-bar-item--clicked': clicked }" @click="clickButton" :style="cssProps">
    <div class="item--icon" :class="{ 'item--icon--clicked': clicked }" v-html="icon"></div>
    <div class="item--title" :class="{ 'item--title--clicked': clicked }">{{ title }}</div>
  </div>
</template>

<style scoped>
.map-nav-bar-item {
  user-select: none;
  margin-inline: 5px;
  display: flex;
  align-items: center;
  background-color: white;
  box-shadow: 0 0 2px 1px rgba(0, 0, 0, 0.3);
  height: 35px;
  border-radius: 35px;
  cursor: pointer;
}

.map-nav-bar-item--clicked {
  background-color: var(--color);
}

.item--icon {
  filter: var(--filter);
  margin-left: 11px;
  margin-right: 5px;
  width: 20px;
  height: 20px;
}

.item--icon--clicked {
  filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(228deg) brightness(103%) contrast(101%);
}

.item--title {
  font-size: 15px;
  margin-right: 15px;
}

.item--title--clicked {
  color: white;
}
</style>

<script>
export default {
  data() {
    return {
      clicked: false,
    };
  },
  props: {
    title: String,
    icon: String,
    filter: String,
    color: String,
  },
  computed: {
    cssProps() {
      return {
        "--filter": this.filter,
        "--color": this.color,
      };
    },
  },
  methods: {
    clickButton() {
      this.clicked = !this.clicked;
      this.$emit('click');
    },
  }
};
</script>
