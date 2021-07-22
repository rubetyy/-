<template>
  <div id="livepage">
    <h1>LiveBroadpage</h1>
    <el-row>
      <el-button icon="el-icon-search" circle></el-button>
      <el-button type="primary" icon="el-icon-edit" circle></el-button>
      <el-button type="success" icon="el-icon-check" circle></el-button>
      <el-button type="info" icon="el-icon-message" circle></el-button>
      <el-button type="warning" icon="el-icon-star-off" circle></el-button>
      <el-button type="danger" icon="el-icon-delete" circle></el-button>
    </el-row>

    <br><br><br>
    <el-button type="danger" round @click="getLive">방송정보 get</el-button>
    {{  }}


  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LiveBroadpage',
  // data: function () {
  //   return {

  //   }
  // },
  methods: {
    async getLive() {
      const url = 'https://874d0867-2828-4911-8e80-4e913dbf635d.mock.pstmn.io/live/1'  // 라이브방송 url
      const res = await axios.get(url)
      console.log(res)
    },
  }

}
</script>

<style scoped>
#livepage {
  text-align: center;
}
</style>