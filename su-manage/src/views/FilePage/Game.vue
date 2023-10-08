<template>
  <div>
    <div class="aa">
      <el-input v-model="input" placeholder="请输入内容"></el-input>
    </div>
    <el-table 
       :data="tableData"
       border
       style="width: 100%"
    >
       <el-table-column
          fixed
          prop="id"
          label="id"
          width="50">
       </el-table-column>
       <el-table-column
          prop="fileName"
          label="文件名"
          width="200">
       </el-table-column>
       <el-table-column
          prop="size"
          label="文件大小"
          width="100">
       </el-table-column>
       <el-table-column
          prop="type"
          label="文件类型"
          width="100">
       </el-table-column>
       <el-table-column
          prop="downcounts"
          label="下载次数"
          width="100">
       </el-table-column>
       <el-table-column
          prop="pic"
          label="图片路径"
          width="400">
       </el-table-column>
       <el-table-column
          prop="path"
          label="文件路径"
          width="400">
       </el-table-column>
       <el-table-column
         fixed="right"
         label="操作"
         width="200">
        <template slot-scope="scope">
         <el-button @click="check(scope.row)" type="text" size="small">查看</el-button>
         <el-button @click="download(scope.row.id)" type="text" size="small">下载</el-button>
         <el-button @click="filect(scope.row.id)" type="text" size="small" >编辑</el-button>
         <el-button type="text" size="small" @click="deleteRow(scope.row.id)">删除</el-button>
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
      tableData:[],
      input:'',
    }
  },
  mounted(){
    this.fetchdata()
  },
  methods:{
    fetchdata(){
        request
        .get("/game/files")
        .then(
          response => {
            this.tableData = response.data;
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
        );
       },
  }
}
</script>

<style>

</style>