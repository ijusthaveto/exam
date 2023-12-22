<template>
  <div class='center-container'>
    Student Management
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import httpInstance from '@/utils/http.js'
import {ElMessage} from 'element-plus'

const tableData = ref([])

const count = ref(0)
const load = () => {
  count.value += 2
}

const getStudentInfoList = async () => {
  const res = await httpInstance.get('/user/list')
  console.log(tableData)
  if (res.code === 0) {
    tableData.value = res.data
  } else {
    ElMessage.error(res.message)
  }
}

onMounted(() => {
  getStudentInfoList()
})
</script>

<style lang='scss' scoped>
.center-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  infinite-list {
    height: 300px;
    padding: 0;
    margin: 0;
    list-style: none;
  }
  .infinite-list .infinite-list-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    background: var(--el-color-primary-light-9);
    margin: 10px;
    color: var(--el-color-primary);
  }
  .infinite-list .infinite-list-item + .list-item {
    margin-top: 10px;
  }
}
</style>
