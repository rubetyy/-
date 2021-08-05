<template>
  <div>
      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
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
  name: 'Carousel',
  props: {
  file: {
    type: String,
    }
  },


}
</script>

<style>

</style>