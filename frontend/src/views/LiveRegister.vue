<template>
  <div>
    <h1 id="header">방송 만들기</h1>

    <div class="broad-box">
      <!-- 상품상세페이지에서 링크(상품pk, 상품명, 이미지??, 유저id) 넘겨줘야함 -->
      <span>판매 상품 : </span>
      <el-link href="http://localhost:8080/product?productId=9&userId=hyewon" :underline="false" type="primary">
        상품 바로가기(네이버처럼 상품이미지+상품명 보이게 구성하고 싶음)
      </el-link>
      <hr>
      <!-- <h3>썸네일 등록</h3> -->
      <!-- 사진 등록 or 이 부분 빼고 나중에 화면 캡쳐로 대체 -->
      <!-- <input type="file"> -->

      <h3>방송 제목</h3>
      <el-input
        type="text"
        placeholder="방송 제목을 입력하세요"
        v-model="live_title"
        maxlength="30"
        show-word-limit
        style="margin-bottom: 30px;"
      >
      </el-input>
    </div>
    <div class="start-btn">
      <el-button type="primary" round @click="startlive">방송 시작하기</el-button>
    </div>
    
  </div>
</template>

<script>
// 필수항목 유효성 검사 & 방송을 만든 사용자만 CRUD 가능 -> 로그인정보 받아서 제목 수정할 수 있도록 구성할것
import { mapActions } from 'vuex'

const liveStore = 'liveStore'

export default {
  name: 'LiveRegister',
  data: function () {
    return {
      live_title: '',
      product_pk: '',
    }
  },
  methods : {
    ...mapActions(liveStore, ['startLive',]),
    // 방송시작하기
    startlive: function() {
      if (this.live_title.trim()) {
        const params = {
          live_title: this.live_title.trim(),
          product_pk: 3,  // 상품 상세페이지에서 넘겨준 pk 담아서 보내기. 일단 임시로 지정함
          // userid: this.userid,  // userid인지 writer인지 하나 결정 후 로그인한 사용자 정보 store에서 받아와서 그걸로 넣을것
        }
        this.startLive(params)
        .then(res => {
          // router 인자로 방송id 필요
          this.$router.push({ name: 'LiveBroadpage', params: { id: res.data.id } })  // id 일단 임시 (DB/BE 확정후 고칠것)
        })
        .catch(err => {
          console.log(err + '방송만들기 에러')
        })
      } else {
        alert('제목을 입력해주세요')
      }
    },

  },
}
</script>

<style scoped>
#header {
  text-align: center;
}
.broad-box {
  margin: 50px 300px 30px;
}
.start-btn {
  display: flex;
  justify-content: center;
  /* text-align: center; */
}
</style>