<template>
  <div>
    <h1 id='header'>라이브 ON</h1>
    <div v-if="lives.length">
      <CarouselList :lives="lives"/>
    </div>
    <div v-else>
      <div class="nolive-msg">
        현재 방송중인 상품이 없습니다
      </div>
    </div>
  </div>
</template>

<script>
import CarouselList from '@/components/CarouselList.vue'

export default {
  name: 'LiveList',
  props: {
  lives: Array,
  },
  components: {
    CarouselList,
  },
  data() {
    return {
      slide: 0,
      sliding: null,
    }
  },
  methods: {
    onSlideStart() {
      this.sliding = true
    },
    onSlideEnd() {
      this.sliding = false
    },
  }
}
</script>

<style scoped>
#header {
  text-align: start;
  margin-bottom: 20px;
}
.row {
  margin-left: 5%;
  margin-right: 5%;
  display: flex;
  justify-content: center;
}
.nolive-msg {
  font-size: 1.2rem;
  color: rgb(133, 133, 133);
  margin-top: 80px;
  margin-bottom: 80px;
}
</style>