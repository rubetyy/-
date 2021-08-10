<template>
  <div class='container'>
    <h1 id='header'>
      {{ this.receiver }}님이랑 나눈 대화
    </h1>
  </div>
</template>

<script>
export default {
  name: 'ChatRoom',
  data() {
    return {
      receiver: this.$route.params.userid,
      sender: '',
      message: '',
      messages: [],
      roomId: '',
    }
  },
  created() {
    
  }
}
</script>

<style>

</style>