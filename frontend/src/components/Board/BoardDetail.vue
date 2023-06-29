<template>
  <div class="board-detail-box">
    <div class="board-detail-topic">게시판</div>
    <hr class="hr-bold" />
    <div class="board-detail-header-box">
      <div class="board-detail-title-box">
        <div class="board-detial-cat">[{{ article.category }}]</div>
        <div class="board-detail-title">{{ article.title }}</div>
      </div>
      <div>{{ article.createTime }}</div>
    </div>
    <hr class="hr-bold" />
    <tip-tap-content-ele :description="article.content"></tip-tap-content-ele>
    <hr class="hr-bold" />
    <div class="board-under-info-box">
      <div class="board-view-box">
        <div class="board-view-info">조회수 : {{ article.view }}</div>
        <div class="board-view-info">좋아요 : {{ article.heartCnt }}</div>
      </div>
      <div class="heart-box" @click="heartClick()">
        <svg
          class="card-heart-icon"
          :class="{ isHeart: article.heart }"
          xmlns="http://www.w3.org/2000/svg"
          height="24"
          viewBox="0 0 24 24"
          width="24"
        >
          <path d="M0 0h24v24H0z" fill="none" />
          <path
            v-if="article.heart"
            d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"
          />
          <path
            v-else
            d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55l-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"
          />
        </svg>
      </div>
    </div>
    <hr class="hr-bold" />
    <div class="mv-btn-box">
      <button @click="mvBoard" class="article_btn">목록</button>
      <button @click="updateArticle()" v-if="isAuthor" class="article_btn">
        수정
      </button>
      <button @click="delArticle" v-if="isAuthor" class="article_btn">
        삭제
      </button>
    </div>
    <div class="board-detail-topic">댓글({{ page.total }})</div>
    <div class="comment-input-box">
      <textarea
        class="comment-textarea"
        placeholder="댓글 작성"
        name=""
        id=""
        cols="30"
        rows="10"
        v-model="inputComment"
      ></textarea>
      <button class="comment-write-btn" @click="commentWrite()">작성</button>
    </div>
    <div
      class="comment-view-box"
      v-for="(comment, index) in comments"
      :key="index"
    >
      <div class="comment-author-box">
        <div>
          <img class="comment-avatar" src="https://placekitten.com/300/300" />
        </div>
        <div class="comment-author">{{ comment.name }}</div>
        <div class="comment-date">{{ comment.createTime }}</div>
      </div>
      <div class="comment-content">
        {{ comment.content }}
      </div>
    </div>
    <div class="pagination-box">
      <pagination
        :page="page"
        @onPage="onPage"
        v-if="this.comments.length !== 0"
      ></pagination>
    </div>
  </div>
</template>

