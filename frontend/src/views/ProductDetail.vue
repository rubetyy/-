<template>
  <div>
    <h1 id="header">제품 상세</h1>
        {{this.userId.id}}
        {{this.userId.nickname}}
    <br>
    {{ productFile }}
    {{ productFile.images }}
<!-- ../../../backend/images/20210727/865456997892800.png -->


    <div>
      <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner" style="width:80%; height:500px; margin:auto; ">
          <div class="carousel-item active">
            <img src="../../../backend/images/20210727/865456997892800.png" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="../../../backend/images/20210802/고양이.jpg" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="../../../backend/images/20210802/캡처.jpg" class="d-block w-100" alt="...">
          </div>
        </div>
        <button class="carousel-control-prev btn-o" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span class="carousel-control-next-icon" style="color: red;" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>


      <div class="content">
  
          <div style="font-size: 20px;  padding: 30px;">
            <router-link :to="{name: 'ProfilePage', params: {userid: productFile.userId}}">닉네임들어갈곳</router-link>
          </div>
          <div class="content-title"> 
            <p>{{ productFile.title }}</p>
            <section>
              <!-- 라이브 유무 버튼 -->
              <div v-if="productFile.isLive == 0">
                <p class="live-text">Live중이 아닙니다</p>
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-off" viewBox="0 0 16 16">
                  <path d="M11 4a4 4 0 0 1 0 8H8a4.992 4.992 0 0 0 2-4 4.992 4.992 0 0 0-2-4h3zm-6 8a4 4 0 1 1 0-8 4 4 0 0 1 0 8zM0 8a5 5 0 0 0 5 5h6a5 5 0 0 0 0-10H5a5 5 0 0 0-5 5z"/>
                </svg>

                <div v-if="productFile.userId == this.userId.id">
                  <button class="btn-o">
                    <router-link :to="{name: 'LiveRegister'}">방송시작하기</router-link>
                  </button>
                  <button class="btn-o">수정</button>
                  <button class="btn-o">삭제</button>
                </div>
                
                <!-- <button @clickproductpk>방송 시작하기(pk보내기)</button> -->
                <br>        
              </div>

              <div v-else>
                <p>Live중입니다</p>
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-on" viewBox="0 0 16 16" style="color: green">
                  <path d="M5 3a5 5 0 0 0 0 10h6a5 5 0 0 0 0-10H5zm6 9a4 4 0 1 1 0-8 4 4 0 0 1 0 8z"/>
                </svg>
                <router-link :to="{name: 'LivePage', params: { id: productFile.id }}">라이브 방송 시청</router-link>
                <br>
              </div>  
            </section>
          </div>

          
        <div class="content-content">
          <div class="content-sub">
            {{this.category[productFile.categoryId]}} /
            <time>{{createTime}}</time>
          </div>
          <div> 
            <p style="font-size: 20px; margin-bottom:70px;">가격: {{ productFile.price }}원</p>
          </div>
          <div style="font-size: 25px; ">제품 설명: {{ productFile.description }} </div>
        </div>
      </div>
      

    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
const productStore = 'productStore'

export default {
  name: 'ProductDetail',
  data() {
      return {
        slide: 0,
        sliding: null,
        thumbnail: [],
        userId: {
          id: '',
          nickname:JSON.parse(localStorage.getItem('userInfo')).nickname,
        },
        createTime: '',
        category: {
          '1' : '의류',
          '2' : '음식',
          '3' : '전자제품',
          '4' : '기타',
        }
      }
    },
  computed: {
    ...mapGetters(productStore,[
      'getProductDetailFile'
    ]),
    productFile: function() {
      return this.getProductDetailFile
    },
    image() {
      return this.productFile.thumbnail
    },

  },

  methods: {
    ...mapActions(productStore,[
    'productDetail',
    ]),
  },
  async created() {
    this.productDetail(this.$route.params.product_pk)
    .then(()=>{
      this.userId.id = this.productFile.userId
      })
      .then(()=>{
        var time = this.productFile.createdAt
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