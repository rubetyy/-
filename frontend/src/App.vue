<template>
  <div id="app">
    <div id="nav">
        <router-link :to="{name: 'MainPage'}">홍당무 라이브</router-link> |
        <router-link :to="{name: 'CategoryPage'}">의류, 음식, 전자제품, 기타</router-link> |
      <!-- <div v-if="isLogged"> -->
        <router-link :to="{name: 'MyPage'}">마이페이지</router-link> |
        <router-link :to="{name: 'ProductRegister'}">상품 등록</router-link> |
        <router-link :to="{name: 'LiveRegister'}">LiveRegister</router-link> |
        <router-link :to="{name: 'ProductDetail'}">ProductDetail</router-link> |
        <router-link to="#" @click.native="logoutClick">로그아웃</router-link> |
      <!-- </div> -->

      <!-- <div v-else> -->
        <router-link :to="{name: 'Signup'}">회원가입</router-link> |
        <router-link :to="{name: 'Login'}">로그인</router-link> |
      <!-- </div> -->
      
    </div>
    <router-view/> 
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
  /* font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; */
  color: #2c3e50;
}

#nav {
  padding: 30px;
  font-family: 'netmarbleB';
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #ff8a3d;
}

::selection {
  background: #ffeadc;
}
a {color:#ff8a3d; text-decoration:none;}
a:active, a:hover {color:#ff6600;}
a:active {background-color:transparent}

#header {
  text-align: center;
  font-family: 'netmarbleB';
}
</style>