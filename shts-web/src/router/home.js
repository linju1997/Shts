export default [
    {
        path: '/home',
        component: () => import('../views/home/Home.vue')
    },
    {
        path: '/oversell',
        component: () => import('../views/home/Oversell.vue')
    },
    {
        path: '/buy',
        component: () => import('../views/home/Buy.vue')
    },
    {
        path: '/collection',
        component: () => import('../views/home/Collection.vue')
    },
    {
        path: '/feedback',
        component: () => import('../views/home/FeedBack.vue')
    },
    {
        path: '/about',
        component: () => import('../views/home/About.vue')
    },
    {
        path: '/userinfo/edit',
        component: () => import('../views/home/UserInfoEdit.vue')
    },
    {
        path: '/userinfo/modify/username',
        component: () => import('../views/home/modify/ModifyUsername.vue')
    },
    {
        path: '/userinfo/modify/email',
        component: () => import('../views/home/modify/ModifyEmail.vue')
    },
    {
        path: '/userinfo/modify/phone',
        component: () => import('../views/home/modify/ModifyPhoneNumber.vue')
    },
    {
        path: '/userinfo/modify/address',
        component: () => import('../views/home/modify/ModifyAddress.vue')
    }
];
