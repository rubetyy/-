
<template v-if="lives">
<!-- Slider main container -->
<div class="swiper-container">
  <!-- Additional required wrapper -->
  <!-- {{lives[0]}} -->
  <div class="swiper-wrapper">
    <!-- Slides -->
    <!-- <div class="swiper-slide" v-for="(live, idx) in lives" :key="idx" :live="live"> 
      <img :src="`http://i5c103.p.ssafy.io:8080/${live.filepath}`"  alt="" style="width:100%; height:90%;">
      <p>{{live.livetitle}}
      <router-link :to="{name: 'LivePage', params: { id: live.livepk }}">라이브 방송 시청</router-link>
      </p>
    </div> -->

    <div class="swiper-slide card" v-for="(live, idx) in lives" :key="idx" :live="live"> 
     <router-link :to="{name: 'LivePage', params: { id: live.livepk }}">
        <img :src="`http://i5c103.p.ssafy.io:8080/${live.filepath}`" class="card-img-top" alt="...">
        <div class="card-body">
          <p style="text-align:center;">{{live.livetitle}}
            <!-- <router-link :to="{name: 'LivePage', params: { id: live.livepk }}">라이브 방송 시청</router-link> -->
         </p>
        </div>
    </router-link>
    </div>

    <!-- <div class="swiper-slide">Slide 2</div>
    <div class="swiper-slide">Slide 3</div>
    <div class="swiper-slide">Slide 4</div>
    <div class="swiper-slide">Slide 5</div>
    <div class="swiper-slide">Slide 6</div>
    <div class="swiper-slide">Slide 7</div>
    <div class="swiper-slide">Slide 8</div>
    <div class="swiper-slide">Slide 9</div> -->
  ...
  </div>
  <!-- If we need pagination -->
  <div class="swiper-pagination"></div>

  <!-- If we need navigation buttons -->
  <div class="swiper-button-prev"></div>
  <div class="swiper-button-next"></div>

  <!-- If we need scrollbar -->
  <div class="swiper-scrollbar"></div>
</div>
</template>
<script>
  // import Swiper from 'swiper';
  // import Swiper styles
  import 'swiper/swiper-bundle.css';
  // import SwiperCore, { Navigation, Pagination } from 'swiper/core';
  import Swiper from 'swiper/bundle';

  // configure Swiper to use modules
  // SwiperCore.use([Navigation, Pagination]);

export default {
  // components: {
  //   Swiper,
    
  // },
  props: {
    lives: Array,
  },
  data() {
    return {
        
        
    };
  },
  mounted() {
    const swiper = new Swiper('.swiper-container', {
    observer: true,
    observeParents: true,
    // preloadImages: true,
    // Optional parameters
    direction: 'horizontal',
    loop: false,
    slidesPerView: 4,
    spaceBetween: 50,
    breakpoints: {
      1024: {
        slidesPerView: 4,
        spaceBetween: 40
      },
      768: {
        slidesPerView: 3,
        spaceBetween: 30
      },
      640: {
        slidesPerView: 2,
        spaceBetween: 20
      },
      320: {
        slidesPerView: 1,
        spaceBetween: 10
      }
    },
    // If we need pagination
    pagination: {
      el: '.swiper-pagination',
    },

    // Navigation arrows
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },

    // And if we need scrollbar
    scrollbar: {
      el: '.swiper-scrollbar',
    },
    
    });
    // const swiper = document.querySelector('.swiper-container').swiper;
    swiper.slideNext();
    
  },
  methods: {
    
  }
  
}
</script>
<style scoped>
.swiper-container {
  width: 1280px;
  height: 450px;
}
.card {
  /* margin-right: 0; */
  width: 14rem;
  height: 450px;
  margin-top: 30px;
}

.card-img-top {
  height: 350px;
  /* overflow: hidden; */
}

</style>