<template>
  <div>
    {{categoryLiveProducts}}
    <LiveList :lives="categoryLiveProducts"/>
    <hr>
    <ProductList :products="categoryProducts" />
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
  watch: {
    $route() {
      const categoryId = this.$route.params.categoryId
      this.getCPList(categoryId)
      this.getCPLiveList(categoryId)

    }
  },

  computed: {
    ...mapGetters(productStore, ['CPList',]),
    categoryProducts: function() {
      return this.CPList
    },
    ...mapGetters(liveStore, ['CPLiveList',]),
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
        .then(()=>{
      console.log('성공 getLiveList')
    })
        .catch(err => {
      console.log(err)
      console.log('에러 getLiveList')
    })

  },
}
</script>

<style scoped>

</style>