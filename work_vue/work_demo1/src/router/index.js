import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../components/login.vue'
import Add from '../components/add.vue'

const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/add",
    component: Add
  }
  
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})


export default router
