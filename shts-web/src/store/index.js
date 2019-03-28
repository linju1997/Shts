import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    tabActive: 0,
    userInfo: {}
  },
  mutations: {
    changeActive(state, active) {
      state.tabActive = active;
    },
    changeUserInfo(state, userinfo) {
      state.userInfo = userinfo;
    }
  },
  actions: {}
})
