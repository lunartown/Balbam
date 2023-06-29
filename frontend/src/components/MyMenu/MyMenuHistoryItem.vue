<template>
  <tr class="history__item">
    <td>{{ item.createTime | dateFilter }}</td>
    <td>
      <router-link :to="{ name: 'packageDetail', params: { packageId: item.packageId } }">{{
        item.title
      }}</router-link>
    </td>
    <td>{{ (item.price * item.headcount) | priceFilter }}원</td>
    <td>{{ item.headcount }}명</td>
    <td>{{ item.startDate | dateFilter }}<br />{{ item.endDate | dateFilter }}</td>
    <td>{{ item.region }}</td>
    <td v-html="state"></td>
  </tr>
</template>

<script>
export default {
  props: {
    item: Object,
  },
  filters: {
    dateFilter(value) {
      return value.replace(/^.{2}/, "").replaceAll("-", ".");
    },
    priceFilter(value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  computed: {
    state() {
      let now = new Date();
      now =
        this.leadingZeros(now.getFullYear(), 4) +
        "-" +
        this.leadingZeros(now.getMonth() + 1, 2) +
        "-" +
        this.leadingZeros(now.getDate(), 2);

      if (this.item.startDate > now) {
        return "예약완료/<br>여행을 기다리는 중";
      } else if (this.item.startDate <= now && now <= this.item.endDate) {
        return "여행중";
      } else {
        return "지난 여행";
      }
    },
  },
  methods: {
    leadingZeros(n, digits) {
      let zero = "";
      n = n.toString();

      if (n.length < digits) {
        for (let i = 0; i < digits - n.length; i++) zero += "0";
      }
      return zero + n;
    },
  },
};
</script>

<style scoped>
.history__item {
  text-align: center;
  color: #333;
}
.history__item > td {
  padding: 15px;
}
a:hover {
  text-decoration: underline;
  color: #64403a;
}
</style>
