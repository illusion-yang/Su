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
          label="头像"
          width="120">
          <div slot-scope="scope">
            <el-image
            style="width: 100px; height: 100px"
            :src="getUrl(scope.row.avatar)"
            >
            </el-image>
          </div>
       </el-table-column>
       <el-table-column
          prop="username"
          label="用户名"
          width="100">
       </el-table-column>
       <el-table-column
          prop="phone"
          label="手机号"
          width="200">
       </el-table-column>
       <el-table-column
          prop="email"
          label="邮箱"
          width="200">
       </el-table-column>
       <el-table-column
          prop="status"
          label="状态"
          width="100">
          <template slot-scope="scope">
            {{ String(scope.row.status) === '0' ? '已禁用' : '正常' }}
          </template>
       </el-table-column>
       <el-table-column
       label="收藏"
       width="90">
       <template slot-scope="scope">
            <el-button @click="gocollect(scope.row.id)">收藏</el-button>
          </template>
       </el-table-column>
       <el-table-column
         label="操作"
         width="150">
        <template slot-scope="scope">
         <el-button @click="editInfo(scope.row.id)" type="text" size="medium">编辑</el-button>
         <el-button type="text" size="medium" @click.native.prevent="deleteRow(scope.row.id)">删除</el-button>
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

    <el-dialog title="用户信息" :visible.sync="dialogTableVisible" width="30%">
    <el-form ref="form" :model="form" label-width="80px" status-icon :rules="rules">
      <el-form-item label="用户名" prop="username">
      <el-input type="text" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
      <el-input  v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
      <el-input v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
      <el-input v-model="form.email"></el-input>
      </el-form-item>
    </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogTableVisible = false">取 消</el-button>
      <el-button type="primary" @click="updateInfo('form')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/request';
export default {
data(){
        var validateUserName = (rule, value, callback) =>{
            if(value === ''){
                callback(new Error('请输入用户名'));
            }else if(value.length < 5){
                callback(new Error('长度不够'))
            }else{
                callback();
            }
        }
        var validatePassword = (rule, value, callback) =>{
            if(value === ''){
                callback(new Error('请输入密码'));
            }else{
                callback();
            }
        }
        return{
            tableData:[],
            tempDate:[],
            dialogTableVisible: false,
            status:"",
            form:{
              id:"",
              username:"",
              password:"",
              phone:"",
              email:"",
            },
            rules:{
                username:[
                    {
                        validator: validateUserName, trigger:'blur' //触发规则：失去焦点
                    }
                ],
                password:[
                    {
                        validator: validatePassword, trigger:'blur'
                    }
                ]
            },
            input:"",
            currentPage: 1, //当前页
            pageSize: 5, //分页每页大小
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
            if(item.username.includes(this.input)){
              this.tableData.push(item)
            }
          }
        }
      }
    },
    methods:{
        fetchdata(){
            this.tempData = [];
            this.tableData = [];
        this.request.get("user/all").then(
          response => {
            this.tempDate = response.data;
            this.tableData = response.data;
            console.log(response.data)
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
        );
       },
       //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
            
        },
      deleteRow(id) {
        this.request.delete("/user/"+id).then(res => {
          console.log(res)
        if (res.code === 200) {
          this.$message.success("删除成功")
        } else {
          this.$message.error("删除失败")
        }
      })
      },
      statusHandle (row) {
        this.id = row.id
            this.status = row.status
            this.$confirm('确认调整该账号的状态?', '提示', {
              'confirmButtonText': '确定',
              'cancelButtonText': '取消',
              'type': 'warning'
              }).then(()=>{
                this.request.post("/user/update",{'id':this.id,'status':!this.status?1:0}).then(res=>{
                  console.log(res)
                  if(res.code === 200){
                    window.location.reload()
                    this.$message.success('账号状态更改成功！')
                  }else{
                    this.$message.error('请求出错了')
                  }
                })
              })
      },
      editInfo(id){
        this.dialogTableVisible = true
        this.request.get("/user/"+id).then(res=>{
          console.log(res)
        if (res.code === 200) {
          this.$message.success("回写成功")
          this.form = res.data
        } else {
          this.$message.error("回写失败")
        }
        })
      },
      updateInfo(form){
        this.$refs[form].validate((vaild)=>{
          if(vaild){
            this.request.post("/user/update",this.form).then(res=>{
              console.log(res)
              if(res.code === 200){
                this.$message.success(res.msg)
                this.dialogTableVisible = false
              }else{
                this.$message.error(res.msg)
              }
            })
          }
        })
      },
      getUrl(path){
        return `http://localhost:8080/static`+path;
      },
      gocollect(id){
        this.$router.push({path:`collect/${id}`});
      }
    }
}
</script>

<style scoped>
.aa{
  width:300px;
  display: flex;
}
</style>