import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL


const productStore = {
  namespaced: true,

  state: {
    productFile: [], // 등록 시 상품 단일 상세정보
    productList: [], // 메인페이지 상품리스트
    productDetailFile: [] // 상품 단일 상세정보 담는 곳
  },

  getters: {
    getProductFile(state) {
      return state.productFile
    },
    hotProductList: function (state) {
      return state.productList
    },
    getProductDetailFile(state) {
      return state.productDetailFile
    }
  },

  mutations: {
    REGISTER(state, file) { //제품등록
      // state.productFile = JSON.parse(file)
      state.productFile = file
      console.log(state.productFile.get('title'))
    },
    SET_PRODUCT_LIST (state, data) {
      state.productList = data
    },
    PRODUUCT_DETAIL(state, data) {
      state.productDetailFile = data
    }
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
      // console.log('들어옴?')
      const PRODUCTDETAIL_URL = BASE_URL +  `/product?productId=23&userId=test-1`
      console.log(PRODUCTDETAIL_URL)
      const response = await axios.get(PRODUCTDETAIL_URL)
      const data = response.data
      commit('PRODUUCT_DETAIL', data)
      // console.log(response,'res')
      // const movieItem = response.data
      // commit('GET_MOVIE', movieItem)
      // return response
    },

    // 메인페이지 상품리스트
    getProductList: async function(context) {
      const url = BASE_URL + '/home'  // 데이터 받아오는 url
      const res = await axios.get(url)
      // context.commit('SET_PRODUCT_LIST', res.data.productList)
      context.commit('SET_PRODUCT_LIST', res.data.liveList)  // 위에가 맞음!! 데이터확인용
    }
  }
}

export default productStore