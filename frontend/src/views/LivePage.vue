<template>
  <div>

    <div v-if="data">
      <h3>방송제목: {{ data.livetitle }}</h3>
      <p>userid: {{ data.userid }}</p>
      <p>usernickname: {{ data.usernickname }}</p>

      <!-- 판매자 -->
      <div>
        <!-- productpk 로 연결하기 -->
        <button class="btn-g"><i class="bi bi-cursor"></i> 상품보기</button>
        <button class="btn-g"><i class="bi bi-heart-fill"></i> 방송제목 수정</button>
        <button class="btn-g"><i class="bi bi-x-circle"></i> 방송종료</button>
      </div>

      <!-- 구매자 -->
      <div>
        <button class="btn-g"><i class="bi bi-hand-index"></i> 상품보기</button>
        <button class="btn-g"><i class="bi bi-heart-fill"></i> 찜하기</button>
        <button class="btn-g"><i class="bi bi-x-lg"></i> 나가기</button>
      </div>

    </div>

  </div>
</template>

<script>
// 공통) 상품정보(상품상세페이지 링크 걸어야함), 판매자 프로필보기, *실시간 채팅(component), *방송화면(component)
// 판매자 뷰) 방송제목 수정, 방송 종료
// 구매자 뷰) 방송 나가기, 판매자 프로필보기, 찜하기는 상품상세에서??(네이버쇼핑라이브는 방송화면에서 찜버튼 있음)

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


</style>