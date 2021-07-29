<template>
  <div>
    <h1 id="header">방송 만들기</h1>

    <div class="div-box">
      <!-- 상품상세페이지에서 링크(상품pk, 상품명, 이미지??, 유저id) 넘겨줘야함 -->
      <div style="margin-bottom: 30px;">
        <span>판매 상품 : </span>
        <a href="#">
          상품 바로가기(네이버처럼 상품이미지+상품명 보이게 구성하고 싶음)
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
      <button class="btn" @click="startlive">방송 시작하기</button>
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
  font-family: 'netmarbleB';
}
.div-box {
  width: 800px;
  margin: 50px auto 80px;
}
.center-btn {
  display: flex;
  justify-content: center;
}
button {
  text-align: center;
}
button:hover {
  background-color: #fff3eb;
}
.btn {
  background-color: #ffeadc;
  color: #ff6600;
  border-radius: 38.5px;
  padding: 10px 20px;
}
textarea:hover, textarea:active, textarea:focus,
input:hover, input:active, input[type="text"]:focus,
.uneditable-input:focus {
  border-color: #f5f3f1;
  box-shadow: 0 1px 1px rgba(229, 103, 23, 0.075) inset, 0 0 2px rgba(229, 103, 23, 0.6);
  /* box-shadow: none; */
}
button:active, button:focus {
  box-shadow: none;
}
</style>