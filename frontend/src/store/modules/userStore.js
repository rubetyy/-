import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

const userStore = {
  namespaced: true,

  state: {
    userInfo: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')) : null,
    token: localStorage.getItem('token'),
  },

  getters: {
    getToken(state) {
      return state.token
    },
  },

  mutations: {
    LOGIN_CHECK(state, token){
      state.token = token
    },
    LOGOUT(state){
      state.token = ''
      localStorage.removeItem('userInfo')
      localStorage.removeItem('token')
    },
  },

  actions: {
    login: async function ({commit}, credentials) {
      const LOGIN_URL = BASE_URL + '/auth/login'
      const res = await axios.post(LOGIN_URL, credentials)
      const token = res.data.accessToken
      localStorage.setItem('token', token)
      const data = {id: credentials.id, nickname: res.data.nickname,}
      const data2 = JSON.stringify(data) 
      localStorage.setItem('userInfo', data2)
      commit('LOGIN_CHECK', token)
    },
    logout({commit}){
      commit('LOGOUT')
    },
    signup: async function ({dispatch}, credentials) {
      const SIGNUP_URL = BASE_URL + '/users/join'
      const res = await axios.post(SIGNUP_URL, credentials)
      if (res.data.statusCode === 200) {
        dispatch('login', credentials)
      } else {
        throw new Error(res.status)
      }
    },
    getMyPage: async function (context, userId) {
      const url = BASE_URL + `/mypage/${userId}`
      const result = await axios.get(url)
      return result.data
    },
    checkNickname: async function (context, nickname) {
      const url = BASE_URL + `/users/checknickname/${nickname}`
      const res = await axios.get(url)
      return res.data
    },
    checkUserid: async function (context, userId) {
      const url = BASE_URL + `/users/check/${userId}`
      const res = await axios.get(url)
      return res.data
    },
    startChat: async function (context, params) {
      const url = BASE_URL + `/chatroom/start`
      const res = await axios.post(url, params)
      return res.data
    },
    like: async function (context, data) {
      const url = BASE_URL + '/product/wish'
      await axios.post(url, data)
    },
    dislike: async function (context, data) {
      const url = BASE_URL + `/product/wish/${data}`
      await axios.delete(url)
    },
  }
}

export default userStore