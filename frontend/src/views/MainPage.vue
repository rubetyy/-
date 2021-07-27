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
import { mapGetters } from 'vuex'

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
    // slice(0,12) -> BE에서 처리
    ...mapGetters(productStore, ['hotProductList',]),
    hotProducts: function() {
      return this.hotProductList
    }
  },
  methods : {

  },
  // created: function() {
  //   this.$store.dispatch('getProductList')
  //   .then(res => {
  //     console.log(res)
  //   })
  //   .catch(err => {
  //     console.log(err)
  //   })
  // }
}
</script>

<style scoped>

</style>