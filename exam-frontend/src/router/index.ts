import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('@/views/Layout/ExamLayout.vue'),
      children: [
        {
          path: '',
          component: () => import('@/views/Home/ExamHome.vue')
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login/ExamLogin.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/Register/ExamRegister.vue')
    },
    {
      path: '/Admin',
      name: 'Admin',
      component: () => import('@/views/Admin/ExamAdmin.vue')
    }
  ]
})

export default router
