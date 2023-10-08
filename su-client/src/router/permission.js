import router from './index'

//导航守卫
router.beforeEach((to,from,next)=>{
    if(to.meta.isLogin){
        let token = localStorage.getItem("token");
        if(token){
            next();
        }else{
            next({
                name:"Login"
            })
        }
    }else{
        next();
    }
}) 
