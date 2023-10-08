<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
    <el-breadcrumb-item>收藏</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table :data="tableData" border style="width: 100%;margin-top:20px">
        <el-table-column
          fixed
          prop="col.id"
          label="id"
          width="50">
       </el-table-column>
       <el-table-column
          prop="col.fileId"
          label="文件id"
          width="70">
       </el-table-column>
       <el-table-column
       prop="music.fileName"
          label="文件名"
          width="70">
       </el-table-column>
       <el-table-column
         label="操作"
         width="100">
        <template slot-scope="scope">
         <el-button type="text" size="small" @click.native.prevent="deleteRow(scope.row.id)">删除</el-button>
        </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from '@/utils/request';
export default {
    data(){
        return{
            CollectId:'',
            tableData:[],
        }
    },
    created(){
        this.CollectId = this.$route.params.id;
        this.fetchdata()
    },
    methods:{
        fetchdata(){
        this.request.get("collect/one/"+this.CollectId).then(
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
        
       }
    }
}
</script>

<style>

</style>