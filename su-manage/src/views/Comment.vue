<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/community' }">论坛管理</el-breadcrumb-item>
    <el-breadcrumb-item>评论</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table :data="tableData" border style="width: 100%;margin-top:20px" >
      <el-table-column
          fixed
          prop="com.id"
          label="id"
          width="50">
       </el-table-column>
       <el-table-column
          prop="user.username"
          label="用户名"
          width="70">
       </el-table-column>
       <el-table-column
          prop="com.count"
          label="内容"
          width="450">
       </el-table-column>
       <el-table-column
         label="操作"
         width="100">
        <template slot-scope="scope">
         <el-button type="text" size="small" @click.native.prevent="deleteRow(scope.row.com.id)">删除</el-button>
        </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      tableData:[]
    }
  },
  created(){
    this.CommentId = this.$route.params.id;
    this.fetchdata()
  },
  methods:{
        fetchdata(){
        this.request.get("comment/one/"+this.CommentId).then(
          response => {
            console.log(response)
            this.tableData = response;
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
        );
       },
       deleteRow(id){
        console.log(id)
        this.request.delete("/comment/"+id).then(res => {
          console.log(res)
        if (res.code === 200) {
          this.$message.success("删除成功")
        } else {
          this.$message.error("删除失败")
        }
        })
       }
    }
}
</script>

<style>

</style>