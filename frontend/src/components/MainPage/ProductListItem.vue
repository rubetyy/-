<template>
  <div class="col">
    <!-- 상품 이미지랑 제목만 보내는 걸로 -->
    <div class="card">
      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3eBNF4vrGY7wZXQWf38XttL5wDckdUG-M4A&usqp=CAU" class="card-img-top" alt="...">
      <!-- <img :src="product.thumbnail" class="image"> ↑임시주소 -->
      <div class="card-body">
        <h5>{{ product.title }}</h5>
        <p>{{ product.description }}</p>
        <a href="#">상품 보러가기</a>
      </div>
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
}
/* 카드 높이 어케 맞춤..............ㅠㅠ */
.card-img-top {
  height: 14rem;
  /* overflow: hidden; */
}

</style>