<template>
  <div>
    <h1 id="header">방송 만들기</h1>

    <div class="broad-box">
      <!-- 상품상세페이지에서 링크 넘겨줘야함 -> 자동으로 링크 등록할 수 있도록 -->
      <p>판매 상품 : {링크받아서 걸기}</p>
      <hr>
      <h3>썸네일 등록</h3>
      <!-- 사진 등록 or 이 부분 빼고 나중에 화면 캡쳐로 대체 -->
      <input type="file">

      <h3>방송 제목</h3>
      <el-input
        type="text"
        placeholder="방송 제목을 입력하세요"
        v-model="title"
        maxlength="30"
        show-word-limit
        style="margin-bottom: 30px;"
      >
      </el-input>
      <h3>방송 내용</h3>
      <el-input
        type="textarea"
        placeholder="방송 내용을 입력하세요"
        v-model="text"
        maxlength="100"
        show-word-limit
      >
      </el-input>
    </div>
    <div class="start-btn">
      <el-button type="primary" round @click="startLive">방송 시작하기</el-button>
    </div>
    
  </div>
</template>

<script>
// 필수항목 유효성 검사
// import { mapActions } from 'vuex'

// const live = 'live'

export default {
  name: 'MakeBroadCast',
  data: function () {
    return {
      title: '',
      text: '',
    }
  },
  methods : {
    startLive: function() {
      if (this.title.trim() && this.text.trim()) {
        const params = {
          title: this.title.trim(),
          text: this.text.trim(),
        }
        this.$store.dispatch('reviewModify', params)
        this.$router.push({ name: 'LiveBroadpage' })
      } else if (this.title.trim()) {
        alert('내용을 입력해주세요')
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