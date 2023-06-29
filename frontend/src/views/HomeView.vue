<template>
  <div class="home">
    <home-banner></home-banner>
    <home-content></home-content>
  </div>
</template>

<script>
// @ is an alias to /src
import HomeBanner from "@/components/Home/HomeBanner.vue";
import HomeContent from "@/components/Home/HomeContent.vue";

export default {
  name: "HomeView",
  components: {
    HomeBanner,
    HomeContent,
  },
};
</script>
