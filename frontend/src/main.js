import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from '@/store/index.js'
import Vuebar from 'vuebar';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(Vuebar);
Vue.use(BootstrapVue)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
