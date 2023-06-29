<template>
  <div class="mymenu-heart">
    <div class="heart__title">좋아요</div>
    <ul class="heart__categories">
      <li class="heart__category">
        <a><span>사진</span></a>
      </li>
      <li class="heart__category">
        <a><span style="font-weight:lighter; color:#606060">커뮤니티</span></a></li>
    </ul>
    <table class="heart__table">
      <thead class="table--head">
        <tr>
          <th>카테고리</th>
          <th>제목</th>
          <th>글쓴이</th>
          <th>작성시간</th>
          <th>조회수</th>
          <th>좋아요</th>
        </tr>
      </thead>
      <my-menu-heart-item></my-menu-heart-item>
      <tr class="empty">
        <td colspan="6">
          <div class="empty--div">
            <div class="empty--img"></div>
            <strong>좋아요한 게시글이 없습니다.</strong>
          </div>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
.mymenu-heart {
  width: 100%;
}

.heart__title {
  font-size: 22px;
  font-weight: bold;
  height: 55px;
}

.heart__categories {
  margin-bottom: 20px;
  font-size: 15px;
  display: flex;
}

.heart__category {
  margin-right: 15px;
  display: flex;
  justify-content: center;
  padding: 0px 2px 6px;
  border-bottom: 2px solid #5e2bb8;
}


.heart__number {
  font-size: 14px;
  font-weight: 400;
  color: #333;
  margin-bottom: 14px;
}

.heart__delete {
  width: 80px;
  height: 36px;
  padding: 7px 15px;
  font-size: 13px;
  border-radius: 3px;
  border: 1px solid lightgray;
}

.heart__table {
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
import MyMenuHeartItem from "@/components/MyMenu/MyMenuHeartItem.vue";
import CommonAxios from "@/util/http-common.js";

export default {
  data() {
    return {
      items: [],
    };
  },
  components: {
    MyMenuHeartItem,
  },
  created() {
    CommonAxios.get("/user");
  },
};
</script>
