import axios from 'axios'

const live = {
  namespaced: true,
  state: {

  },
  getters: {

  },
  mutations: {

  },
  actions: {
    // 방송만들기 post 요청-> 방송id 할당받음 -> 그 id 주소 리턴해서 라이브방송 페이지로 이동
    async startLive(context, params) {
      const url = 'http://localhost:8080/~~~'  // 방송등록 post 요청보낼 url 넣기
      const res = await axios.post(url, params)
      console.log(res)
    },
  }
}

export default live