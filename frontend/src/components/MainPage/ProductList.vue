<template>
  <div>
    <h1>상품 리스트</h1>
    <ProductListItem v-for="(product, idx) in products" :key="idx" :product="product"/>
  </div>
</template>

<script>
import ProductListItem from '@/components/MainPage/ProductListItem.vue'

export default {
  name: 'ProductList',
  components: {
    ProductListItem,
  },
  props: {
    products: Array,
  },
  data: function() {
    return {

    }
  },


}
</script>

<style scoped>
.p-box {
  margin: 0 60px;
}
</style>