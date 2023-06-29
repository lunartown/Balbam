<template>
  <div class="card-box">
    <div class="img-main-box">
      <img class="card-img" :src="article.mainImg" />

      <div class="img-header-box">
        <div class="card-region">{{ article.region }}</div>
        <div class="img-heart-box">
          <div class="heart-count">{{ article.heartCnt }}</div>
          <svg
            class="card-heart-icon"
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"
            />
          </svg>
        </div>
      </div>
    </div>
    <div class="card-title">{{ article.title }}</div>
    <div class="card-info">
      <div class="card-name">{{ article.name }}</div>
      <div class="card-date">{{ article.createTime }}</div>
    </div>
    <hr class="hr-lighter" />
    <div class="card-view-box">
      <div class="card-view-info">조회수 {{ setView }} ↑</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    article: Object,
  },
  computed: {
    setView() {
      var result = 100000;
      while (result > 0) {
        if (this.article.view / result >= 1) {
          return result;
        }
        result /= 10;
      }
      return result;
    },
  },
};
</script>

<style scoped>
.card-box {
  width: 290px;
  display: flex;
  flex-direction: column;
}
.img-main-box {
  position: relative;
  display: flex;
  flex-direction: row;
  width: 290px;
  height: 220px;
  overflow: hidden;
  border-radius: 1rem;
}
.card-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: all 0.2s linear;
}
.card-img:hover {
  transform: scale(1.4);
}
.img-header-box {
  display: flex;
  flex-direction: row;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  justify-content: space-between;
  background-image: linear-gradient(to top, transparent, rgba(0, 0, 0, 0.6));
  border-radius: 1rem;
}
.img-heart-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 1rem 1rem 1rem 0;
}
.heart-count {
  margin: auto 5px;
  color: white;
  font-weight: bold;
}
.card-region {
  margin-bottom: 2rem;
  padding: 0.5rem;
  color: white;
  background-color: #3b4552;
}

.card-title {
  height: 78px;
  padding-block: 10px;
  font-size: 1.25rem;
  font-weight: bold;
}
.card-date {
  text-align: right;
  padding: 5px;
  color: gray;
  font-size: 0.8rem;
}
.hr-lighter {
  border: dashed 1px lightgray;
  margin-block: 5px;
}
.card-view-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 0.5rem 0;
  margin-bottom: 1rem;
}
.card-view-info {
  color: gray;
  font-size: 0.8rem;
}
.card-heart-icon {
  filter: invert(65%) sepia(40%) saturate(5423%) hue-rotate(324deg) brightness(100%) contrast(88%);
  margin: auto 0;
}
.card-info {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.card-name {
  color: rgb(240, 108, 94);
  font-weight: bold;
}
</style>
