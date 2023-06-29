<template>
  <div class="card-box">
    <div>
      <img :src="pack.mainImg" />
    </div>
    <div class="card-region">{{ pack.region }}</div>
    <div class="card-title">
      {{ pack.title }}
    </div>
    <div class="price">{{ setPrice }}원</div>
    <div class="card-info">
      <div class="card-date">{{ pack.createTime }}</div>
      <div>
        <svg
          class="card-heart-icon"
          xmlns="http://www.w3.org/2000/svg"
          height="24"
          viewBox="0 0 24 24"
          width="24"
          :class="{ isHeart: pack.heart }"
        >
          <path d="M0 0h24v24H0z" fill="none" />
          <path
            v-if="pack.heart"
            d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"
          />
          <path
            v-else
            d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55l-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"
          />
        </svg>
      </div>
    </div>
    <hr class="hr-lighter" />
    <div class="card-view-box">
      <div class="card-view-info">찜 {{ pack.heartCnt }} ↑</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    pack: Object,
  },
  computed: {
    setPrice() {
      let result = "";
      const price = String(this.pack.price);
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
.card-box {
  display: flex;
  flex-direction: column;
  border-radius: 1rem;
  margin: 0 2rem;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
}
.card-box img {
  width: 100%;
  object-fit: cover;
  border-radius: 1rem;
}
.card-region,
.card-title {
  padding-top: 1rem;
  padding-left: 1rem;
  font-weight: bold;
}
.card-region {
  font-size: 0.8rem;
}
.card-info {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 1rem 1rem 0 1rem;
}
.card-date {
  color: gray;
  font-size: 0.8rem;
}
.hr-lighter {
  border: solid 1px lightgray;
  margin: 0.5rem 1rem;
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
  padding-left: 1rem;
}
.card-heart-icon {
  filter: invert(52%) sepia(5%) saturate(3%) hue-rotate(324deg) brightness(95%)
    contrast(88%);
}
.price {
  font-weight: bold;
  color: rgb(240, 108, 94);
  padding: 1rem 1rem 0 1rem;
}
.isHeart {
  filter: invert(65%) sepia(40%) saturate(5423%) hue-rotate(324deg)
    brightness(100%) contrast(88%);
}
</style>
