<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
        <span>热点日历</span>
    </div>
    <div v-for="(item,o) in list" :key="o" class="text item">
    <a :href="item.url">{{item.name}}</a>
  </div>
  </el-card>
</template>

<script>
export default {
    data(){
        return{
            list:[
            {
                id:1,
                name:"愚人节",
                url:"http://www.baidu.com",
            },
            {
                id:2,
                name:"清明节",
                url:"http://www.baidu.com",
            },
            {
                id:3,
                name:"劳动节",
                url:"http://www.baidu.com",
            },
            {
                id:4,
                name:"青年节",
                url:"http://www.baidu.com",
            }
        ]
        }
    }
}
</script>

<style scoped>
.item {
    margin-bottom: 18px;
}
.box-card {
    width: 260px;
    margin-top: 15px;
    margin-left: 15px;
  }
</style>