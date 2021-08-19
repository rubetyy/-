<template>
  <div class="col-sm-6 col-lg-4 col-xl-3">
    <router-link class="r-link" :to="{name: 'ProductDetail', params: {product_pk: product.id}}">
      <div class="card">
        <img :src="`https://i5c103.p.ssafy.io/api/${image}`" class="card-img-top main-image" alt="product thumnail">
        <div class="card-body card-t">{{ product.title }}</div>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'ProductListItem',
  props: {
    product: Object,
  },
  computed: {
    image() {
      return this.product.filepath
    }
  }
}
</script>

<style scoped>
.card {
  width: 16rem;
  margin: 25px auto;
  position: relative;
}
.card-img-top {
  height: 17rem;
}
.card-t {
  font-size: 1.1rem;
  text-align: center;
}
.r-link:active, .r-link:hover {
  color:#1e1e1e !important;
  background-color:transparent !important;
}
</style>