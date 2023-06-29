<template>
  <div>
    <div id="package-content-box">
      <carousel-view class="carousel" :imgs="packages.img"></carousel-view>
      <board-content
        class="board-content"
        :isPackage="true"
        :content="packages"
        @heartClick="heartClick"
      ></board-content>
    </div>
    <div class="package-detail-box">
      <div>
        <div>
          <board-top-menu :titles="titles" @catClick="menuClick"></board-top-menu>
        </div>
        <div class="basic-info-box">
          <div>
            <div class="detail-label">기본정보</div>
            <div class="detail-img-box">
              <div v-for="(img, index) in packages.descImg" :key="index">
                <img :src="img" />
              </div>
            </div>
          </div>
        </div>
        <div class="inquiry-info-box">
          <div class="inquiry-top-box">
            <div class="detail-label">상품문의</div>
            <button class="inquiry-wrt-btn" ref="inquiry" @click="writeInquiry()">문의하기</button>
          </div>
          <ul class="inquiry-caution-list">
            <li class="inquiry-catuion-item">
              상품과 관계없는 글, 양도, 광고성, 욕설, 비방 도배 등의 글은 예고 없이 삭제됩니다.
            </li>
            <li class="inquiry-catuion-item">
              공개 게시판 이므로 개인정보(주민번호, 연락처, 주소, 계좌번호, 카드번호 등)가 타인에게
              노출될 경우 개인정보 도용의 위험이 있으니 주의해 주시기 바랍니다.
            </li>
          </ul>
          <hr class="hr-bold" />
          <div
            class="inquiry-main-box"
            v-for="(inquiry, index) in inquirys"
            :key="index"
            :class="[{ active: inquriySelected == index ? true : false }]"
          >
            <div class="inquiry-author-box">
              <div v-if="!inquiry.answerId" class="inquiry-process process-wait">답변대기</div>
              <div v-else class="inquiry-process process-complete">답변완료</div>
              <div class="inquiry-author">{{ inquiry.userName }}</div>
              <div class="inquiry-date">{{ inquiry.createTime }}</div>
            </div>
            <div class="inquiry-content">{{ inquiry.content }}</div>
            <div class="inquiry-btn-box">
              <button class="inquiry-btn" @click="writeAnswer(inquiry.inquiryId)">답변달기</button
              ><button class="inquiry-btn">수정하기</button>
            </div>
            <div class="inquriy-answer-view-btn-box">
              <button class="inquiry-answer-view-btn" @click="inquiryClick(index)">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="24"
                  viewBox="0 0 24 24"
                  width="24"
                  class="not-active-icon"
                >
                  <path d="M0 0h24v24H0z" fill="none" />
                  <path d="M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6z" />
                </svg>
              </button>
            </div>

            <div v-if="inquriySelected == index">
              <div class="inquiry-author-box">
                <div class="inquiry-answer-author">{{ inquiry.guideName }}</div>
                <div class="inquiry-date">{{ inquiry.answerTime }}</div>
              </div>
              <div class="inquiry-content">
                {{ inquiry.answer }}
              </div>
              <div class="inquiry-btn-box">
                <button class="inquiry-btn">수정하기</button>
              </div>
            </div>
          </div>
          <div class="pagination-box">
            <pagination
              :page="inquiryPage"
              @onPage="onInquiryPage"
              v-if="this.inquirys.length !== 0"
            ></pagination>
          </div>
        </div>
        <div class="review-main-box">
          <div class="review-info-box">
            <div class="detail-label">{{ reviewPage.total }}개의 상품 리뷰</div>
            <button class="inquiry-wrt-btn" ref="review" @click="writeReview">리뷰 작성하기</button>
          </div>
          <div class="review-list-box">
            <div class="reivew-content-box" v-for="(review, index) in reviews" :key="index">
              <hr class="hr-bold" />
              <div class="review-author-box">
                <div class="review-author">{{ review.name }}</div>
                <div class="review-date">{{ review.createTime }}</div>
              </div>
              <div class="review-content">{{ review.content }}</div>
            </div>
            <div class="pagination-box">
              <pagination
                :page="reviewPage"
                @onPage="onReviewPage"
                v-if="this.reviews.length !== 0"
              ></pagination>
            </div>
          </div>
        </div>
      </div>
      <div class="package-buying-form">
        <div class="package-buy-info-box">
          <div class="buying-region">{{ packages.region }}</div>
          <div class="buying-title">{{ packages.title }}</div>
        </div>
        <hr class="hr-lighter" />
        <div class="package-price-box">
          <div class="buying-price-desc">결제 금액</div>
          <div class="buying-price">{{ setPrice }}원</div>
        </div>
        <button class="buying-btn" @click="reserve">예약하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import CarouselView from "@/components/Element/CarouselViewEle.vue";
