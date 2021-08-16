<template>
  <div id="livechat">
    <div class="chatlog" ref="messages">
      <div v-for="(item, idx) in messages" :key="idx">
        {{ item.sender }}: {{ item.message }}
      </div>
    </div>
    <div class="send">
      <input type="text" class="form-control" v-model="message" @keyup="sendMessage">
      <button class="btn-g-sm" @click="clickMessage">보내기</button>
    </div>

    <Modal v-if="!isLive" @close="showModal=false" :fct="goMain">
      <h3 slot="header">
        알림!
      </h3>
      <div slot="body" style="text-align:center">방송이 종료되었습니다</div>
    </Modal>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Modal from '@/components/Modal'
import swal from 'sweetalert'

const BASE_URL = process.env.VUE_APP_BASE_URL

export default {
  name: 'LiveChat',
  components: {
    Modal,
  },
  data() {
    return {
      sender: null,
      message: '',
      messages: [],
      roomId: '',
      isLive: true,
      showModal: false,
    }
  },
  created() {
    this.sender = localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).nickname : null
    this.roomId = localStorage.getItem('wschat.roomId')
    this.connect()
  },
  watch: {
    messages() {
      this.$nextTick(() => {
        let messages = this.$refs.messages
        messages.scrollTo({ top: messages.scrollHeight, behavior: 'smooth' })
      })
    },
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.sender !== null && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (e.keyCode === 13 && this.sender == null) {
        swal({
          text: '로그인 후 이용해주세요',
          icon: 'warning',
          button: false,
        })
      }
    },
    clickMessage() {
      if(this.sender !== null && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (this.sender == null) {
        swal({
          text: '로그인 후 이용해주세요',
          icon: 'warning',
          button: false,
        })
      }
    },
    send() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          type:'TALK',
          roomId:this.roomId,
          sender: this.sender,
          message: this.message 
        }
        this.stompClient.debug = function (){}  //do nothing
        this.stompClient.send("/pub/api/livechat/message", JSON.stringify(msg), {})
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
          this.stompClient.subscribe(`/sub/livechat/room/${this.roomId}`, res => {
            if (JSON.parse(res.body).type === 'LEAVE') {
              this.isLive = false
            } else {
              this.messages.push(JSON.parse(res.body))
            }
          })
        },
        error => {
          console.log('소켓 연결 실패', error)
          this.connected = false
        }
      )
    },
    goMain() {
      this.$router.push({name:"MainPage"})
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
  margin: 15px 10px 10px;
}
.form-control {
  width: 80%;
  display: inline-block;
  margin-right: 0;
  border: 1px solid #ffeadc;
  border-radius: 5px 0 0 5px;
  border-right: none;
  padding-left: 12px;
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