<template>
  <div class="main-container">
    <el-container>
      <el-header class="nav">
        <el-row :gutter="20" type="flex" class="row-bg" align="middle">
          <el-col :span="2">
            <router-link to="/" style="color: white; font-weight: bold">Online Exam</router-link>
          </el-col>
          <el-col :span="2" :offset="16">
            <router-link to="/register" style="color: white">Your exam</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Your profile</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Sign out</router-link>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-scrollbar height="650px">
          <div v-for="item in questionList" :key="item.questionId" class="scrollbar-demo-item">
            <div class=''></div>
          </div>
        </el-scrollbar>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import httpInstance from '@/utils/http.js'

const route = useRoute()

const examId = route.query.examId

const questionList = ref([])

const getQuestionListByExamId = async () => {
  const res = await httpInstance.get(`/exam/${examId}`)
  questionList.value = res.data
}

onMounted(() => {
  getQuestionListByExamId()
  console.log(examId)
})
</script>

<style scoped>
.main-container {
  color: white;
  height: 100vh;
}
el-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.nav {
  flex: 0 0 auto;
  padding-top: 20px;
  background-color: #24292f;
}
el-main {
  flex: 1 0 auto;
}
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
</style>
