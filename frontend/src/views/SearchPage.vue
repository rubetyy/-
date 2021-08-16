<template>
  <div>
    <h1 id="header">검색 결과</h1>
    <LiveList :lives="searchLives"/>
    <div class="line"></div>
    <ProductList :products="searchProducts" :header="header" />
  </div>
</template>

<script>
import LiveList from '@/components/MainPage/LiveList.vue'
import ProductList from '@/components/MainPage/ProductList.vue'
import { mapGetters } from 'vuex'

const productStore = 'productStore'

export default {
  name:'SearchPage',
  components: {
    LiveList,
    ProductList,
  },
  data: function () {
    return {
      header: "상품 리스트",
    }
  },
  computed: {
    ...mapGetters(productStore, ['getSearchLiveList',]),
    searchLives: function() {
      return this.getSearchLiveList
    },
    ...mapGetters(productStore, ['getSearchProductList',]),
    searchProducts: function() {
      return this.getSearchProductList
    },
  },
}
</script>

<style scoped>
#header {
  margin-bottom: 30px;
}
.line {
  margin-top: 30px;
  margin-bottom: 50px;
  border-top: 2px solid #efeff0;
}
</style>