import Vue from 'vue'
import Router from 'vue-router'
import Home from './home'

Vue.use(Router);

export default new Router({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes: [
    ...Home,
    {
      path: '/',
      component: () => import('../views/index/Index.vue')
    },
    {
      path: '/saleshow',
      component: () => import('../views/saleshow/SaleShow.vue')
    },
    {
      path: '/publish',
      component: () => import('../views/publish/Publish.vue')
    },
    {
      path: '/message',
      component: () => import('../views/message/Message.vue')
    },
    {
      path: '/login',
      component: () => import('../views/user/Login.vue')
    },
    {
      path: '/userinfo',
      component: () => import('../views/user/Userinfo.vue')
    },
    {
      path: '/goods/:id',
      component: () => import('../components/Goods.vue')
    },
    {
      path: '/contact/:username',
      component: () => import('../components/User.vue')
    },
  ]
})
