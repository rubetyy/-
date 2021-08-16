<template>
  <div id="app">
    <div id="nav">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarToggler">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <router-link :to="{name: 'MainPage'}" class="navbar-brand"><img src="./assets/logo.png" alt="" width="35" height="35">홍당무 라이브</router-link>
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 1}}" class="navbar-brand" >의류</router-link> 
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 2}}" class="navbar-brand" >음식</router-link> 
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 3}}" class="navbar-brand" >전자제품</router-link> 
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 4}}" class="navbar-brand" >기타</router-link> 
            </ul>

            <div class="d-flex me-5">
              <input class="form-control me-2" type="search" placeholder="제품을 검색하세요" aria-label="Search" v-model="keyword"  @keyup.enter="search">
              <button class="btn-o btn-small" type="submit" @click="search">Search</button>
            </div>

            <div v-if="isLogged">
              <router-link :to="{name: 'ProductRegister'}" class="navbar-brand">상품 등록</router-link>
              <router-link :to="{name: 'MyPage'}" class="navbar-brand" >마이페이지</router-link> 
              <router-link to="#" @click.native="logoutClick" class="navbar-brand">로그아웃</router-link> 
            </div>
            <div v-else>
              <router-link :to="{name: 'Signup'}" class="navbar-brand">회원가입</router-link> 
              <router-link :to="{name: 'Login'}" class="navbar-brand">로그인</router-link> 
            </div>
          </div>
        </div>
      </nav>
    </div>
    <div id="article">
      <router-view/> 
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
const userStore = 'userStore'
const productStore = 'productStore'

export default {
  name: 'App',

  data: function () {
    return {
      userId: '',
      keyword: '',
    }
  },
  computed: {
    ...mapGetters(userStore,[
      'getToken'
    ]),
    isLogged: function(){
      return this.getToken
    },
  },
  methods : {
    ...mapActions(userStore,[
      'logout'
    ]),
    logoutClick: function() {
      this.logout()
      .then(()=> {
        this.ter.push({name:"MainPage"})
      })
    },
      ...mapActions(productStore,[
    'getSearch'
    ]),
    search() {
      const data = {
        'search' : this.keyword
      }
      this.getSearch(data)
      .then(()=>{
        this.$router.push({name:"SearchPage"})
      })
    }
  },
  created (){
    this.userId = JSON.parse(localStorage.getItem('userInfo'))
    console.log(this.userId)
  }
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
  min-height: 90vh;
}

#nav {
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
.container-fluid {
  padding-inline: 100px;
}

#article {
  font-family: 'Noto Sans KR';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #1e1e1e;
  padding-top: 48px;
  padding-bottom: 100px;
  box-sizing: border-box;
  max-width: 1200px;
  min-width: 400px;
  margin: 0 auto;
}

#header {
  text-align: center;
  font-family: 'netmarbleB';
}

.form-box {
  width: 800px;
  margin: 50px auto 80px;
}

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
input:hover, input:active, input[type="text"]:focus, input[type="search"]:focus,
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
.center-btn {
  display: flex;
  justify-content: center;
}
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
.btn-small {
  padding: 0px 10px;
  font-size: 1rem;
  line-height: 0;
}
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

::-webkit-scrollbar {
  width: 11px;
  height: 11px;
} 
::-webkit-scrollbar-thumb {
  background-color: rgba(255, 201, 164, 0.75);
  border: 4px solid transparent;
  border-radius: 10px;
} 
::-webkit-scrollbar-track { 
  background-color: rgba(255, 139, 61, 0.15);
}
</style>