import BoardContent from "@/components/Element/BoardContentEle.vue";
import Pagination from "@/components/Element/PaginationEle.vue";
import BoardTopMenu from "@/components/Element/BoardTopMenu.vue";
import httpCommon from "@/util/http-common";
import jwtDecode from "jwt-decode";
import { mapState, mapActions } from "vuex";
const Swal = require("sweetalert2");
export default {
  components: { CarouselView, BoardContent, Pagination, BoardTopMenu },
  data() {
    return {
      inquriySelected: -1,
      titles: ["기본정보", "상품문의", "리뷰"],
      packageId: "",
      isGuide: false,
      inquiryPage: {
        total: 0,
        page: 1,
        count: 10,
      },
      reviewPage: {
        total: 0,
        page: 1,
        count: 10,
      },
      packages: {},
      inquirys: [],
      reviews: [],
    };
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    onInquiryPage(page) {
      this.inquiryPage.page = page;
      this.getInquiry();
    },
    onReviewPage(page) {
      this.reviewPage.page = page;
      this.getReview();
    },
    inquiryClick(idx) {
      if (this.inquirys[idx].answerId) {
        if (this.inquriySelected == idx) this.inquriySelected = -1;
        else this.inquriySelected = idx;
      }
    },
    menuClick(idx) {
      if (idx == 1) {
        this.$refs.inquiry.focus();
      } else if (idx == 2) {
        this.$refs.review.focus();
      }
    },
    getInquiry() {
      httpCommon
        .get("package/inquiry/count", { params: { packageId: this.packageId } })
        .then(({ data }) => {
          this.inquiryPage.total = data;
          httpCommon
            .get("package/inquiry", {
              params: {
                currentPage: this.inquiryPage.page,
                totalCount: this.inquiryPage.total,
                word: this.packageId,
              },
            })
            .then(({ data }) => {
              this.inquirys = data;
            });
        });
    },
    getReview() {
      httpCommon
        .get("package/review/count", { params: { packageId: this.packageId } })
        .then(({ data }) => {
          this.reviewPage.total = data;
          httpCommon
            .get("package/review", {
              params: {
                currentPage: this.reviewPage.page,
                totalCount: this.reviewPage.total,
                word: this.packageId,
              },
            })
            .then(({ data }) => {
              this.reviews = data;
            });
        });
    },
    heartClick() {
      let token = sessionStorage.getItem("access-token");
      if (token == null) return;
      let decodeToken = jwtDecode(token);
      if (this.packages.heart) {
        httpCommon
          .delete("package/heart", {
            params: {
              packageId: this.packages.packageId,
              userId: decodeToken.userId,
            },
          })
          .then(() => {
            this.packages.heartCnt--;
          });
      } else {
        httpCommon
          .post("package/heart", {
            packageId: this.packages.packageId,
            userId: decodeToken.userId,
          })
          .then(() => {
            this.packages.heartCnt++;
          });
      }
      this.packages.heart = !this.packages.heart;
    },
    reserve() {
      let token = sessionStorage.getItem("access-token");
      if (token == null) return;
      let decodeToken = jwtDecode(token);

      httpCommon
        .post("package/history", {
          packageId: this.packages.packageId,
          userId: decodeToken.userId,
          headcount: 2,
        })
        .then(() => {
          alert("예약이 완료되었습니다.");
        });
    },
    async writeReview() {
      const { value: text } = await Swal.fire({
        input: "textarea",
        inputLabel: "리뷰 작성",
        inputPlaceholder: "리뷰 내용을 작성해주세요.",
        inputAttributes: {
          "aria-label": "Type your message here",
          maxlength: 300,
        },
        confirmButtonColor: "#F06C5E",
        confirmButtonText: "작성!",
        showCloseButton: true,
      });
      if (text) {
        var userId = 0;
        var decodeToken;
        var token = sessionStorage.getItem("access-token");
        if (token == null) return;
        if (this.isLogin) {
          decodeToken = jwtDecode(token);
          userId = decodeToken.userId;
        }
        httpCommon
          .post("package/review", {
            packageId: this.packageId,
            content: text,
            userId: userId,
          })
          .then(() => {
            this.getReview();
          });
      }
    },

    async writeInquiry() {
      const { value: text } = await Swal.fire({
        input: "textarea",
        inputLabel: "문의 작성",
        inputPlaceholder: "문의 내용을 작성해주세요.",
        inputAttributes: {
          "aria-label": "Type your message here",
          maxlength: 300,
        },
        confirmButtonColor: "#F06C5E",
        confirmButtonText: "작성!",
        showCloseButton: true,
      });
      if (text) {
        var userId = 0;
        var decodeToken;
        var token = sessionStorage.getItem("access-token");
        if (token == null) return;
        if (this.isLogin) {
          decodeToken = jwtDecode(token);
          userId = decodeToken.userId;
        }
        httpCommon
          .post("package/inquiry", {
            packageId: this.packageId,
            content: text,
            userId: userId,
          })
          .then(() => {
            this.getInquiry();
          });
      }
    },
    async writeAnswer(idx) {
      const { value: text } = await Swal.fire({
        input: "textarea",
        inputLabel: "답변 작성",
        inputPlaceholder: "답변 내용을 작성해주세요.",
        inputAttributes: {
          "aria-label": "Type your message here",
          maxlength: 300,
        },
        confirmButtonColor: "#F06C5E",
        confirmButtonText: "작성!",
        showCloseButton: true,
      });
      if (text) {
        var token = sessionStorage.getItem("access-token");
        if (token == null || !this.isLogin) return;

        httpCommon
          .post("package/answer", {
            inquiryId: idx,
            content: text,
            guideId: 1,
          })
          .then(() => {
            this.getInquiry();
          });
      }
    },
  },
  created() {
    var userId = 0;
    var decodeToken;
    this.packageId = this.$route.params.packageId;
    var token = sessionStorage.getItem("access-token");
    if (token != null && this.isLogin) {
      decodeToken = jwtDecode(token);
      userId = decodeToken.userId;
    }
    httpCommon
      .get("package", {
        params: { packageId: this.packageId, userId: userId },
      })
      .then(({ data }) => {
        if (!data.img) {
          data.img = ["@/assets/img/default_img.png"];
        }
        if (!data.descImg) {
          data.descImg = [require("@/assets/img/default_img.png")];
        }
        if (!data.profileImg) {
          data.profileImg = require("@/assets/img/default_profile_img.jpg");
        }
        this.packages = data;
      });
    this.getInquiry();
    this.getReview();
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo", "errorType"]),
    setPrice() {
      let result = "";
      const price = String(this.packages.price);
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
#package-content-box {
  display: flex;
  flex-direction: row;
  justify-content: left;
}
.carousel {
  width: 45%;
  min-width: 700px;
  height: 100%;
  position: sticky;
  top: 100px;
  right: 300px;
}
.hr-bold {
  border: solid 1px black;
  opacity: 1;
  margin: 0.5rem 0;
}
.hr-lighter {
  border: solid 1px lightgray;
  margin: 0.5rem 0;
}
.board-content {
  width: 55%;
}
.package-detail-box {
  display: flex;
  flex-direction: row;
  justify-content: left;
  padding-bottom: 3rem;
}
.detail-label {
  font-weight: bold;
  font-size: 2rem;
  padding: 2rem 0;
}
.detail-img-box {
  padding: 2rem 0;
}
.detail-img-box > div > img {
  width: 100%;
  object-fit: cover;
}
.basic-info-box {
  background-color: rgb(247, 245, 245);
  padding-left: 20%;
}
.inquiry-info-box {
  display: flex;
  flex-direction: column;
  padding-left: 20%;
}
.inquiry-top-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.inquiry-wrt-btn {
  cursor: pointer;
  color: white;
  background-color: rgb(240, 108, 94);
  border-radius: 0.5rem;
  padding: 0.5rem 1rem;
  margin: auto 1rem auto 0;
  font-weight: bold;
}
.inquiry-caution-list {
  list-style: inside;
  color: gray;
}
.inquiry-catuion-item {
  margin-bottom: 1rem;
  text-indent: -23px;
}
.inquiry-author-box,
.review-author-box {
  display: flex;
  flex-direction: row;
}
.inquiry-author-box > *,
.review-author-box > * {
  margin: auto 1rem auto 0;
  font-weight: bold;
}
.inquiry-process {
  border-radius: 1rem;
  padding: 5px 10px;
  color: white;
}
.process-wait {
  background-color: gray;
}
.process-complete {
  background-color: rgb(240, 108, 94);
}
.inquiry-date,
.review-date {
  border-left: 2px solid lightgray;
  color: gray;
  padding-left: 1rem;
}
.inquiry-content,
.review-content {
  padding-top: 1rem;
  font-weight: bold;
}
.inquriy-answer-view-btn-box {
  text-align: right;
}
.inquiry-answer-view-btn {
  background-color: white;
}
.not-active-icon {
  filter: invert(83%) sepia(0%) saturate(3657%) hue-rotate(29deg) brightness(140%) contrast(73%);
}
.active svg {
  filter: invert(0%) sepia(0%) saturate(7475%) hue-rotate(302deg) brightness(97%) contrast(98%);
}
.inquiry-answer-author,
.review-author {
  color: rgb(240, 108, 94);
}
.inquiry-main-box {
  border-bottom: 1px solid lightgray;
  padding: 1rem 0;
}
.pagination-box {
  display: flex;
  justify-content: center;
  padding: 5rem 0;
}
.review-main-box {
  display: flex;
  flex-direction: column;
}
.review-info-box {
  text-align: center;
  padding-left: 20%;
  padding-bottom: 2rem;
  margin-bottom: 3rem;
  background-color: rgb(247, 245, 245);
}
.review-list-box {
  padding-left: 20%;
}
.reivew-content-box {
  padding-bottom: 2rem;
}
.package-buying-form {
  border: 1px solid lightgray;
  padding-top: 2rem;
  width: 30%;
  height: 100%;
  position: sticky;
  top: 100px;
  right: 0;
}
.package-buy-info-box,
.package-price-box {
  padding-left: 2rem;
}
.buying-region {
  font-weight: bold;
  font-size: 1.25rem;
}
.buying-title {
  font-weight: bold;
  font-size: 2rem;
  padding: 1rem 0;
}
.package-price-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding-top: 2rem;
}
.buying-price-desc {
  font-weight: bold;
  font-size: 1.25rem;
}
.buying-price {
  font-weight: bold;
  font-size: 1.5rem;
  color: rgb(240, 108, 94);
  padding-right: 1rem;
}
.buying-btn {
  cursor: pointer;
  color: white;
  background-color: rgb(240, 108, 94);
  width: 100%;
  padding: 2rem 3rem;
  margin-top: 3rem;
  font-weight: bold;
  font-size: 2rem;
}
.inquiry-btn {
  padding: 0.5rem 1rem;
  margin-left: 1rem;
  margin-bottom: 2px;
  border: 0;
  border-radius: 5px;
  font-weight: bold;
  background-color: rgb(240, 108, 94);
  color: white;
}
.inquiry-btn-box {
  text-align: right;
  margin-right: 1rem;
}
</style>
