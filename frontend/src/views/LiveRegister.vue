<template>
  <div>
    <h1 id="header">방송 만들기</h1>

    <div class="form-box">
      <div style="margin-bottom: 30px;">
        <span>판매 상품 : </span>
        <a href="#">
          <i class="bi bi-arrow-up-right-square-fill"></i> {{ data.productTitle }}
        </a>
      </div>

      <h3>방송 제목</h3>
      <input
        type="text"
        class="form-control"
        placeholder="방송 제목을 입력하세요 (50글자 이내)"
        v-model="livetitle"
        maxlength="50"
        show-word-limit
        style="margin-bottom: 30px;"
      >
    </div>
    <div class="center-btn">
      <button class="btn-o" @click="startlive">방송 시작하기</button>
    </div>
    
  </div>
</template>

<script>
// 필수항목 유효성 검사 & 방송을 만든 사용자만 CRUD 가능 -> 로그인정보 받아서 제목 수정할 수 있도록 구성할것
import { mapActions, mapGetters } from 'vuex'
const liveStore = 'liveStore'

export default {
  name: 'LiveRegister',
  data: function () {
    return {
      livetitle: '',
      data: {},
    } 
  },
  created() {
    this.data = this.getProductInfo()
  },
  methods : {
    ...mapGetters(liveStore, ['getProductInfo',]),

    ...mapActions(liveStore, ['startLive']),
    startlive: function() {
      if (this.livetitle.trim()) {
        const params = {
          productpk: this.data.produckPk,
          livetitle: this.livetitle.trim(),
          userid: JSON.parse(localStorage.getItem('userInfo')).id,
        }
        console.log(params, typeof(params))
        this.startLive(params)
        .then(res => {
          console.log(res.livepk)
          console.log('라이브레지스터')
          this.$router.push({ name: 'LivePage', params: { id: res.livepk } })
        })
        .catch(err => {
          console.log(err + '방송만들기 에러')
        })
      } else {
        alert('방송 제목을 입력해주세요')
      }
    },

  },
}
</script>

<style scoped>
i {
  color:#ff8a3d;
}

</style>