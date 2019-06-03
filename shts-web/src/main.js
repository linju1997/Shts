import Vue from 'vue';
import Vant from 'vant';
import {Toast} from "vant";
import App from './App.vue';
import router from './router';
import store from './store';
import common from './common';
import 'vant/lib/index.css';
import './interception'

Vue.use(Vant);
Toast.setDefaultOptions({position: 'top'});
Vue.use(Toast);
Vue.config.productionTip = false;
Vue.prototype.$cm = common;

Vue.config.devtools = true

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
