<template>
  <div>
    <LiveList :lives="nowLives"/>
    <div class="line"></div>
    <ProductList :products="hotProducts" :header="header"/>
  </div>
</template>

<script>
import LiveList from '@/components/MainPage/LiveList.vue'
import ProductList from '@/components/MainPage/ProductList.vue'
import { mapGetters, mapActions } from 'vuex'

const productStore = 'productStore'
const liveStore = 'liveStore'

export default {
  name: 'MainPage',
  components: {
    LiveList,
    ProductList,
  },
  data: function () {
    return {
      header: "인기 상품 TOP 12",
    }
  },
  computed: {
    ...mapGetters(productStore, ['hotProductList',]),
    hotProducts: function() {
      return this.hotProductList
    },
    ...mapGetters(liveStore, ['getLiveProductInfo',]),
    nowLives: function() {
      return this.getLiveProductInfo
    },
  },
  methods : {
    ...mapActions(productStore, ['getProductList']),
    ...mapActions(liveStore,['getLiveList'])
  },
  created: function() {
    this.getLiveList()
    this.getProductList()
  }
}
</script>

<style scoped>
.line {
  margin-top: 30px;
  margin-bottom: 50px;
  border-top: 2px solid #efeff0;
}
</style>