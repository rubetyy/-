<template>
  <div>
    <!-- <div>
        <img src="../../../backend/images/20210723/498640179290400.png" alt="">
    </div> -->

    <div>
      <label for="file"  style="display:block" >
        <div class="img-box">

          <div v-if = "!files3.length">
            <p style="font-size:25px;">No images</p>
          </div>

          <div v-else>
            <!-- <div v-for="(file,idx) in filesPreview" :key="idx"> -->
              <!-- {{file}} -->
              <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div v-if="this.filesPreview.length === 1" class="carousel-inner" style="width:80%; height:500px; margin:auto; ">
                  <div v-for="(file,idx) in filesPreview" :key="idx" class="carousel-item" :class="checkActive"> 
                    <img :src="file" class="d-block w-100" alt="...">
                  </div>
                </div>
                <div v-else class="carousel-inner" style="width:80%; height:500px; margin:auto; ">
                  <div v-for="(file,idx) in filesPreview" :key="idx" class="carousel-item" :class="checkActive"> 
                    <img :src="filesPreview[idx]" class="d-block w-100" alt="...">
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
              <!-- <img :src="file" alt=""> -->
            </div>
          <!-- </div> -->
          
        </div>
        </label>
      <input type="file" id="file" multiple="multiple" accept=".gif, .jpg, .png" @change="upload" >
    </div>

    <div class="form-box product-register-form">
        <h1 id="header">상품 등록</h1>
        <div class= "image-container">
            <div v-if = "!files.length">
                <div class="input-group">
                <input type="file" class="form-control" id="file" name="images" ref ='files' aria-describedby="inputGroupFileAddon04" aria-label="Upload"  @change="imageUpload2"  multiple="multiple" >
                </div>
            </div>

            <div v-else>
                <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                    <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                        <h5>이미지 미리보기 x</h5>
                    </div>
                    <img :src="file.preview" style="width:50%"/>
                </div>
                <div class="image-box">
                    <label for="file">추가 사진 등록</label>
                    <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                </div>
            </div>
            <button style="float: right;" class="btn btn-outline-secondary btn-g" type="button" id="file" v-on:click='thumbnailupload'>이미지등록</button>
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
import { mapActions, mapGetters } from 'vuex'

const productStore = 'productStore'
const userStore = 'userStore'


export default {
  name: 'ProductRegister',
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
          
      }
  },

  computed:{
      ...mapGetters(userStore,{
          userInfo: 'getUserInfo'
      }),
      image() {
        return `${this.files3[0].name}`
    },

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
          console.log(this.productFile)
          //이때 다시 받아와라?
          this.$router.push({name:"ProductDetail" , params: { product_pk: this.productFile.id}})
        })
      },

      thumbnailupload() {
        console.log(this.files2)
        this.productFile.images = this.files2
      },


      imageUpload2(e){
        const filearray = e.target.files[0];
        this.files2.push(filearray)

        let num = -1;
        for (let i = 0; i < this.$refs.files.files.length; i++){
            this.files = [
                ...this.files,
                {
                    //실제 파일
                    file: this.$refs.files.files[i],

                    //이미지 프리뷰
                    preview: URL.createObjectURL(this.$refs.files.files[i]),
                   
                    //삭제및 관리를 위한 number
                    number: i
                }
            ];
            num = i;
            this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장

        }


      },

      imageAddUpload(e) {
        let filearray = e.target.files[0];
        this.files2.push(filearray)
 
          let num = -1;
          for (let i = 0; i < this.$refs.files.files.length; i++) {
              this.files = [
                  ...this.files,
                  //이미지 업로드
                  {
                      //실제 파일
                      file: this.$refs.files.files[i],
                      //이미지 프리뷰
                      preview: URL.createObjectURL(this.$refs.files.files[i]),
                      //삭제및 관리를 위한 number
                      number: i + this.uploadImageIndex
                  }
              ];
              num = i;
          }
          this.uploadImageIndex = this.uploadImageIndex + num + 1;
      },

      fileDeleteButton(e) {
          const name = e.target.getAttribute('name');
          this.files = this.files.filter(data => data.number !== Number(name));
      },
      ///////////
      upload(e) {
 

        // console.log(e.target.files)
        this.files3.push(e.target.files[0])
        // console.log(this.files3)

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


      }
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
}
input, select{
  padding: 15px;
  font-size: 20px;
  border-radius: 5px;
  border: 1px solid lightgrey;
}

.img-box{
  width: 400px;
  height: 300px;
  border: 3px solid grey; ;
  border-radius: 4px ;
  text-align: center;

}

</style>