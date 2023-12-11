import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/Layout/index.vue'
import Home from '@/views/Home/index.vue'
import Login from '@/views/Login/index.vue'
import Register from '@/views/Register/index.vue'
import Admin from '@/views/Admin/index.vue'
import AdminLogin from '@/views/AdminLogin/index.vue'
import User from '@/views/User/index.vue'
import Exam from '@/views/User/components/Exam.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '',
          component: Home
        }
      ]
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/admin',
      component: Admin,
      children: [
        {
          path: '',
          component: AdminLogin
        }
      ]
    },
    {
      path: '/user',
      component: User,
    },
    {
      path: '/exam',
      component: Exam
    }
  ]
})

export default router
