<template>
  <div>
    <h1 id="header">{{ header }}</h1>
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
  /* margin-left: 5%;
  margin-right: 5%; */
  display: flex;
  justify-content: center;
}
</style>