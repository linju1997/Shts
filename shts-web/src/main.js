import Vue from 'vue'
import Vant from 'vant';
import axios from 'axios'
import qs from 'qs'
import App from './App.vue'
import router from './router'
import store from './store'
import common from './commom'
import 'vant/lib/index.css';

// axios 全局默认配置
// axios.defaults.baseURL = 'http://127.0.0.1/';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

Vue.use(Vant);
Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;
Vue.prototype.$common = common;
Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
