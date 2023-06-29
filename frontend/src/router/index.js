import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";
import MapView from "../views/MapView.vue";

import HomeView from "@/views/HomeView.vue";
// import HomeBanner from "@/components/Home/HomeBanner";

import UserView from "@/views/UserView.vue";
import UserLogin from "@/components/User/UserLogin.vue";
import UserSignup from "@/components/User/UserSignup.vue";
import UserPwFind from "@/components/User/UserPwFind.vue";

import BoardMain from "@/components/Board/BoardMain.vue";
import ImgBoardMain from "@/components/ImgBoard/ImgBoardMain.vue";
import PackageMain from "@/components/Package/PackageMain.vue";
import NotificationMain from "@/components/Notification/NotificationMain.vue";

import PackageDetail from "@/components/Package/PackageDetail.vue";
import PackageList from "@/components/Package/PackageList.vue";
import PackageWrite from "@/components/Package/PackageWrite.vue";

import BoardDetail from "@/components/Board/BoardDetail.vue";
import BoardWrite from "@/components/Board/BoardWrite.vue";
import BoardUpdate from "@/components/Board/BoardUpdate.vue";

import ImgBoardDetail from "@/components/ImgBoard/ImgBoardDetail.vue";
import ImgBoardWrite from "@/components/ImgBoard/ImgBoardWrite.vue";

import NotificationList from "@/components/Notification/NotificationList.vue";
import NotificationInquiry from "@/components/Notification/NotificationInquiry.vue";
import NotificationDetail from "@/components/Notification/NotificationDetail.vue";
import NotificationHistory from "@/components/Notification/NotificationHistory.vue";
import NotificationWrite from "@/components/Notification/NotificationWrite.vue";
import NotificationInquiryWrite from "@/components/Notification/NotificationInquiryWrite.vue";
import NotificationAnswerWrite from "@/components/Notification/NotificationInquiryAnswerWrite.vue";
import NotificationUpdate from "@/components/Notification/NotificationUpdate.vue";
import NotificationInquiryUpdate from "@/components/Notification/NotificationInquiryUpdate.vue";
import NotificationInquiryAnswerUpdate from "@/components/Notification/NotificationInquiryAnswerUpdate.vue";

import MyMenuView from "@/views/MyMenuView.vue";
import MyMenuHistory from "@/components/MyMenu/MyMenuHistory.vue";
import MyMenuBasket from "@/components/MyMenu/MyMenuBasket.vue";
import MyMenuUserInfo from "@/components/MyMenu/MyMenuUserInfo.vue";
import MyMenuPassword from "@/components/MyMenu/MyMenuPassword.vue";
import MyMenuQuit from "@/components/MyMenu/MyMenuQuit.vue";
import MyMenuHeart from "@/components/MyMenu/MyMenuHeart.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    redirect: { name: "home" },
    component: MainView,
    children: [
      {
        path: "/home",
        name: "home",
        component: HomeView,
      },
      {
        path: "/user",
        name: "user",
        redirect: "login",
        component: UserView,
        children: [
          {
            path: "login",
            name: "login",
            component: UserLogin,
          },
          {
            path: "signup",
            name: "signup",
            component: UserSignup,
          },
          {
            path: "pwFind",
            name: "pwFind",
            component: UserPwFind,
          },
        ],
      },
      {
        path: "/imgBoard",
        name: "imgBoardMain",
        component: ImgBoardMain,
      },
      {
        path: "/package",
        name: "packageMain",
        component: PackageMain,
      },
      {
        path: "/package/write",
        name: "packageWrite",
        component: PackageWrite,
      },
      {
        path: "/package/detail/:packageId",
        name: "packageDetail",
        component: PackageDetail,
      },
      {
        path: "/package/list/:word",
        name: "packageList",
        component: PackageList,
      },
      {
        path: "/notification",
        name: "notification",
        redirect: { name: "notificationHistory" },
        component: NotificationMain,
        children: [
          {
            path: "inquiry",
            name: "notificationInquiry",
            component: NotificationInquiry,
          },
          {
            path: "list",
            name: "notificationList",
            component: NotificationList,
          },
          {
            path: "history",
            name: "notificationHistory",
            component: NotificationHistory,
          },
        ],
      },
      {
        path: "/notification/detail/:notificationId",
        name: "notificationDetail",
        component: NotificationDetail,
      },
      {
        path: "/notification/write",
        name: "notificationWrite",
        component: NotificationWrite,
      },
      {
        path: "/notification/update",
        name: "notificationUpdate",
        component: NotificationUpdate,
      },
      {
        path: "/notification/inquiry/write",
        name: "notificationInquiryWrite",
        component: NotificationInquiryWrite,
      },
      {
        path: "/notification/inquiry/update",
        name: "notificationInquiryUpdate",
        component: NotificationInquiryUpdate,
      },
      {
        path: "/notification/answer/write/:inquiryId",
        name: "notificationAnswerWrite",
        component: NotificationAnswerWrite,
      },
      {
        path: "/notification/inquiry/update",
        name: "notificationInquiryAnswerUpdate",
        component: NotificationInquiryAnswerUpdate,
      },
      {
        path: "/imgBoard/detail/:articleId",
        name: "imgBoardDetail",
        component: ImgBoardDetail,
      },
      {
        path: "/imgBoard/write",
        name: "imgBoardWrite",
        component: ImgBoardWrite,
      },
      {
        path: "/board/main",
        name: "boardMain",
        component: BoardMain,
      },
      {
        path: "/board/write",
        name: "boardWrite",
        component: BoardWrite,
      },
      {
        path: "/board/detail/:articleId",
        name: "boardDetail",
        component: BoardDetail,
      },
      {
        path: "/board/update/:articleId",
        name: "boardUpdate",
        component: BoardUpdate,
      },
      {
        path: "/mymenu",
        name: "mymenu",
        redirect: { name: "mymenuHistory" },
        component: MyMenuView,
        children: [
          {
            path: "history",
            name: "mymenuHistory",
            component: MyMenuHistory,
          },
          {
            path: "basket",
            name: "mymenuBasket",
            component: MyMenuBasket,
          },
          {
            path: "userinfo",
            name: "mymenuUserInfo",
            component: MyMenuUserInfo,
          },
          {
            path: "password",
            name: "mymenuPassword",
            component: MyMenuPassword,
          },
          {
            path: "quit",
            name: "mymenuQuit",
            component: MyMenuQuit,
          },
          {
            path: "heart",
            name: "mymenuHeart",
            component: MyMenuHeart
          }
        ]
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    component: MapView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
