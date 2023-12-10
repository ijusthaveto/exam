import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/ExamLogin.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/ExamRegister.vue')
    },
  ]
})

export default router
