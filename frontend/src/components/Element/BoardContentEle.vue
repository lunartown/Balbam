<template>
  <div id="article-board-div">
    <div class="article-header">
      <div class="region">{{ content.region }}</div>
      <div class="subject">
        {{ content.title }}
      </div>
      <div class="price" v-if="isPackage">{{ setPrice }}원</div>
      <div class="flex-line">
        <div class="date" v-if="isPackage">{{ content.startDate }} ~ {{ content.endDate }}</div>
        <div class="date" v-else>{{ content.createTime }}</div>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          :class="{ isHeart: content.heart }"
          height="24"
          viewBox="0 0 24 24"
          width="24"
          class="heart-icon"
          @click="heartClick"
        >
          <path d="M0 0h24v24H0z" fill="none" />
          <path
            v-if="content.heart"
            d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"
          />
          <path
            v-else
            d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55l-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"
          />
        </svg>
      </div>
    </div>
    <div class="article-main">
      <div class="article-info-box">
        <hr class="hr-lighter" />
        <div class="flex-line info-box">
          <div class="avatar-box">
            <img class="avatar" :src="content.profileImg" />
            <div class="avatar-name">{{ content.name }}</div>
          </div>
          <div class="info-line">
            <div class="article-info" v-if="!isPackage">조회수 {{ content.view }}</div>
            <div class="article-info">좋아요 {{ content.heartCnt }}</div>
          </div>
        </div>
        <hr class="hr-lighter" />
      </div>

      <div class="content">
        <tip-tap-content-ele :description="content.content"></tip-tap-content-ele>
      </div>
      <div class="btn-box">
        <button class="detail-btn" v-if="isAuthor">수정하기</button>
        <button class="detail-btn" v-if="isAuthor" @click="delArticle()">삭제하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import TipTapContentEle from "@/components/Element/TopTapContentEle.vue";
export default {
  components: { TipTapContentEle },
  props: {
    isPackage: Boolean,
    content: {},
    isAuthor: Boolean,
  },
  data() {
    return {};
  },
  methods: {
    heartClick() {
      this.$emit("heartClick");
    },
    delArticle() {
      this.$emit("delArticle");
    },
  },
  computed: {
    setPrice() {
      if (!this.isPackage) return 0;
      let result = "";
      const price = String(this.content.price);
      const len = price.length;
      for (var i = 0; i < len; i++) {
        if (i % 3 == 0 && i != 0) result = "," + result;
        result = price.charAt(len - 1 - i) + result;
      }
      return result;
    },
  },
};
</script>

<style scoped>
#article-board-div {
  display: flex;
  flex-direction: column;
  min-width: 750px;
  text-align: left;
  font-weight: bold;
}
.article-header {
  margin: 5rem 30% 5rem 5rem;
}

.region {
  margin-top: 50px;
  justify-content: left;
  font-size: 1.25rem;
}
.flex-line {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.icon {
  padding: 0 auto;
}
.date {
  text-align: left;
  margin: auto 0;
  font-size: 1.25rem;
  color: gray;
}
.subject {
  margin: 15px 0;
  font-size: 2rem;
  color: black;
}
.price {
  margin: 2rem 0;
  font-size: 2rem;
  color: rgb(240, 108, 94);
}
.article-main {
  background-color: rgb(247, 247, 247);
}
.article-info {
  padding: 1rem;
}
.info-line {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.content {
  padding: 0 30% 5rem 5rem;
  color: black;
  font-size: 1.1rem;
}
.hr-lighter {
  border: solid 1px lightgray;
  margin: 1rem 0;
}
.heart-icon {
  cursor: pointer;
  width: 40px;
  height: 40px;
  padding: 5px;
}

.avatar {
  border-radius: 100%;
  width: 50px;
  height: 50px;
}
.avatar-box {
  display: flex;
  flex-direction: row;
  margin: auto 0;
}
.avatar-name {
  margin: auto 1rem;
  font-size: 1.25rem;
}
.isHeart {
  filter: invert(65%) sepia(40%) saturate(5423%) hue-rotate(324deg) brightness(100%) contrast(88%);
}
.btn-box {
  display: flex;
  justify-content: right;
  flex-direction: row;
  padding: 0 30% 0 5rem;
}
.detail-btn {
  background-color: rgb(240, 108, 94);
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  margin: 0.5rem 1rem;
}
.info-box {
  padding: 0 30% 0 5rem;
}
</style>
