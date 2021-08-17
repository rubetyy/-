<template>
  <div>
    <!-- <div id="main-image">
      <div>누구나 판매할 수 있고<br>누구나 구매할 수 있는</div>
      <div>홍당무 라이브</div>
      <div>상품 판매를 위해 라이브 방송을 켜보세요 !</div>
      <div>라이브방송은 판매물품 등록 후 가능합니다</div>
    </div> -->
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