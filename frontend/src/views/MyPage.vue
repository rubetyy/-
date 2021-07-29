<template>
  <div class='container'>
    <h1 id="header">MyPage</h1>
    <hr>

    <div>
      <h2>아이디: {{ userInfo }}</h2>
      <h2>닉네임: 치킨 </h2>
    </div>
    <hr>

    <div class='d-flex justify-content-evenly'>
      <div>
        <h2>판매중</h2>
      </div>
      <div>
        <h2>판매완료</h2>
      </div>
      <div>
        <h2>구매목록</h2>
      </div>
      <div>
        <h2>찜</h2>
      </div>
      <div>
        <h2>채팅</h2>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'MyPage',

  computed: {
    ...mapGetters(userStore, [
      'getUserInfo'
    ]),
    userInfo: function () {
      return this.getUserInfo
    }
  }
}
</script>

<style scoped>

</style>