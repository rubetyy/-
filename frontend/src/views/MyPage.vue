<template>
  <div class='container'>
      {{this.myPageInfo.wishlist}}
    <div  v-if='this.myPageInfo'  class=''>
      <h2>아이디: {{myPageInfo.userinfo.userid}} </h2>
      <h2>닉네임: {{myPageInfo.userinfo.usernickname}}</h2>
    </div>
    <hr>

    <div class='d-flex justify-content-evenly'>
      <ul>
        <h2>판매중</h2>
        <li v-for='product in myPageInfo.issold' :key='product.id'>
          <router-link :to="`/product/${product.id}`">{{product.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>판매완료</h2>
        <li v-for='soldout in myPageInfo.soldout' :key='soldout.id'>
          <router-link :to="`/product/${soldout.id}`">{{soldout.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>구매완료</h2>
        <li v-for='orderProduct in myPageInfo.orderlist' :key='orderProduct.productpk'>
          <router-link :to="`/product/${orderProduct.id}`">{{orderProduct.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>찜</h2>
        <li v-for='wishProduct in myPageInfo.wishlist' :key='wishProduct.id'>
          <router-link :to="`/product/${wishProduct.id}`">{{wishProduct.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>채팅</h2>
          <li v-for='chatroom in myPageInfo.chatlist' :key='chatroom.chatroompk'>
            

            
            <span v-if='userId==chatroom.useridseller'>
              <router-link :to="`/chatroom/${chatroom.useridbuyer}`">{{chatroom.useridbuyer}}</router-link>
            </span>
            <span v-else>
              <router-link :to="`/chatroom/${chatroom.useridseller}`">{{chatroom.useridseller}}</router-link>
            </span>
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
      userId: JSON.parse(localStorage.getItem('userInfo')).id,
      myPageInfo: '',
    }
  },
  methods: {
    ...mapActions(userStore, ['getMyPage']),
  },

  async created() {
    this.getMyPage(this.userId)
      .then(response => {
        // console.log(response)
        this.myPageInfo = response
        console.log(this.myPageInfo)
        console.log('들어옴?')
      })
      .catch(error => {
        console.log(error)
      })
  }
}
</script>

<style scoped>
.container {
  position:relative;

}
</style>