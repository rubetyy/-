import Vue from 'vue'
import VueRouter from 'vue-router'
import MainPage from '../views/MainPage.vue'
import Signup from '@/views/Signup.vue'
import Login from '@/views/Login.vue'
import MyPage from '@/views/MyPage.vue'
import ProfilePage from '@/views/ProfilePage.vue'

import CategoryPage from '@/views/CategoryPage.vue'
import LivePage from '@/views/LivePage.vue'
import ProductRegister from '@/views/ProductRegister.vue'
import LiveRegister from '@/views/LiveRegister.vue'
import ProductDetail from '@/views/ProductDetail.vue'
import ChatRoom from '@/views/ChatRoom.vue'
/// 카테고리
import ClothesPage from '@/views/ClothesPage.vue'
import FoodPage from '@/views/FoodPage.vue'
import ElecPage from '@/views/ElecPage.vue'
import EtcPage from '@/views/EtcPage.vue'


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
    path: '/mypage/:userid',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/profilepage/:userid',
    name: 'ProfilePage',
    component: ProfilePage
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
    path: '/product/:product_pk',
    name: 'ProductDetail',
    component: ProductDetail
  },
  {
    path: '/chatroom/:user_id_buyer',
    name: 'ChatRoom',
    component: ChatRoom
  },
  {
    path: '/category/home?categoryId=1',
    name: 'ClothesPage',
    component: ClothesPage
  },
  {
    path: '/category/home?categoryId=2',
    name: 'FoodPage',
    component: FoodPage
  },
  {
    path: '/category/home?categoryId=3',
    name: 'ElecPage',
    component: ElecPage
  },
  {
    path: '/category/home?categoryId=4',
    name: 'EtcPage',
    component: EtcPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
