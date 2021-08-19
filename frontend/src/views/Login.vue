<template>
  <div>
    <div class="form-box login-form">
      <h1 id="header">Login</h1>
      <div class="id-input">
        <input type="text" id='id' placeholder="ID" class="input-form" v-model='credentials.id'>
      </div>
      <div class="password-input">
        <input type="password" id='password' placeholder="Password" class="input-form" v-model='credentials.password'>
      </div>
      <button class="btn-g" v-on:click='loginClick'>로그인</button>
      <router-link class="r-link" :to="{name: 'Signup'}" style="display: block;  margin-top: 30px">아직 계정이 없나요?</router-link>
    </div>
  </div>
</template>

<script>
import swal from 'sweetalert'
import { mapActions } from 'vuex'

const userStore = 'userStore'

export default {
  name: 'Login',
  data: function () {
    return {
      credentials: {
        id: null,
        password: null,
      },
    }
  },
  methods: {
    ...mapActions(userStore, ['login']),
    loginClick(){
      if (this.credentials.id == null) {
        swal({
          text: '아이디를 입력하세요',
          icon: 'warning',
          button: false,
        })
      } else if (this.credentials.password == null) {
        swal({
          text: '비밀번호를 입력하세요',
          icon: 'warning',
          button: false,
        })
      } else {
        this.login(this.credentials)
        .then(() =>{
          this.$router.push({name:"MainPage"})
        })
        .catch(error => {
          if (error.response.status === 500) {
            swal({
              text: '없는 아이디 입니다',
              icon: 'warning',
              button: false,
            })
          } else if (error.response.status === 401) {
            swal({
              text: '잘못된 비밀번호 입니다',
              icon: 'warning',
              button: false,
            })
          }
        })
      }
    }
  }
}
</script>

<style scoped>
div {
  box-sizing: border-box;
}
.login-form {
  height: 500px;
  padding: 40px;;
  text-align: center;
  border-radius: 15px;
  background-color: #F9A77C;
}
.input-form {
  margin: 50px;
  padding: 10px 10px;
  width: 50%;
  border-radius: 5px;
  outline:none;
}
</style>