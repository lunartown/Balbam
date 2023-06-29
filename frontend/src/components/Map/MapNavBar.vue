<template>
  <div class="map-nav-bar">
    <map-nav-bar-item
      v-for="(item, index) in items"
      :icon="item.icon"
      :filter="item.filter"
      :title="item.title"
      :color="item.color"
      :key="index"
      @click="$emit('select', index)"
    ></map-nav-bar-item>
  </div>
</template>
<style scoped>
.map-nav-bar {
  top: 10px;
  margin-left: 10px;
  width: 100%;
  display: flex;
  gap: 10px 0;
  flex-wrap: wrap;
  position: relative;
  z-index: 3;
}
</style>
<script>
import MapNavBarItem from "./MapNavBarItem.vue";
export default {
  data() {
    return {
      categories: [12, 14, 15, 25, 28, 32, 38, 39],
      selected: [false, false, false, false, false, false, false, false],
      items: [
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="AttractionsOutlinedIcon"><path d="M20.15 14.42c.23-.77.35-1.58.35-2.42s-.12-1.65-.35-2.42c.78-.6 1.02-1.7.51-2.58s-1.58-1.23-2.49-.85c-1.11-1.17-2.56-2.03-4.18-2.42C13.85 2.75 13.01 2 12 2s-1.85.75-1.98 1.73c-1.63.39-3.07 1.25-4.19 2.42-.91-.38-1.98-.03-2.49.85s-.27 1.98.51 2.58c-.23.77-.35 1.58-.35 2.42s.12 1.65.35 2.42c-.78.6-1.02 1.7-.51 2.58s1.58 1.23 2.49.85c.4.42.83.79 1.3 1.12L5.78 22h1.88l.98-2.19c.44.19.9.34 1.38.46.13.98.97 1.73 1.98 1.73s1.85-.75 1.98-1.73c.46-.11.91-.26 1.34-.44L16.3 22h1.88l-1.34-3c.48-.34.93-.72 1.34-1.15.91.38 1.99.03 2.49-.85.5-.88.26-1.98-.52-2.58zm-6.59 4.33c-.37-.46-.93-.75-1.56-.75s-1.2.29-1.57.75c-.4-.09-.79-.21-1.16-.37l1.43-3.19c.4.16.84.25 1.3.25.44 0 .87-.08 1.26-.23l1.42 3.18c-.36.15-.73.27-1.12.36zm-3.08-6.73c0-.83.67-1.5 1.5-1.5s1.5.67 1.5 1.5-.67 1.5-1.5 1.5-1.5-.67-1.5-1.5zm8.23 1.99c-.61.07-1.18.41-1.52.99-.32.56-.34 1.2-.12 1.75-.28.29-.58.55-.9.79l-1.5-3.35c.49-.59.78-1.34.78-2.16 0-1.89-1.55-3.41-3.46-3.41s-3.46 1.53-3.46 3.41c0 .8.28 1.54.75 2.13l-1.52 3.39c-.31-.23-.6-.48-.87-.76.26-.56.24-1.22-.09-1.79-.34-.59-.93-.94-1.56-.99-.22-.68-.33-1.4-.33-2.15 0-.64.09-1.26.25-1.85.66-.03 1.3-.38 1.65-1 .37-.63.35-1.38.01-1.98.92-.98 2.11-1.69 3.45-2.03.34.59.99 1 1.73 1s1.39-.4 1.73-1c1.34.34 2.53 1.07 3.44 2.05-.32.59-.33 1.33.03 1.95.35.6.96.95 1.6 1 .16.59.25 1.21.25 1.86 0 .75-.12 1.47-.34 2.15z"></path></svg>`,
          filter: `invert(71%) sepia(99%) saturate(1393%) hue-rotate(338deg) brightness(101%) contrast(101%)`,
          color: "#FFB74D",
          title: "관광지",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="MuseumOutlinedIcon"><path d="M22 11V9L12 2 2 9v2h2v9H2v2h20v-2h-2v-9h2zm-4 9H6V9h12v11z"></path><path d="m10 14 2 3 2-3v4h2v-7h-2l-2 3-2-3H8v7h2z"></path></svg>`,
          filter: `invert(52%) sepia(23%) saturate(6642%) hue-rotate(184deg) brightness(99%) contrast(93%)`,
          color: "#90CAF9",
          title: "문화시설",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="FestivalOutlinedIcon"><path d="M23 11V9c-6-2-11-7-11-7S7 7 1 9v2c0 1.49.93 2.75 2.24 3.26C3.2 16.76 2.92 19.69 2 22h20c-.92-2.31-1.2-5.24-1.24-7.74C22.07 13.75 23 12.49 23 11zM12 4.71c1.33 1.14 3.49 2.84 6.11 4.29H5.89C8.51 7.55 10.67 5.85 12 4.71zM13 11h3c0 .83-.67 1.5-1.5 1.5S13 11.83 13 11zm-3.5 1.5c-.83 0-1.5-.67-1.5-1.5h3c0 .83-.67 1.5-1.5 1.5zM6 11c0 .83-.67 1.5-1.5 1.5S3 11.83 3 11h3zm-1.34 9c.39-1.86.54-3.82.57-5.58.68-.15 1.29-.49 1.76-.98.25.25.54.45.85.62-.1 1.87-.26 4-.52 5.93H4.66zm4.69 0c.24-1.83.39-3.78.48-5.53.84-.08 1.61-.45 2.17-1.02.56.57 1.32.94 2.17 1.02.1 1.75.24 3.7.48 5.53h-5.3zm7.32 0c-.27-1.94-.43-4.07-.52-5.93.31-.17.61-.37.85-.62.47.48 1.08.83 1.76.98.03 1.76.18 3.72.57 5.58h-2.66zm2.83-7.5c-.83 0-1.5-.67-1.5-1.5h3c0 .83-.67 1.5-1.5 1.5z"></path></svg>`,
          color: "#B39DDB",
          filter:
            "invert(39%) sepia(11%) saturate(5417%) hue-rotate(231deg) brightness(91%) contrast(78%)",
          title: "축제공연행사",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="TourOutlinedIcon"><path d="M21 4H7V2H5v20h2v-8h14l-2-5 2-5zm-3.86 5.74.9 2.26H7V6h11.05l-.9 2.26-.3.74.29.74zM14 9c0 1.1-.9 2-2 2s-2-.9-2-2 .9-2 2-2 2 .9 2 2z"></path></svg>`,
          filter:
            "invert(82%) sepia(7%) saturate(4015%) hue-rotate(64deg) brightness(75%) contrast(77%)",
          color: "#81C784",
          title: "여행코스",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="KitesurfingOutlinedIcon"><path d="M6 3c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm14.06-2h-2.12L15.5 3.44l1.06 1.06 3.5-3.5zM22 23v-2h-1c-1.04 0-2.08-.35-3-1-1.83 1.3-4.17 1.3-6 0-1.83 1.3-4.17 1.3-6 0-.91.65-1.96 1-3 1H2v2h1c1.03 0 2.05-.25 3-.75 1.89 1 4.11 1 6 0 1.89 1 4.11 1 6 0 .95.5 1.97.75 3 .75h1zm-1-9.72c0 1.44-2.19 3.62-5.04 5.58-.31.09-.63.14-.96.14-1.2 0-2.27-.66-3-1.5-.73.84-1.8 1.5-3 1.5-.94 0-1.81-.41-2.49-.99.46-.39.96-.78 1.49-1.17l-1.55-2.97C6.15 13.3 6 12.64 6 12V8c0-1.1.9-2 2-2h3c1.38 0 2.63-.56 3.54-1.46l1.41 1.41C14.68 7.21 12.93 8 11 8H9.6v3.5h2.8l1.69 1.88c1.95-.84 3.77-1.38 5.06-1.38.84 0 1.85.25 1.85 1.28zm-8.8.99-.7-.77-2.5.1.83 2.01c.59-.38 1.81-1.06 2.37-1.34z"></path></svg>`,
          filter:
            "invert(33%) sepia(56%) saturate(2712%) hue-rotate(154deg) brightness(94%) contrast(101%)",
          color: "#4DB6AC",
          title: "레포츠",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="BedOutlinedIcon"><path d="M21 10.78V8c0-1.65-1.35-3-3-3h-4c-.77 0-1.47.3-2 .78-.53-.48-1.23-.78-2-.78H6C4.35 5 3 6.35 3 8v2.78c-.61.55-1 1.34-1 2.22v6h2v-2h16v2h2v-6c0-.88-.39-1.67-1-2.22zM14 7h4c.55 0 1 .45 1 1v2h-6V8c0-.55.45-1 1-1zM5 8c0-.55.45-1 1-1h4c.55 0 1 .45 1 1v2H5V8zm-1 7v-2c0-.55.45-1 1-1h14c.55 0 1 .45 1 1v2H4z"></path></svg>`,
          filter:
            "invert(35%) sepia(5%) saturate(3007%) hue-rotate(330deg) brightness(95%) contrast(88%)",
          color: "#A1887F",
          title: "숙박",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="ShoppingBagOutlinedIcon"><path d="M18 6h-2c0-2.21-1.79-4-4-4S8 3.79 8 6H6c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2zm-6-2c1.1 0 2 .9 2 2h-4c0-1.1.9-2 2-2zm6 16H6V8h2v2c0 .55.45 1 1 1s1-.45 1-1V8h4v2c0 .55.45 1 1 1s1-.45 1-1V8h2v12z"></path></svg>`,
          filter:
            "invert(28%) sepia(79%) saturate(5892%) hue-rotate(328deg) brightness(93%) contrast(95%)",
          color: "#F06292",
          title: "쇼핑",
        },
        {
          icon: `<svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiBox-root css-1om0hkc" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="RestaurantOutlinedIcon"><path d="M16 6v8h3v8h2V2c-2.76 0-5 2.24-5 4zm-5 3H9V2H7v7H5V2H3v7c0 2.21 1.79 4 4 4v9h2v-9c2.21 0 4-1.79 4-4V2h-2v7z"></path></svg>`,
          filter:
            "invert(51%) sepia(50%) saturate(5936%) hue-rotate(346deg) brightness(102%) contrast(101%)",
          color: "#FF8A65",
          title: "음식점",
        },
      ],
    };
  },
  components: {
    MapNavBarItem,
  },
};
</script>
