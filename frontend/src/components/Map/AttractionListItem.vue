<template>
  <div id="attraction-list-item">
    <div class="inline">
      <div class="container">
        <div class="content">
          <div class="title">{{ attraction.title }}</div>
          <div class="category">{{ attraction.category }}</div>
        </div>
        <div class="content">
          <div class="address">{{ attraction.addr1 | getAddress }}</div>
          <div class="hit">조회수 {{ attraction.readCount | numberPoint }}회</div>
        </div>
      </div>
      <div class="heart" :class="{'heart--clicked': heart}" @click="clickHeart">
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
      <img class="picture--img" :src="attraction.firstImage" onerror="this.style.display='none'" />
    </div>
  </div>
</template>

<style scoped>
#attraction-list-item {
  padding-top: 15px;
  padding-inline: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #dbdbdb;
}

#attraction-list-item:hover {
  background-color: rgb(247, 249, 250);
}

.inline {
  display: flex;
  justify-content: space-between;
}
.heart {
  margin-top: 10px;
  width: 20px;
  filter: invert(29%) sepia(0%) saturate(4404%) hue-rotate(138deg) brightness(150%) contrast(58%);
}

.heart--clicked {
  filter: invert(25%) sepia(33%) saturate(5707%) hue-rotate(348deg) brightness(104%) contrast(80%);
}
.title {
  font-size: 16px;
  font-weight: bold;
  color: #3e2723;
  margin-right: 5px;
}
.content {
  align-items: end;
  display: flex;
}
.category {
  font-size: 12px;
  color: #808080;
}

.address {
  margin-right: 5px;
  font-size: 13px;
  color: #464040;
}
.hit {
  letter-spacing: -0.07em;
  font-size: 12px;
  color: #808080;
}
.picture {
  margin-top: 10px;
  width: 100%;
}

.picture > img {
  width: 100%;
  height: 150px;
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
  props: {
    attraction: Object,
  },
  filters: {
    getAddress(value) {
      let array = value.split(" ", 3);
      return array[0] + " " + array[1];
    },
    numberPoint(value) {
      return String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    clickHeart() {
      this.heart = !this.heart;
    }
  }
};
</script>
