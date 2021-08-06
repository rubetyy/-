<template>
  <div>
      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      
      <!-- :src="`http://i5c103.p.ssafy.io/${file}` -->
      <b-carousel-slide>
        <template #img >
          <img 
            class="d-block w-70 m-auto"
            width="1024"
            height="480"
            :src="`http://i5c103.p.ssafy.io/${file}`"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
  </div>
</template>

<script>
export default {
  name: 'Carousel',
  props: {
  file: {
    type: String,
    }
  },


}
</script>

<style scoped>

</style>