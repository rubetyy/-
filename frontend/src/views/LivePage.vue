<template>
  <div>
    <div v-if="isLive">
      <div v-if="data">
        <div class="title">
          <div class="inline">{{ data.livetitle }}</div>
          <router-link :to="{ name: 'ProfilePage', params: { userid: data.userid } }">
            {{ data.usernickname }}
          </router-link>
        </div>
      </div>

      <div id="livevideo">
        <LiveVideo :liveInfo="data" :isSeller="isSeller" />
      </div>
      <LiveChat />

      <div v-if="!isSeller" class="center-btn" style="margin-top:30px;">
        <button class="btn-g" @click="goProduct">상품보기</button>
        <button class="btn-g"><i class="bi bi-heart-fill"></i> 찜하기</button>
        <button class="btn-g" @click="goMain"><i class="bi bi-x-lg"></i> 나가기</button>
      </div>
    </div>

    <Modal v-else @close="showModal=false" :fct="goMain">
      <h3 slot="header">
        알림!
      </h3>
      <div slot="body" style="text-align:center">현재 방송중이 아닙니다</div>
    </Modal>
  </div>
</template>

<script>
import LiveVideo from '@/components/LivePage/LiveVideo'
import LiveChat from '@/components/LivePage/LiveChat'
import Modal from '@/components/Modal'

import { mapActions } from 'vuex'
const liveStore = 'liveStore'

export default {
  name: 'LivePage',
  components: {
    LiveVideo,
    LiveChat,
    Modal,
  },
  data: function () {
    return {
      data: null,
      isSeller: false,
      isLive: true,
      // showModal: false,
      nowUser: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).id : null,
    }
  },
  created() {
    const liveId = this.$route.params.id
    const params = {
      liveid: this.liveId,
      userid: this.nowUser,
    }
    localStorage.setItem('wschat.roomId',liveId)
    this.getLiveInfo(params)
    .then(res => {
      this.data = res.data
      if (this.data.userid  === this.nowUser) {
        this.isSeller = true
      }
    })

  },
  methods: {
    ...mapActions(liveStore, ['getLiveInfo']),
    
    goProduct() {
      this.$router.push({ name: 'ProductDetail', params: { product_pk: this.data.productpk } })
    },
    goMain() {
      this.$router.push({ name: 'MainPage'})
    },
  },
}
</script>

<style>
#livevideo {
  width: 770px;
  height: 540px;
  /* border: 1px solid red; */
  display: inline-block;
}
.inline {
  display: inline-block;
}
.title {
  width: 700px;
  margin-left: 20px;
  margin-top: 10px;
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}
.title > div {
  font-family: 'netmarbleB';
  font-size: 2.2rem;
}
.btn-g {
  margin-left: 20px;
  margin-right: 20px;
  padding: 6px 20px;
}
</style>