<template>
<div>
  <div class="index-left">
    <HomeNav />
  </div>
  <div class="index-mid">
    <HomeSwiper />
  </div>
</div>
</template>

<script>
import HomeNav from './Homepage/HomeNav.vue'
import HomeSwiper from './Homepage/HomeSwiper.vue'

export default {
    name:'Home',
    components:{
      HomeNav,
      HomeSwiper}
}
</script>

<style scoped>
.index-left{
  float: left;
  width:300px;
}
.index-mid{
  width: 1200px;
}
</style>