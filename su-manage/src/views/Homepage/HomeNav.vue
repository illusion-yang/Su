<template>
  <div class="header">
    <h3>素材网后台管理</h3>
    <div class="a">
       <span class="user-name">{{userName}}</span>
       <el-button @click.native="logoutHandle">退出</el-button>
    </div>
  </div>
  
</template>

<script>
export default {
  computed:{
      userName(){
        return localStorage.getItem('username')
      }
    },
  methods:{
      logoutHandle(){
        localStorage.removeItem("token")
        localStorage.removeItem("username")
        this.$router.push("/login")
      }
    }
}
</script>

<style scoped>
.header {
  position: absolute;
  z-index: 100;
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  font-size: 20px;
  color: #2c3e50;
  background: #ffff;
  box-shadow: 0px 0px 8px 2px rgba(0, 0, 0, 0.3);
}
.a{
  position: absolute;
  right: 0;
  padding-right: 30px;
}

.user-name{
    color: #1f56e1;
    margin-right: 30px;
    font-size: 15px;
    /* border: 1px solid #1f56e1; 
    border-radius: 50%; */
    width: 40px;
    height: 40px;
    display: inline-block;
    line-height: 40px;
  }
</style>