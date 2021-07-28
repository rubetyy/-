<template>
  <div class='container'>
    <h1>MyPage</h1>
    <hr>

    <div>
      <h2>아이디: </h2>
      <h2>닉네임: </h2>
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
import axios from 'axios'

export default {
  name: 'MyPage',

  created: function () {
    console.log('created')
    axios({
      method: 'get',
      url: 'https://874d0867-2828-4911-8e80-4e913dbf635d.mock.pstmn.io/mypage?userId=hyewon'
    })
  },


}
</script>

<style scoped>

</style>