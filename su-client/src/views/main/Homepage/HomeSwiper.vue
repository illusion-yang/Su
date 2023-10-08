<template>
  <div class="index">
    <el-carousel :interval="4000" type="card" height="300px">
    <el-carousel-item v-for="item in bannerImg" :key="item">
      <img class="swiperimg" :src="item">
    </el-carousel-item>
  </el-carousel>
  </div>
</template>

<script>
export default {
    name:'index',
    data(){
        return{
            bannerImg:[
                require("../../../assets/img/1.jpg"),
                require("../../../assets/img/2.jpg"),
                require("../../../assets/img/3.jpg"),
                require("../../../assets/img/4.jpg"),
                require("../../../assets/img/5.jpg"),
            ]
        }
    }
}
</script>

<style scoped>
.index{
  margin-top: 15px;
}

.swiperimg{
  width: 100%;
  height: 100%;
}
</style>