<template>
  <div>
    <div class="form-box login-form">
      <h1 id="header">Login</h1>
      <div class="id-input">
        <!-- <label for="userid">id: </label> -->
        <input type="text" id='id' placeholder="ID" class="input-form" v-model='credentials.id'>
      </div>

      <div class="password-input">
        <!-- <label for="userpassword">password: </label> -->
        <input type="password" id='password' placeholder="Password" class="input-form" v-model='credentials.password'>
      </div>
      <button class="btn-g" v-on:click='loginClick'>로그인</button>
      <!-- <input type="submit" class="button" v-on:click='loginClick'> -->
      <router-link  :to="{name: 'Signup'}" style="display: block;  margin-top: 30px">아직 계정이 없나요?</router-link>
    </div>
  </div>
</template>

<script>
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
    ...mapActions(userStore, [
      'login',
    ]),
    loginClick(){
      this.login(this.credentials)
      .then(()=>{
        this.$router.push({name:"MainPage"})
      })
    }


    }


}
</script>

<style scoped>

div{
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