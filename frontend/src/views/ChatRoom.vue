<template>
  <div>
    <h1 id='header'>
      To. {{ this.receiver }}
    </h1>
    <h4 v-if="productFile.images" style="text-align: center; margin: 25px">상품명: {{this.productFile.images[0].product.title}}</h4>

    <div id="chatroom">
      <div id="chatlog" ref="messages" class="chatlog">
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

    <div v-if="productFile.images">

      <div v-if="productFile.images[0].product.isSold == 1">
        <p style="text-align:center; font-size:20px;">판매완료 된 상품입니다.</p>
      </div>

      <div v-else>
        <div v-if="productFile.images[0].product.userId == this.userid " style="text-align:center;">
          <button class="btn-g" @click="soldoutBtn">판매완료</button>
        </div>
        <div v-else>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import axios from 'axios'
import swal from 'sweetalert'
import { mapActions, mapGetters } from 'vuex'
const productStore = 'productStore'

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
      productpk: this.$route.query.productpk,
      userid: JSON.parse(localStorage.getItem('userInfo')).id,
    }
  },
  created() {
    this.connect()
    this.getMsg()
    const data = {
      'userid': JSON.parse(localStorage.getItem('userInfo')).id,
      'productpk': this.productpk,
    }
    this.productDetail(data)
    // var chatlog = document.getElementById('chatlog')
    // chatlog.scrollIntoView(false)
    // chatlog.scrollTop = chatlog.scrollHeight
  },
  mounted() {
    this.scrollDown()
  },
    computed: {
    ...mapGetters(productStore,[
      'getProductDetailFile'
    ]),
    productFile: function() {
      return this.getProductDetailFile
    },
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
    scrollDown() {
      // var chatlog = this.$refs.messages
      var chatlog = document.getElementById("chatlog")
      console.log(chatlog)
      chatlog.scrollTop = chatlog.scrollHeight;
      console.log(chatlog.scrollTop)
      console.log(chatlog.scrollHeight)
      // window.scrollTo({ top: chatlog.scrollHeight, behavior: 'smooth' })
    },
    sendMessage (e) {
      if(e.keyCode === 13 && this.nowUser.trim() !== '' && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (e.keyCode === 13 && this.nowUser.trim() == '') {
        swal({
          text: '로그인 후 이용해주세요',
          icon: 'warning',
          button: false,
        })
      }
    },
    clickMessage() {
      if(this.nowUser.trim() !== '' && this.message.trim() !== ''){
        this.send()
        this.message = ''
      } else if (this.nowUser.trim() == '') {
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
          type:'ONE',
          roomId:this.roomId,
          sender: this.nowUser,
          message: this.message 
        }
        this.stompClient.debug = function (){}
        this.stompClient.send("/pub/api/chat/message", JSON.stringify(msg), {})
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
          swal({
            text: '권한이 없습니다',
            icon: 'warning',
            button: false,
          })
        }
      })
      .catch((err) => {
        console.log(err)
        swal({
          text: '권한이 없습니다',
          icon: 'warning',
          button: false,
        })
      })
    },
    ...mapActions(productStore,[
    'soldout',
    ]),
    soldoutBtn() {
      swal({
        title: '정말 판매완료 하시겠습니까?',
        text: "한번 완료하면 취소할 수 없습니다",

        icon: 'warning',
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
          swal("판매완료 되었습니다!", {
            icon: "success",
          });
          const data = {
          'userid': JSON.parse(localStorage.getItem('userInfo')).id,
          'productpk': this.productpk
          }
          this.soldout(data)
        } else {
          swal("취소되었습니다");
        }
      });

    },
    ...mapActions(productStore,[
    'productDetail',
    ]),

  }
}
</script>

<style scoped>
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