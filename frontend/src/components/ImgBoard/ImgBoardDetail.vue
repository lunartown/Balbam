<template>
  <div id="image-board-detail">
    <carousel-view class="carousel" :imgs="article.img"></carousel-view>
    <board-content
      class="board-content"
      :isPackage="false"
      :content="article"
      :isAuthor="isAuthor"
      @heartClick="heartClick"
      @delArticle="delArticle()"
    ></board-content>
  </div>
</template>

<script>
import CarouselView from "@/components/Element/CarouselViewEle.vue";
import BoardContent from "@/components/Element/BoardContentEle.vue";
import httpCommon from "@/util/http-common";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
const Swal = require("sweetalert2");
export default {
  components: { CarouselView, BoardContent },
  data() {
    return {
      article: {},
      isAuthor: false,
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    mvBoard() {
      this.$router.push({
        name: "imgBoardMain",
      });
    },
    heartClick() {
      let token = sessionStorage.getItem("access-token");
      let decodeToken = jwtDecode(token);
      if (this.isLogin) {
        if (this.article.heart) {
          httpCommon
            .delete("img/article/heart", {
              params: {
                articleId: this.article.articleId,
                userId: decodeToken.userId,
              },
            })
            .then(() => {
              this.article.heartCnt--;
            });
        } else {
          httpCommon
            .post("img/article/heart", {
              articleId: this.article.articleId,
              userId: decodeToken.userId,
            })
            .then(() => {
              this.article.heartCnt++;
            });
        }
        this.article.heart = !this.article.heart;
      } else {
        alert("로그인을 확인해주세요.");
      }
    },
    async delArticle() {
      await Swal.fire({
        title: "정말 삭제하시겠습니까?",
        showDenyButton: true,
        confirmButtonText: "네",
        denyButtonText: `아니요`,
      }).then((result) => {
        if (result.isConfirmed) {
          if (this.isLogin) {
            httpCommon.delete(`img/article/${this.article.articleId}`).then(() => {
              this.mvBoard();
            });
          } else {
            alert("로그인을 확인해주세요.");
          }
        }
      });
    },
  },
  created() {
    let token = sessionStorage.getItem("access-token");
    let decodeToken = jwtDecode(token);
    httpCommon
      .get("img/article", {
        params: {
          articleId: this.$route.params.articleId,
          userId: decodeToken.userId,
        },
      })
      .then(({ data }) => {
        this.article = data;
        if (this.article.userId == decodeToken.userId) this.isAuthor = true;
        if (!this.article.profileImg)
          this.article.profileImg = require("@/assets/img/default_profile_img.jpg");
      });
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
  },
};
</script>

<style scoped>
#image-board-detail {
  display: flex;
  flex-direction: row;
  justify-content: left;
}
.carousel {
  width: 45%;
  height: 100%;
  position: sticky;
  top: 100px;
  right: 300px;
}
.board-content {
  width: 55%;
}
</style>
