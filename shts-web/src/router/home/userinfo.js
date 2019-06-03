export default [
  {
    path: '/userinfo/edit',
    component: () => import('../../views/home/UserInfoEdit.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/userinfo/modify/username',
    component: () => import('../../views/home/modify/ModifyUsername.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/userinfo/modify/email',
    component: () => import('../../views/home/modify/ModifyEmail.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/userinfo/modify/phone',
    component: () => import('../../views/home/modify/ModifyPhoneNumber.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/userinfo/modify/address',
    component: () => import('../../views/home/modify/ModifyAddress.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/userinfo/modify/remark',
    component: () => import('../../views/home/modify/ModifyRemark.vue'),
    meta: {
      requireAuth: true
    }
  }
]
