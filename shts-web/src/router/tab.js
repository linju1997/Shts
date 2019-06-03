export default [
  {
    path: '/',
    component: () => import('../views/index/Index.vue')
  },
  {
    path: '/saleshow',
    component: () => import('../views/saleshow/SaleShow.vue'),
    meta:{
      requireAuth:true
    }
  },
  {
    path: '/publish',
    component: () => import('../views/publish/Publish.vue'),
    meta:{
      requireAuth:true
    }
  },
  {
    path: '/message',
    component: () => import('../views/message/Message.vue'),
    meta:{
      requireAuth:true
    }
  },

  {
    path: '/publishGoods/:id',
    component: () => import('../components/Goods.vue'),
    meta:{
      requireAuth:true
    }
  },
  {
    path: '/contact/:username',
    component: () => import('../components/User.vue'),
    meta:{
      requireAuth:true
    }
  },
]
