<template>
  <div class='container'>
    <h1 id="header">MyPage</h1>
    <hr>
    <!-- {{myPageInfo}} -->

    <div v-if='myPageInfo' class='div-box'>
      <h2>아이디: {{myPageInfo.userinfo.userid}} </h2>
      <h2>닉네임: {{myPageInfo.userinfo.usernickname}}</h2>
      
      <div class='center-btn'>
        <button class='btn'>개인정보 수정</button>
      </div>
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
          <li v-for='chatRoom in myPageInfo.chatlist' :key='chatRoom.chatroompk'>
            <router-link :to="`/chatroom/${chatRoom.user_id_buyer}`">{{chatRoom.user_id_buyer}}</router-link>
          </li>
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
#header {
  text-align: center;
  font-family: 'netmarbleB';
}
.div-box {
  width: 800px;
  margin: 50px auto 80px;
}
.center-btn {
  display: flex;
  justify-content: center;
}
button {
  text-align: center;
}
button:hover {
  background-color: #fff3eb;
}
.btn {
  background-color: #ffeadc;
  color: #ff6600;
  border-radius: 38.5px;
  padding: 10px 20px;
}
textarea:hover, textarea:active, textarea:focus,
input:hover, input:active, input[type="text"]:focus,
.uneditable-input:focus {
  border-color: #f5f3f1;
  box-shadow: 0 1px 1px rgba(229, 103, 23, 0.075) inset, 0 0 2px rgba(229, 103, 23, 0.6);
  /* box-shadow: none; */
}
button:active, button:focus {
  box-shadow: none;
}

</style>