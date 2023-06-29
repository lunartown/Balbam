<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
@font-face {
  font-family: "EF_jejudoldam";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-EF@1.0/EF_jejudoldam.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@700&family=Roboto&display=swap");

* {
  font-family: "Noto Sans KR", sans-serif;
  /* padding: 0;
  margin: 0; */
  box-sizing: border-box;
}

*:focus {
  outline: none;
}

/* ul {
  list-style-type: none;
} */

/* a {
  color: inherit;
  text-decoration: none;
} */

input {
  outline: none;
}

#app {
  min-height: 100vh;
  position: relative;
  display: flex;
  flex-direction: column;
}
</style>

<script>
export default {
  name: "App",
  components: {},
};
</script>
