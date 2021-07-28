<template>
  <div id="livepage">
    <h1>LivePage</h1>
    <hr>

    <div v-if="data">
    {{ data.livetitle }}

    </div>
    <!-- {{ data }} 바인딩 -->
    <!-- <div v-if="data">
      <h2>{{ data.live_title }}</h2>
      <div>
        <el-button icon="el-icon-user-solid" circle></el-button>
        <span>{{ data.userid }} (아이디)</span>
        유저 누구인지 정보 받아야함!!
      </div>
      <el-row>
        <el-button type="primary" icon="el-icon-thumb">상품보기</el-button>
        <el-button type="success" icon="el-icon-star-on">찜하기</el-button>
        <el-button type="danger" icon="el-icon-close">방송종료</el-button>
      </el-row>
    </div> -->


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