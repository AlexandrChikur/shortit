import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router";

import ApiPlugin from './plugins/api'
import router from './router/index'

import '@/assets/styles/main.css'

Vue.use(ApiPlugin)
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
