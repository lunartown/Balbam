<template>
  <div class="notification-detail-box">
    <div class="notification-detail-topic">공지사항</div>
    <hr class="hr-bold" />
    <div class="notification-detail-header-box">
      <div class="notification-detail-title-box">
        <div class="notification-detial-cat">[{{ notification.category }}]</div>
        <div class="notification-detail-title">
          {{ notification.title }}
        </div>
      </div>
      <div>{{ notification.createTime }}</div>
    </div>
    <hr class="hr-lighter" />
    <tip-tap-content-ele
      :description="notification.content"
    ></tip-tap-content-ele>
    <hr class="hr-lighter" />
    <div class="mv-btn-box">
      <button @click="mvList" class="mv-list-btn">목록</button>
      <button @click="mvUpdate" v-if="isAdmin" class="mv-list-btn">수정</button>
      <button @click="delNotifcation" v-if="isAdmin" class="mv-list-btn">
        삭제
      </button>
    </div>
  </div>
</template>

<script>
import TipTapContentEle from "@/components/Element/TopTapContentEle.vue";
import httpCommon from "@/util/http-common";
const Swal = require("sweetalert2");
export default {
  components: {
    TipTapContentEle,
  },
  data() {
    return {
      notification: {},
      isAdmin: false,
    };
  },
  methods: {
    mvList() {
      this.$router.push({ name: "notificationList" });
    },
    mvUpdate() {
      this.$router.push({
        name: "notificationUpdate",
        params: { notification: this.notification },
      });
    },
    async delNotifcation() {
      await Swal.fire({
        title: "정말 삭제하시겠습니까?",
        showDenyButton: true,
        confirmButtonText: "네",
        denyButtonText: `아니요`,
      }).then((result) => {
        if (result.isConfirmed) {
          httpCommon
            .delete(`notification/${this.notification.notificationId}`)
            .then(() => {
              this.mvList();
            });
        }
      });
    },
    formatDate(date) {
      return (
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1 > 9
          ? (date.getMonth() + 1).toString()
          : "0" + (date.getMonth() + 1)) +
        "-" +
        (date.getDate() > 9
          ? date.getDate().toString()
          : "0" + date.getDate().toString())
      );
    },
  },
  created() {
    this.isAdmin = this.$route.params.isAdmin;
    httpCommon
      .get("notification", {
        params: { notificationId: this.$route.params.notificationId },
      })
      .then(({ data }) => {
        let date = new Date(data.createTime);
        data.createTime = this.formatDate(date);
        this.notification = data;
      });
  },
};
</script>

<style scoped>
.notification-detail-box {
  display: flex;
  flex-direction: column;
  margin: 90px 160px;
  min-width: 500px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: bold;
}
.mv-list-btn {
  color: white;
  background-color: rgb(240, 108, 94);
  border-radius: 5px;
  padding: 1rem 2rem;
  font-weight: bold;
  margin: 0 1rem;
  border: 0;
}
.mv-btn-box {
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}
.notification-detail-header-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 1rem 3rem;
  font-size: 1.25rem;
}
.notification-detail-title-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}
.notification-detail-title {
  margin-left: 1rem;
}
.notification-detail-topic {
  font-size: 1.3rem;
  padding-bottom: 2rem;
}
.notification-detial-cat {
  color: rgb(240, 108, 94);
}
</style>
