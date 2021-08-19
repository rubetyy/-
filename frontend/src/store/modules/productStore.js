import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

const productStore = {
  namespaced: true,

  state: {
    productFile: [],
    productList: [],
    productDetailFile: [],
    CPList: [],
    SearchLiveList: [],
    SearchProductList: [],
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
    getSearchLiveList(state) {
      return state.SearchLiveList
    },
    getSearchProductList(state) {
      return state.SearchProductList
    },
  },

  mutations: {
    REGISTER(state, file) {
      state.productFile = file
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
    SET_SEARCH_LIVE_LIST (state, data) {
      state.SearchLiveList = data
    },
    SET_SEARCH_PRODUCT_LIST (state, data) {
      state.SearchProductList = data
    },
  },

  actions: {
    register: async function ({commit}, productFile) {
      const REGISTER_URL = BASE_URL + '/product/create'
      let data = new FormData();
      if (productFile.images != null) {
        for (let i = 0; i < productFile.images.length; i++) {
          const images = productFile.images[i];
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
      const response = await axios.post(REGISTER_URL, data, config)
      commit('REGISTER', response.data)
    },
    productDetail: async function ({ commit }, detailData) {   
      const PRODUCTDETAIL_URL = BASE_URL + `/product/detail`
      const response = await axios.post(PRODUCTDETAIL_URL, detailData)
      const data = response.data
      commit('PRODUUCT_DETAIL', data)
    },
    getProductList: async function (context) {
      const url = BASE_URL + '/product/main'
      const res = await axios.get(url)
      context.commit('SET_PRODUCT_LIST', res.data.productList)
    },
    getCPList: async function (context, categoryId) {
      const url = BASE_URL + `/category/${categoryId}`
      const res = await axios.get(url)
      context.commit('SET_CP_LIST', res.data.productList)
    },
    getSearch: async function ({ commit }, search) {
      const url = BASE_URL + `/product/search`
      const res = await axios.post(url, search)
      commit('SET_SEARCH_LIVE_LIST', res.data.liveList)
      commit('SET_SEARCH_PRODUCT_LIST', res.data.productList)
    },
    soldout: async function (context, data) {
      const url = BASE_URL + `/product/sold`
      const res = await axios.post(url, data)
      return res.data
    },
  }
}

export default productStore