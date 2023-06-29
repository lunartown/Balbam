<template>
  <div class="card-box">
    <div class="card-img_box">
      <img :src="pack.mainImg" alt="" />
    </div>
    <div class="card-region">{{ pack.region }}</div>
    <div class="card-title">
      {{ pack.title }}
    </div>
    <div class="price">{{ setPrice }}원</div>
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
  width: 290px;
}
.card-img_box {
}
.card-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 1rem;
}
.card-region,
.card-title {
  padding-top: 5px;
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
.price {
  font-weight: bold;
  color: rgb(240, 108, 94);
  padding-block: 5px;
}
</style>
