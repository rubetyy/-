<template>
  <div id="livechat">
    <div class="chatlog">
      <div v-for="(item, idx) in recvList" :key="idx">
        {{ item.username }}: {{ item.content }}
      </div>
    </div>
    <div class="send">
      <input type="text" class="form-control" v-model="message" @keyup="sendMessage">
      <button class="btn-g-sm" @click="clickMessage">보내기</button>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

const BASE_URL = process.env.VUE_APP_BASE_URL

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
      const serverURL = BASE_URL + '/ws'
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

<style scoped>
#livechat {
  width: 380px;
  float: right;
  border-radius: 5px;
  background-color: #fff3eb;
}
input:hover, input:active, input[type="text"]:focus,
.uneditable-input:focus {
  border-color: #ffeadc;
  box-shadow: none;
}
.chatlog {
  height:450px;
  overflow:auto;
  margin: 10px 20px;
}
.form-control {
  width: 80%;
  display: inline-block;
  margin-right: 0;
  border: 1px solid #ffeadc;
  border-radius: 5px 0 0 5px;
  border-right: none;
  /* outline: none; */
}
.send {
  display: flex;
  justify-content: center;
  padding-bottom: 10px;
}
.btn-g-sm {
  border: 1px solid #ffeadc;
  background: #ffdfc8;
  border-left: none;
  border-radius: 0 5px 5px 0;
  border-radius: 0 5px 5px 0;
  padding: 6px 8px;
  font-weight: 400;
}
</style>