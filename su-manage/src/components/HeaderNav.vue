<template>
  <div class="sidebar">
    <el-menu 
      class="sidebar-el-menu"
      background-color="#ffffff"
      active-text-color="#30a4fc"
      default-active="2"
      router
      >
      <el-menu-item index="/" style="font-size:18px">
        <el-icon class="el-icon-pie-chart"></el-icon>
        <span>系统首页</span>
      </el-menu-item>
      <el-menu-item index="/user" style="font-size:18px">
        <el-icon class="el-icon-user"></el-icon>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/document" style="font-size:18px">
        <el-icon class="el-icon-folder"></el-icon>
        <span>资源分类</span>
      </el-menu-item>
      <el-menu-item index="/community" style="font-size:18px">
        <el-icon class="el-icon-chat-square"></el-icon>
        <span>论坛管理</span>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title" >
          <i class="el-icon-folder"></i>
          <span style="font-size:18px">资源管理</span>
        </template>
        <el-menu-item index="/music" style="font-size:18px">音效资源</el-menu-item>
        <el-menu-item index="/vedio" style="font-size:18px">影视资源</el-menu-item>
        <el-menu-item index="/game" style="font-size:18px">游戏资源</el-menu-item>
        <el-menu-item index="/model" style="font-size:18px">模型资源</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  data(){
    return{
      active:"/",
    }
  }
}
</script>

<style>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}

.sidebar > ul {
  height: 100%;
}

.sidebar-el-menu:not(.el-menu--collapse) {
  width: 150px;
}

</style>