import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' //引入element ui默认主题
import router from "./router/index"
import store from './store/index'
import "./assets/common.css"
import "./router/permission"
import request from './utils/request'
import VCharts from 'v-charts'

Vue.prototype.$axios = axios //全局注册，使用方法：this.$axios.get('url').then(res=>{})
Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(VCharts)
Vue.prototype.request = request
Vue._watchers = Vue.prototype._watchers = [] //v-charts 'map'问题

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
