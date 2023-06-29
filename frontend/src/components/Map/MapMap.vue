<template>
  <div id="mapMap">
    <map-nav-bar @select="select"></map-nav-bar>
    <div id="naverMap"></div>
  </div>
</template>

<style>
#mapMap {
  width: 10px;
  flex: 1;
}
#naverMap {
  position: fixed !important;
  width: 100%;
  height: 100vh;
  left: 0px;
  top: 0px;
}
</style>
<script>
import MapNavBar from "@/components/Map/MapNavBar.vue";
export default {
  name: "MapMap",
  components: {
    MapNavBar,
  },
  methods: {
    select(index) {
      this.$emit('select', index);
    }
  }
};
</script>
