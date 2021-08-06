<template>
  <div class='container'>
    <h1 id="header">MyPage</h1>
    <hr>

    <div v-if='myPageInfo' class=''>
      <h2>아이디: {{myPageInfo.userinfo.userid}} </h2>
      <h2>닉네임: {{myPageInfo.userinfo.usernickname}}</h2>
      
      <!-- {{myPageInfo}} -->

      <div class='center-btn'>
        <button class='button'>개인정보 수정</button>
      </div>
    </div>
    <hr>

    <div class='d-flex justify-content-evenly'>
      <ul>
        <h2>판매중</h2>
        <li v-for='product in myPageInfo.issold' :key='product.id'>{{product.title}}</li>
      </ul>
      <ul>
        <h2>판매완료</h2>
        <li v-for='soldOut in myPageInfo.soldout' :key='soldOut.id'>
          <router-link :to="`/product/${soldOut.id}`">{{soldOut.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>구매완료</h2>
        <li v-for='orderProduct in myPageInfo.orderlist' :key='orderProduct.productpk'>{{orderProduct.producttitle}}</li>
      </ul>
      <ul>
        <h2>찜</h2>
        <li v-for='wishProduct in myPageInfo.wishlist' :key='wishProduct.id'>
          <router-link :to="`/product/${wishProduct.id}`">{{wishProduct.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>채팅</h2>
          <li v-for='chatRoom in myPageInfo.chatlist' :key='chatRoom.chatroompk'>
            <router-link :to="`/chatroom/${chatRoom.user_id_buyer}`">{{chatRoom.user_id_buyer}}</router-link>
          </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'MyPage',

  data: function () {
    return {
      userId: '',
      myPageInfo: '',
    }
  },
  methods: {
    ...mapActions(userStore, ['getMyPage']),
  },

  async created() {
    console.log(this.$route)
    this.userId = this.$route.params.userid
    console.log(this.userId)

    this.getMyPage(this.userId)
      .then(response => {
        this.myPageInfo = response
      })
      .catch(error => {
        console.log(error)
      })
  }
}
</script>

<style scoped>

</style>