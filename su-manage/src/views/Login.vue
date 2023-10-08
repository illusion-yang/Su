<template>
  <div clss="login">
    <el-card class="box-card">
    <div slot="header" class="clearfix">
        <span>素材网后台</span>
    </div>
    <el-form :model="loginForm" :rules="rules" status-icon ref="loginForm">
        <el-form-item label="用户名" label-width="60px" prop="username">
            <el-input type="text" v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="60px" prop="password">
            <el-input type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click.native="submitForm('loginForm')">提交</el-button>
        </el-form-item>
    </el-form>       
  </el-card>
</div>
</template>

<script>

import request from '@/utils/request'

export default {
    data(){
        //验证规则
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
            loginForm:{
                username:"admin",
                password:"123"
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
            }
        }
    },
    methods:{
        submitForm(formName){
            this.$refs[formName].validate((valid)=>{
                if(valid){
                    this.request.post("/admin/login",this.loginForm).then(res=>{
                            console.log(res);
                            if(res.code === 200){
                                this.$message.success(res.msg)
                                localStorage.setItem("token",JSON.stringify(res))
                                localStorage.setItem("username",this.loginForm.username)
                                this.$router.push('/')
                            }else{
                                this.$message.error(res.msg)
                            }
                        })
                }
            })
        }
    }
    
}
</script>

<style scoped>
.login{
    width:1200px;
    margin: 0 auto;
}

.box-card{
        width: 500px;
        margin: 100px auto;
}

</style>