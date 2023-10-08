<template>
  <el-menu :default-active="active" mode="horizontal" router>
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/music">音效</el-menu-item>
    <el-menu-item index="/game">游戏</el-menu-item>
    <el-menu-item><el-input v-model="input" placeholder="请输入内容"></el-input>
    </el-menu-item>
    <el-menu-item class="user">
      <el-dropdown>
        <span class="user-name">疏影</span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="go">个人主页</el-dropdown-item>
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item @click.native="logoutHandle">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
    data(){
        return{
            active:"/",
            input:''
        }
    },
    methods:{
      logoutHandle(){
        localStorage.removeItem("token")
        this.$router.push("/login")
      },
      go(){
        this.$router.push("/consumer")
      }
    }
}
</script>

<style>
.user{
  float: right!important;
  margin-right: 20px;
  line-height: 60px;
}
.user-name{
    color: #1f56e1;
    margin-right: 10px;
    font-size: 15px;
    border: 1px solid #1f56e1;
    border-radius: 50%;
    width: 40px;
    height: 40px;
    display: inline-block;
    line-height: 40px;
  }
</style>