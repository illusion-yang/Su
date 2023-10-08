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
          prop="path"
          label="文件路径"
          width="400">
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

    <el-dialog title="视频文件" 
    width="35%"
    :visible.sync="video">
      <video width="320" height="240" controls>
         <source :src="src" type="video/mp4">
         <!-- <source src="movie.ogg" type="video/ogg"> -->
         您的浏览器不支持 video 标签。
      </video>
    </el-dialog>

    <el-dialog title="视频文件分类"
    width="40%"
    :visible.sync="dialogTableVisible"
    >
    <el-form :model="form" res="form">
      <el-form-item label="文件名称" prop="fileName">
      <el-input v-model="form.fileName"></el-input>
      </el-form-item>
      <el-form-item label="文件类型" prop="type">
      <el-radio-group v-model="form.type">
      <el-radio label="高清实拍"></el-radio>
      <el-radio label="短视频"></el-radio>
      <el-radio label="后期特效"></el-radio>
      <el-radio label="免费商用"></el-radio>
    </el-radio-group>
  </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogTableVisible = false">取 消</el-button>
    <el-button type="primary" @click="contV('form')">确 定</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/request';
export default {
  data(){
    return{
      tableData:[],
      input:'',
      video:false,
      src:'',
      dialogTableVisible: false,
      form:{
        id:'',
        fileName:'',
        type:'',
      }
    }
  },
  mounted(){
    this.fetchdata()
  },
  methods:{
    fetchdata(){
        request
        .get("/video/files")
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
       check(row){
        this.video = true
        this.src = "http://localhost:8080/static"+row.path
       },
       filect(id){
        this.dialogTableVisible = true
        this.request.get("/video/"+id).then(res=>{
          console.log(res)
        if (res.code === 200) {
          this.$message.success("回写成功")
          this.form = res.data
        } else {
          this.$message.error("回写失败")
        }
        })
       }
  },
}
</script>

<style>
.aa{
  width:300px;
  display: flex;
}
</style>