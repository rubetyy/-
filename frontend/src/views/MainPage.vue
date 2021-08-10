<template>
  <div>
    <!-- {{nowLives}} -->
    <LiveList :lives="nowLives"/>
    <hr>
    <ProductList :products="hotProducts" />
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

    }
  },
  computed: {
    ...mapGetters(productStore, ['hotProductList',]),
    hotProducts: function() {
      return this.hotProductList
    },
    ...mapGetters(liveStore, ['getLiveProductInfo',]),
    nowLives: function() {
      // console.log(this.getLiveProductInfo,'getLiveProductInfo')
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
    .then(()=>{
      console.log('성공 getProductList')
    })
    .catch(err => {
      console.log(err)
      console.log('에러 getProductList')
    })
  }
}
</script>

<style scoped>

</style>