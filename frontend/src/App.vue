<template>
  <div id="app">
    <div id="nav">
        <router-link :to="{name: 'MainPage'}">MainPage</router-link> |
        <router-link :to="{name: 'CategoryPage'}">CategoryPage</router-link> |
      <!-- <div v-if="isLogged"> -->
        <router-link :to="{name: 'Mypage'}">Mypage</router-link> |
        <!-- <router-link :to="{name: 'LiveBroadpage'}">LiveBroadpage</router-link> | -->
        <router-link :to="{name: 'ProductRegister'}">ProductRegister</router-link> |
        <router-link :to="{name: 'MakeBroadCast'}">MakeBroadCast</router-link> |
        <router-link :to="{name: 'ProductDetail'}">ProductDetail</router-link> |
        <router-link to="#" @click.native="logout">Logout</router-link> |
      <!-- </div> -->

      <!-- <div v-else> -->
        <router-link :to="{name: 'Signup'}">Signup</router-link> |
        <router-link :to="{name: 'Login'}">Login</router-link> |
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
      'LOGOUT'
    ]),
    logout: function() {
      this.LOGOUT()
      .then(()=> {
        this.$router.push({name:"MainPage"})
    })
   }
  },

  }

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
