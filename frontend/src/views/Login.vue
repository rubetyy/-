<template>
  <div>
    <h1>Login</h1>
      <div>
        <label for="id">id: </label>
        <input type="text" id='id' v-model='credentials.id'>
      </div>

      <div>
        <label for="password">password: </label>
        <input type="password" id='password' v-model='credentials.password'>
      </div>
      <input type="submit" v-on:click='login'>
    
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
      'LOGIN',
    ]),
    login(){
      this.LOGIN(this.credentials)
      .then(()=>{
        this.$router.push({name:"MainPage"})
      })
    }


    }


}
</script>

<style scoped>

</style>