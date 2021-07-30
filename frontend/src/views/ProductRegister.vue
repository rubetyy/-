<template>
  <div>
    <h1>ProductRegister</h1>

    <!-- <div>
        <img src="../../../backend/images/20210723/498640179290400.png" alt="">
    </div> -->

    <div class="main-container">
        <br>
            <!-- display: flex; -->
            <!-- flex-wrap: wrap; -->
    이미지 등록 다른방법1
<!-- <form action="" method="post" enctype="multipart/form-data"> -->
        <div class= "image-container">
            <div v-if = "!files.length">
                <div class="input-group">
                <input type="file" class="form-control" id="file" name="images" ref ='files' aria-describedby="inputGroupFileAddon04" aria-label="Upload"  @change="imageUpload2"  multiple="multiple" >
                </div>
            </div>

            <div v-else>
                <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                    <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                        x
                        <h5>이미지 미리보기</h5>
                    </div>
                    <img :src="file.preview" />
                </div>
                <div class="image-box">
                    <label for="file">추가 사진 등록</label>
                    <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                </div>
            </div>
            <button class="btn btn-outline-secondary" type="button" id="file" v-on:click='thumbnailupload'>이미지등록</button>
        </div>




  <br>

  <!-- <div>
    이미지 등록 다른방법2
    <form method="post" enctype="multipart/form-data">
      <div>
        <label for="image_uploads" >Choose images to upload (PNG, JPG)</label>
        <input type="file" id="image_uploads" name="image_uploads" accept=".jpg, .jpeg, .png" multiple >
      </div>
      <div class="preview">
        <p>No files currently selected for upload</p>
      </div>
      <div>
        <button>Submit</button>
      </div>
    </form>
    <br>
  </div> -->

<!-- {{userInfo}} -->
  <div>제목
    <div class="input-group mb-3">
      <input type="text" class="form-control"  aria-describedby="button-addon2" v-model='productFile.title'>
      <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
    </div>
    <br>
  </div>

  <div>
    카테고리
    <!-- <b-form-select
      v-model="productFile.category"
      :options="options"
      class="mb-3"
      value-field="item"
      text-field="name"
      disabled-field="notEnabled"
    ></b-form-select> -->


    <select class="form-select" aria-label="Default select example" v-model="productFile.category">
      <option value="1">의류</option>
      <option value="2">음식</option>
      <option value="3">전자제품</option>
      <option value="4">기타</option>
    </select>

    <div class="mt-3">선택된 카테고리: <strong>{{ productFile.category }}</strong></div>
    <br>
  </div>

  <div>
    내용
    <div class="form-floating">
      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" v-model="productFile.description"></textarea>
      <label for="floatingTextarea2">제품설명</label>
    </div>  
    <br>
  </div>

  <div>
    <h5>가격</h5>
    <div class="input-group">
      <input type="text" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" v-model='productFile.price'>
      <span class="input-group-text">\</span>
      <span class="input-group-text">{{productFile.price}}</span>
    </div>
    <br>
  </div>
    <!-- <input type="submit" id="submit" value="전송" v-on:click='register'/> -->

  <button v-on:click='registerClick'>등록</button>

<!-- </form> -->
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
             
          }
      }
  },

  computed:{
      ...mapGetters(userStore,{
          userInfo: 'getUserInfo'
      }),
    },
      
  methods: {
      ...mapActions(productStore,[
          'register',
      ]),
      registerClick() { //등록버튼
        this.productFile.user_id = this.userInfo
        // console.log(this.productFile.user_id)
        this.register(this.productFile)
        .then(()=>{
            this.$router.push({name:"ProductDetail"})
        })


      },

      thumbnailupload() {
        //   for (let i = 0; i < this.$refs.files2.files.length; i++){
        //       this.productFile.images = this.files2[i].preview
        //       console.log(typeof(this.productFile.images))
        //   }
        console.log(this.files2)
        this.productFile.images = this.files2
        // console.log(this.productFile.images)
        //   for (let i = 0; i < (this.files2.length) +1; i++){
        //       this.productFile.images = this.files2[i]
        //       console.log(this.productFile.images)
        //   }
      },


      imageUpload2(e){
        const filearray = e.target.files[0];
        this.files2.push(filearray)
        // let filearray = e.target.files[0]
        // this.files2 = filearray
        // console.log(this.$refs.files.files)
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
        //   console.log(this.$refs.files.files);
        // console.log(e.target.files)
        // for (let i = 0; i < e.target.files.length; i++) {
        //     console.log(e.target.files[i])
        let filearray = e.target.files[0];
        console.log(this.files2)
        console.log(typeof(this.files2))

        this.files2.push(filearray)
        

          // this.files = [...this.files, this.$refs.files.files];
          //하나의 배열로 넣기c
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
          // console.log(this.filesPreview);
      },

      fileDeleteButton(e) {
          const name = e.target.getAttribute('name');
          this.files = this.files.filter(data => data.number !== Number(name));
      },
  }  
}
</script>

