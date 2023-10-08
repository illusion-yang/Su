<template>
<div clss="login">
    <el-card class="box-card">
    <div slot="header" class="clearfix">
        <span>素材网</span>
    </div>
    <el-tabs v-model="currentIndex" stretch @tab-click="handTabsleClick">
        <el-tab-pane label="登录" name="login">
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
        </el-tab-pane>
        <el-tab-pane label="注册" name="register">
            <el-form :model="registerForm" :rules="rules" status-icon ref="registerForm">
                <el-form-item label="用户名" label-width="60px" prop="username">
                    <el-input type="text" v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" label-width="60px" prop="password">
                    <el-input type="password" v-model="registerForm.password"></el-input>
                </el-form-item>
                 <el-form-item label="确认密码" label-width="60px" prop="configurePassword">
                    <el-input type="password" v-model="registerForm.configurePassword"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click.native="submitForm('registerForm')">提交</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>
  </el-card>
</div>
</template>

<script>

import request from '../utils/request'

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
        var validateConfigurePassword = (rule, value, callback) =>{
            if(value === ''){
                callback(new Error('请输入密码'));
            }else if(value !== this.registerForm.password){
                callback(new Error('两次密码不一致'));
            }else{
                callback();
            }
        }
        return{
            currentIndex:"login",
            loginForm:{
                username:"admin",
                password:"123"
            },
            registerForm:{
                username:"",
                password:"",
                configurePassword:""
            },
            activeTab:"login",
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
                ],
                configurePassword:[
                    {
                        validator:validateConfigurePassword, trigger:'blur'
                    }
                ]
            }
        }
    },
    methods:{
        submitForm(formName){
            this.$refs[formName].validate((valid)=>{
                if(valid){
                    if(this.activeTab === "login"){
                        //登录
                        request.post("http://localhost:8080/user/login",this.loginForm).then(res=>{
                            //console.log(res);
                            if(res.code === 200){
                                this.$message.success(res.msg)
                                localStorage.setItem("token",JSON.stringify(res))
                                this.$router.push('/')
                            }else{
                                this.$message.error(res.msg)
                            }
                        })
                        
                    }
                    if(this.activeTab === "register"){
                        //注册
                        request.post("http://localhost:8080/user/register",this.registerForm).then(res=>{
                            //console.log(res);
                            if(res.code===200){
                                this.$message.success(res.msg)
                            }else{
                                this.$message.error(res.msg)
                            }
                        })
                        
                    }
                }else{
                    return;
                }
            })
        },
        handTabsleClick(tab){
            console.log(tab.naem);
            this.activeTab = tab.name;
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
        width: 600px;
        margin: 100px auto;
}

</style>