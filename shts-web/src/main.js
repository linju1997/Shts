import Vue from 'vue';
import Vant from 'vant';
import {Toast} from "vant";
import axios from 'axios';
import App from './App.vue';
import router from './router';
import store from './store';
import common from './common';
import 'vant/lib/index.css';

Vue.use(Vant);
Toast.setDefaultOptions({position: 'top', duration: 1000});
Vue.use(Toast);
Vue.config.productionTip = false;
Vue.prototype.$cm = common;
axios.defaults.baseURL = "http://127.0.0.1";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.prototype.$axios = axios;


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
