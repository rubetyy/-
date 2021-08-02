<template>
  <div>
    유저이름: 
    <input type="text" v-model="username">
    내용: 
    <input type="text" v-model="message" @keyup="sendMessage">
    <button @click="sendMessage">채팅</button>
    <div v-for="(item, idx) in recvList" :key="idx">
      유저이름: {{ item.username }} | 내용: {{ item.content }}
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

import { mapGetters } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'LiveChat',
  data() {
    return {
      username: '',
      message: '',
      recvList: []
    }
  },
  created() {
    this.connect()
  },
  computed: {
    ...mapGetters(userStore, ['getUserInfo']),
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.username !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    
    send() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          username: this.username,
          content: this.message 
        };
        this.stompClient.debug = function (){};  //do nothing
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    connect() {
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.debug = function (){};
      this.stompClient.connect(
        {},
        () => {
          this.connected = true;
          this.stompClient.debug = function (){};
          this.stompClient.subscribe("/send", res => {
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    }
  }
}
</script>