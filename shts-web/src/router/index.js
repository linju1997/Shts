import Vue from 'vue'
import Router from 'vue-router'
import Home from './home'
import Tab from './tab'

Vue.use(Router);

export default new Router({
  routes: [...Home, ...Tab,
    {
      path: '/login',
      component: () => import('../views/user/Login.vue')
    },
    {
      path: '/userinfo',
      component: () => import('../views/user/Userinfo.vue'),
      meta: {
        requireAuth: true
      }
    }
  ]
})
