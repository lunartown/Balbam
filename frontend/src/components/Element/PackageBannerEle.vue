<template>
  <div class="container">
    <div class="item">
      <div class="quote">
        <p>가슴까지 웅장해지는, 아름다운 서울의 야경🛕<span>서울</span></p>
      </div>
    </div>
    <div class="item">
      <div class="quote">
        <p>죽기 전에 가 봐야한다는 그곳, 성산일출봉👊<span>제주도</span></p>
      </div>
    </div>
    <div class="item">
      <div class="quote">
        <p>아름다운 부산🤿바다로 떠나보라<span>부산</span></p>
      </div>
    </div>
    <div class="item">
      <div class="quote">
        <p>산 좋고 물 좋은, 사시사철 강원🌲<span>강원도</span></p>
      </div>
    </div>
    <div class="item">
      <div class="quote">
        <p>하나만 믿고 떠나는🌴대구🌴<span>대구</span></p>
      </div>
    </div>
    <div class="item">
      <div class="quote">
        <p>오만가지 매력✨을 가진 대전<span>대전</span></p>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css2?family=Asap:ital,wght@0,400;1,400");

* {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: "Asap", "Poppins", sans-serif;
  text-shadow: #000 0 0 10px;
}

.container {
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
  background: #111;

  @media (min-width: 1024px) {
    flex-direction: row;
  }
}

.item {
  position: relative;
  flex: calc(100vw / 6) 1 1;
  background-size: cover;
  overflow: hidden;
  filter: saturate(90%);
  transition: 1s;
}

.item:before {
  content: "";
  position: absolute;
  inset: 0;
  background: linear-gradient(25deg, rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0));
}

.item:not(:last-child) {
  border-bottom: 1px solid #ccc;
  animation: borderPulse 5s infinite;

  @media (min-width: 1024px) {
    border-right: 1px solid #ccc;
    border-bottom: none;
  }
}

.item:hover {
  flex-basis: 40%;
  filter: saturate(120%);

  @media (min-width: 1024px) {
    flex-basis: 75%;
  }

  .quote {
    opacity: 1;
    transform: translateX(0);
  }
}

.item:nth-child(1) {
  background-image: url("https://www.travelnbike.com/news/photo/201603/18544_17674_14.jpg");
  background-position: 72% 35%;
}

.item:nth-child(2) {
  background-image: url("https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/1jPF/image/6dTBQAAJW6QLbLUaneCZWL3Vhf4.jpg");
  background-position: 60% 8%;
}

.item:nth-child(3) {
  background-image: url("https://a.cdn-hotels.com/gdcs/production37/d1169/1dcbfef5-2070-48ce-8d62-3e0fffa21797.jpg");
  background-position: 52% 8%;
}

.item:nth-child(4) {
  background-image: url("https://youimg1.tripcdn.com/target/1A0815000000x8dx0C97B_D_1180_558.jpg?proc=source%2Ftrip");
  background-position: 45% 8%;
}

.item:nth-child(5) {
  background-image: url("https://www.kukinews.com/data/kuk/image/2021/07/04/kuk202107040021.680x.0.jpg");
  background-position: 45% 25%;
}

.item:nth-child(6) {
  background-image: url("https://youimg1.tripcdn.com/target/0104j1200093s964u101D_D_1180_558.jpg?proc=source%2Ftrip");
  background-position: 65% 2%;
}

.quote {
  position: absolute;
  color: #fff;
  bottom: 35%;
  left: 5rem;
  width: calc(100% - 10rem);
  opacity: 0;
  transition: 1s;
  transform: translateX(50%);

  @media (min-width: 512px) {
    left: 15%;
    bottom: 35%;
    width: 20vw;
  }

  @media (min-width: 1024px) {
    left: 15%;
    bottom: 35%;
    width: 30vw;
  }

  p {
    position: relative;
    display: inline-block;
    margin-bottom: 1.7rem;
    font-size: 1.4rem;
    text-wrap: balance;
    font-style: italic;

    &:before,
    &:after {
      position: absolute;
      font-size: 5.5rem;
      opacity: 0.6;
    }

    &:before {
      content: "“";
      top: 4rem;
      left: -2rem;
      transform: translate(-100%, -100%);
    }

    &:after {
      content: "”";
      bottom: 2rem;
      right: -2rem;
      transform: translate(100%, 100%);
    }
  }

  span {
    position: absolute;
    bottom: -2rem;
    right: 0;
    text-align: right;
    font-size: 1.2rem;
    font-weight: bold;
    transform: translate(4rem, 100%);

    &:before {
      content: "—";
      margin-right: 0.7rem;
    }
  }
}

@keyframes borderPulse {
  0%,
  100% {
    border-color: rgba(204, 204, 204, 1);
  }
  25%,
  75% {
    border-color: rgba(204, 204, 255, 0.9);
  }
  50% {
    border-color: rgba(204, 204, 255, 0.5);
  }
}
</style>
