<template>
  <div>
    <div class="err-info">
      <img src="../assets/404.png" alt="PageNotFound-image" width="250" height="330">
      <div class="err-text">페이지를 찾을 수 없습니다</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PageNotFound',
}
</script>

<style>
.err-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
}
.err-text {
  font-size: 1.4rem;
  color: #ff8a3d;
  margin-top: 50px;
  margin-left: 50px;
}
</style>