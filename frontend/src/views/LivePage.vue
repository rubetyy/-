<template>
  <div>


    <LiveChat />

    {{ isSeller }}
    <div v-if="data">
      <h3>{{ data.livetitle }}</h3>
      {{ data }}

      <!-- mypage url 아직 미완성 -->
      <!-- <router-link :to="{ name: 'MyPage', params: { id: data.usernickname } }"> -->
      <router-link :to="{name: 'MyPage'}">
        {{ data.usernickname }}
      </router-link>


      <button class="btn-g" @click="goProduct">상품보기</button>

      <!-- 판매자 -->
      <div>
        <!-- productpk 로 연결하기 -->
        <button class="btn-g"><i class="bi bi-x-circle"></i> 방송종료</button>
      </div>

      <!-- 구매자 -->
      <div>
        <button class="btn-g"><i class="bi bi-heart-fill"></i> 찜하기</button>
        <button class="btn-g"><i class="bi bi-x-lg"></i> 나가기</button>
      </div>

    </div>

  </div>
</template>

<script>
import LiveChat from '@/components/LivePage/LiveChat'
import { mapActions } from 'vuex'
const liveStore = 'liveStore'

export default {
  name: 'LivePage',
  components: {
    LiveChat,
  },
  data: function () {
    return {
      data: null,
      isSeller: false,
    }
  },
  created() {
    const liveId = this.$route.params.id
    this.getLiveInfo(liveId)
    .then(res => {
      console.log(res)
      console.log('확인확인')
      this.data = res.data
      if (this.data.userid  === JSON.parse(localStorage.getItem('userInfo')).id) {
        this.isSeller = true
      }
    })
  },
  methods: {
    ...mapActions(liveStore, ['getLiveInfo']),
    
    goProduct: function () {
      // window.open("https://google.com", "_blank");
      console.log(this.data.productpk)
      this.$router.push({ name: 'ProductDetail', params: { product_pk: this.data.productpk } })
    }

  },

}
</script>

<style scoped>


</style>