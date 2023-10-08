import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/main/Home.vue'
import Layout from "../views/Layout.vue"

Vue.use(Router)

const routes = [
    {
        path:'/',
        //name:"Layout",
        component:Layout,
        children:[
            {
                path:'',
                name:'Home',
                component:Home,
                // meta:{
                //     isLogin:true
                // }
            },
            {
                path:'music',
                name:'Music',
                component:() => import('../views/main/Music.vue'),
                // meta:{
                //     isLogin:true
                // }
            },
            {
                path:'game',
                name:'Game',
                component:() => import('../views/main/Game.vue'),
                // meta:{
                //     isLogin:true
                // }
            },
            {
                path:'consumer',
                name:'Consumer',
                component:() => import('../views/main/Consumer.vue'),
                meta:{
                    isLogin:true
                }
            }
        ]
    },
    {
        path:"/login",
        name:'Login',
        component:()=>import('../views/Login.vue')
    }
]

const router = new Router({
    mode:'history',
    base:process.env.BASE_URL,
    routes
})

export default router