<template>
  <div>
    <h1 id="header">제품 상세</h1>
    <div>
      <PdCarousel class="img-box" style="max-width:1024px margin:0px; " :thumbnail="thumbnail" />
      <div v-if="productFile.images" class="content">
        <div class="userinfo">
          <i class="bi bi-person-circle"></i>
          <router-link class="r-link" :to="{name: 'ProfilePage', params: {userid: productFile.images[0].product.userId}}">
            {{productFile.usernickname}}
          </router-link>
        </div>
        <div class="content-sold" >
          <div v-if=" productFile.images[0].product.isSold == 1" style="display: flex;">
            <p style="margin-right: 30px; ">판매완료</p>
            <p style="color: grey;">조회수: {{productFile.images[0].product.viewCount}}</p>
          </div>
          <div v-else style="display: flex; padding:10px;" >
            <p style="display: inline-block; margin-right: 30px; color:#ff8a3d;" >판매중</p>
            <p style="color: grey;">조회수: {{productFile.images[0].product.viewCount}}</p>
          </div>
          <div v-if='userId.id != productFile.images[0].product.userId'>
            <button class="btn-o" style="margin-right:13px;" @click="startchat">1:1 채팅하기</button>
            <button v-if="productFile.wish.flag==true" class="btn-o" @click="dislikeProduct">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
              </svg> 찜하기 취소
            </button>
            <button v-else  class="btn-o" @click="likeProduct">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
              </svg> 찜하기
            </button>
          </div>
        </div>
        <div class="content-title"> 
          <p>{{ productFile.images[0].product.title }}</p>
          <section>
            <div v-if="productFile.images[0].product.isLive == 0">
              <p class="live-text">Live중이 아닙니다</p>
              <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-off" viewBox="0 0 16 16">
                <path d="M11 4a4 4 0 0 1 0 8H8a4.992 4.992 0 0 0 2-4 4.992 4.992 0 0 0-2-4h3zm-6 8a4 4 0 1 1 0-8 4 4 0 0 1 0 8zM0 8a5 5 0 0 0 5 5h6a5 5 0 0 0 0-10H5a5 5 0 0 0-5 5z"/>
              </svg>
              <div v-if="productFile.images[0].product.userId == this.userId.id">
                <button class="btn-o" @click="makelive">방송 시작하기</button>
              </div>
              <br>        
            </div>
            <div v-else>
              <p>Live중입니다</p>
              <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-on" viewBox="0 0 16 16" style="color: green">
                <path d="M5 3a5 5 0 0 0 0 10h6a5 5 0 0 0 0-10H5zm6 9a4 4 0 1 1 0-8 4 4 0 0 1 0 8z"/>
              </svg>
              <router-link class="r-link" :to="{name: 'LivePage', params: { id: productFile.images[0].product.liveId }}">라이브 방송 시청</router-link>
              <br>
            </div>  
          </section>
        </div>
        <div class="content-content">
          <div class="content-sub">
            {{this.category[productFile.images[0].product.categoryId]}} /
            <time>{{createTime}}</time>
          </div>
          <div> 
            <p style="font-size: 1.2rem; margin-bottom:40px;">가격: {{ productFile.images[0].product.price }}원</p>
          </div>
          <div style="font-size: 1.4rem;">제품 설명: {{ productFile.images[0].product.description }} </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import swal from 'sweetalert'
import PdCarousel from '@/components/PdCarousel'
import { mapActions, mapGetters } from 'vuex'

const productStore = 'productStore'
const liveStore = 'liveStore'
const userStore = 'userStore'

