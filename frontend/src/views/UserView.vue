<template>
  <div class="user">
    <router-view></router-view>
  </div>
</template>

<style>
* {
  padding: 0;
  margin: 0;
  border: none;
  box-sizing: border-box;
}

ul {
  list-style-type: none;
}

a {
  text-decoration: none;
}
</style>
<script>
export default {
  name: "UserView",
  components: {},
};
</script>
