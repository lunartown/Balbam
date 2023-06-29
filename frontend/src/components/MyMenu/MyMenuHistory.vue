<template>
  <div class="mymenu-history">
    <div class="history__title">예약내역</div>
    <ul class="history__categories">
      <li class="history__category" :class="category == 1 && 'selected'" @click="search(1)">
        <span>예약내역</span><span v-if="category == 1">&nbsp;{{ items.length }}</span>
      </li>
      <li class="history__category" :class="category == 2 && 'selected'" @click="search(2)">
        <span>취소/환불</span><span v-if="category == 2">&nbsp;{{ items.length }}</span>
      </li>
      <li class="history__category" :class="category == 3 && 'selected'" @click="search(3)">
        <span>지난내역</span><span v-if="category == 3">&nbsp;{{ items.length }}</span>
      </li>
    </ul>
    <div class="history__number">총 {{ items.length }}건</div>
    <table class="history__table">
      <thead class="table--head">
        <tr>
          <th>예약일</th>
          <th>상품명</th>
          <th>결제 금액</th>
          <th>인원</th>
          <th>출발일/귀국일</th>
          <th>지역</th>
          <th>여행/예약 상태</th>
        </tr>
      </thead>
      <tbody>
        <my-menu-history-item
          v-for="(item, index) in items"
          :key="index"
          :item="item"
        ></my-menu-history-item>
        <tr v-if="!items.length" class="empty">
          <td colspan="7">
            <div class="empty--div">
              <div class="empty--img"></div>
              <strong>예약한 상품이 없습니다.</strong>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.mymenu-history {
  width: 100%;
}

.history__title {
  font-size: 22px;
  font-weight: bold;
  height: 55px;
}

.history__categories {
  margin-bottom: 20px;
  font-size: 15px;
  display: flex;
}

.history__category {
  cursor: pointer;
  margin-right: 15px;
  display: flex;
  justify-content: center;
  padding: 0px 2px 6px;
  font-weight: lighter;
  color: #606060;
}
.selected {
  color: #333;
  font-weight: 400;
  border-bottom: 2px solid #5e2bb8;
}

.history__number {
  font-size: 14px;
  font-weight: 400;
  color: #333;
  margin-bottom: 14px;
}
.history__table {
  font-size: 15px;
  border-collapse: separate;
  border-spacing: 0px;
  width: 100%;
  border-top: 0.7px solid black;
  border-bottom: 0.7px solid lightgray;
}

.table--head {
  height: 54px;
  background-color: #f7f7f7;
}

.table--head th {
  padding: 15px;
  text-align: center;
  border-bottom: 1px solid #ddd;
  color: #333;
}

.empty {
  height: 202px;
}

.empty--div {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.empty--div > strong {
  position: relative;
  display: block;
  color: #333;
  margin-top: 20px;
  line-height: 1.6;
  font-size: 17px;
  font-weight: 400;
}
.empty--img {
  content: "";
  display: inline-block;
  width: 45px;
  height: 45px;
  background: url(https://image.hanatour.com/usr/static/img2/pc/com/spr_icn.png) no-repeat;
  background-position: 0 -205px;
}
</style>

<script>
import MyMenuHistoryItem from "./MyMenuHistoryItem.vue";
import CommonAxios from "@/util/http-common.js";
import jwtDecode from "jwt-decode";

export default {
  data() {
    return {
      category: 1,
      items: [],
      page: {
        total: 0,
        page: 1,
        count: 10,
      },
    };
  },
  components: {
    MyMenuHistoryItem,
  },
  methods: {
    async search(index) {
      this.category = index;
      let token = sessionStorage.getItem("access-token");
      let decodeToken = jwtDecode(token);
      let userId = decodeToken.userId;
      let canceled = 0;
      let passed = null;
      if (index == 2) {
        canceled = 1;
      } else if (index == 1) {
        passed = ">=";
      } else if (index == 3) {
        passed = "<=";
      }
      await CommonAxios.get("/package/countHistory", {
        params: {
          userId: userId,
          canceled: canceled,
          passed: passed,
        },
      }).then(({ data }) => {
        console.log(data);
        this.page.total = data;
      });
      await CommonAxios.get("package/getHistory", {
        params: {
          userId: userId,
          canceled: canceled,
          passed: passed,
          currentPage: this.page.page,
          totalCount: this.page.total,
        },
      }).then(({ data }) => {
        console.log(data);
        this.items = data;
      });
    },
  },
  async created() {
    await this.search(1);
  },
};
</script>
