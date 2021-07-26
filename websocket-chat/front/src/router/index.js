import Vue from 'vue'
import VueRouter from 'vue-router'
import LiveBroadpage from '../views/LiveBroadpage.vue'  // 라이브채팅

Vue.use(VueRouter)

const routes = [
  // 라이브채팅
  {
    path: '/',
    name: 'LiveBroadpage',
    component: LiveBroadpage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
