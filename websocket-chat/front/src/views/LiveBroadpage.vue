<template>
  <div>
    <h1>LiveBroadpage.vue</h1>
    <hr>

    <!-- 실시간 채팅 컴포넌트 -->
    <LiveChat />


  </div>
</template>

<script>
import LiveChat from '@/components/LiveBroadPage/LiveChat'

export default {
  name: 'LiveBroadpage',
  components: {
    LiveChat
  },
  data: function () {
    return {

    }
  },
  methods: {

  },

}
</script>

<style scoped>

</style>
