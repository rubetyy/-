import axios from 'axios'

// const BASE_URL = process.env.BASE_URL


const productStore = {
  namespaced: true,

  state: {
    productFile: [],
    // 원래 빈값. 테스트용으로 넣어놓음
    productList: [{"p_name": "일리윤 울트라 리페어 로션","thumbnail": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThQ_9Sbh2EF_d3evMH_KJ1M_Gdl3awitk1uQ&usqp=CAU",},
                  {"p_name": "당근당근","thumbnail": "https://src.hidoc.co.kr/image/lib/2019/5/22/20190522111341113_0.jpg",},
                  {"p_name": "메론메론","thumbnail": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYC841ivGTNLzEpkGocpLCbGgT58dRNdY99A&usqp=CAU",},
                  {"p_name": "참외참외","thumbnail": "https://lh3.googleusercontent.com/proxy/Dx5G_eRn_huGIE0CSMcvnFkwtkNQnXFmGQvdxkvRYxMjFmAkb8apSyRbqyDHxpGxQIj0GvM28KGKzdER5WGPthmOUm0PnjOgw9uRKRawnD_dR8tLk666zD4u9PCt_U_4YTWi2oMq7O6QFZKiq5mfyIwgEOH6CLFPSwP-z_1P5uPvuI6PXtMx2wTl00jJtkJUTGLthQtdd1Ouwua7FfAeEl4AYsrTx4z1fw",},],
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
      state.productFile = JSON.parse(file)
    },
    SET_PRODUCT_LIST (state, data) {
      state.productList = data
    },
  },

  actions: {
    // 제품등록
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
      data.append('user_id', productFile.user_id);

      const response = await axios.post(REGISTER_URL, data)
      console.log(response)
      // console.log(response.config.data)
      commit('REGISTER', response.config.data)
    },

    // 제품상세
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