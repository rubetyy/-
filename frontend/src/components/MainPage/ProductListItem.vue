<template>
  <div class="col">
    <router-link :to="{name: 'ProductDetail', params: {product_pk: product.id}}">
      <div class="card">
          <!-- <div class="main-viewcount">
            <p>{{product.product.viewCount}}</p>
          </div> -->
        <img :src="`http://i5c103.p.ssafy.io/api/${image}`" class="card-img-top main-image" alt="...">
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
.col {
  flex: 0;
  text-align: center;
}
.card {
  /* margin-right: 0; */
  width: 16rem;
  /* height: 400px; */
  margin-top: 50px;
  position: relative;
}
.card-img-top {
  height: 17rem;
  /* overflow: hidden; */
}
.card-t {
  font-size: 1.1rem;
}
a:active, a:hover {
  color:#1e1e1e !important;
  background-color:transparent !important;
}
.main-viewcount {
  position: absolute;
}
</style>