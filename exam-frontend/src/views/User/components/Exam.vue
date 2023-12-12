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
          <div class="question-container">
            <h1 class="exam-title" style="text-align: center">Exam 1</h1>
            <h2>0x01. Single Choice</h2>
            <div v-for="(item, index) in singleList" :key="item.questionId">
              <p :id="'question' + (index + 1)">
                {{ index + 1 }}.{{ item.questionContent }}
              </p>
              <el-radio-group>
                <el-radio :label="2" v-for="index in JSON.parse(item.options)" :key="index">{{ index }}.{{ JSON.parse(item.options).index }}</el-radio>
              </el-radio-group>
            </div>
            <h2>0x02. Multiple Choice</h2>
            <MultipleChoice :data="mutipleList" />
            <h2>0x03. True or False</h2>
            <TrueOrFalse :data="boolList" />
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
import { computed } from 'vue';

const route = useRoute()

const examId = route.query.examId

const questionList = ref([])

const getQuestionListByExamId = async () => {
  const res = await httpInstance.get(`/exam/${examId}`)
  console.log(res.data)
  questionList.value = res.data
}

const singleList = computed(() => {
  return questionList.value.filter(item => item.questionType === 'Single Choice')
})

const mutipleList = computed(() => {
  return questionList.value.filter(item => item.questionType === 'Multiple Choice')
})

const boolList = computed(() => {
  return questionList.value.filter(item => item.questionType === 'True/False')
})

onMounted(() => {
  getQuestionListByExamId()
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
  color: var(--el-color-primary);
}
</style>
