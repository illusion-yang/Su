<template>
  <div>
    <div class="aa">
      <el-input class="a" v-model="input" placeholder="根据文件名查询"></el-input>
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
          prop="oldFileName"
          label="旧文件名"
          width="350">
       </el-table-column>
       <el-table-column
          prop="newFileName"
          label="新文件名"
          width="350">
       </el-table-column>
       <el-table-column
          prop="ext"
          label="文件后缀"
          width="60">
       </el-table-column>
       <el-table-column
          prop="path"
          label="文件路径"
          width="600">
       </el-table-column>
       <el-table-column
          prop="size"
          label="文件大小"
          width="100">
       </el-table-column>
       <el-table-column
          prop="type"
          label="文件类型"
          width="150">
       </el-table-column>
       <el-table-column
          prop="isImg"
          label="是否为图片"
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
         <el-button @click="filect(scope.row)" type="text" size="medium" >编辑</el-button>
         <el-button type="text" size="medium" @click="deleteRow(scope.row.id)">删除</el-button>
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

    <el-dialog title="图片文件" 
    width="20%"
    :visible.sync="img">
    <el-image 
    style="width: 100px; height: 100px"
    :src="src" 
    :preview-src-list="srcList">
    </el-image>
    </el-dialog>

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

    <el-dialog title="视频文件" 
    width="35%"
    :visible.sync="video">
      <video width="320" height="240" controls>
         <source :src="src" type="video/mp4">
         <!-- <source src="movie.ogg" type="video/ogg"> -->
         您的浏览器不支持 video 标签。
      </video>
    </el-dialog>

    <el-dialog title="音效文件分类"
    width="40%"
    :visible.sync="Mfile"
    >
    <el-form :model="form" res="form">

      <el-form-item label="文件名称" prop="fileName">
      <el-input v-model="form.fileName"></el-input>
      </el-form-item>
      <el-form-item label="文件类型" prop="type">
      <el-radio-group v-model="form.type">
      <el-radio label="实时音效"></el-radio>
      <el-radio label="影视音效"></el-radio>
      <el-radio label="免费商用"></el-radio>
      <el-radio label="音效作品"></el-radio>
    </el-radio-group>
  </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="Mfile = false">取 消</el-button>
    <el-button type="primary" @click="contM('form')">确 定</el-button>
    </span>
    </el-dialog>
    <el-dialog title="视频文件分类"
    width="40%"
    :visible.sync="Vfile"
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
    <el-button @click="Vfile = false">取 消</el-button>
    <el-button type="primary" @click="contV('form')">确 定</el-button>
    </span>
    </el-dialog>

    <el-dialog title="游戏文件分类"
    width="40%"
    :visible.sync="Gfile"
    >
    <el-form :model="form" res="form">
      <el-form-item label="文件名称" prop="fileName">
      <el-input v-model="form.fileName"></el-input>
      </el-form-item>
      <el-form-item label="文件类型" prop="type">
      <el-radio-group v-model="form.type">
      <el-radio label="游戏资源"></el-radio>
      <el-radio label="2d素材"></el-radio>
      <el-radio label="Unity3D"></el-radio>
      <el-radio label="UE"></el-radio>
      </el-radio-group>
      <el-form-item label="文件图片">
        <el-upload
        class="upload-demo"
        action="http://localhost:8080/game/save"
        
        :before-remove="beforeRemove"
        :auto-upload="false"
        accept=""
        :data="form"
        ref="upload"
        >
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="Gfile = false">取 消</el-button>
    <el-button type="primary" @click="contG('form')">确 定</el-button>
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
            tempDate:[],
            img: false,
            music: false,
            video: false,
            Mfile: false,
            Vfile: false,
            Gfile: false,
            url:"",
            srcList:['http://localhost:8080/static/img/260e0f9c-915c-4cc5-968a-febb02bcaa53.jpg'],
            src:"",
            input:'',
            form:{
              id:'',
              size:'',
              path:"",
              fileName:"",
              type:"",
            },
            currentPage: 1, //当前页
            pageSize: 7, //分页每页大小
            fileList: [],
        }
    },
    mounted(){
        this.fetchdata()
    },
    watch:{
      input: function(){
        if(this.input == ''){
          this.tableData = this.tempDate;
        }else{
          this.tableData = [];
          for(let item of this.tempDate){
            if(item.oldFileName.includes(this.input)){
              this.tableData.push(item)
            }
          }
        }
      },
      
    },
    methods:{
        fetchdata(){
            this.tempData = [];
            this.tableData = [];
        request.get("/api/files").then(response => {
            this.tempDate = response.data;
            this.tableData = response.data;
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
        )
       },
       
       //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
            
        },
       check(row){
        if(row.type.slice(0,5) === 'image'){
          this.img = true
        }else if(row.type.slice(0,5) === 'audio'){
          this.music = true
        }else if(row.type.slice(0,5) === 'video'){
          this.video = true
        }
        this.src = "http://localhost:8080/static"+row.path
       },
       download(row){
         console.log(row.id)
         this.request.get("/api/download/"+row.id,{responseType: "blob"}).then(res=>{
           console.log(res)
           console.log("下载成功")
           let fileName = row.oldFileName;
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
       deleteRow(id) {
         this.request.delete("/api/"+id).then(res => {
          console.log(res.data)
        if (res.code === 200) {
          this.$message.success("删除成功")
        } else {
          this.$message.error("删除失败")
        }
      })
      },
      startPlay(){
      // let player = document.querySelector('#player');
      //player.play();
      },
      ended(){

      },
      
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      filect(row){
        if(row.type.slice(0,5) === 'audio'){
          this.Mfile = true
        }else if(row.type.slice(0,5) === 'video'){
          this.Vfile = true
        }else if(row.type.slice(0,5) === 'image'){
          this.Gfile = true
        }  
         this.form.path = row.path;
         this.form.size = row.size;
      },
      contM(form){
          this.request.post("/music/save",this.form).then(res=>{
          console.log(res)

          if(res.code===200){
              this.$message.success(res.msg)
              localStorage.setItem("file",JSON.stringify(res))
            }else{
              this.$message.error(res.msg)
            }
         })
         },
      contV(form){
          this.request.post("/video/save",this.form).then(res=>{
          console.log(res)

          if(res.code===200){
              this.$message.success(res.msg)
              localStorage.setItem("file",JSON.stringify(res))
            }else{
              this.$message.error(res.msg)
            }
         })
         },
      contG(form){
        this.$refs.upload.submit();
      }
    }
}
</script>

<style scoped>
.aa{
  width:300px;
  display: flex;
}
.a{
  margin-right: 20px;
}
</style>