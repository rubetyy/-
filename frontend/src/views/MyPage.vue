<template>
  <div class='container'>
    <h1 id="header">MyPage</h1>
    <hr>
    {{myPageInfo}}
    <div>
      <!-- <h2>아이디: {{myPageInfo.userinfo.userid}} </h2> -->
      <!-- <h2>닉네임: {{myPageInfo.userinfo.usernickname}}</h2> -->
      <button>개인정보 수정</button>
    </div>
    <hr>

    <div class='d-flex justify-content-evenly'>
      <ul>
        <h2>판매중</h2>
        <li v-for='product in myPageInfo.issold' :key='product.productpk'>{{product.producttitle}}</li>
      </ul>
      <ul>
        <h2>판매완료</h2>
        <li v-for='soldOut in myPageInfo.soldout' :key='soldOut.productpk'>{{soldOut.producttitle}}</li>
      </ul>
      <ul>
        <h2>구매완료</h2>
        <li v-for='orderProduct in myPageInfo.orderlist' :key='orderProduct.productpk'>{{orderProduct.producttitle}}</li>
      </ul>
      <ul>
        <h2>찜</h2>
        <li v-for='wishProduct in myPageInfo.wishlist' :key='wishProduct.productpk'>{{wishProduct.producttitle}}</li>
      </ul>
      <ul>
        <h2>채팅</h2>
        <li v-for='chatRoom in myPageInfo.chatlist' :key='chatRoom.chatroompk'>{{chatRoom.user_id_buyer}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'MyPage',

  data: function () {
    return {
      userId: '',
      myPageInfo: '',
    }
  },

  computed: {
    ...mapGetters(userStore, ['getUserInfo']),
  },

  methods: {
    ...mapActions(userStore, ['getMyPage']),
  },

  async created() {
    this.userId = localStorage.getItem('credentials')
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