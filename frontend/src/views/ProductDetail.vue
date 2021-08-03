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
        <div>
          <router-link :to="{name: 'ProfilePage', params: {userid: productFile.userId}}">닉네임들어갈곳</router-link>
        </div>
        <div class="content-title"> 
          <h3>{{ productFile.title }}</h3>
        </div>
        
        <div class="content-sub">
          {{this.category[productFile.categoryId]}} /
          게시 시간: <time>{{createTime[0]}}</time>
        </div>
        <div> 
          <h5>가격:  {{ productFile.price }}원 </h5>
        </div>
          <div>제품 설명:   {{ productFile.description }} </div>
      </div>
      <section>
        <br>
        <!-- 라이브 유무 버튼 -->
        <div v-if="productFile.isLive == 0">
          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-off" viewBox="0 0 16 16">
            <path d="M11 4a4 4 0 0 1 0 8H8a4.992 4.992 0 0 0 2-4 4.992 4.992 0 0 0-2-4h3zm-6 8a4 4 0 1 1 0-8 4 4 0 0 1 0 8zM0 8a5 5 0 0 0 5 5h6a5 5 0 0 0 0-10H5a5 5 0 0 0-5 5z"/>
          </svg>
          <p>Live중이 아닙니다</p>
          <div v-if="productFile.userId == this.userId.id"  >
            <router-link :to="{name: 'LiveRegister'}">방송시작하기</router-link>
            <button>수정</button>
            <button>삭제</button>
          </div>
          
          <!-- <button @clickproductpk>방송 시작하기(pk보내기)</button> -->
          <br>        
        </div>

        <div v-else>
          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-toggle-on" viewBox="0 0 16 16" style="color: green">
            <path d="M5 3a5 5 0 0 0 0 10h6a5 5 0 0 0 0-10H5zm6 9a4 4 0 1 1 0-8 4 4 0 0 1 0 8z"/>
          </svg>
          <p>Live중입니다</p>
          <router-link :to="{name: 'LivePage', params: { id: productFile.id }}">라이브 방송 시청</router-link>
          <br>
        </div>  
      </section>

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
          id: JSON.parse(localStorage.getItem('userInfo')).id,
          nickname:JSON.parse(localStorage.getItem('userInfo')).nickname,
        },
        createTime: [],
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
      var time = this.productFile.createdAt
      let arr = time.split(' ');
      let date = arr[0].split('-').reverse().join('-');
      let tmArr = arr[1].split('.');
      let tm = tmArr[0]

      this.createTime = [date, tm]
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
}
.content > div {
  margin-bottom: 30px;
}
/* .content-title {
  margin-bottom: 30px;

}*/

.content-sub {
  color: grey;
}
</style>