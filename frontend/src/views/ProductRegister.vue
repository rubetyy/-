<template>
  <div>

    <!-- <div>
      <label for="file"  style="display:block" >
        <div class="img-box">

          <div v-if = "!files3.length">
            <p style="font-size:25px;">No images</p>
          </div>

          <div v-else >
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
              <Carousel v-for="(file,idx) in filesPreview" :key="idx" :file="file"/>
            </b-carousel>

          </div>
          
        </div>
        </label>
      <input type="file" id="file" multiple="multiple" accept=".gif, .jpg, .png" @change="upload" >
    </div> -->

    <div class="form-box product-register-form">
      <h1 id="header">상품 등록</h1>
        <div class= "image-container">
          <label for="file"  style="display:block" >
        <div class="img-box">

          <div v-if = "!files3.length">
            <p style="font-size:25px;">No images</p>
          </div>

          <div v-else >
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
              <Carousel v-for="(file,idx) in filesPreview" :key="idx" :file="file"/>
            </b-carousel>

          </div>
          
        </div>
        </label>
          <input type="file" id="file" multiple="multiple" accept=".gif, .jpg, .png" @change="upload" >
        </div>

  <br>
  <div>
    <div class="input-group mb-3">
      <input type="text" class="form-control"  aria-describedby="button-addon2" placeholder="제목" v-model='productFile.title'>
    </div>
    <br>
  </div>

  <div>
    <p>카테고리</p>
    <select class="form-select" aria-label="Default select example" placeholder="제목" v-model="productFile.category">
      <option value="1">의류</option>
      <option value="2">음식</option>
      <option value="3">전자제품</option>
      <option value="4">기타</option>
    </select>

    <div class="mt-3">선택된 카테고리: <strong>{{ productFile.category }}</strong></div>
    <br>
  </div>

  <div>
    
    <div class="form-floating">
      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" v-model="productFile.description"></textarea>
      <label for="floatingTextarea2">제품설명</label>
    </div>  
    <br>
  </div>

  <div>
  
    <div class="input-group">
      <input type="text" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" placeholder="가격" v-model='productFile.price'>
      <span class="input-group-text">{{productFile.price}}원</span>
    </div>
    <br>
  </div>

  <button class="btn-o" style="display: block; margin: auto; width:100px;" v-on:click='registerClick'>등록</button>
  </div>

</div>
</template>

<script>
import Carousel from '@/components/Carousel'

import { mapActions, mapGetters } from 'vuex'

const productStore = 'productStore'
const userStore = 'userStore'


export default {
  name: 'ProductRegister',

  components: {
    Carousel,
  },

  data() {
      return {
        selected: '선택',
        options: [
          { item: 1, name: '의류' },
          { item: 2, name: '음식' },
          { item: 3, name: '전자제품'},
          { item: 4, name: '기타' }
        ],
          files: [], //업로드용 파일
          files2:[], // 새로 이미지 담을 배열?
          files3:[], // 테스트용

          filesPreview: [],
          uploadImageIndex: 0, // 이미지 업로드를 위한 변수

          productFile: {
              images: '',
              title: '',
              category: '',
              description: '',
              price: '',
              is_sold: '',
              live_status: '',
              user_id: ''
             
          },
          checkActive: 'active',
          slide: 0,
          sliding: null
          
      }
  },

  computed:{
      ...mapGetters(userStore,{
          userInfo: 'getUserInfo'
      }),
      image() {
        return `${this.files3[0].name}`
    },
    ...mapGetters(productStore,{
      productInfo: 'getProductFile'
    })

    },
      
  methods: {
      ...mapActions(productStore,[
          'register',
      ]),
      registerClick() { //등록버튼
        console.log(JSON.parse(localStorage.getItem('userInfo')).id,'userId')
        this.productFile.user_id = JSON.parse(localStorage.getItem('userInfo')).id
        // this.productFile.user_id = this.userInfo
        this.register(this.productFile)
        .then(()=>{
          console.log(this.productFile,'detail보내기전')
          //이때 다시 받아와라? store에서 id값 가져올수있나?
          console.log(this.productInfo)
          const product_pk = this.productInfo.id
          this.$router.push({name:"ProductDetail" , params: { product_pk: product_pk}})
        })
      },
      fileDeleteButton(e) {
          const name = e.target.getAttribute('name');
          this.files = this.files.filter(data => data.number !== Number(name));
      },
      ///////////
      upload(e) {
        // console.log(e.target.files)
        this.files3.push(e.target.files[0])
        this.productFile.images = this.files3
        console.log(this.productFile.images,'이미지확인')
        const objectURL = window.URL.createObjectURL(e.target.files[0])
        // console.log(objectURL)
        this.filesPreview.push(objectURL)
        // console.log(this.files3.length,'files3')
        console.log(this.filesPreview.length,'filesPreview')

        // 1. filesPreview 길이가 1 이상이 되면 클래스를 비워줌
        if (this.filesPreview.length > 1) {
          this.checkActive = null
          console.log(this.checkActive,'ca')
        }
        // 2. watch로 변환하는거 추적
      },


      onSlideStart() {
        this.sliding = true
      },
      onSlideEnd() {
        this.sliding = false
      },
  }  
}
</script>

<style scoped>
.product-register-form {
  padding: 40px;;
  border-radius: 15px;
}

.image-container {
  margin-top: 40px;
  margin-bottom: 40px;
  height: 500px;
}
input, select{
  padding: 15px;
  font-size: 20px;
  border-radius: 5px;
  border: 1px solid lightgrey;
}

.img-box{
  width: 100%;
  height: 100%;
  border: 3px solid grey; ;
  border-radius: 4px ;
  text-align: center;

}

</style>