import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import userStore from '@/store/modules/userStore.js'
import live from '@/store/modules/live.js'

const store = new Vuex.Store({
  modules: {
    // 키: 값 형태로 저장됩니다.
    userStore: userStore,
    live: live,
  }
})

export default store