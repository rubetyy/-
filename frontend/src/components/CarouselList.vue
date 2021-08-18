<template v-if="lives">
<div class="swiper-container">
  <div class="swiper-wrapper">
    <div class="swiper-slide card" v-for="(live, idx) in lives" :key="idx" :live="live"> 
     <router-link class="r-link" :to="{name: 'LivePage', params: { id: live.livepk }}">
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
  import 'swiper/swiper-bundle.css';
  import Swiper from 'swiper/bundle';

export default {
  props: {
    lives: Array,
  },
  mounted() {
    const swiper = new Swiper('.swiper-container', {
    observer: true,
    observeParents: true,
    direction: 'horizontal',
    loop: false,
    slidesPerView: 4,
    spaceBetween: 50,
    breakpoints: {
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
.r-link:active, .r-link:hover {
  color:#1e1e1e !important;
  background-color:transparent !important;
}
</style>