<template>
  <div>
    <h1 id='header'>라이브 리스트</h1>
  </div>
</template>

<script>
// import LiveListItem from '@/components/MainPage/LiveListItem.vue'

export default {
  name: 'LiveList',
  props: {
  lives: Array,
},
  // components: {
  //   LiveListItem,
  // },
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
  margin-bottom: 2rem;
}
.row {
  margin-left: 5%;
  margin-right: 5%;
  display: flex;
  /* flex-wrap: nowrap; */
  justify-content: center;
}
</style>