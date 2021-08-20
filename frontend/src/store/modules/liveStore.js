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
        context.commit('REMOVE_P_INFO')
        return res.data
      } else {
        throw new Error(res.status)
      }
    },
    getLiveInfo: async function (context, data) {
      const url = BASE_URL + `/live`
      const res = await axios.post(url, data)
      if (res.status === 200) {
        return res
      } else {
        throw new Error(res.status)
      }
    },
    makeLive(context, data) {
      context.commit('SET_P_INFO', data)
    },
    getLiveList: async function (context) {
      const url = BASE_URL + '/product/main'
      const res = await axios.get(url)
      context.commit('SET_LIVE_LIST', res.data.liveList)
    },
    getCPLiveList: async function (context, categoryId) {
      const url = BASE_URL + `/category/${categoryId}`
      const res = await axios.get(url)
      context.commit('SET_CP_LIVE_LIST', res.data.liveList)
    },
  }
}

export default liveStore