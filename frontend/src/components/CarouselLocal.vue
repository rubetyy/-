<template>
  <div>
      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      
      <!-- :src="`http://i5c103.p.ssafy.io/${file}` -->
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block w-100"
            width="1024"
            height="480"
            :src="file"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
  </div>
</template>

<script>
export default {
  name: 'CarouselLocal',
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
</style>