<template>
  <div class="col">
    <router-link :to="{name: 'ProductDetail', params: {product_pk: product.product.id}}">
      <div class="card">
          <!-- <div class="main-viewcount">
            <p>{{product.product.viewCount}}</p>
          </div> -->
        <img :src="`http://i5c103.p.ssafy.io:8080/${image}`" class="card-img-top main-image" alt="...">
        <div class="card-body">
          <a>{{ product.product.title }}</a>
        </div>
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
  data: function() {
    return {
      
    }
  },
  computed: {
    image() {
      return this.product.filePath
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
  width: 14rem;
  /* height: 400px; */
  margin-top: 50px;
  position: relative;

}

.card-img-top {
  height: 14rem;
  /* overflow: hidden; */
}
a:active, a:hover {
  color:#1e1e1e !important;
  background-color:transparent !important;
}
.main-viewcount {
  position: absolute;

}
</style>