<template>
  <div>
    <h1 id='header'>
      To. {{ this.receiver }}
    </h1>
    <div id="chatroom">
      <div class="chatlog">
        <div v-for="(item, idx) in previousMsg" :key="100-idx">
          <div v-if="item.sender==nowUser" class="myMsg">
            <span class="msg">{{ item.message }}</span>
          </div>
          <div v-else class="anotherMsg">
            <span class="anotherName">{{ item.sender }}</span>
            <span class="msg">{{ item.message }}</span>
          </div>
        </div>

        <div v-for="(item, idx) in messages" :key="idx">
          <div v-if="item.sender==nowUser" class="myMsg">
            <span class="msg">{{ item.message }}</span>
          </div>
          <div v-else class="anotherMsg">
            <span class="anotherName">{{ item.sender }}</span>
            <span class="msg">{{ item.message }}</span>
          </div>
        </div>
      </div>
      <div class="send">
        <input type="text" id="message" class="form-control" placeholder="메세지를 입력해주세요" v-model="message" @keyup="sendMessage">
        <button class="btn-c-sm" @click="clickMessage">보내기</button>
      </div>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import axios from 'axios'

const BASE_URL = process.env.VUE_APP_BASE_URL

export default {
  name: 'ChatRoom',
  data() {
    return {
      receiver: this.$route.query.nickname,
      message: '',
      messages: [],
      roomId: this.$route.params.pk,
      nowUser: JSON.parse(localStorage.getItem('userInfo')).nickname,
      previousMsg: [],
    }
  },
  created() {
    this.connect()
    this.getMsg()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.sender.trim() !== '' && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (e.keyCode === 13 && this.sender.trim() == '') {
        alert('로그인 후 이용해주세요')
      }
    },
    clickMessage() {
      if(this.sender.trim() !== '' && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (this.sender.trim() == '') {
        alert('로그인 후 이용해주세요')
      }
    },
    send() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          type:'ONE',// ONE 으로 바꾸기
          roomId:this.roomId,
          sender: this.nowUser,
          message: this.message 
        }
        this.stompClient.debug = function (){}  //do nothing
        this.stompClient.send("/pub/chat/message", JSON.stringify(msg), {})
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
            this.messages.push(JSON.parse(res.body))
          })
        },
        error => {
          console.log('소켓 연결 실패', error)
          this.connected = false
        }
      )
    },
    getMsg() {
      const url = BASE_URL + `/chatroom/`
      const data = {
        chatroompk: this.roomId,
        userid: JSON.parse(localStorage.getItem('userInfo')).id,
      }
      axios.post(url, data)
      .then(res => {
        if (res.data.userStatus==1) {
          this.previousMsg = res.data.talk
        } else {
          alert('권한이 없습니다(userStatus)')
        }
      })
      .catch((err) => {
        console.log(err)
        alert('권한이 없습니다(error)')
      })
    },
  }
}
</script>

<style>
#chatroom {
  max-width: 1000px;
  border-radius: 5px;
  background-color: #fff3eb;
  margin: 40px auto;
  padding: 10px;
}
input:hover, input:active, input[type="text"]:focus,
.uneditable-input:focus {
  border-color: #ffeadc;
  box-shadow: none;
}
.chatlog {
  height:450px;
  margin: 10px 20px;
  overflow-y: auto;
  padding: 10px;
}
.form-control {
  width: 88%;
  display: inline-block;
  margin-right: 0;
  border: 1px solid #ffeadc;
  border-radius: 5px 0 0 5px;
  border-right: none;
  line-height: 32px;
  padding-left: 20px;
}
.send {
  display: flex;
  justify-content: center;
  padding-bottom: 10px;
}
.btn-c-sm {
  border: 1px solid #ffeadc;
  background: #ffdfc8;
  border-left: none;
  border-radius: 0 5px 5px 0;
  border-radius: 0 5px 5px 0;
  padding: 6px 18px;
  font-weight: 400;
}
.myMsg {
  text-align: right;
}
.anotherMsg {
  text-align: left;
  margin-bottom: 5px;
}
.msg {
  display: inline-block;
  border-radius: 15px;
  padding: 7px 15px;
  margin-bottom: 10px;
  margin-top: 5px;
}
.anotherMsg > .msg {
  background-color: #ebebeb;
}
.myMsg > .msg {
  background-color: #ff9752;
  color: #fff;
}
.anotherName {
  font-size: 13px;
  display: block;
}

</style>