// 파일 위치: /root/src/store/modules/userStore.js
import axios from 'axios'

// axios.defaults.baseURL = 'http://localhost:8080'


const productStore = {
  namespaced: true,
  state: {
    productFile: []
  },
  getters: {
    getProductFile(state) {
      return state.productFile
    },
  },
  mutations: {
    REGISTER(state, file) {
      state.productFile = JSON.parse(file)
    }
  },
  actions: {
    async REGISTER({commit}, productFile) {
      const REGISTER_URL = 'https://874d0867-2828-4911-8e80-4e913dbf635d.mock.pstmn.io/product/create'
      const data = JSON.stringify(productFile)
      const response = await axios.post(REGISTER_URL, data)
      console.log(response)
      console.log(response.config.data)
      commit('REGISTER', response.config.data)
    }
  }

}

export default productStore