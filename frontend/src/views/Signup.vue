<template>
  <div>
    <h1>Signup</h1>
     <div>
      <label for="nickname">nickname: </label>
      <input type="text" userNickname='nickname' v-model='credentials.nickname'>
    </div>
    <div>
      <label for="id">id: </label>
      <input type="text" id='id' v-model='credentials.id'>
    </div>
    <div>
      <label for="password">password: </label>
      <input type="password" id='password' v-model='credentials.password'>
    </div>
    <div>
      <label for="passwordConfirmation">password check: </label>
      <input type="password" id='passwordConfirmation' v-model='credentials.passwordConfirmation'>
    </div>
    <div>
      <label for="department">department: </label>
      <input type="text" id='department' v-model='credentials.department'>
    </div>
    <div>
      <label for="position">position: </label>
      <input type="text" id='position' v-model='credentials.position'>
    </div>
    <input type="submit" v-on:click='signupClick'>
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

</style>