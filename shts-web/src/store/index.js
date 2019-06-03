import Vue from 'vue'
import Vuex from 'vuex'
import Cookie from 'js-cookie'

Vue.use(Vuex);


export default new Vuex.Store({
  state: {
    tabActive: 0,
    userInfo: Cookie.get("userInfo"),
    loginStatus: Cookie.get("loginStatus") === 'true'
  },
  mutations: {
    changeActive(state, active) {
      state.tabActive = active;
    },
    updateUserInfo(state, userInfo) {
      state.userInfo = userInfo;
      Cookie.set("userInfo", userInfo)
    },
    login(state) {
      state.loginStatus = true
      Cookie.set("loginStatus", "true")
    },
    exit(state) {
      state.loginStatus = false
      state.userInfo = {}
      Cookie.remove("loginStatus")
      Cookie.remove("userInfo")
      Cookie.remove("JSESSIONID")
    }
  },
  getters: {},
  actions: {}
})
