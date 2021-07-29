<template>
  <div>
    <h1>ProductDetail</h1>
    {{ productFile }}
    {{ productFile.images }}

    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" class="btn btn-primary">Primary</button>

        <button type="button"  class="btn btn-primary" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="../../../backend/images/20210727/865456997892800.png" class="d-block w-50" alt="">
        <div class="carousel-caption d-none d-md-block">
          <h5>First slide label</h5>
          <p>Some representative placeholder content for the first slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="#" class="d-block w-50" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>Second slide label</h5>
          <p>Some representative placeholder content for the second slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="#" class="d-block w-50" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>Third slide label</h5>
          <p>Some representative placeholder content for the third slide.</p>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev btn btn-info" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next btn btn-info" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
    <!-- <div>
      <p class="mt-4">
        Slide #: {{ slide }}<br>
        Sliding: {{ sliding }}
      </p>     
    </div> -->
    <div>제목  
      <h3>{{ productFile.title }}</h3>
    </div>
    <div>설명  {{ productFile.description }} </div>
    <div>상품 카테고리  {{ productFile.categoryId }} </div>
    <div>가격  {{ productFile.price }} </div>
    <div>
      <button>수정</button>
      <button>삭제</button>
    </div>
    <div class="container justify-content: center; flex-direction: column">
      <section>
        <p>케로셀 이미지</p>
        <input type="checkbox" id="checkbox" checked class="inputbox">
        <label for="checkbox" class="labelbox"><span class="spanbox"></span></label>
      </section>
      <section>프로필</section>
      <section>
        <h5 style="margin-top:0px">{{ productFile.title }}</h5>
        <p>{{productFile.categoryId}} <time>{{createTime[0]}}</time> </p>
        <div>
          <p class="margin-top: 16px; margin-bottom: 16px">{{productFile.description}}></p>
          <p>{{productFile.price}}</p>
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
        thumbnail: []
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
      // console.log(this.productFile)
      return this.productFile.thumbnail
    },
    createTime() {
      var time = this.productFile.createdAt
      let arr = time.split(' ');
      console.log(arr)
      let date = arr[0].split('-').reverse().join('-');
      let tmArr = arr[1].split('.');
      let tm = tmArr[0]
      console.log(date)
      console.log(tm)
      return [date, tm]
    }
  },

  methods: {
    // onSlideStart() {
    //   this.sliding = true
    // },
    // onSlideEnd() {
    //   this.sliding = false
    // },
    ...mapActions(productStore,[
    'productDetail',
    ]),
  },
  async created() {
    
    this.productDetail()
    console.log('dd')
    },
}
</script>

<style scoped>
.inputbox[type="checkbox"] {
    position: absolute;
    visibility: hidden;
}
.labelbox {
    display: block;
    position: absolute;
    width: 60px;
    height: 34px;
    border-radius: 17px;
    background-color: #ddd;
    transition-duration: 0.2s;
}
.spanbox {
    position: absolute;
    left: 3px;
    top: 3px;
    z-index: 1;
    width: 28px;
    height: 28px;
    border-radius: 50%;
    background-color: #fff;
    transition-duration: 0.2s;
}
.labelbox:before,
.labelbox:after{
    position: absolute;
    top: 0;
    width: 34px;
    font-size: 11px;
    line-height: 34px;
    color: #fff;
    text-align: center;
}

.labelbox:before {
    left: 0;
    content: 'ON';
}

.labelbox:after {
    right: 0;
    content: 'OFF';
}

.inputbox:checked + label {
    background-color: #00c73c;
}

.inputbox:checked + label span {
    transform: translateX(26px);
}
</style>