<script>
import Pagination from "@/components/Element/PaginationEle.vue";
import TipTapContentEle from "@/components/Element/TopTapContentEle.vue";
import httpCommon from "@/util/http-common";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
const Swal = require("sweetalert2");
export default {
  components: { Pagination, TipTapContentEle },
  data() {
    return {
      isAuthor: false,
      isHeart: false,
      isNotHeart: true,
      articleId: 0,
      inputComment: "",
      article: {},
      comments: [],
      page: {
        total: 0,
        page: 1,
        count: 10,
      },
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    mvBoard() {
      this.$router.push({
        name: "boardMain",
      });
    },
    onPage(page) {
      this.page.page = page;
      this.getComment();
    },
    commentWrite() {
      let token = sessionStorage.getItem("access-token");
      if (this.inputComment == "" || token == null) return;
      let decodeToken = jwtDecode(token);
      if (this.isLogin) {
        httpCommon
          .post("article/comment", {
            articleId: this.articleId,
            userId: decodeToken.userId,
            content: this.inputComment,
          })
          .then(() => {
            this.getComment();
            this.inputComment = "";
          });
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
          let token = sessionStorage.getItem("access-token");
          if (token != null && this.isLogin) {
            httpCommon.delete(`article/${this.articleId}`).then(() => {
              this.mvBoard();
            });
          } else {
            alert("로그인을 확인해주세요.");
          }
        }
      });
    },
    updateArticle() {
      let token = sessionStorage.getItem("access-token");
      if (token == null) return;
      if (this.isLogin) {
        this.$router.push({
          name: "boardUpdate",
          params: {
            articleId: this.articleId,
            article: this.article,
          },
        });
      } else {
        alert("로그인을 확인해주세요.");
      }
    },
    getComment() {
      httpCommon
        .get("article/comment/count", { params: { articleId: this.articleId } })
        .then(({ data }) => {
          this.page.total = data;
        });

      httpCommon
        .get("article/comment", {
          params: {
            articleId: this.articleId,
            currentPage: this.page.page,
            totalCount: this.page.total,
          },
        })
        .then(({ data }) => {
          this.comments = data;
        });
    },
    heartClick() {
      let token = sessionStorage.getItem("access-token");
      if (token == null) return;
      let decodeToken = jwtDecode(token);
      if (this.isLogin) {
        if (this.article.heart) {
          httpCommon
            .delete("article/heart", {
              params: { articleId: this.articleId, userId: decodeToken.userId },
            })
            .then(() => {
              this.article.heartCnt--;
            });
        } else {
          httpCommon
            .post("article/heart", {
              articleId: this.articleId,
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
  },
  created() {
    let token = sessionStorage.getItem("access-token");
    this.articleId = this.$route.params.articleId;
    let decodeToken;
    let userId = 0;
    if (token != null && this.isLogin) {
      decodeToken = jwtDecode(token);
      userId = decodeToken.userId;
    }
    httpCommon
      .get("article", {
        params: { articleId: this.articleId, userId: userId },
      })
      .then(({ data }) => {
        this.article = data;
        if (this.article.userId == userId) this.isAuthor = true;
      });

    this.getComment();
  },
  computed: {
    ...mapState("userStore", [
      "isLogin",
      "isLoginError",
      "userInfo",
      "errorType",
    ]),
  },
};
</script>

<style scoped>
.board-detail-box {
  display: flex;
  flex-direction: column;
  margin: 90px 160px;
  min-width: 500px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: bold;
}
.article_btn {
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
.board-detail-header-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 1rem 3rem;
  font-size: 1.25rem;
}
.board-detail-title-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}
.board-detail-content-box {
  display: flex;
  flex-direction: row;
  padding: 1rem 3rem;
}
.board-detail-title {
  margin-left: 1rem;
}
.board-detail-topic {
  font-size: 1.3rem;
  padding-bottom: 2rem;
}
.board-detial-cat {
  color: rgb(240, 108, 94);
}
.board-under-info-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 1rem 0;
}
.board-view-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.board-view-info {
  padding: 0 3rem;
}
.heart-box {
  padding-right: 3rem;
}
.comment-input-box {
  display: flex;
  flex-direction: row;
}
.comment-textarea {
  width: 90%;
  height: 6rem;
  padding: 1rem;
  resize: none;
  border: 2px solid lightgray;
  border-radius: 1rem;
  font-weight: bold;
  font-size: 1rem;
}
.comment-write-btn {
  width: 10%;
  margin-left: 2rem;
  border-radius: 1rem;
  font-weight: bold;
  font-size: 1.5rem;
  background-color: rgb(240, 108, 94);
  color: white;
  border: 0;
}
.comment-view-box {
  margin-top: 2rem;
}
.comment-author-box {
  display: flex;
  flex-direction: row;
  justify-content: left;
}
.comment-avatar {
  border-radius: 100%;
  width: 3rem;
  height: 3rem;
}
.comment-author,
.comment-date {
  margin: auto 0 auto 1rem;
}
.comment-date {
  color: gray;
}
.pagination-box {
  display: flex;
  justify-content: center;
  margin-top: 3rem;
}
.comment-content {
  font-weight: bold;
  margin: 1rem 0;
}
.isHeart {
  filter: invert(65%) sepia(40%) saturate(5423%) hue-rotate(324deg)
    brightness(100%) contrast(88%);
}
</style>
