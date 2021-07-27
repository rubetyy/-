// 파일 위치: /root/src/store/modules/userStore.js
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'


const userStore = {
  namespaced: true,
  state: {
    credentials: localStorage.getItem('credentials') ? localStorage.getItem('credentials') : '',
    token: localStorage.getItem('token'),

  },
  getters: {
    getUserInfo(state) {
      return state.credentials
    },
    getToken(state) {
      return state.token
    }
  },
  mutations: {
    LOGIN(state, data){
      state.credentials = data
    },
    LOGIN_CHECK(state, token){
      state.token = token
    },
    LOGOUT(state){
      state.credentials = {}
      state.token = ''
      localStorage.removeItem('token')
      localStorage.removeItem('credentials')
    },
    SIGNUP(state, credentials) {
      state.credentials = credentials
    },
  },
  actions: {
    async LOGIN({commit}, credentials) {
      const LOGIN_URL = '/api/v1/auth/login/'
      const data = credentials
      const response = await axios.post(LOGIN_URL, data)
      console.log(response)
      const token = response.data.accessToken

      localStorage.setItem('token', token)
      localStorage.setItem('credentials', data.id)

      commit('LOGIN', data)
      commit('LOGIN_CHECK', token)
      // dispatch('AUTH_PROFILE')
      },


      LOGOUT({commit}){
        commit('LOGOUT')
      },
      
    async SIGNUP({commit, dispatch}, credentials) {
      const SIGNUP_URL = '/api/v1/users/'
      const data = credentials
      const response = await axios.post(SIGNUP_URL, data)
      commit('SIGNUP', response.data)
      dispatch('LOGIN', credentials)
    },
  }

}

export default userStore