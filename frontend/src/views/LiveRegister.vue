<template>
  <div>
    <h1 id="header">방송 만들기</h1>

    <div class="broad-box">
      <!-- 상품상세페이지에서 링크(상품pk, 상품명, 이미지??, 유저id) 넘겨줘야함 -->
      <span>판매 상품 : </span>
      <a href="#">
        상품 바로가기(네이버처럼 상품이미지+상품명 보이게 구성하고 싶음)
      </a>
      <hr>
      <!-- <h3>썸네일 등록</h3> -->
      <!-- 사진 등록 or 이 부분 빼고 나중에 화면 캡쳐로 대체 -->
      <!-- <input type="file"> -->

      <h3>방송 제목</h3>
      <input
        type="text"
        class="form-control"
        placeholder="방송 제목을 입력하세요"
        v-model="livetitle"
        maxlength="30"
        show-word-limit
        style="margin-bottom: 30px;"
      >
    </div>
    <div class="start-btn">
      <button class="btn btn-outline-primary" @click="startlive">방송 시작하기</button>
    </div>
    
  </div>
</template>

<script>
// 필수항목 유효성 검사 & 방송을 만든 사용자만 CRUD 가능 -> 로그인정보 받아서 제목 수정할 수 있도록 구성할것
import { mapActions, mapGetters } from 'vuex'

const liveStore = 'liveStore'
const userStore = 'userStore'

export default {
  name: 'LiveRegister',
  data: function () {
    return {
      livetitle: '',
    } 
  },
  computed: {
    ...mapGetters(userStore, ['getUserInfo']),
  },
  methods : {
    ...mapActions(liveStore, ['startLive']),
    // 방송시작하기
    startlive: function() {
      if (this.livetitle.trim()) {
        const params = {
          productpk: 14,  // 상품 상세페이지에서 넘겨준 pk 담아서 보내기. 일단 임시로 지정함
          livetitle: this.livetitle.trim(),
          userid: this.getUserInfo,
        }
        this.startLive(params)
        .then(res => {
          // router 인자로 liveid 필요
          this.$router.push({ name: 'LivePage', params: { id: res.livepk } })  // id 일단 임시 (백엔드 확정되면 고칠것)
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