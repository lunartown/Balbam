<template>
  <div class="mymenu-basket">
    <div class="basket__title">찜한 상품</div>
    <div class="basket__number">총 0건</div>
    <div class="basket__delete">선택삭제</div>
    <table class="basket__table">
      <thead class="table--head">
        <tr>
          <th>
            <div class="checkbox">
              <svg
                class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
                focusable="false"
                aria-hidden="true"
                viewBox="0 0 24 24"
                data-testid="CheckBoxOutlinedIcon"
              >
                <path
                  d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V5h14v14zM17.99 9l-1.41-1.42-6.59 6.59-2.58-2.57-1.42 1.41 4 3.99z"
                ></path>
              </svg>
            </div>
          </th>
          <th>지역</th>
          <th>상품명</th>
          <th>상품금액</th>
          <th>출발일/귀국일</th>
        </tr>
      </thead>
      <my-menu-basket-item></my-menu-basket-item>
      <tr class="empty">
        <td colspan="5">
          <div class="empty--div">
            <div class="empty--img"></div>
            <strong>찜한 상품이 없습니다.</strong>
          </div>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
.mymenu-basket {
  width: 100%;
}

.basket__title {
  font-size: 22px;
  font-weight: bold;
  height: 55px;
}

.basket__categories {
  margin-bottom: 20px;
  font-size: 15px;
  display: flex;
}

.basket__number {
  font-size: 14px;
  font-weight: 400;
  color: #333;
  margin-bottom: 14px;
}

.basket__delete {
  width: 80px;
  height: 36px;
  padding: 7px 15px;
  font-size: 13px;
  border-radius: 3px;
  border: 1px solid lightgray;
}

.basket__table {
  margin-top: 15px;
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

.checkbox {
  margin-left: 5px;
  width: 22px;
  height: 22px;
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
import MyMenuBasketItem from "@/components/MyMenu/MyMenuBasketItem.vue";
import CommonAxios from "@/util/http-common.js";

export default {
  data() {
    return {
      items: [],
    };
  },
  components: {
    MyMenuBasketItem,
  },
  created() {
    CommonAxios.get("/user");
  },
};
</script>
