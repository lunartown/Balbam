<template>
  <div class="footer">
    <div class="footer__nav">
      <ul class="footer__gnb">
        <li class="gnb--item">
          <a href="">회사소개</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item">
          <a href="">이용약관</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item personal-inform">
          <a href="">개인정보처리방침</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item">
          <a href="">청소년보호방침</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item">
          <a href="">입점/제휴문의</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item">
          <a href="">대량구매문의</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item">
          <a href="">매장안내</a>
          <div class="line"></div>
        </li>
        <li class="gnb--item"><a href="">임직원인증</a></li>
      </ul>
      <div class="footer__custom-menu">
        <a>
          <div class="item">여행사서비스</div>
          <div class="triangle"></div>
        </a>
        <a>
          <div class="item">파트너센터</div>
          <div class="triangle"></div>
        </a>
      </div>
    </div>
    <hr class="horizontal-line" />
    <div class="footer__banner">
      <div class="footer__info">
        <div class="footer__logo">(주)발밤발밤</div>
        <div class="footer__detail">
          <div>대표이사 : 최명하</div>
          <div class="line"></div>
          <div>서울특별시 강남구 어쩌동 무슨빌딩 3층 1102호</div>
          <div class="line"></div>
          <div>호스팅서비스 사업자 : 하이루시스템</div>
          <div class="line"></div>
          <div class="register">사업자등록번호 : 222-11-000333</div>
          <div>통신판매업신고 : 제2016-아무구-1242호</div>
          <div class="line"></div>
          <div>Email : lunartown@gmail.com</div>
        </div>
        <div class="footer__text">
          <div class="text--title">소비자피해보상</div>
          <p>
            자사는 서울특별시관광협회 공제영업보증보험에 가입되어 있습니다. 영업보증보험: 1.5억원 /
            기획여행보증: 2억원
          </p>
          <p>
            발밤발밤은 통신판매중개자이며 통신판매의 당사자가 아닙니다. 따라서 상품·거래정보 및
            거래에 대하여 책임을 지지않습니다.
          </p>
          <p>부득이한 사정에 의해 확정된 여행일정이 변경되는 경우 여행자의 사전 동의를 받습니다.</p>
        </div>
      </div>
      <div class="footer__help">
        <div class="help--tel">고객센터 1555-9999</div>
        <div class="help--time">운영센터 : 평일 09:00 ~ 18:00</div>
        <div class="help--link">
          <a href=""
            >고객센터 바로가기
            <div class="arrow"></div
          ></a>
        </div>
        <div class="help--qrcode">
          <img src="@/assets/img/QR code.png" alt="QR Code" />
          <div class="qrcode--title">발밤발밤 APP 다운</div>
          <div class="qrcode--text">스마트폰으로 QR코드 스캔</div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
* {
  padding: 0;
  margin: 0;
}

ul {
  list-style-type: none;
}

a {
  color: inherit;
  text-decoration: none;
}
.footer {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.footer > div {
  width: 1280px;
  padding: 0 10px;
}

.footer > .horizontal-line {
  width: 100%;
  height: 1px;
  background-color: #ededed;
  box-shadow: 0;
  border: 0;
}

.footer__nav {
  display: flex;
  height: 56px;
  align-items: center;
  justify-content: space-between;
}

.footer__gnb {
  display: flex;
}

.footer__gnb .gnb--item {
  display: flex;
  align-items: center;
  font-size: 14px;
  letter-spacing: -50;
  margin: 0 18px 0 0;
}

.gnb--item > a {
  color: black !important;
}

.footer__gnb .personal-inform {
  color: #007694;
}

.footer__gnb .gnb--item > .line {
  border-left: 1px solid #ededed;
  height: 10px;
  margin-left: 18px;
}

.footer__custom-menu,
.footer__custom-menu > a {
  display: flex;
}

.footer__custom-menu .item {
  margin-left: 30px;
}

.footer__custom-menu .triangle {
  position: relative;
  top: 10px;
  width: 0;
  height: 0;
  border: 4px solid transparent;
  border-top: 5px solid gray;
  margin-left: 5px;
}

.footer__banner {
  display: flex;
  justify-content: space-between;
  margin: 40px 0;
}

.footer__info {
  justify-content: space-between;
  width: 1000px;
}

.footer__logo {
  font-size: 18px;
  font-weight: bold;
  margin: 0 0 16px 0;
}

.footer__detail {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  font-size: 13px;
  margin-bottom: 16px;
}

.footer__detail > div {
  margin-bottom: 8px;
  margin-right: 12px;
  color: #505050;
}

.footer__detail > .line {
  border-left: 1px solid #ededed;
  height: 10px;
}

.footer__detail > .register {
  color: #191919;
  text-decoration: underline;
}

.footer__text {
  font-size: 13px;
  color: #505050;
}

.footer__text > .text--title {
  color: black;
  font-weight: bold;
  margin-bottom: 12px;
}

.footer__text > p {
  margin-bottom: 8px;
}

.footer__help {
  margin-right: 60px;
  font-family: "Roboto", sans-serif;
}

.help--tel {
  font-weight: bold;
  font-size: 18px;
  margin: 0 0 16px;
}

.help--time {
  margin: 0 0 6px;
  font-size: 12px;
}

.help--link {
  display: flex;
  align-items: center;
  font-size: 12px;
}

.help--link > a {
  display: flex;
  align-items: center;
}

.help--link .arrow {
  margin-left: 5px;
  width: 5px;
  height: 5px;
  border-top: 1px solid black;
  border-right: 1px solid black;
  transform: rotate(45deg);
}

.help--qrcode {
  display: flex;
  flex-flow: column wrap;
  justify-content: end;
  height: 50px;
  position: relative;
  top: 50px;
}

.help--qrcode > img {
  width: 46px;
  height: 46px;
  margin: 0 10px 0 0;
}

.qrcode--title {
  font-size: 15px;
  font-weight: 700;
}

.qrcode--text {
  font-size: 13px;
  font-weight: 100;
  color: #505050;
}
</style>
<script>
export default {
  name: "HomeFooter",
};
</script>
