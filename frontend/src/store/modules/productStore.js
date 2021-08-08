import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL


const productStore = {
  namespaced: true,

  state: {
    productFile: [], // 등록 시 상품 단일 상세정보
    productList: [], // 메인페이지 상품리스트
    productDetailFile: [], // 상품 단일 상세정보 담는 곳
    CPList: [],
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
    },
    CPList (state) {
      return state.CPList
    },
  },

  mutations: {
    REGISTER(state, file) { //제품등록
      // state.productFile = JSON.parse(file)
      state.productFile = file
      // console.log(state.productFile.get('title'))
    },
    SET_PRODUCT_LIST (state, data) {
      state.productList = data
    },
    PRODUUCT_DETAIL(state, data) {
      state.productDetailFile = data
    },
    SET_CP_LIST (state, data) {
      state.CPList = data
    },
  },

  actions: {
    // 제품등록
    async register({commit}, productFile) {
      const REGISTER_URL = BASE_URL + '/product/create'
      let data = new FormData();
      if (productFile.images != null) {
        for (let i = 0; i < productFile.images.length; i++) {
          console.log(i,'i')
          const images = productFile.images[i];
          console.log(images,'images')
          data.append('images', images);
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
      console.log(data.images,'data.image')
      const response = await axios.post(REGISTER_URL, data, config)
      console.log(response,'ressponse')
      // console.log(response.config.data)
      commit('REGISTER', response.data)
    },

    // 제품상세
    async productDetail({ commit }, product_pk) {   
      const PRODUCTDETAIL_URL = BASE_URL +  `/product/${product_pk}`
      console.log(PRODUCTDETAIL_URL)
      const response = await axios.get(PRODUCTDETAIL_URL)
      const data = response.data
      commit('PRODUUCT_DETAIL', data)
    },

    // 메인페이지 상품리스트
    getProductList: async function(context) {
      const url = BASE_URL + '/product/main'
      const res = await axios.get(url)
      context.commit('SET_PRODUCT_LIST', res.data.productList)
    },
    // 카테고리 상품리스트
    getCPList: async function(context, categoryId) {
      const url = BASE_URL + `/category/${categoryId}`
      const res = await axios.get(url)
      // res data 정렬 기준 무엇? id 오름차순? 조회수?
      context.commit('SET_CP_LIST', res.data.productList)
    },
  }
}

export default productStore