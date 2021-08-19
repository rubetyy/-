<template>
<div class="swiper-container">
  <div class="swiper-wrapper">
    <div class="swiper-slide" v-for="(file, idx) in filesPreview" :key="idx" :file="file"> 
      <img :src="file" class="card-img-top " alt="card-img">
    </div>
  </div>
  <div class="swiper-pagination"></div>
  <div class="swiper-button-prev"></div>
  <div class="swiper-button-next"></div>
  <div class="swiper-scrollbar"></div>
</div>
</template>

<script>
import 'swiper/swiper-bundle.css';
import Swiper from 'swiper/bundle';

export default {
  name: 'PdCarouselList',
  props: {
    filesPreview: Array,
  },
  mounted() {
    const swiper = new Swiper('.swiper-container', {
      observer: true,
      observeParents: true,
      direction: 'horizontal',
      loop: false,
      slidesPerView: 2,
      spaceBetween: 20,
      pagination: {
        el: '.swiper-pagination',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
      scrollbar: {
        el: '.swiper-scrollbar',
      },
    });
    swiper.slideNext();
  },
}
</script>

<style scoped>
.swiper-container {
  width: 1200px;
  height: 700px;
  display: flex;
  justify-content: center;
}
.card {
  width: 14rem;
  height: 410px;
}
.card-img-top {
  height: 500px;
}
.card {
  position: relative;
}
.card-t {
  font-size: 1rem;
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