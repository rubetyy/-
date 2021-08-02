<template>
  <div class="form-background">
    <div class="form-box signup-form">
      <h1 id="header">Signup</h1>
      <div>
        <!-- <label for="nickname">nickname: </label> -->
        <input type="text" userNickname='nickname' class="input-form" placeholder="Nickname" v-model='credentials.nickname'>
      </div>
      <div>
        <!-- <label for="id">id: </label> -->
        <input type="text" id='id' class="input-form" placeholder="ID" v-model='credentials.id'>
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
        id: null,
        password: null,
        // passwordConfirmation: null,
        // position: null,
        // department: null,
        nickname: null,
      },
    }
  },
  methods : {
      ...mapActions(userStore,[
        'signup'
      ]),
      signupClick: function() {
        this.signup(this.credentials)
        .then(()=> {
          this.$router.push({name:"MainPage"})
      })
    }
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
  background-color:#F9A77C;
  padding: 40px;;
  text-align: center;
  border-radius: 15px;
}
.input-form {
  margin: 50px;
  padding: 10px 10px;
  width: 50%;
  border-radius: 5px;
  outline:none;
}
</style>