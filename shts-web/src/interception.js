import router from './router'
import store from './store'
import {Toast} from 'vant'
import {getUserInfo} from "./config/getData";

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (store.state.loginStatus) {
      getUserInfo().then(ret => {
        if (ret.status === 'success') {
          store.commit("updateUserInfo", ret.data)
          next()
        } else {
          Toast({
            position: "top",
            message: "个人信息加载失败，请重新登录"
          })
          store.commit("exit")
          router.push("/login")
        }
      }).catch(() => {
        Toast("个人信息加载失败，请重新登录")
        store.commit("exit")
        router.push("/login")
      })
    } else {
      Toast({
        position: "top",
        message: "需要登录"
      })
      router.push("/login")
    }
  } else {
    next()
  }
})
