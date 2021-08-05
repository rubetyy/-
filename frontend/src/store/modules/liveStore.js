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
      if (res.status === 200) {
        console.log('스타트라이브 에러')
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
      console.log(liveId)
      const res = await axios.get(url)
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
  }
}

export default liveStore