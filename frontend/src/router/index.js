import Vue from 'vue'
import VueRouter from 'vue-router'
import MainPage from '../views/MainPage.vue'
import Signup from '@/views/Signup.vue'
import Login from '@/views/Login.vue'
import MyPage from '@/views/MyPage.vue'
import CategoryPage from '@/views/CategoryPage.vue'
import LivePage from '@/views/LivePage.vue'
import ProductRegister from '@/views/ProductRegister.vue'
import LiveRegister from '@/views/LiveRegister.vue'
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
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/categorypage',
    name: 'CategoryPage',
    component: CategoryPage
  },
  {
    path: '/live/:id',
    name: 'LivePage',
    component: LivePage
  },
  {
    path: '/productregister',
    name: 'ProductRegister',
    component: ProductRegister
  },
  {
    path: '/live/live-start',
    name: 'LiveRegister',
    component: LiveRegister
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
