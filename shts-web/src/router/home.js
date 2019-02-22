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
        path: '/address',
        component: () => import('../views/home/address/Address.vue')
    },
    {
        path: '/contact',
        component: () => import('../views/home/Contact.vue')
    },
    {
        path: '/question',
        component: () => import('../views/home/Question.vue')
    },
    {
        path: '/setting',
        component: () => import('../views/home/setting/Setting.vue')
    }
];
