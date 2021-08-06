import axios from 'axios'


const BASE_URL = process.env.VUE_APP_BASE_URL

const userStore = {
  namespaced: true,

  state: {
    userInfo: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')) : null,  // {id: 'hyewonTest', nickname: '혜원짱짱',} (현재 로그인한 유저정보)
    token: localStorage.getItem('token'),
  },

  getters: {
    // getUserInfo(state) {
    //   return state.userInfo
    // },
    getToken(state) {
      return state.token
    }
  },

  mutations: {
    // LOGIN(state, data){
    //   state.userInfo.id = data.id
    //   state.userInfo.nickname = data.nickname
    // },
    LOGIN_CHECK(state, token){
      state.token = token
    },
    LOGOUT(state){
      state.token = ''
      localStorage.removeItem('userInfo')
      localStorage.removeItem('token')
      // state.userInfo.id = null
      // state.userInfo.nickname = null
    },
  },

  actions: {
    async login({commit}, credentials) {
      const LOGIN_URL = BASE_URL + '/auth/login/'
      const res = await axios.post(LOGIN_URL, credentials)
      const token = res.data.accessToken
      localStorage.setItem('token', token)
      const data = {id: credentials.id, nickname: res.data.nickname,}
      const data2 = JSON.stringify(data) 
      localStorage.setItem('userInfo', data2)
      // commit('LOGIN', data)
      commit('LOGIN_CHECK', token)
    },
    logout({commit}){
      commit('LOGOUT')
    },
    async signup({dispatch}, credentials) {
      const SIGNUP_URL = BASE_URL + '/users/join'
      const res = await axios.post(SIGNUP_URL, credentials)
      if (res.data.statusCode === 200) {
        dispatch('login', credentials)
      } else {
        throw new Error(res.status)
      }
    },
    async getMyPage(context, userId) {
      const url = BASE_URL + `/mypage/${userId}`
      console.log(url, userId)
      const result = await axios.get(url)
      console.log(result)
      return result.data
    },
    // 닉네임 아직 구현 전. 구현 후 테스트 필수
    async checkNickname (context, nickname) {
      const url = BASE_URL + `/users/check/${nickname}`
      const res = await axios.get(url)
      return res.data
    },
    async checkUserid (context, userId) {
      const url = BASE_URL + `/users/check/${userId}`
      console.log(url)
      const res = await axios.get(url)
      console.log(res)
      return res.data
    },
  }
}

export default userStore