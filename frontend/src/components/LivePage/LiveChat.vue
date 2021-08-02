<template>
  <div>
    <input type="text" v-model="message" @keyup="sendMessage">
    <button @click="clickMessage">채팅</button>
    <div v-for="(item, idx) in recvList" :key="idx">
      유저이름: {{ item.username }} | 내용: {{ item.content }}
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name: 'LiveChat',
  data() {
    return {
      username: '',//닉네임
      message: '',
      recvList: []
    }
  },
  created() {
    this.username = JSON.parse(localStorage.getItem('userInfo')).nickname
    this.connect()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.username.trim() !== '' && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (e.keyCode === 13 && this.username.trim() == '') {
        alert('로그인 후 이용해주세요')
        // 로그인 창으로 바로가기?
      }
    },
    clickMessage() {
      if(this.username.trim() !== '' && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (this.username.trim() == '') {
        alert('로그인 후 이용해주세요')
        // 로그인 창으로 바로가기?
      }
    } ,  
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