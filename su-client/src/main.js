import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' //引入element ui默认主题
import router from "./router/index"
import store from './store/index'
import "./assets/css/common.css"
import request from './utils/request'
import "./router/permission"

Vue.prototype.$axios = axios //全局注册，使用方法：this.$axios.get('url').then(res=>{})
Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.prototype.request = request

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
