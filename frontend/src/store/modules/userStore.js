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
    },
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

  actions: { //로그인
    async login({commit}, credentials) {
      const LOGIN_URL = BASE_URL + '/auth/login'
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
    //회원가입
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
      // console.log(result,'result')
      return result.data
    },
    async checkNickname (context, nickname) {
      const url = BASE_URL + `/users/checknickname/${nickname}`
      console.log(url)
      const res = await axios.get(url)
      return res.data
    },
    async checkUserid (context, userId) {
      const url = BASE_URL + `/users/check/${userId}`
      const res = await axios.get(url)
      return res.data
    },
    async startChat (context, params) {
      const url = BASE_URL + `/chat/start`
      const res = await axios.post(url, params)
      return res.data
    },
    //찜하기
    async like(context, data) {
      const url = BASE_URL + '/product/wish'
      const res = await axios.post(url, data)
      console.log(res,'like')
    },
    // 찜하기 취소
    async dislike(context, data) {
      const url = BASE_URL + `/product/wish/${data}`
      const res = await axios.delete(url)
      console.log(res,'dislike')
    },
  }
}

export default userStore