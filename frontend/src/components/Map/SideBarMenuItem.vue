<template>
  <div class="side-bar-menu-item">
    <router-link :to="{ name: 'home' }" class="item__link">
      <div class="item--logo" v-html="icon"></div>
      <div class="item--title">{{ title }}</div>
    </router-link>
  </div>
</template>
<style scoped>
.side-bar-menu-item {
  margin-bottom: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.item__link {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.item--logo {
  width: 25px;
  height: 25px;
}

.item--title {
  font-size: 12px;
}
</style>
<script>
export default {
  props: {
    title: String,
    icon: String,
  },
};
</script>
