<template>
  <div class="map">
    <map-side-bar :attractions="attractions" @keywordSearch="keywordSearch"></map-side-bar>
    <map-map @select="select"></map-map>
    <package-bar></package-bar>
  </div>
</template>

<style>
* {
  padding: 0;
  margin: 0;
  border: none;
  box-sizing: border-box;
}

ul {
  list-style-type: none;
}

a {
  text-decoration: none;
}
.map {
  height: 100%;
  width: 100%;
  display: flex;
  flex: 1;
  overflow-x: hidden;
  position: absolute;
}
</style>

<script>
/* eslint-disable */
import MapSideBar from "@/components/Map/MapSideBar.vue";
import PackageBar from "@/components/Map/PackageBar.vue";
import MapMap from "@/components/Map/MapMap.vue";
import CommonAxios from "@/util/http-common.js";

export default {
  name: "MapView",
  components: {
    MapSideBar,
    PackageBar,
    MapMap,
  },
  data() {
    return {
      map: "",
      markers: [],
      isKeywordSearch: false,
      infoWindows: [],
      attractions: [],
      categories: [12, 14, 15, 25, 28, 32, 38, 39],
      selected: [false, false, false, false, false, false, false, false],
    };
  },
  methods: {
    loadMap() {
      const mapScript = document.createElement("script");
      const CLIENT_ID = "v8fa47ot12";
      const CLIENT_SECRET = "uIS6KyFlILy04F0OQsyXgbmN0PN3OensM1Bk4nEF";
      mapScript.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${CLIENT_ID}`;

      mapScript.addEventListener("load", () => {
        this.initMap();
      });

      document.head.appendChild(mapScript);

      // const locationScript = document.createElement("script");
      // locationScript.src = `https://openapi.map.naver.com/openapi/v3/maps.js?clientId=${CLIENT_ID}&submodules=geocoder`;
      // document.head.appendChild(locationScript);
    },

    initMap() {
      this.map = new naver.maps.Map(document.getElementById("naverMap"), {
        zoom: 10,

        zoomControl: true,

        zoomControlOptions: {
          position: naver.maps.Position.RIGHT_TOP,
        },
      });

      // var marker = new naver.maps.Marker({
      //   position: new naver.maps.LatLng(36, 127),

      //   map: this.map,
      // });

      // this.markers.push(marker);

      // var infoWindow = new naver.maps.InfoWindow({
      //   content: "표시내용",
      // });

      // this.infoWindows.push(infoWindow);
    },

    select(index) {
      this.selected[index] = !this.selected[index];
      if (this.isKeywordSearch) {
        this.keywordCategorySearch();
      } else this.locationSearch();
    },

    locationSearch() {
      let searchBody = {};
      searchBody.categories = "";

      let mapBounds = this.map.getBounds();
      searchBody.west = mapBounds.west();
      searchBody.east = mapBounds.east();
      searchBody.north = mapBounds.north();
      searchBody.south = mapBounds.south();

      searchBody.limit = 100;

      for (let i = 0; i < this.categories.length; i++) {
        if (this.selected[i]) {
          searchBody.categories += this.categories[i];
        }
      }

      CommonAxios.post("/attraction/search", searchBody).then(({ data }) => {
        this.attractions = data;
        this.loadMarkers(data);
      });
    },

    keywordCategorySearch() {
      let data = [];
      for (const attraction of this.attractions) {
        for (let i = 0; i < 8; i++) {
          if (this.selected[i] && (attraction.contentTypeId == this.categories[i])) {
            data.push(attraction);
          }
        }
      }
    },

    keywordSearch(keyword, sidoCode, gugunCode) {
      this.isKeywordSearch = true;
      let searchBody = {};
      searchBody.categories = "";

      for (let i = 0; i < this.categories.length; i++) {
        if (this.selected[i]) {
          searchBody.categories += this.categories[i];
        }
      }

      if (sidoCode) searchBody.sidoCode = sidoCode;
      if (gugunCode) searchBody.gugunCode = gugunCode;
      if (keyword) searchBody.keyword = keyword;
      
      searchBody.limit = 100;

      CommonAxios.post("/attraction/search", searchBody).then(({ data }) => {
        this.attractions = data;
        this.loadMarkers(data);
      });
    },

    loadMarkers(data) {
      for (let i = this.markers.length - 1; i >= 0; i--) {
        this.markers[i].setMap(null);
        this.markers.pop();
      }

      let position, marker;

      for (let i = 0; i < data.length; i++) {
        position = new naver.maps.LatLng(data[i].latitude, data[i].longitude);

        marker = new naver.maps.Marker({
          map: this.map,
          position: position,
          title: data[i].title,
        });

        this.markers.push(marker);
      }
    },

    // updateMarkers(map, markers) {
    //   var mapBounds = map.getBounds();
    //   var marker, position;

    //   for (var i = 0; i < markers.length; i++) {
    //     marker = markers[i];
    //     position = marker.getPosition();

    //     if (mapBounds.hasLatLng(position)) {
    //       showMarker(map, marker);
    //     } else {
    //       hideMarker(map, marker);
    //     }
    //   }
    // },

    // showMarker(map, marker) {
    //   if (marker.setMap()) return;
    //   marker.setMap(map);
    // },

    // hideMarker(map, marker) {
    //   if (!marker.setMap()) return;
    //   marker.setMap(null);
    // },
  },
  created() {
    this.loadMap();
  },
};
</script>
