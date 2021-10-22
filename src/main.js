import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
import axios from 'axios';
import qs from 'qs'
import echarts from 'echarts'



axios.defaults.baseURL = 'http://localhost:9000/test'
Vue.prototype.$http = axios
Vue.prototype.$qs = qs
Vue.prototype.$charts = echarts

Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
