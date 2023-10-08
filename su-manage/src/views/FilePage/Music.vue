<template>
  <div>
    <div class="aa">
      <el-input v-model="input" placeholder="请输入内容"></el-input>
    </div>
    <el-table 
       :data="tableData"
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
         <el-button @click="check(scope.row)" type="text" size="medium">查看</el-button>
         <el-button @click="download(scope.row)" type="text" size="medium">下载</el-button>
         <el-button @click="filect(scope.row.id)" type="text" size="medium" >编辑</el-button>
         <el-button type="text" size="medium" @click="deleteRow(scope.row.id)">删除</el-button>
        </template>
        </el-table-column>
    </el-table>

    <el-dialog title="音效文件" 
    width="35%"
    :visible.sync="music">
      <audio id="player"
       :src="src"
       controls="controls"
       preload="true"
       @canplay="startPlay"
       @ended="ended"
      >
      </audio>
    </el-dialog>

    <el-dialog title="文件信息" status-icon :visible.sync="dialogTableVisible" width="40%">
    <el-form :model="fileform" label-width="80px">
      <el-form-item label="文件名" prop="fileName">
      <el-input type="text" v-model="fileform.fileName"></el-input>
      </el-form-item>
      <el-form-item label="类型" prop="type">
      <el-radio-group v-model="fileform.type">
      <el-radio label="实时音效"></el-radio>
      <el-radio label="影视音效"></el-radio>
      <el-radio label="免费商用"></el-radio>
      <el-radio label="音效作品"></el-radio>
    </el-radio-group>
      </el-form-item>
    </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogTableVisible = false">取 消</el-button>
      <el-button type="primary" @click="updateInfo('fileform')">确 定</el-button>
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
      music: false,
      dialogTableVisible: false,
      src:"",
      input:'',
      fileform:{
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
        .get("/music/files")
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
    startPlay(){
      // let player = document.querySelector('#player');
      //player.play();
    },
    ended(){

    },
    search(){},
    check(row){
          this.music = true
          console.log(row.path)
          this.src = "http://localhost:8080/static"+row.path
       },
    download(row){
      console.log(row.id)
      this.request.get("/music/download/"+row.id,{responseType: "blob"}).then(res=>{
           console.log(res)
           console.log("下载成功")
           let fileName = row.fileName+".mp3";
           if (!res) {
           return;
           }
           let blob = new Blob([res], { type: "application/octet-stream" });
           if ("download" in document.createElement("a")) {
           // 不是IE浏览器
           let url = window.URL.createObjectURL(blob);
           let link = document.createElement("a");
           link.style.display = "none";
           link.href = url;
           link.setAttribute("download", fileName);
           document.body.appendChild(link);
           link.click();
           document.body.removeChild(link); // 下载完成移除元素
           window.URL.revokeObjectURL(url); // 释放掉blob对象
           } else {
           // IE 10+
           window.navigator.msSaveBlob(blob, fileName);
           }
           })
    },
    filect(id){
      this.dialogTableVisible = true
        this.request.get("/music/"+id).then(res=>{
          console.log(res)
        if (res.code === 200) {
          this.$message.success("回写成功")
          this.fileform = res.data
        } else {
          this.$message.error("回写失败")
        }
        })
    },
    updateInfo(fileform){
        // this.$refs[form].validate((vaild)=>{
        //   if(vaild){
            this.request.post("/music/update",this.fileform).then(res=>{
              console.log(res)
              if(res.code === 200){
                this.$message.success(res.msg)
                this.dialogTableVisible = false
              }else{
                this.$message.error(res.msg)
              }
            })
        //   }
        // })
      },
    deleteRow(id) {
        this.request.delete("/music/"+id).then(res => {
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
.aa{
  width:300px;
  display: flex;
}
</style>