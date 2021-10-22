import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name:'Login',
    component:() => import('../views/login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children:[ 
    {
      path: '/maxscore',
      name: 'Maxscore',
      component: () => import('../views/Score/MaxScore.vue')
    },
    {
      path: '/countBySch',
      name: 'CountBySch',
      component: () => import('../views/count/CountBySch.vue')
    },
  ]
  },
 
]

const router = new VueRouter({
  routes
})

export default router
