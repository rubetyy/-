<template>
  <div>
<!-- 배너 -->
    <!-- <div class='banner1 d-flex justify-content-start flex-wrap align-items-center'>
      <div>
        <h1>
          상품의 상태를<br>
          <span>실시간 영상<i class="bi bi-play-btn-fill"></i>으로</span> <br>
          확인하세요!
        </h1>
      </div>

      <div class='align-self-end'>
        <img src="../assets/live-exam.png" alt="live-image" style="width:600px;" align="right;">
      </div>

      <div>
        <h5>
          사진과 너무 다른 상품 때문에 당황하셨나요? <br>
          <span>홍당무 라이브</span>가 해결해드리겠습니다!
        </h5>
      </div>
    </div> -->

<!-- 라이브리스트, 상품리스트 -->
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

/* .banner1 {
  width: 100% !important;
  height: 500px;
  background-color: #fbf7f2;
  margin-bottom: 48px;
  font-family: 'netmarbleB';
}

.banner1 h1 span {
}

.banner1 div:nth-child(3) span {
  color: #ff6600;
}

.banner1 div:nth-child(1) {
  display: inline-block;
  padding: 20px;
}

.banner1 div:nth-child(3) {
  padding: 20px;
  display: inline-block;
} */
</style>