<template>
  <div class="home">
    <Header></Header>
    <router-view></router-view>
    <home-footer></home-footer>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import HomeFooter from "@/components/Home/HomeFooter.vue";

export default {
  name: "MainView",
  components: {
    Header,
    HomeFooter,
  },
};
</script>
