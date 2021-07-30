<template>
  <div class="container">
    <h1 id="header">상품 리스트</h1>
      <div class="row">
        <ProductListItem v-for="(product, idx) in products" :key="idx" :product="product"/>
      </div>
  </div>
</template>

<script>
import ProductListItem from '@/components/MainPage/ProductListItem.vue'

export default {
  name: 'ProductList',
  props: {
    products: Array,
  },
  components: {
    ProductListItem,
  },

}
</script>

<style scoped>
#header {
  text-align: start;
  margin-bottom: 2rem;
}
.row {
  margin-left: 5%;
  margin-right: 5%;
  display: flex;
  /* flex-wrap: nowrap; */
  justify-content: center;
}
</style>