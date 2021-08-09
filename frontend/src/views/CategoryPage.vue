<template>
  <div>
    <LiveList />
    <hr>
    <ProductList :products="categoryProducts" />
  </div>
</template>

<script>
import LiveList from '@/components/MainPage/LiveList.vue'
import ProductList from '@/components/MainPage/ProductList.vue'
import { mapGetters, mapActions } from 'vuex'

const productStore = 'productStore'

export default {
  name: 'CategoryPage',
  components: {
    LiveList,
    ProductList,
  },
  watch: {
    $route() {
      const categoryId = this.$route.params.categoryId
      this.getCPList(categoryId)
    }
  },
  computed: {
    ...mapGetters(productStore, ['CPList',]),
    categoryProducts: function() {
      return this.CPList
    }
  },
  methods : {
    ...mapActions(productStore, ['getCPList']),
  },
  created: function() {
    const categoryId = this.$route.params.categoryId
    this.getCPList(categoryId)
  },
}
</script>

<style scoped>

</style>