<template>
  <div>
    <h1 id="header">제품 상세</h1>
        <!-- {{this.userId.id}}
        {{this.userId.nickname}} -->
    <br>
    <!-- {{ this.thumbnail }} -->
    <!-- {{ productFile.images }}  -->
    <div>
    <div>
        <div class="img-box">


          <div >
            <b-carousel
              id="carousel-1"
              v-model="slide"
              :interval="4000"
              controls
              indicators
              background="#ababab"
              img-width="1024"
              img-height="480"
              style="text-shadow: 1px 1px 2px #333;"
              @sliding-start="onSlideStart"
              @sliding-end="onSlideEnd"
            >
              <Carousel v-for="(file,idx) in thumbnail" :key="idx" :file="file"/>
            </b-carousel>

          </div>
          
        </div>
    </div>

      <div v-if="productFile" class="content">
  
          <div style="font-size: 20px;  padding: 30px;">
            <router-link :to="{name: 'ProfilePage', params: {userid: productFile.images[0].product.userId}}">{{productFile.usernickname}}</router-link>
          </div>
          <div class="content-title"> 
            <p>{{ productFile.images[0].product.title }}</p>

            <section>
              <!-- 라이브 유무 버튼 -->
              <div v-if="productFile.images[0].product.isLive == 0">
                <p class="live-text">Live중이 아닙니다</p>
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-off" viewBox="0 0 16 16">
                  <path d="M11 4a4 4 0 0 1 0 8H8a4.992 4.992 0 0 0 2-4 4.992 4.992 0 0 0-2-4h3zm-6 8a4 4 0 1 1 0-8 4 4 0 0 1 0 8zM0 8a5 5 0 0 0 5 5h6a5 5 0 0 0 0-10H5a5 5 0 0 0-5 5z"/>
                </svg>

                <div v-if="productFile.images[0].product.userId == this.userId.id">
                  <button class="btn-o" @click="makelive">방송 시작하기</button>
                  <!-- <button class="btn-o">
                    <router-link :to="{name: 'LiveRegister'}">방송시작하기</router-link>
                  </button> -->
                  <button class="btn-o">수정</button>
                  <button class="btn-o">삭제</button>
                </div>
                
                <br>        
              </div>

              <div v-else>
                <p>Live중입니다</p>
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-on" viewBox="0 0 16 16" style="color: green">
                  <path d="M5 3a5 5 0 0 0 0 10h6a5 5 0 0 0 0-10H5zm6 9a4 4 0 1 1 0-8 4 4 0 0 1 0 8z"/>
                </svg>
                <router-link :to="{name: 'LivePage', params: { id: productFile.images[0].product.liveId }}">라이브 방송 시청</router-link>
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
            <p style="font-size: 20px; margin-bottom:70px;">가격: {{ productFile.images[0].product.price }}원</p>
          </div>
          <div style="font-size: 25px; ">제품 설명: {{ productFile.images[0].product.description }} </div>
        </div>
      </div>
      

    </div>
  </div>
</template>

<script>
import Carousel from '@/components/Carousel'

import { mapActions, mapGetters } from 'vuex'
const productStore = 'productStore'
const liveStore = 'liveStore'

export default {
  name: 'ProductDetail',
  components: {
    Carousel,
  },
  data() {
      return {
        slide: 0,
        sliding: null,
        thumbnail: [],
        userId: {
          id: '',
          nickname: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).nickname : null,

        },
        createTime: '',
        category: {
          '1' : '의류',
          '2' : '음식',
          '3' : '전자제품',
          '4' : '기타',
        },
      }
    },
  computed: {
    ...mapGetters(productStore,[
      'getProductDetailFile'
    ]),
    productFile: function() {
      return this.getProductDetailFile
    },
    // image() {
    //   for (let idx = 0; idx < array.length; idx++) {
    //     let images = this.productFile.images[idx].filePath
        
    //   }
    //     return images
    // },

  },

  methods: {
    ...mapActions(productStore,[
    'productDetail',
    ]),
    ...mapActions(liveStore, ['makeLive']),
    makelive: function () {
      const data = {
        productPk: this.productFile.images[0].product.id,
        productTitle: this.productFile.images[0].product.title,
      }
      this.makeLive(data)
      this.$router.push({ name: 'LiveRegister' })
    },
      onSlideStart() {
        this.sliding = true
      },
      onSlideEnd() {
        this.sliding = false
      },
  },


  async created() {
    this.productDetail(this.$route.params.product_pk)
    .then(()=>{
      this.userId.id = this.productFile.images[0].product.userId
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
#carouselExampleCaptions {
  height: 100px;
  position: relative;
}
.content {
  margin-top: 50px;
  padding: 20px;;
  /* display: flex; */
}
.content > div {
  margin-bottom: 30px;
}
.content-title {
  padding: 30px;
  display: flex;
  justify-content: space-between;
  border-bottom: 1.5px solid #e9ecef;
}
.content-title > p {
 font-size: 40px;
 line-height: 1.6;
 letter-spacing: -0.6px;
}

.content-sub {
  color: grey;
  font-size: 20px;
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
</style>