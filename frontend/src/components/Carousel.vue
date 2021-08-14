<template>
  <div>
      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      
      <!-- :src="`http://i5c103.p.ssafy.io/${file}` -->
      <b-carousel-slide class="max-size">
        <template #img >
          <img 
            class="d-block w-70 m-auto"
           

            :src="`http://i5c103.p.ssafy.io:8080/${file}`"
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

<style>
.sr-only {
  display: none !important;
}
.max-size {
  max-width: 100%;
  height: auto;
  display: block;
}

</style>