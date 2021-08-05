<template>
  <div class="col">
    <!-- 상품 이미지랑 제목만 보내는 걸로 -->
    <div class="card">
      {{this.product.filePath}}
      <router-link :to="`/product/${product.product.id}`">
        <img :src="`http://i5c103.p.ssafy.io/${image}`" class="card-img-top" alt="...">
        <!-- <img :src="product.thumbnail" class="image"> ↑임시주소 -->
        <div class="card-body">
          <h5>{{ product.product.title }}</h5>
        </div>
      </router-link>
    </div>
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
}

.card-img-top {
  height: 14rem;
  /* overflow: hidden; */
}
a:active, a:hover {
  color:#1e1e1e !important;
  background-color:transparent !important;
}
</style>