import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

const liveStore = {
  namespaced: true,
  state: {
    productInfo: null,
    liveList: [],
    CPLiveList: [],


  },
  getters: {
    getProductInfo(state) {
      return state.productInfo
    },
    getLiveProductInfo (state) {
      return state.liveList
    },
    CPLiveList (state) {
      return state.CPLiveList
    },
  },
  mutations: {
    SET_P_INFO(state, data) {
      state.productInfo = data
    },
    REMOVE_P_INFO(state) {
      state.productInfo = null
    },
    SET_LIVE_LIST (state, data) {
      state.liveList = data
      // console.log(state.liveList,'state')
    },
    SET_CP_LIVE_LIST (state, data) {
      state.CPLiveList = data
    },
  },
  actions: {
    startLive: async function (context, params) {
      const url = BASE_URL + '/live/live-start'
      const res = await axios.post(url, params)
      if (res.status === 200) {
        console.log('스타트라이브 성공')
        context.commit('REMOVE_P_INFO')
        console.log(res)
        return res.data
      } else {
        console.log('스타트라이브 에러')
        console.log(res.status)
        console.log(res)
        throw new Error(res.status)
      }
    },
    getLiveInfo: async function (context, liveId) {
      const url = BASE_URL + `/live/${liveId}`
      const res = await axios.get(url)
      console.log(res)
      if (res.status === 200) {
        console.log('겟라이브인포 성공')
        return res
      } else {
        console.log(res.status)
        console.log(res)
        console.log('겟라이브인포 에러')
        throw new Error(res.status)
      }
    },
    makeLive(context, data) {
      context.commit('SET_P_INFO', data)
    },
    // 메인페이지 라이브리스트
    async getLiveList(context) {
      const url = BASE_URL + '/product/main'
      const res = await axios.get(url)
      console.log(res.data.liveList)
      context.commit('SET_LIVE_LIST', res.data.liveList)
    },
    //카테고리 라이브리스트
    getCPLiveList: async function(context, categoryId) {
      const url = BASE_URL + `/category/${categoryId}`
      const res = await axios.get(url)
      // res data 정렬 기준 무엇? id 오름차순? 조회수?
      context.commit('SET_CP_LIVE_LIST', res.data.liveList)
    },
  }
}

export default liveStore