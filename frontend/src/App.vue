<template>
  <div>
    <div id="nav">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <router-link :to="{name: 'MainPage'}" class="navbar-brand"><img src="./assets/logo.png" alt="" width="35" height="35" class="d-inline-block align-text-middle">홍당무 라이브</router-link>
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 1}}" class="navbar-brand" >의류</router-link> 
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 2}}" class="navbar-brand" >음식</router-link> 
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 3}}" class="navbar-brand" >전자제품</router-link> 
              <router-link :to="{name: 'CategoryPage', params:{categoryId: 4}}" class="navbar-brand" >기타</router-link> 
            </ul>

            <div class="d-flex me-5">
              <input class="form-control me-2" type="search" placeholder="제품을 검색하세요" aria-label="Search" v-model="keyword"  @keyup.enter="search">
              <button class="btn-o " type="submit" @click="search">Search</button>
            </div>

            <!-- <div class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                예상
              </a>
              
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><router-link :to="{name: 'ProductRegister'}" class="dropdown-item">상품 등록</router-link></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="#">다른 구분할꺼 넣을꺼</a></li>
              </ul>
            </div> -->
              
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

    <footer class='bg-light'>
      © 2021 광주3팀 서울광주롱디팀
    </footer>
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
        this.$router.push({name:"MainPage"})
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

/* #app {
  font-family: 'Noto Sans KR';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #1e1e1e;
  max-width: 1200px;
  min-width: 400px;
  margin: 0 auto;
} */

/* 임시 네비게이션 바 (추후 변경예정) */
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

footer {
  /* position: absolute; */
  bottom: 0;
  left: 0;
  width: 100%;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 20px;
}

/* 컨텐츠 내부 (개발하기 편하게 일단 위아래 여백 뒀습니다) */
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