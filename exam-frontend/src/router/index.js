import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/Layout/index.vue'
import Home from '@/views/Home/index.vue'
import Login from '@/views/Login/index.vue'
import Register from '@/views/Register/index.vue'
import Admin from '@/views/Admin/index.vue'
import AdminLogin from '@/views/AdminLogin/index.vue'
import User from '@/views/User/index.vue'
import Exam from '@/views/User/components/Exam.vue'
import Student from '@/views/Admin/components/Student/index.vue'
import Question from '@/views/Admin/components/Question/index.vue'
import Grade from '@/views/Admin/components/Grade/index.vue'
import ExamManagement from '@/views/Admin/components/Exam/index.vue'
import StudentImportVue from '@/components/Student/StudentImport.vue'
import StudentGradeManagementVue from '@/components/Grade/StudentGradeManagement.vue'
import BoolQuestionVue from '@/components/Question/BoolQuestion.vue'
import MultipleChoiceVue from '@/components/Question/MultipleChoiceVice.vue'
import QuestionBankVue from '@/components/Question/QuestionBank.vue'
import SingleChoiceVue from '@/components/Question/SingleChoiceVice.vue'
import TestPaperImportVue from '@/components/Question/TestPaperImport.vue'
import PaperAdministrationVue from '@/components/Exam/PaperAdministration.vue'
import PaperGenerationVue from '@/components/Exam/PaperGeneration.vue'
import StudentManagementVue from '@/components/Student/StudentManagement.vue'

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
      path: '/adminLogin',
      component: AdminLogin
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
          path: 'student',
          component: Student,
          children: [
            {
              path: 'import',
              component: StudentImportVue
            },
            {
              path: '',
              component: StudentManagementVue,
            }
          ]
        },
        {
          path: 'question',
          component: Question,
          children: [
            {
              path: 'bool',
              component: BoolQuestionVue
            },
            {
              path: 'multi',
              component: MultipleChoiceVue
            },
            {
              path: '',
              component: QuestionBankVue
            },
            {
              path: 'single',
              component: SingleChoiceVue
            },
            {
              path: 'import',
              component: TestPaperImportVue
            }
          ]
        },
        {
          path: 'exam',
          component: ExamManagement,
          children: [
            {
              path: '',
              component: PaperGenerationVue
            }
          ]
        },
      ]
    },
    {
      path: '/user',
      component: User,
    },
    {
      path: '/exam',
      component: Exam
    },
    {
      path: '/history',
      component: () => import('@/components/common/History.vue')
    }
  ]
})



export default router
