<template>
  <tr class="basket__item">
    <td>
      <div class="checkbox">
      <svg
        class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc"
        focusable="false"
        aria-hidden="true"
        viewBox="0 0 24 24"
        data-testid="CheckBoxOutlineBlankOutlinedIcon"
      >
        <path
          d="M19 5v14H5V5h14m0-2H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2z"
        ></path>
      </svg>
    </div>
    </td>
    <td>서울</td>
    <td><a href="/">롯데월드 패키지(호텔1박+자유이용권)</a></td>
    <td>144,971원</td>
    <td>23.05.24<br />23.05.26</td>
  </tr>
</template>

<script>
export default {};
</script>

<style scoped>
.basket__item {
  text-align: center;
  color: #333;
}
.basket__item > td {
  padding: 15px;
}
.checkbox {
  margin-left: 5px;
  width: 22px;
  height: 22px;
}
a:hover {
  text-decoration: underline;
  color: #64403a;
}
</style>
