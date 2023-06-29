<template>
  <tr class="basket__item">
    <td>후기</td>
    <td><a href="/">나 혼자... 제주도 여행!</a></td>
    <td>내가 일정 짠거에는 27일에 한림공원 들리면서 협재 해수욕장에서 놀기로 되어있었는데 그</td>
    <td>2023-05-23</td>
    <td></td>
    <td class="heart__td">
      <div class="heart" :class="{ 'heart--clicked': heart }" @click="clickHeart">
        <svg
          v-if="!heart"
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
        <svg
          v-if="heart"
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
    </td>
  </tr>
</template>

<script>
export default {
  data() {
    return {
      heart: true,
    }
  },
  methods: {
    clickHeart() {
      this.heart = !this.heart;
    }
  }
};
</script>

<style scoped>
.basket__item {
  text-align: center;
  color: #333;
}
.basket__item > td {
  padding: 15px;
}
.heart__td {
  display: flex;
  justify-content: center;
}
.heart {
  margin-top: 10px;
  width: 20px;
  filter: invert(29%) sepia(0%) saturate(4404%) hue-rotate(138deg) brightness(150%) contrast(58%);
}

.heart--clicked {
  filter: invert(25%) sepia(33%) saturate(5707%) hue-rotate(348deg) brightness(104%) contrast(80%);
}
a:hover {
  text-decoration: underline;
  color: #64403a;
}
</style>
