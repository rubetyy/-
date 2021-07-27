// 파일 위치: /root/src/store/modules/userStore.js
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'


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
     /// 제품등록
    async REGISTER({commit}, productFile) {
      const REGISTER_URL = '/product/create'
      // const data = JSON.stringify(productFile)
      let data = new FormData();
      if (productFile.images != null) {
        for (let i = 0; i < productFile.images.length; i++) {
          const image = productFile.images[i];
          data.append('images', image);
        }
      }
      data.append('title', productFile.title);
      data.append('category', productFile.category);
      data.append('description', productFile.description);
      data.append('price', productFile.price);
      data.append('is_sold', productFile.is_sold);
      data.append('live_status', productFile.live_status);
      console.log(productFile.user_id)
      data.append('user_id', productFile.user_id);


      const response = await axios.post(REGISTER_URL, data)
      console.log(response)
      // console.log(response.config.data)
      commit('REGISTER', response.config.data)
    },

    /// 제품상세
    async PRODUCTDETAIL({ commit }) {   
      // const MOIVE_URL = `/api/v1/movies/${movie_id}/`
      // localhost:8080/product?productId=9&userId=hyewon
      console.log('들어옴?')
      const PRODUCTDETAIL_URL = `/product?productId=27&userId=test-1`
      const response = await axios.get(PRODUCTDETAIL_URL)
      console.log(response,'res')
      console.log(commit)
      // const movieItem = response.data
      // commit('GET_MOVIE', movieItem)
      // return response
    },

  }

}

export default productStore