import Vue from 'vue'
import VueRouter from 'vue-router'
import MainPage from '../views/MainPage.vue'
import Signup from '@/views/Signup.vue'
import Login from '@/views/Login.vue'
import Mypage from '@/views/Mypage.vue'
import CategoryPage from '@/views/CategoryPage.vue'
import LiveBroadpage from '@/views/LiveBroadpage.vue'
import ProductRegister from '@/views/ProductRegister.vue'
import MakeBroadCast from '@/views/MakeBroadCast.vue'
import ProductDetail from '@/views/ProductDetail.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: MainPage
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage
  },
  {
    path: '/categorypage',
    name: 'CategoryPage',
    component: CategoryPage
  },
  {
    path: '/livebroadpage',
    name: 'LiveBroadpage',
    component: LiveBroadpage
  },
  {
    path: '/productregister',
    name: 'ProductRegister',
    component: ProductRegister
  },
  {
    path: '/makebroadcast',
    name: 'MakeBroadCast',
    component: MakeBroadCast
  },
  {
    path: '/productdetail',
    name: 'ProductDetail',
    component: ProductDetail
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
