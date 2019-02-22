import Vue from 'vue'
import Router from 'vue-router'
import Home from './home'

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        ...Home,
        {
            path: '/',
            name: 'index',
            component: () => import('../views/index/Index.vue')
        },
        {
            path: '/hot',
            name: 'Hot goods',
            component: () => import('../views/hot/Hot.vue')
        },
        {
            path: '/publish',
            name: 'Publish goods',
            component: () => import('../views/publish/Publish.vue')
        },
        {
            path: '/message',
            name: 'Message',
            component: () => import('../views/message/Message.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/user/Login.vue')
        }

    ]
})
