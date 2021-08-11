<template>
  <div>
    <!-- <div v-if="isLive"> -->
      <div id="livevideo">
        <LiveVideo :liveInfo="data" :isSeller="isSeller" />
      </div>
      <LiveChat />

      <div v-if="data">
        <div class="title">
          <h2 class="inline">{{ data.livetitle }}</h2>
          <router-link :to="{ name: 'ProfilePage', params: { userid: data.userid } }">
            {{ data.usernickname }}
          </router-link>
        </div>
      </div>

      <div class="center-btn">
        <button class="btn-g" @click="goProduct">상품보기</button>
        <div v-if="isSeller" class="inline">
          <!-- productpk 로 연결하기 -->
          <button class="btn-g"><i class="bi bi-x-circle"></i> 방송종료</button>
        </div>
        <div v-else class="inline">
          <button class="btn-g"><i class="bi bi-heart-fill"></i> 찜하기</button>
          <button class="btn-g"><i class="bi bi-x-lg"></i> 나가기</button>
        </div>
      </div>
    <!-- </div> -->

    <!-- <Modal v-else @close="showModal=false">
      <h3 slot="header">
        알림!
        <i class="fas fa-time closeModalBtn" @click="showModal=false"></i>
        </h3>
        <div slot="body" style="text-align:center">현재 방송중이 아닙니다</div>
    </Modal> -->
  </div>
</template>

<script>
import LiveVideo from '@/components/LivePage/LiveVideo'
import LiveChat from '@/components/LivePage/LiveChat'
// import Modal from '@/components/Modal'

import { mapActions } from 'vuex'
const liveStore = 'liveStore'

export default {
  name: 'LivePage',
  components: {
    LiveVideo,
    LiveChat,
    // Modal,
  },
  data: function () {
    return {
      data: null,
      isSeller: false,
      // isLive: true,
      // showModal: false,
    }
  },
  created() {
    const liveId = this.$route.params.id
    localStorage.setItem('wschat.roomId',liveId)
    this.getLiveInfo(liveId)
    .then(res => {
      this.data = res.data
      if (this.data.userid  === JSON.parse(localStorage.getItem('userInfo')).id) {
        this.isSeller = true
      }
    })

  },
  methods: {
    ...mapActions(liveStore, ['getLiveInfo']),
    
    goProduct: function () {
      this.$router.push({ name: 'ProductDetail', params: { product_pk: this.data.productpk } })
    },
  },
}
</script>

<style scoped>
#livevideo {
  width: 722px;
  height: 541px;
  border: 1px solid red;
  display: inline-block;
}
.inline {
  display: inline-block;
}
.title {
  width: 700px;
  margin-top: 20px;
  margin-bottom: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.btn-g {
  margin-left: 20px;
  margin-right: 20px;
  padding: 6px 20px;
}
</style>