<template>
  <div class="paging">
    <a class="pagingFirst" @click="prevPage()" :disabled="page.page == 1"
      ><svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
        <path d="M0 0h24v24H0z" fill="none" />
        <path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg
    ></a>
    <ul class="pagingList">
      <li
        v-for="item in countInPages"
        :key="item"
        :class="[{ active: startPage - 1 + item == page.page ? true : false }]"
        class="paging-idx"
        @click="selectPage(startPage - 1 + item)"
      >
        {{ startPage - 1 + item }}
      </li>
    </ul>
    <a class="pagingLast" @click="nextPage()"
      ><svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24">
        <path d="M0 0h24v24H0z" fill="none" />
        <path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg
    ></a>
  </div>
</template>
<script>
export default {
  name: "PagingView",
  props: {
    page: {
      type: Object,
    },
    defaultPages: {
      type: Number,
      default: 10,
    },
  },
  computed: {
    startPage() {
      let value = Math.floor((this.page.page - 1) / this.defaultPages) * this.defaultPages + 1;
      return value;
    },
    totalPage() {
      var count = this.page.count ? this.page.count : 10;
      return Math.floor(this.page.total / count) + (this.page.total % count == 0 ? 0 : 1);
    },
    countInPages() {
      let currPages = this.totalPage - (this.startPage - 1);
      if (currPages < this.defaultPages) {
        return currPages;
      } else {
        return this.defaultPages;
      }
    },
  },
  methods: {
    selectPage(val) {
      if (val === this.page.page) {
        return;
      }
      this.$emit("onPage", val);
    },

    prevPage() {
      if (this.page.page == 1) {
        return;
      }
      this.selectPage(this.page.page - 1);
    },

    nextPage() {
      if (this.page.page == this.totalPage) {
        return;
      }

      this.selectPage(this.page.page + 1);
    },
  },
};
</script>

<style scoped>
.paging {
  display: flex;
}
.pagingList {
  display: flex;
}
.paging-idx {
  padding: 0 0.5rem;
  font-weight: bold;
  color: black;
}
.active {
  color: rgb(240, 108, 94);
}
* {
  font-family: "Noto Sans KR", sans-serif;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

*:focus {
  outline: none;
}

ul {
  list-style-type: none;
}

a {
  color: inherit;
  text-decoration: none;
}

input {
  outline: none;
}
</style>
