<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useExamStore } from '@/stores/examStore';

const examList = ref([])
const store = useExamStore()

const router = useRouter()

const viewExam = (id) => {
  console.log(id)
  router.push({
    path: '/exam',
    query: {
      examId: id
    }
  })
}

onMounted(() => {
  store.selectExamListById()
})
</script>
<template>
  <div class="main-container">
    <el-container>
      <el-header class="nav">
        <el-row :gutter="20" type="flex" class="row-bg" align="middle">
          <el-col :span="20">
            <router-link to="/" style="color: white; font-weight: bold">Online Exam</router-link>
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
        <el-row :gutter="20">
          <el-col :span="5" v-for="item in store.examList" :key="item.examId">
            <div class="exam-container">
              <div class="exam-title exam-item">{{ item.examTitle }}</div>
              <div class="exam-start-time exam-item">{{ item.startTime }}</div>
              <div class="exam-end-time exam-item">{{ item.endTime }}</div>
              <div class="exam-link exam-item">
                <el-link @click="viewExam(item.examId)">View Exam</el-link>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

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

.exam-container {
  width: 200px;
  display: grid;
  padding: 10px;
  margin: 10px;
  place-items: center;
  /*background-color: rebeccapurple;*/
  border: 1px white solid;
}

.exam-item {
  width: 150px;
  margin: 5px;
  display: grid;
  place-items: center;
}</style>
