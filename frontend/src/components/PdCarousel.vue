<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="white;"
      img-width="1024"

      style="text-shadow: 1px 1px 2px #ff8a3d;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide v-for="(file, idx) in thumbnail" :key="idx" :file="file">
          <template #img >
            <img
              class="d-block w-70 m-auto card-img-top"
              :src="`http://i5c103.p.ssafy.io:/api/${file}`"
              alt="image slot"
            >
          </template>
        </b-carousel-slide>
      <!-- Slide with blank fluid image to maintain slide aspect ratio -->

    </b-carousel>
  </div>
    <!-- <br>
    <p class="mt-4">
      Slide #: {{ slide }}<br>
    </p> -->
</template>

<script>
  export default {
    name: 'PdCarousel',
    props: {
    thumbnail: {
      type: Array,
      }
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
      }
    }
  }
</script>
<style>
.max-size {
  max-width: 100%;
  height: auto;
  display: block;
}
.img-box {
  width: 100%;
  max-height: 700px;
  overflow: hidden;
}
.sr-only{
  display: none  !important;;
}

</style>