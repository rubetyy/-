import Vue from 'vue'
import VueRouter from 'vue-router'

import MainPage from '../views/MainPage.vue'
import Signup from '@/views/Signup.vue'
import Login from '@/views/Login.vue'
import MyPage from '@/views/MyPage.vue'
import ProfilePage from '@/views/ProfilePage.vue'
import CategoryPage from '@/views/CategoryPage.vue'
import LivePage from '@/views/LivePage.vue'
import LiveRegister from '@/views/LiveRegister.vue'
import ProductRegister from '@/views/ProductRegister.vue'
import ProductDetail from '@/views/ProductDetail.vue'
import ChatRoom from '@/views/ChatRoom.vue'
import SearchPage from '@/views/SearchPage.vue'
import PageNotFound from '@/views/PageNotFound.vue'


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
    path: '/profilepage/:userid',
    name: 'ProfilePage',
    component: ProfilePage
  },
  {
    path: '/category/:categoryId',
    name: 'CategoryPage',
    component: CategoryPage
  },
  {
    path: '/live/:id',
    name: 'LivePage',
    component: LivePage
  },
  {
    path: '/live-start',
    name: 'LiveRegister',
    component: LiveRegister
  },
  {
    path: '/productregister',
    name: 'ProductRegister',
    component: ProductRegister
  },
  {
    path: '/product/:product_pk',
    name: 'ProductDetail',
    component: ProductDetail
  },
  {
    path: '/chatroom/:pk',
    name: 'ChatRoom',
    component: ChatRoom
  },
  {
    path: '/search?q=:q',
    name: 'SearchPage',
    component: SearchPage
  },
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/404',
    name: 'PageNotFound',
    component: PageNotFound
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
