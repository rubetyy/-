<template>
  <div id="app">
    <div id="nav">
        <router-link :to="{name: 'MainPage'}">홍당무 라이브</router-link> |
        <router-link :to="{name: 'CategoryPage'}">의류, 음식, 전자제품, 기타</router-link> |

      <div v-if="isLogged">
        <router-link :to="{name: 'MyPage'}">마이페이지</router-link> |
        <router-link :to="{name: 'ProductRegister'}">상품 등록</router-link> |
        <router-link :to="{name: 'LiveRegister'}">LiveRegister</router-link> |
        <router-link :to="{name: 'ProductDetail'}">ProductDetail</router-link> |
        <router-link to="#" @click.native="logoutClick">로그아웃</router-link> |
      </div>

      <div v-else>
        <router-link :to="{name: 'Signup'}">회원가입</router-link> |
        <router-link :to="{name: 'Login'}">로그인</router-link> |
      </div>
      
    </div>
    <div id="article">
      <router-view/> 
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'App',

  computed: {
    ...mapGetters(userStore,[
      'getToken'
    ]),
    isLogged: function(){
      return this.getToken
    }
  },

  methods : {
    ...mapActions(userStore,[
      'logout'
    ]),
    logoutClick: function() {
      this.logout()
      .then(()=> {
        this.$router.push({name:"MainPage"})
      })
    }
  },
}
</script>

<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.notosanskr * { 
 font-family: 'Noto Sans KR', sans-serif;
}
@font-face {
    font-family: 'netmarbleB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.1/netmarbleB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#app {
  font-family: 'Noto Sans KR';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #1e1e1e;
  max-width: 1200px;
  min-width: 400px;
  margin: 0 auto;
}

/* 임시 네비게이션 바 (추후 변경예정) */
#nav {
  padding-top: 20px;
  font-family: 'netmarbleB';
  font-size: 20px;
}
#nav a {
  font-weight: bold;
  color: #333;
}
#nav a.router-link-exact-active {
  color: #ff8a3d;
}

/* 컨텐츠 내부 (개발하기 편하게 일단 위아래 여백 뒀습니다) */
#article {
  padding-top: 48px;
  padding-bottom: 100px;
  box-sizing: border-box;
}

/* article의 헤더 */
#header {
  text-align: center;
  font-family: 'netmarbleB';
}

/* form 레이아웃 */
.form-box {
  width: 800px;
  margin: 50px auto 80px;
}

/* 버튼, input, a태그 등 기타 공통 스타일링 */
::selection {
  background: #ffeadc;
}
a {
  color: #1e1e1e; 
  text-decoration:none;
}
a:active, a:hover {
  color:#ff6600;
  background-color:transparent;
}
textarea:hover, textarea:active, textarea:focus,
input:hover, input:active, input[type="text"]:focus,
.uneditable-input:focus {
  border-color: #e2e2e2;
  box-shadow: 0 0 5px #fc9652c4;
}
button:active, button:focus {
  box-shadow: none;
}
button {
  text-align: center;
  border: 0;
  outline: none;
  font-weight: bold;
}
/* 버튼 가운데 정렬 */
.center-btn {
  display: flex;
  justify-content: center;
}

/* 오렌지버튼 */
.btn-o {
  background-color: #ffeadc;
  color: #ff6600;
  border-radius: 38.5px;
  padding: 0px 20px;
  line-height: 47px;
  font-weight: 450;
}
.btn-o:hover {
  background-color: #fff3eb;
}

/* 회색버튼 */
.btn-g {
  background-color: #f4f4f4;
  border-radius: 15px;
  padding: 0px 20px;
  line-height: 42px;
  color: #555;
}
.btn-g:hover {
  background-color: #f8f8f8;
  color: #333;
}

</style>