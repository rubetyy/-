
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
        <div class="main-viewcount">
          <!-- {{live}} -->
          <p style="color: white">{{live.liveviewercount}}명이 시청 중</p>
        </div>
        <img :src="`https://i5c103.p.ssafy.io/api/${live.filepath}`" class="card-img-top" alt="...">
        <div class="card-body card-t">{{live.livetitle}}</div>
    </router-link>
    </div>
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
      // 1024: {
      //   slidesPerView: 4,
      //   spaceBetween: 40
      // },
      // 768: {
      //   slidesPerView: 3,
      //   spaceBetween: 30
      // },
      // 640: {
      //   slidesPerView: 2,
      //   spaceBetween: 20
      // },
      320: {
        slidesPerView: 4,
        spaceBetween: 40
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
}
</script>
<style scoped>
.swiper-container {
  width: 1200px;
  height: 430px;
}
.card {
  width: 14rem;
  height: 23rem;
}
.card-img-top {
  height: 19rem;
}
.card {
  position: relative;
}
.card-t {
  font-size: 1.1rem;
  text-align:center;
}
.main-viewcount {
  position: absolute;
  border: 5px solid #ff8a3d;
  border-radius: 15px;
  background-color: rgba(26,26,26,0.301961);
  text-align: center;
  top: 10px;
  left: 15px;
  height: 40px;
}
a:active, a:hover {
  color:#1e1e1e !important;
  background-color:transparent !important;
}
</style>