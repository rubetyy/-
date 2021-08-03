<template>
  <div>
    <LiveList />
    <hr>
    <ProductList :products="hotProducts" />
  </div>
</template>

<script>
import LiveList from '@/components/MainPage/LiveList.vue'
import ProductList from '@/components/MainPage/ProductList.vue'
import { mapGetters, mapActions } from 'vuex'

const productStore = 'productStore'

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
    }
  },
  methods : {
    ...mapActions(productStore, ['getProductList']),

  },
  created: function() {
    this.getProductList()
    .then(()=>{
      console.log('성공 created getProductList')
    })
    // .then(res => {
    //   console.log(res)
    //   console.log('성공 created getProductList')
    // })
    .catch(err => {
      console.log(err)
      console.log('에러 getProductList')
    })
  }
}
</script>

<style scoped>

</style>