<template>
  <div>
    <LiveList :lives="categoryLiveProducts"/>
    <div class="line"></div>
    <ProductList :products="categoryProducts" :header="header" />
  </div>
</template>

<script>
import LiveList from '@/components/MainPage/LiveList.vue'
import ProductList from '@/components/MainPage/ProductList.vue'
import { mapGetters, mapActions } from 'vuex'

const productStore = 'productStore'
const liveStore = 'liveStore'

export default {
  name: 'CategoryPage',
  components: {
    LiveList,
    ProductList,
  },
  data: function () {
    return {
      header: "상품 리스트",
    }
  },
  watch: {
    $route() {
      const categoryId = this.$route.params.categoryId
      this.getCPList(categoryId)
      this.getCPLiveList(categoryId)
    }
  },
  computed: {
    ...mapGetters(productStore, ['CPList']),
    categoryProducts: function() {
      return this.CPList
    },
    ...mapGetters(liveStore, ['CPLiveList']),
    categoryLiveProducts: function() {
      return this.CPLiveList
    },
  },
  methods : {
    ...mapActions(productStore, ['getCPList']),
    ...mapActions(liveStore, ['getCPLiveList']),
  },
  created: function() {
    const categoryId = this.$route.params.categoryId
    this.getCPList(categoryId)
    this.getCPLiveList(categoryId)
  },
}
</script>

<style scoped>
.line {
  margin-top: 30px;
  margin-bottom: 50px;
  border-top: 2px solid #efeff0;
}
</style>