<template>
  <div>
    유저이름: 
    <input type="text" v-model="username">
    내용: 
    <input type="text" v-model="message" @keyup="sendMessage">
    <button>채팅</button>
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
      username: "",
      message: "",
      recvList: []
    }
  },
  created() {
    this.connect()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.username !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    
    send() {
      // console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          username: this.username,
          content: this.message 
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    connect() {
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          this.stompClient.subscribe("/send", res => {
            // console.log('구독으로 받은 메시지 입니다.', res.body);
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    }
  }
}
</script>