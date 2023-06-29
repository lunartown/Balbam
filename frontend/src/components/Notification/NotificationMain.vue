<template>
  <div>
    <div class="notification-main">
      <div class="notification-navi">
        <h2>고객센터</h2>
        <hr class="hr-bold" />
        <router-link class="navi-menu" :to="{ name: 'notificationInquiry' }"
          >자주 찾는 질문</router-link
        >
        <hr class="hr-lighter" />
        <router-link class="navi-menu" :to="{ name: 'notificationHistory' }"
          >문의내역</router-link
        >
        <hr class="hr-lighter" />
        <router-link class="navi-menu" :to="{ name: 'notificationList' }"
          >공지사항</router-link
        >
        <hr class="hr-bold" />
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.notification-main {
  display: flex;
  flex-direction: row;
  justify-content: center;
  font-family: "Noto Sans KR", sans-serif;
  margin-top: 90px;
}
.notification-navi {
  display: flex;
  flex-direction: column;
  text-align: left;
  color: black;
}
.navi-menu {
  text-decoration: none;
  font-weight: bold;
  color: black;
  margin: 0.5rem 0;
  font-size: 1.1rem;
  padding: 0.5rem 1rem;
}
.navi-menu:hover {
  color: rgb(240, 108, 94);
}
.navi-menu.router-link-exact-active {
  color: rgb(240, 108, 94);
}
</style>
<style>
.hr-bold {
  border: solid 1px black;
  opacity: 1;
  margin: 0.5rem 0;
}
.hr-lighter {
  border: solid 1px lightgray;
  margin: 0.5rem 0;
}
</style>
