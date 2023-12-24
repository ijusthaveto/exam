<script setup>
import {computed, ref, onMounted} from 'vue'
import httpInstance from '@/utils/http.js'
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter()
const handleExit = () => {
  localStorage.clear()
  ElMessage.success('Exit successfully.')
  router.push('/')
}
const tableData = ref([])

const search = ref('')
const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.examTitle.toLowerCase().includes(search.value.toLowerCase())
    )
)

const getHistoryList = async () => {
  const res = await httpInstance.get('/user/history')
  tableData.value = res.data
}



onMounted(() => {
  getHistoryList()
})



</script>
<template>
  <div class="main-container">
    <el-container>
      <el-header class="nav">
        <el-row :gutter="20" align="middle" class="row-bg" type="flex">
          <el-col :span="20">
            <router-link style="color: white; font-weight: bold" to="/">Online Exam</router-link>
          </el-col>
          <el-col :span="2">
            <router-link style="color: white" to="/user">Your exam</router-link>
          </el-col>
          <el-col :span="2">
            <router-link style="color: white" to="" @click="handleExit">Sign out</router-link>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-table :data="filterTableData" style="width: 100%">
          <el-table-column label="Exam Title" prop="examTitle" />
          <el-table-column label="Subject Name" prop="subjectName" />
          <el-table-column label="Score" prop="score" />
          <el-table-column label="Start Time" prop="startTime" />
          <el-table-column label="End Time" prop="endTime" />
          <el-table-column align="right">
            <template #header>
              <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
          </el-table-column>
        </el-table>
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

:deep(.el-table) {
  --el-table-border-color: #e6edf3;
  --el-table-bg-color: #000000;
  --el-table-header-text-color: white;
  --el-table-header-bg-color: #24292f;
  --el-table-tr-bg-color: #24292f;
  color: #e6edf3;
  --el-table-row-hover-bg-color: #555c5f;
  --el-table-border: 1px solid #e6edf3;
  --el-border-radius-circle: 20%;
}

.el-table-column {
  height: 50px;
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
