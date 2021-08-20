<template>
  <div v-if='myPageInfo'>
    <div class='userinfo'>
      <span class='b-name'>{{myPageInfo.userinfo.usernickname}}</span> 님의 프로필
    </div>
    <div class='d-flex justify-content-evenly'>
      <ul>
        <h2>판매중</h2>
        <li v-for='product in myPageInfo.issold' :key='product.id'>
          <router-link class="r-link" :to="`/product/${product.id}`">- {{product.title}}</router-link>
        </li>
      </ul>
      <ul>
        <h2>판매완료</h2>
        <li v-for='soldout in myPageInfo.soldout' :key='soldout.id'>
          <router-link class="r-link" :to="`/product/${soldout.id}`">- {{soldout.title}}</router-link>
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
    this.userId = this.$route.params.userid
    this.getMyPage(this.userId)
      .then(response => {
        this.myPageInfo = response
        if (!response.userinfo.usernickname) {
          this.$router.push({name:"PageNotFound"})
        }
      })
      .catch(() => {
        this.$router.push({name:"PageNotFound"})
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
  font-size: 1.7rem;
  text-align: center;
  line-height: 5rem;
}
.b-name {
  font-weight: 600;
  font-size: 1.8rem;
  font-family: 'netmarbleB';
}
</style>