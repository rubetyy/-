<template>
  <div id="livepage">
    <h1>LivePage</h1>
    <hr>

    <div v-if="data">
      <h3>방송제목: {{ data.livetitle }}</h3>
      <p>userid: {{ data.userid }}</p>
      <p>usernickname: {{ data.usernickname }}</p>

      <div>
        <button>상품보기</button>  <!-- productpk 로 연결하기 -->
        <button>찜하기</button>
        <button>방송종료</button>
      </div>

    </div>

  </div>
</template>

<script>
// 공통) 상품정보(상품상세페이지 링크 걸어야함), 판매자 프로필보기, *실시간 채팅(component), *방송화면(component)
// 판매자 뷰) 방송제목 수정, 방송 종료
// 구매자 뷰) 방송 나가기, 판매자 프로필보기

import { mapActions } from 'vuex'
const liveStore = 'liveStore'

export default {
  name: 'LivePage',
  data: function () {
    return {
      data: null,
    }
  },
  created() {
    const liveId = this.$route.params.id
    this.getLiveInfo(liveId)
    .then(res => {
      this.data = res.data
    })
  },
  methods: {
    ...mapActions(liveStore, ['getLiveInfo']),
  },

}
</script>

<style scoped>
#livepage {
  text-align: center;
}
</style>