<template>
  <div id="attraction-list">
    <attraction-list-item v-for="(attraction, index) in attractions" :key="index" :attraction="attraction"></attraction-list-item>
  </div>
</template>

<style scoped>
#attraction-list {
  height: 1px;
  flex: 1;
  overflow: auto;
}
</style>
<script>
import AttractionListItem from "@/components/Map/AttractionListItem.vue";
export default {
  name: "attractionList",
  props: {
    number: {
      type: Number,
      default: 0,
    }, 
    attractions: {
      type: Array,
      default: () => [],
    }
  },
  components: {
    AttractionListItem,
  },
};
</script>
