<template>
  <div>
    <div class="aa">
      <el-input v-model="input" placeholder="请输入内容"></el-input>
    </div>
    <el-table 
       :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
       border
       style="width: 100%;font-size:16px"
    >
       <el-table-column
          fixed
          prop="id"
          label="id"
          width="50">
       </el-table-column>
       <el-table-column
          prop="userId"
          label="用户id"
          width="70">
       </el-table-column>
       <el-table-column
          prop="title"
          label="标题"
          width="200">
       </el-table-column>
       <el-table-column
          prop="content"
          label="内容"
          width="400">
       </el-table-column>
       <el-table-column
          prop="type"
          label="类型"
          width="60">
       </el-table-column>
       <el-table-column
          prop="status"
          label="状态"
          width="60">
          <template slot-scope="scope">
            {{ String(scope.row.status) === '0' ? '已禁用' : '正常' }}
          </template>
       </el-table-column>
       <el-table-column
          prop="commentCounts"
          label="评论数"
          width="60">
       </el-table-column>
       <el-table-column
          prop="score"
          label="点赞"
          width="60">
       </el-table-column>
       <el-table-column
       label="评论"
       width="100">
       <template slot-scope="scope">
            <el-button @click="gocomment(scope.row.id)">评论</el-button>
          </template>
       </el-table-column>
       <el-table-column
         fixed="right"
         label="操作"
         width="200">
        <template slot-scope="scope">
         <el-button type="text" size="medium" @click="deleteRow(scope.row.id)">删除</el-button>
         <el-button type="text" size="medium" @click="statusHandle(scope.row)">{{ scope.row.status == '1' ? '禁用' : '启用' }}</el-button>
        </template>
        </el-table-column>
    </el-table>

    <el-pagination
    background
    layout = "total,prev,pager,next"
    :current-page="currentPage"
    :page-size="pageSize"
    :total="tableData.length"
    @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import request from '@/utils/request';
export default {
  data(){
    return{
      tableData:[],
      tempDate:[],
      input:'',
      currentPage: 1, //当前页
      pageSize: 5, //分页每页大小
    }
  },
  mounted(){
    this.fetchdata()
  },
  methods:{
    fetchdata(){
      this.tempData = [];
      this.tableData = [];
      request.get("/discuss/files").then(response => {
            this.tempDate = response.data;
            this.tableData = response.data;
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
        )
    },
    gocomment(id){
      this.$router.push({path:`comment/${id}`});
    },
    statusHandle (row) {
        this.id = row.id
            this.status = row.status
            this.$confirm('确认调整该话题的状态?', '提示', {
              'confirmButtonText': '确定',
              'cancelButtonText': '取消',
              'type': 'warning'
              }).then(()=>{
                this.request.post("/discuss/update",{'id':this.id,'status':!this.status?1:0}).then(res=>{
                  console.log(res)
                  if(res.code === 200){
                    window.location.reload()
                    this.$message.success('话题状态更改成功！')
                  }else{
                    this.$message.error('请求出错了')
                  }
                })
              })
      },
    deleteRow(){

    },
    //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
            
        },
  }
}
</script>

<style scoped>
.aa{
  width:300px;
  display: flex;
}
</style>