<style scoped>
    .main-container {
                width: 800px;
                height: 400px;
                margin: 100px auto;
            }
            
    .room-deal-information-container {
        margin-top: 50px;
        color: #222222;
        border: 1px solid #dddddd;
    }

    .room-deal-information-title {
        text-align: center;
        font-size: 18px;
        line-height: 60px;
        border-bottom: 1px solid #dddddd;
    }

    .room-deal-information-content-wrapper {
        min-height: 50px;
        display: flex;
    }

    .room-deal-informtaion-content-title {
        font-size: 15px;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 150px;
        background-color: #f9f9f9;
    }

    .room-deal-information-content {
        width: 100%;
        font-size: 14px;
    }

    .room-deal-option-selector {
        display: flex;
        align-items: center;
        padding: 15px;
    }

    .room-deal-option-item {
        width: 100px;
        height: 40px;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid #cccccc;
        border-radius: 5px;
        cursor: pointer;
    }

    .room-deal-option-item:last-child {
        margin-left: 10px;
    }

    .room-deal-option-notice {
        margin-left: auto;
        font-size: 14px;
        color: #888888;
    }

    .room-deal-option-item-deposit {
        margin-left: 10px;
    }

    .room-deal-information-wrapper {
        display: flex;
        flex-direction: column;
    }

    .room-deal-information-option {
        padding: 10px;
        display: flex;
        align-items: center;
    }

    .room-deal-information-option:last-child {
        border-bottom: 1px solid #dddddd;
    }

    .room-deal-information-item-type {
        font-size: 13px;
        color: #fff;
        background-color: #61b6e5;
        min-width: 50px;
        height: 26px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 3px;
    }

    .room-deal-information-item-wrapper {
        display: flex;
        align-items: center;
        margin-left: 10px;
        height: 46px;
        width: 100%;
    }

    .room-deal-information-item-wrapper>input {
        border: 1px solid #dddddd;
        width: 140px;
        height: 100%;
        padding: 0 15px;
        font-size: 15px;
    }

    .room-deal-inforamtion-won {
        margin: 0 10px;
    }

    .room-deal-information-example {
        color: #888888;
    }

    .room-deal-information-option:not(:first-child) {
        margin-top: 10px;
    }

    .room-deal-inforamtion-divide {
        font-size: 22px;
        margin: 0 8px;
        color: #222222;
        font-weight: 100;
    }

    .room-deal-close-button-wrapper {
        margin-left: auto;
        cursor: pointer;
    }

    .room-deal-close-button {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 22px;
        height: 22px;
        background-color: #666666;
        color: rgb(220, 220, 220);
    }

    .room-deal-cliked {
        background-color: rgb(235, 235, 235);
        color: rgb(170, 170, 170);
    }

    .room-file-upload-example {
        height: 100%;
    }

    .room-write-content-container {
        border-top: 1px solid #dddddd;
        min-height: 260px;
    }

    .room-picture-notice {
        margin: 20px;
        padding: 20px 40px;
        border: 1px solid #dddddd;
    }

    .file-preview-content-container {
        height: 100%;
    }

    .room-file-upload-wrapper {
        margin: 20px;
        border: 1px solid #dddddd;
        background-color: #f4f4f4;
        min-height: 350px;
        font-size: 15px;
        color: #888888;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
    }

    .room-file-upload-example-container {
        display: flex;
        align-items: center;
        justify-content: center;
        /* height: 100%;
    width: 100%; */
    }

    .room-file-image-example-wrapper {
        text-align: center;
    }

    .room-file-notice-item {
        margin-top: 5px;
        text-align: center;
    }

    .room-file-notice-item-red {
        color: #ef4351;
    }

    .image-box {
        margin-top: 30px;
        padding-bottom: 20px;
        text-align: center;
    }

    .image-box input[type='file'] {
        position: absolute;
        width: 0;
        height: 0;
        padding: 0;
        overflow: hidden;
        border: 0;
    }

    .image-box label {
        display: inline-block;
        padding: 10px 20px;
        background-color: #232d4a;
        color: #fff;
        vertical-align: middle;
        font-size: 15px;
        cursor: pointer;
        border-radius: 5px;
    }

    .file-preview-wrapper {
        padding: 10px;
        position: relative;
    }

    .file-preview-wrapper>img {
        position: relative;
        width: 190px;
        height: 130px;
        z-index: 10;
    }

    .file-close-button {
        position: absolute;
        /* align-items: center; */
        line-height: 18px;
        z-index: 99;
        font-size: 18px;
        right: 5px;
        top: 10px;
        color: #fff;
        font-weight: bold;
        background-color: #666666;
        width: 20px;
        height: 20px;
        text-align: center;
        cursor: pointer;
    }

    .file-preview-container {
        height: 100%;
        display: flex;
        flex-wrap: wrap;
    }

    .file-preview-wrapper-upload {
        margin: 10px;
        padding-top: 20px;
        background-color: #888888;
        width: 190px;
        height: 130px;
    }

    .room-write-button-wrapper {
        margin-top: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #222222;
    }

    .room-write-button-wrapper>div {
        width: 160px;
        height: 50px;
        border: 1px solid #dddddd;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 15px;
        cursor: pointer;
    }

    .room-write-button {
        margin-left: 15px;
        color: #fff;
        background-color: #1564f9;
    }

    .room-write-button:hover {
        opacity: 0.8;
    }
    .image-container{
        margin-bottom: 100pxs;
    }
</style>