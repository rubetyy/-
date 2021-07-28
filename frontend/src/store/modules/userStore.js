import axios from 'axios'

// axios.defaults.baseURL = 'http://localhost:8080'
// const url = BASE_URL + `주소/${변수}/`
const BASE_URL = process.env.VUE_APP_BASE_URL

const userStore = {
  namespaced: true,
  state: {
    credentials: localStorage.getItem('credentials') ? localStorage.getItem('credentials') : '',  // 로그인한 유저 아이디
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
    async login({commit}, credentials) {
      const LOGIN_URL = BASE_URL + '/auth/login/'
      const data = credentials
      const response = await axios.post(LOGIN_URL, data)
      // console.log(response)
      const token = response.data.accessToken

      localStorage.setItem('token', token)
      localStorage.setItem('credentials', data.id)

      commit('LOGIN', data)
      commit('LOGIN_CHECK', token)
      },


      logout({commit}){
        commit('LOGOUT')
      },
      
    async signup({commit, dispatch}, credentials) {
      const SIGNUP_URL = BASE_URL + '/users/join'
      const data = credentials
      const response = await axios.post(SIGNUP_URL, data)
      commit('SIGNUP', response.data)
      dispatch('login', credentials)
    },
  }

}

export default userStore