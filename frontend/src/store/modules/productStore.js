import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL


const productStore = {
  namespaced: true,

  state: {
    productFile: [], // 상품 단일 상세정보
    productList: [], // 메인페이지 상품리스트
  },

  getters: {
    getProductFile(state) {
      return state.productFile
    },
    hotProductList: function (state) {
      return state.productList
    },
  },

  mutations: {
    REGISTER(state, file) {
      // state.productFile = JSON.parse(file)
      state.productFile = file
      console.log(state.productFile.get('title'))

    },
    SET_PRODUCT_LIST (state, data) {
      state.productList = data
    },
  },

  actions: {
    // 제품등록
    async register({commit}, productFile) {
      const REGISTER_URL = BASE_URL + '/product/create'
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
      data.append('user_id', productFile.user_id);

      const token =  localStorage.getItem('token')
      const config ={
        headers: {
          Authorization: `Bearer ${token}`
        }
      }

      const response = await axios.post(REGISTER_URL, data, config)
      console.log(response)
      // console.log(response.config.data)
      commit('REGISTER', response.config.data)
    },

    // 제품상세
    async productDetail({ commit }) {   
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

    // 전체 상품 조회하는 api용 url을 만들어야 할듯 -> 그렇게해서 조회수순으로 소팅하거나 백엔드에서 아예 소팅한걸 넘겨주던가
    getProductList: async function(context) {
      const url = 'https://~~'  // 데이터 받아오는 용 api
      const res = await axios.get(url)
      console.log(res)
      context.commit('SET_PRODUCT_LIST', res.data)
    }
  }
}

export default productStore