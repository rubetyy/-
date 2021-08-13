<template>
  <div>
    <h3>Search</h3>
    <LiveList :lives="searchLives"/>
    <hr>
    <ProductList :products="searchProducts" />
  </div>
</template>

<script>
import LiveList from '@/components/MainPage/LiveList.vue'
import ProductList from '@/components/MainPage/ProductList.vue'
import { mapGetters } from 'vuex'

const productStore = 'productStore'
// const liveStore = 'liveStore'

export default {
  name:'SearchPage',
  components: {
    LiveList,
    ProductList,
  },
  data: function () {
    return {

    }
  },
  computed: {
    ...mapGetters(productStore, ['getSearchLiveList',]),
    searchLives: function() {
      return this.getSearchLiveList
    },
    ...mapGetters(productStore, ['getSearchProductList',]),
    searchProducts: function() {
      // console.log(this.getLiveProductInfo,'getLiveProductInfo')
      return this.getSearchProductList
    },
  },


}
</script>

<style>

</style>