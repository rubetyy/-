<template>
  <div class="form-background">
    <div class="form-box signup-form">
      <h1 id="header">Signup</h1>
      <div>
        <!-- <label for="nickname">nickname: </label> -->
        <input type="text" userNickname='nickname' class="input-form" placeholder="Nickname" v-model='credentials.nickname'>
        <button class="btn-g" @click="checknickname">중복검사</button>
      </div>

      <div>
        <!-- <label for="id">id: </label> -->
        <input type="text" id='id' class="input-form" placeholder="ID" v-model='credentials.id'>
        <button  class="btn-g" @click="checkid">중복검사</button>
      </div>

      <div>
        <!-- <label for="password">password: </label> -->
        <input type="password" id='password' class="input-form" placeholder="Password" v-model='credentials.password' style="margin-right:149.2px">
      </div>
      <div>
        <input type="password " id='password' class="input-form" placeholder="PasswordConfirmation" v-model='credentials.passwordconfirmation' style="margin-right:149.2px">

      </div>
      <div style="display: flex; justify-content: center">
        <button class="btn-g register" v-on:click='signupClick'>회원가입</button>
      </div>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'
const userStore = 'userStore'
import swal from 'sweetalert'

export default {
  name: 'Signup',

  data: function () {
    return {
      credentials: {
        id: '',
        password: '',
        nickname: '',
      },
      v : {
        id: false,
        nickname: false,
      },
      text:'',
      flag: false,
    }
  },

  methods : {
    ...mapActions(userStore,['signup']),
    signupClick: function() {
      if (this.v.nickname && this.v.id && this.credentials.password.trim().length > 3 && this.credentials.password == this.credentials.passwordconfirmation) {
          this.signup(this.credentials)
          .then(()=> {
          swal({
            text: '회원가입이 완료되었습니다',
            icon: 'success',
            button: false,
          })
            this.$router.push({name:"MainPage"})
        })
      } else if (!this.v.nickname) {
        swal({
          text: '닉네임 중복검사 해주세요',
          icon: 'warning',
          button: false,
        })
      } else if (!this.v.id) {
        swal({
          text: '아이디 중복검사 해주세요',
          icon: 'warning',
          button: false,
        })
      } else if (this.credentials.password.trim().length < 4) {
        swal({
          text: '비밀번호는 공백을 제외한 4자리 이상 가능합니다',
          icon: 'warning',
          button: false,
        })
      } else if (this.credentials.password != this.credentials.passwordconfirmation) {
        alert('비밀번호확인과 같지 않습니다')
      }
    },

    ...mapActions(userStore,['checkNickname']),
    checknickname: function() {
      if (this.credentials.nickname.trim()) {
        this.checkNickname(this.credentials.nickname)
        .then((res) => {
          if (res === 0) {
            this.v.nickname = true
            swal({
              text: '사용가능한 닉네임입니다',
              icon: 'success',
              button: false,
            })
          } else {
            swal({
              text: '이미 존재하는 닉네임입니다',
              icon: 'warning',
              button: false,
            })
          }
        })
      } else {
        swal({
          text: '닉네임을 입력해주세요',
          icon: 'warning',
          button: false,
        })
      }
    },

    ...mapActions(userStore,['checkUserid']),
    checkid: function() {
      if (this.credentials.id.trim()) {
        this.checkUserid(this.credentials.id)
        .then((res) => {
          // 0 -> 없는 아이디 / 1 -> 중복, 이미 있는 아이디
          if (res === 0) {
            this.v.id = true
            swal({
              text: '사용가능한 아이디입니다',
              icon: 'success',
              button: false,
            })
          } else {
            swal({
              text: '이미 존재하는 아이디입니다',
              icon: 'warning',
              button: false,
            })
          }
        })
      } else {
        swal({
          text: '아이디를 입력해주세요',
          icon: 'warning',
          button: false,
        })
      }
    },

    goback() {
    this.$router.go(0)
    },
  },
}
</script>

<style scoped>
div{
  box-sizing: border-box;
  margin-bottom: 30px;
  margin-top: 30px;
}
/* .form-background{
  background-color: #ff8a3d;
} */
.signup-form {
  height: 700px;
  padding-left: 40px;
  padding: 40px;
  text-align: left;
  border-radius: 15px;
  background-color: #F9A77C;
}
.input-form {
  margin: 20px;
  margin-left: 140px;
  padding: 10px 10px;
  width: 50%;
  border-radius: 5px;
  outline:none;
}
.register {
  display: flex;
  justify-content: center;
}
</style>