import userinfo from './userinfo'
export default [
  {
    path: '/home',
    component: () => import('../../views/home/Home.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/oversell',
    component: () => import('../../views/home/Oversell.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/buy',
    component: () => import('../../views/home/Order.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/collection',
    component: () => import('../../views/home/Collection.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/feedback',
    component: () => import('../../views/home/FeedBack.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/about',
    component: () => import('../../views/home/About.vue'),
    meta: {
      requireAuth: true
    }
  },
  ...userinfo
];
