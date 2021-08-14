<template>
  <div v-if='myPageInfo'>
    <div class='userinfo'>
      <h3>닉네임: {{myPageInfo.userinfo.usernickname}}</h3>
      <h3>ID: {{myPageInfo.userinfo.userid}}</h3>
    </div>

    <div class='d-flex justify-content-evenly'>
      <ul>
        <h2>판매중</h2>
        <li v-for='product in myPageInfo.issold' :key="product.id">
          <router-link :to="{name: 'ProductDetail', params: {product_pk: product.id}}">{{product.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>판매완료</h2>
        <li v-for='soldout in myPageInfo.soldout' :key="soldout.id">
          <router-link :to="{name: 'ProductDetail', params: {product_pk: soldout.id}}">{{soldout.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>구매완료</h2>
        <li v-for='orderProduct in myPageInfo.orderlist' :key="orderProduct.productpk">
          <router-link :to="{name: 'ProductDetail', params: {product_pk: orderProduct.id}}">{{orderProduct.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>찜</h2>
        <li v-for='wishProduct in myPageInfo.wishlist' :key="wishProduct.id">
          <router-link :to="{name: 'ProductDetail', params: {product_pk: wishProduct.id}}">{{wishProduct.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>채팅</h2>
          <li v-for='chatroom in myPageInfo.chatlist' :key="chatroom.chatroompk">
            <span v-if='userId==chatroom.useridseller'>
              <router-link :to="{name: 'ChatRoom', params: {pk: chatroom.chatroompk}, query: {nickname: chatroom.buyernickname, productpk: chatroom.productpk  }}">{{chatroom.buyernickname}}</router-link>
            </span>
            <span v-else>
              <router-link :to="{name: 'ChatRoom', params: {pk: chatroom.chatroompk}, query: {nickname: chatroom.sellernickname, productpk: chatroom.productpk }}">{{chatroom.sellernickname}}</router-link>
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
.userinfo {
  max-width: 90%;
  border-radius: 15px;
  background-color: #fff3eb;
  margin: 40px auto 50px;
  padding: 30px 50px;
}
</style>