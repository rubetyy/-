import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

const liveStore = {
  namespaced: true,
  state: {
    productInfo: null,

  },
  getters: {
    getProductInfo(state) {
      return state.productInfo
    }
  },
  mutations: {
    SET_P_INFO(state, data) {
      state.productInfo = data
    },
    REMOVE_P_INFO(state) {
      state.productInfo = null
    },
  },
  actions: {
    startLive: async function (context, params) {
      const url = BASE_URL + '/live/live-start'
      const res = await axios.post(url, params)
      context.commit('REMOVE_P_INFO')
      return res.data
    },
    getLiveInfo: async function (context, liveId) {
      const url = BASE_URL + `/live/${liveId}`
      console.log(liveId)
      const res = await axios.get(url)
      if (res.status === 200) {
        return res
      } else {
        throw new Error(res.status)
      }
    },
    makeLive(context, data) {
      context.commit('SET_P_INFO', data)
    },
  }
}

export default liveStore