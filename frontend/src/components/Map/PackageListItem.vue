<template>
  <div class="list-item">
    <div class="inline">
      <div class="container">
        <div class="title">[경북 경주] 경주여행 가이드 투어</div>
        <div class="content">
          <div class="region">대구·경북</div>
          <div class="hit">찜 264회</div>
        </div>
        <div class="content">
          <div class="cost--title">인당</div>
          <div class="cost">300,000원</div>
        </div>
      </div>
      <div class="heart" @click="heartClick" :class="{'heart--clicked': heart}">
        <svg v-if="!heart"
          class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
          focusable="false"
          aria-hidden="true"
          viewBox="0 0 24 24"
          data-testid="FavoriteBorderIcon"
        >
          <path
            d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"
          ></path>
        </svg>
        <svg v-if="heart"
          class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
          focusable="false"
          aria-hidden="true"
          viewBox="0 0 24 24"
          data-testid="FavoriteIcon"
        >
          <path
            d="m12 21.35-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"
          ></path>
        </svg>
      </div>
    </div>

    <div class="picture">
      <img src="@/assets/img/main-region.png" alt="" />
    </div>
  </div>
</template>
<style scoped>
.list-item {
  padding-top: 15px;
  padding-inline: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #dbdbdb;
}
.list-item:hover {
  background-color: rgb(247, 249, 250);
}
.inline {
  display: flex;
  justify-content: space-between;
}
.title {
  color: #3e2723;
  font-weight: bold;
  font-size: 16px;
  margin-bottom: 5px;
}

.heart {
  margin-top: 10px;
  width: 20px;
  filter: invert(29%) sepia(0%) saturate(4404%) hue-rotate(138deg) brightness(150%) contrast(58%);
}
.heart--clicked {
  filter: invert(25%) sepia(33%) saturate(5707%) hue-rotate(348deg) brightness(104%) contrast(80%);
}
.content {
  display: flex;
  align-items: end;
}
.region {
  font-size: 14px;
  color: #303030;
  margin-right: 6px;
}

.hit {
  letter-spacing: -0.05em;
  font-size: 13px;
  color: #505050;
}

.cost--title {
  font-size: 13px;
  color: #505050;
  margin-right: 4px;
}

.cost {
  font-size: 16px;
  color: #0a134e;
  font-weight: bold;
}
.picture {
  margin-top: 10px;
  width: 260px;
  height: 150px;
}
.picture > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
<script>
export default {
  data() {
    return {
      heart: false,
    }
  },
  methods: {
    heartClick() {
      this.heart = !this.heart;
    }
  }
};
</script>
