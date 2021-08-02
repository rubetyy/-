import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

const liveStore = {
  namespaced: true,  // 모듈이 독립적으로 사용될 경우 필요
  state: {

  },
  getters: {

  },
  mutations: {

  },
  actions: {
    startLive: async function (context, params) {
      const url = BASE_URL + '/live/live-start'  // 방송등록 url
      const res = await axios.post(url, params)
      return res.data
    },
    // 방송정보 get 요청 (200 ok) -> 방송 정보 리턴 -> 사용자들이 여러 방송 볼 수 있도록
    getLiveInfo: async function (context, liveId) {
      const url = BASE_URL + `/live/${liveId}`  // 라이브방송 url
      const res = await axios.get(url)
      if (res.status === 200) {
        return res
      } else {
        throw new Error(res.status)
      }
    }

  }
}

export default liveStore