<template>
  <div id="livechat">
    <div class="chatlog">
      <div v-for="(item, idx) in messages" :key="idx">
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
import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

export default {
  name: 'LiveChat',
  data() {
    return {
      username: '',//닉네임
      message: '',
      messages: [],
      roomId: '',
      room: {},
    }
  },
  created() {
    this.username = JSON.parse(localStorage.getItem('userInfo')).nickname
    this.roomId = localStorage.getItem('wschat.roomId')
    this.findRoom()
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
          type:'TALK',
          roomId:this.roomId,
          sender: this.username,
          message: this.message 
        }
        this.stompClient.debug = function (){}  //do nothing
        this.stompClient.send("/pub/chat/message", {}, JSON.stringify(msg))
      }
    },    
    connect() {
      const serverURL = BASE_URL + '/ws'
      let socket = new SockJS(serverURL)
      this.stompClient = Stomp.over(socket)
      this.stompClient.debug = function (){}
      this.stompClient.connect(
        {},
        () => {
          this.connected = true
          this.stompClient.debug = function (){}
          this.stompClient.subscribe(`/sub/chat/room/${this.roomId}`, res => {
            console.log(res)
            // this.messages.push(JSON.parse(res.body))
            const recv = JSON.parse(res.body)
            this.recvMessage(recv)
          })
          this.stompClient.send("/pub/chat/message", {}, JSON.stringify({type:'ENTER', roomId:this.roomId, sender:this.username}))
        },
        error => {
          console.log('소켓 연결 실패', error)
          this.connected = false
        }
      )
    },
    findRoom() {
      const url = BASE_URL+`/chat/room/${this.roomId}`
      axios.get(url)
      .then(res => { this.room = res.data })
    },
    recvMessage(recv) {
        this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
    },
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