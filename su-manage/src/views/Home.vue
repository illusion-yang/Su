<template>
  <div>
    <HomeNav />
    <HeaderNav />
    <div class="content-box">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>

import HomeNav from "./Homepage/HomeNav.vue"
import HeaderNav from "../components/HeaderNav.vue"

export default {
components:{
  HomeNav,
  HeaderNav
}
}
</script>

<style scoped>
.content-box {
  position: absolute;
  left: 150px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
  transition: left 0.3s ease-in-out;
  padding: 20px;
}
</style>