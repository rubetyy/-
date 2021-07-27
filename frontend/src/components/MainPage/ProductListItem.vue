<template>
  <div>
    <!-- 상품 이미지랑 제목만 보내는 걸로 -->
    <el-col :span="4" class="card">
      <el-card :body-style="{ padding: '0px' }">
        <img :src="product.thumbnail" class="image">
        <div class="p-con">
          <span>{{ product.p_name }}</span>
          <div>
            <!-- 버튼 클릭시 상세페이지로 이동하도록 함수 작성하기 -->
            <el-button type="text">상세페이지</el-button>
          </div>
        </div>
      </el-card>
    </el-col>

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
.card {
  margin-left: 25px;
  margin-right: 25px;
}
.image {
  width: 100%;
  display: block;
}
.p-con {
  text-align: center;
  padding: 14px;
}

</style>