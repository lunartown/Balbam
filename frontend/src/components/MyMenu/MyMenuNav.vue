<template>
  <div class="mymenu-navi">
    <h2 class="mymenu-title">마이메뉴</h2>
    <hr class="hr-bold" />
    <router-link class="navi-menu" :to="{ name: 'mymenuHistory' }">예약내역</router-link>
    <hr class="hr-lighter" />
    <router-link class="navi-menu" :to="{ name: 'mymenuBasket' }">찜한 상품</router-link>
    <hr class="hr-lighter" />
    <router-link class="navi-menu" :to="{ name: 'mymenuHeart' }">좋아요</router-link>
    <hr class="hr-lighter" />
    <router-link class="navi-menu" :to="{ name: 'mymenuUserInfo' }">회원정보 수정</router-link>
    <hr class="hr-lighter" />
    <router-link class="navi-menu" :to="{ name: 'mymenuPassword' }">비밀번호 변경</router-link>
    <hr class="hr-lighter" />
    <router-link class="navi-menu" :to="{ name: 'mymenuQuit' }">회원탈퇴</router-link>
    <hr class="hr-bold" />
  </div>
</template>
<style scoped>
.mymenu-navi {
  width: 150px;
  display: flex;
  flex-direction: column;
  text-align: left;
  color: black;
  margin-right: 40px;
}
.mymenu-title {
  font-size: 24px;
  padding-bottom: 29px;
}
.navi-menu {
  text-decoration: none;
  color: black;
  display: flex;
  align-items: center;
  font-size: 15px;
  height: 50px;
}
</style>
<script>
export default {};
</script>
