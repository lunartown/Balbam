// import { apiInstance } from "./index.js";
import CommonAxios from "@/util/http-common.js";

// const api = apiInstance();

async function login(user, success, fail) {
  await CommonAxios.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  CommonAxios.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await CommonAxios.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  CommonAxios.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await CommonAxios.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await CommonAxios.get(`/user/logout/${userId}`).then(success).catch(fail);
}

async function signup(user, success, fail) {
  await CommonAxios.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, signup };