export default {
  name: 'ProductDetail',
  components: {
    PdCarousel,
  },
  data: function () {
    return {
      slide: 0,
      sliding: null,
      thumbnail: [],
      userId: {
        id: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).id : null,
        nickname: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).nickname : null,
      },
      createTime: '',
      category: {
        '1' : '의류',
        '2' : '음식',
        '3' : '전자제품',
        '4' : '기타',
      },
      likeFlag: false,
    }
  },
  computed: {
    ...mapGetters(productStore,['getProductDetailFile']),
    productFile: function() {
      return this.getProductDetailFile
    },
  },
  methods: {
    ...mapActions(productStore,['productDetail']),
    ...mapActions(liveStore, ['makeLive']),
    makelive: function () {
      const data = {
        productPk: this.productFile.images[0].product.id,
        productTitle: this.productFile.images[0].product.title,
      }
      this.makeLive(data)
      this.$router.push({ name: 'LiveRegister' })
    },
    onSlideStart(slide) {
      this.slide = slide
      this.sliding = true
    },
    onSlideEnd() {
      this.sliding = false
    },
    ...mapActions(userStore, ['startChat']),
    startchat: function() {
      if (localStorage.getItem('token')==null) {
        swal({
          text: '로그인 후 이용해주세요',
          icon: 'warning',
          button: false,
        })
      } else {
        const params = {
          productpk: this.productFile.images[0].product.id,
          useridbuyer: JSON.parse(localStorage.getItem('userInfo')).id,
          useridseller: this.productFile.images[0].product.userId,
        }
        this.startChat(params)
        .then(res => {
          this.$router.push({name: 'ChatRoom', params: {pk: res.chatroompk}, query: {nickname: res.sellernickname, productpk: params.productpk }})
        })
      }
    },
    ...mapActions(userStore,['like','dislike']),
    likeProduct() {
     if (localStorage.getItem('token')==null) {
        swal({
          text: '로그인 후 이용해주세요',
          icon: 'warning',
          button: false,
        })
      } else {
        const useridbuyer  = this.userId.id
        const productpk = this.productFile.images[0].product.id
        const data = {
          'useridbuyer' : useridbuyer,
          'productpk' : productpk,
        }
        this.like(data)
        .then(()=>{
          this.productFile.wish.flag = true
        })
      }
    },
    ...mapActions(userStore,['dislike']),
    dislikeProduct() {
      const wishproductpk = this.productFile.wish.wishproductpk
      this.dislike(wishproductpk)
        .then(()=>{
        this.productFile.wish.flag = false
      })
    }
  },
  async created() {
    const userid = this.userId.id
    const productpk = this.$route.params.product_pk
    const data = {
      'userid': userid,
      'productpk': productpk,
    }
    this.productDetail(data)
    .then(()=>{
      for (let idx = 0; idx < this.productFile.images.length; idx++) {
        this.thumbnail.push(this.productFile.images[idx].filePath)
        }
      })
      .then(()=>{
        var time = this.productFile.images[0].product.createdAt
        const today = new Date();
        const timeValue = new Date(time);
        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60)
        if (betweenTime < 1) return  this.createTime = '방금전'
        if (betweenTime < 60) {
          return this.createTime = `${betweenTime}분전`
        }
        const betweenTimeHour = Math.floor(betweenTime / 60)
        if (betweenTimeHour < 24) {
          return this.createTime = `${betweenTimeHour}시간전`
        }
        const betweenTimeDay = Math.floor(betweenTime / 60 / 24)
        if (betweenTimeDay < 365) {
          return this.createTime = `${betweenTimeDay}일전`
        }
        return  this.createTime = `${Math.floor(betweenTimeDay / 365)}년전`
      })
  },
}
</script>

<style scoped>
.content {
  margin-top: 50px;
  padding: 20px;
}
.content-title {
  padding: 30px 30px 10px 30px;
  display: flex;
  justify-content: space-between;
  border-bottom: 1.5px solid #e9ecef;
}
.content-title > p {
 font-size: 35px;
 line-height: 2;
 letter-spacing: -0.6px;
 font-weight: 450;
}
.content-sub {
  color: grey;
  font-size: 1.2rem;
  margin-bottom: 20px;
}
.live-text {
  text-align: center;
  display:inline-block;
  margin-right: 20px;  
  margin-left: 10px;
  font-weight: bold;
}
.content-content {
  padding: 30px;
  border-bottom: 1.5px solid #e9ecef;
}
.content-sold {
  padding-left: 30px;
  margin-bottom: 0px;
  font-size: 1.2rem;
}
.userinfo {
  font-size: 1.3rem;
  padding-bottom: 8px;
  padding-left: 40px;
}
.btn-o {
  border-radius: 38.5px;
  padding: 0px 17px;
  line-height: 45px;
  font-weight: 450;
  font-size: 1rem;
}
.img-box {
  width: 100%;
  max-height: 700px;
  overflow: hidden;
  display: flex;
  justify-content: center;
}
.img-size {
  max-width:810px;
  overflow: hidden;
  align-items:center; 
  text-align:center;
}
</style>