import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Collect from '../views/Collect.vue'
import Comment from '../views/Comment.vue'

Vue.use(Router)

const routes = [
    {
        path:"/",
        component:Home,
        meta:{
            isLogin:true
        },
        children:[
            {
                path:"",
                name:'Info',
                component:()=>import('../views/Homepage/Info.vue')
            },
            {
                path:"/user",
                name:'User',
                component:()=>import('../views/Homepage/User.vue')
            },
            {
                path:"/document",
                name:'Document',
                component:()=>import('../views/Homepage/File.vue')
            },
            {
                path:"/community",
                name:'Community',
                component:()=>import('../views/Homepage/Community.vue')
            },
            {
                path:"music",
                name:'Music',
                component:()=>import('../views/FilePage/Music.vue')
            },
            {
                path:"game",
                name:'Game',
                component:()=>import('../views/FilePage/Game.vue')
            },
            {
                path:"vedio",
                name:'Vedio',
                component:()=>import('../views/FilePage/Vedio.vue')
            },
            {
                path:"model",
                name:'Model',
                component:()=>import('../views/FilePage/Model.vue')
            },
            {
                path:'collect/:id',
                name:'Collect',
                component:Collect
            },
            {
                path:'comment/:id',
                name:'Comment',
                component:Comment
            }
        ]
    },
    {
        path:"/login",
        name:'Login',
        component:()=>import('../views/Login.vue'),
    }
]

const router = new Router({
    mode:'history',
    base:process.env.BASE_URL,
    routes
})

export default router