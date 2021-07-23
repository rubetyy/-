<template>
  <div>
    <h1>ProductDetail</h1>
    {{ productFile }}
    {{ productFile.thumbnail }}
    <div>
     <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        caption="First slide"
        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
        :img-src= image
      ></b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
        <h1>Hello world!</h1>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="https://picsum.photos/1024/480/?image=55"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
          a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
        </p>
      </b-carousel-slide>
      </b-carousel>

      <p class="mt-4">
        Slide #: {{ slide }}<br>
        Sliding: {{ sliding }}
      </p>     
    </div>
    <div>제목  {{ productFile.title }} </div>
    <div>설명  {{ productFile.content }} </div>
    <div>상품 카테고리  {{ productFile.category }} </div>
    <div>가격  {{ productFile.price }} </div>
    <div>
      <button>수정</button>
      <button>삭제</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
const productStore = 'productStore'

export default {
  name: 'ProductDetail',
  data() {
      return {
        slide: 0,
        sliding: null,
        thumbnail: []
      }
    },
  computed: {
    ...mapGetters(productStore,[
      'getProductFile'
    ]),
    productFile: function() {
      return this.getProductFile
    },
    image() {
      console.log(this.productFile)
      return this.productFile.thumbnail
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

<style scoped>

</style>