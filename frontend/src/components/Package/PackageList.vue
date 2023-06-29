<template>
  <div class="package-search-box">
    <div class="package-list-main-box">
      <div>
        <div class="package-list-desc">오사카</div>
      </div>
      <div class="package-hot-board-box">
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>

        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
        <div class="card-package-box" @click="cardClick()">
          <card-view-ele :data="hotPackage"></card-view-ele>
        </div>
      </div>
    </div>
    <div class="paging-box">
      <pagination :page="page" @onPage="onPage" v-if="this.items.length !== 0"></pagination>
    </div>
  </div>
</template>

<script>
import CardViewEle from "@/components/Element/CareViewEle.vue";
import Pagination from "@/components/Element/PaginationEle.vue";
export default {
  components: {
    CardViewEle,
    Pagination,
  },
  data() {
    return {
      items: ["1", "2"],
      menuTitles: ["서울", "경기", "강원", "충청", "전라", "경상", "제주"],
      hotPackage: {
        region: "제주도",
        title: "[제주] 나 혼자... 제주도 여행!",
        date: "2023년 5월 22일",
        img: "https://static.hanatour.com/product/2023/01/17/073401dx8u/small.jpg",
        view: 10435,
        heartCnt: 1022,
        price: "300000",
      },
      packages: [
        [
          {
            region: "제주도",
            title: "[제주] 나 혼자... 제주도 여행!",
            date: "2023년 5월 22일",
            img: "https://static.hanatour.com/product/2023/01/17/073401dx8u/small.jpg",
            view: 10435,
            heartCnt: 1022,
            price: "300000",
          },
        ],
        [
          {
            region: "제주도",
            title: "[제주] 나 혼자... 제주도 여행!",
            date: "2023년 5월 22일",
            img: "https://static.hanatour.com/product/2023/01/17/073401dx8u/small.jpg",
            view: 10435,
            heartCnt: 1022,
            price: "300000",
          },
        ],
      ],
      page: {
        total: 290,
        page: 1,
        count: 10,
      },
    };
  },
  methods: {
    cardClick() {
      this.$router.push({ name: "packageDetail", params: { ispackage: false } });
    },
    onPage(page) {
      this.page.page = page;
    },
  },
};
</script>

<style scoped>
.package-search-box {
  margin-top: 2px;
  margin-bottom: 4rem;
}
.package-list-main-box {
  margin: 90px 8%;
}
.package-list-desc {
  text-align: center;
  font-weight: bold;
  font-size: 2.5rem;
  margin-bottom: 3rem;
}
.package-hot-board-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
.paging-box {
  display: flex;
  justify-content: center;
  margin-bottom: 5rem;
}
</style>
