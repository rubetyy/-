import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import userStore from '@/store/modules/userStore.js'
import liveStore from '@/store/modules/liveStore.js'
import productStore from '@/store/modules/productStore.js'

const store = new Vuex.Store({
  modules: {
    // 키: 값 형태로 저장됩니다.
    userStore: userStore,
    liveStore: liveStore,
    productStore: productStore,
  }
})

export default store