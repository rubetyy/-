<template>
  <div>
    <h1 id="header">방송 시작하기</h1>
    <div class="form-box">
      <div style="margin-bottom: 40px; font-size:1.1rem;">
        <span>판매 상품 : </span>
        <router-link class="r-link" :to="{name: 'ProductDetail', params: { product_pk: pinfo.productPk }}">
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
      >
    </div>
    <div class="caution">
      방송시작하기 버튼을 클릭하면 <strong>바로</strong> 방송이 시작됩니다!<br>
      방송 준비가 다 끝난 후에 시작버튼을 클릭해주세요
    </div>
    <div class="center-btn">
      <button class="btn-o" @click="startlive">방송 시작하기</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
const liveStore = 'liveStore'
import swal from 'sweetalert'

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
          this.$router.push({ name: 'LivePage', params: { id: res.livepk } })
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        swal({
          text: '방송 제목을 입력해주세요',
          icon: 'warning',
          button: false,
        })
      }
    },
  },
}
</script>

<style scoped>
i {
  color:#ff8a3d;
}
.form-control {
  margin-bottom: 30px;
  line-height: 35px;
}
.caution {
  font-size: 1.1rem;
  text-align: center;
  margin-bottom: 30px;
  color: #ff9046;
}

</style>