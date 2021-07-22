import axios from 'axios'

const product = {
  namespaced: true,
  state: {
    // 원래 빈값. 테스트용으로 넣어놓음
    productList: [{"p_name": "일리윤 울트라 리페어 로션","thumbnail": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThQ_9Sbh2EF_d3evMH_KJ1M_Gdl3awitk1uQ&usqp=CAU",},
                  {"p_name": "당근당근","thumbnail": "https://src.hidoc.co.kr/image/lib/2019/5/22/20190522111341113_0.jpg",},
                  {"p_name": "메론메론","thumbnail": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYC841ivGTNLzEpkGocpLCbGgT58dRNdY99A&usqp=CAU",},
                  {"p_name": "참외참외","thumbnail": "https://lh3.googleusercontent.com/proxy/Dx5G_eRn_huGIE0CSMcvnFkwtkNQnXFmGQvdxkvRYxMjFmAkb8apSyRbqyDHxpGxQIj0GvM28KGKzdER5WGPthmOUm0PnjOgw9uRKRawnD_dR8tLk666zD4u9PCt_U_4YTWi2oMq7O6QFZKiq5mfyIwgEOH6CLFPSwP-z_1P5uPvuI6PXtMx2wTl00jJtkJUTGLthQtdd1Ouwua7FfAeEl4AYsrTx4z1fw",},],
    
  },
  getters: {
    hotProductList: function (state) {
      return state.productList
    }

  },
  mutations: {
    SET_PRODUCT_LIST (state, data) {
      state.productList = data
    }

  },
  actions: {
    // 전체 상품 조회하는 api용 url을 만들어야 할듯 -> 그렇게해서 조회수순으로 소팅하거나 백엔드에서 아예 소팅한걸 넘겨주던가
    getProductList: async function(context) {
      const url = 'https://~~'  // 데이터 받아오는 용 api
      const res = await axios.get(url)
      console.log(res)
      context.commit('SET_PRODUCT_LIST', res.data)
    }

  }
}

export default product