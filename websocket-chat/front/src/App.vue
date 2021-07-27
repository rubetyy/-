<template>
  <div id="app">
    <div id="nav">
      <!-- 라이브방송 페이지 (여기 router 최종에는 삭제할 것) -->
      <router-link :to="{name: 'LiveBroadpage'}">LiveBroadpage</router-link>
      
    </div>
    <router-view/> 
  </div>
</template>

<script>
export default {
  name: 'App',


  }

</script>

<style>


</style>
