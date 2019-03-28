import Vue from 'vue';
import Vant from 'vant';
import {Toast} from "vant";
import App from './App.vue';
import router from './router';
import store from './store';
import common from './common';
import 'vant/lib/index.css';

Vue.use(Vant);
Toast.setDefaultOptions({position: 'top'});
Vue.use(Toast);
Vue.config.productionTip = false;
Vue.prototype.$cm = common;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
