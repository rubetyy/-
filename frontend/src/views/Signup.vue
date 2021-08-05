<template>
  <div class="form-background">
    <div class="form-box signup-form">
      <h1 id="header">Signup</h1>
      <div>
        <!-- <label for="nickname">nickname: </label> -->
        <input type="text" userNickname='nickname' class="input-form" placeholder="Nickname" v-model='credentials.nickname'>
        <button @click="checknickname">중복검사</button>
      </div>
      <div>
        <!-- <label for="id">id: </label> -->
        <input type="text" id='id' class="input-form" placeholder="ID" v-model='credentials.id'>
        <button @click="checkid">중복검사</button>
      </div>
      <div>
        <!-- <label for="password">password: </label> -->
        <input type="password" id='password' class="input-form" placeholder="Password" v-model='credentials.password'>
      </div>
      <button class="btn-g" v-on:click='signupClick'>회원가입</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
const userStore = 'userStore'

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
      }
    }
  },
  methods : {
    ...mapActions(userStore,['signup']),
    signupClick: function() {
      if (this.v.nickname && this.v.id && this.credentials.password.trim().length > 3) {
          this.signup(this.credentials)
          .then(()=> {
            this.$router.push({name:"MainPage"})
        })
      } else if (!this.v.nickname) {
        alert('닉네임 중복검사를 해주세요')
      } else if (!this.v.id) {
        alert('아이디 중복검사를 해주세요')
      } else if (this.credentials.password.trim().length < 4) {
        alert('비밀번호는 공백을 제외한 4자리 이상 가능합니다')
      }
    },

    ...mapActions(userStore,['checkNickname']),
    checknickname: function() {
      if (this.credentials.nickname.trim()) {
        this.checkNickname(this.credentials.nickname)
        .then((res) => {
          // true, 1 -> 회원가입 가능 / false,0 -> 중복
          if (res) {
            this.v.nickname = true
            alert('회원가입 가능합니다')
          } else {
            alert('이미 존재하는 닉네임입니다')
          }
        })
      } else {
        alert('닉네임을 입력해주세요')
      }
    },

    ...mapActions(userStore,['checkUserid']),
    checkid: function() {
      if (this.credentials.id.trim()) {
        this.checkUserid(this.credentials.id)
        .then((res) => {
          // true, 1 -> 회원가입 가능 / false,0 -> 중복
          if (res) {
            this.v.id = true
            alert('회원가입 가능합니다')
          } else {
            alert('이미 존재하는 아이디입니다')
          }
        })
      } else {
        alert('아이디를 입력해주세요')
      }
    },

  },


}
</script>

<style scoped>

div{
  box-sizing: border-box;
}
/* .form-background{
  background-color: #ff8a3d;
} */
.signup-form {
  height: 700px;
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