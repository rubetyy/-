<template>
  <div>
    <h1 id="header">방송 만들기</h1>

    <div class="form-box">
      <div style="margin-bottom: 30px;">
        <span>판매 상품 : </span>
        <router-link :to="{name: 'ProductDetail', params: { product_pk: pinfo.productPk }}">
          {{ pinfo.productTitle }} <i class="bi bi-arrow-up-right-square-fill"></i>
        </router-link>
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
import { mapActions, mapGetters } from 'vuex'
const liveStore = 'liveStore'

export default {
  name: 'LiveRegister',
  data: function () {
    return {
      livetitle: '',
      pinfo: {},
    } 
  },
  created() {
    this.pinfo = this.getProductInfo()
  },
  methods : {
    ...mapGetters(liveStore, ['getProductInfo',]),

    ...mapActions(liveStore, ['startLive']),
    startlive: function() {
      if (this.livetitle.trim()) {
        const params = {
          productpk: this.pinfo.productPk,
          livetitle: this.livetitle.trim(),
          userid: JSON.parse(localStorage.getItem('userInfo')).id,
        }
        console.log(params, typeof(params))
        this.startLive(params)
        .then(res => {
          console.log(res.livepk)
          console.log('라이브레지스터')
          this.$router.push({ name: 'LivePage', params: { id: res.livepk } })
          // <router-link :to="{ name: 'LivePage', params: { id: res.livepk } }"></router-link>
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