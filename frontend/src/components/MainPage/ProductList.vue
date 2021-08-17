<template>
  <div>
    <h1 id="header">{{ header }}</h1>
      <div v-if="products.length" class="row">
        <ProductListItem v-for="(product, idx) in products" :key="idx" :product="product"/>
      </div>
      <div v-else class="noproduct-msg">
        현재 판매중인 상품이 없습니다
      </div>
  </div>
</template>

<script>
import ProductListItem from '@/components/MainPage/ProductListItem.vue'

export default {
  name: 'ProductList',
  props: {
    products: Array,
    header: String,
  },
  components: {
    ProductListItem,
  },
}
</script>

<style scoped>
#header {
  text-align: start;
  margin: 0;
}
.row {
  display: flex;
  justify-content: flex-start;
}
.noproduct-msg {
  font-size: 1.2rem;
  color: rgb(133, 133, 133);
  margin-top: 80px;
  margin-bottom: 80px;
}
</style>