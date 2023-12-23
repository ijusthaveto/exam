<template>
  <div class='center-container'>
    <el-table :data="filterTableData" style="width: 100%">
      <el-table-column label="UserNo" prop="userNo" />
      <el-table-column label="Username" prop="username" />
      <el-table-column label="ClassNo" prop="classNo" />
      <el-table-column label="Password" prop="passwordHash" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="Type to search" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >Edit</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import {computed, onMounted, ref} from 'vue'
import httpInstance from '@/utils/http.js'

const tableData = ref([])
const search = ref('')

const filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      !search.value ||
      data.username.toLowerCase().includes(search.value.toLowerCase())
  )
)

const page = ref(1)
const size = ref(16)

const handleEdit = (index, row) => {
  console.log(index, row)
}

const handleDelete = (index, row) => {
  console.log(index, row)
}

const getUserList = async () => {
  const res = await httpInstance.get(`/user/page?page=${page.value}&size=${size.value}`)
  tableData.value = res.data.records
  console.log(tableData.value)
}

onMounted(() => {
  getUserList()
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
