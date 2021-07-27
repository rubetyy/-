import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import store from './store'
import store from '@/store/index.js'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// vue-bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(ElementUI);
Vue.use(BootstrapVue)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
