<template>
  <div class='center-container'>
    <el-table :data="filterTableData" style="width: 100%">
      <el-table-column label="User No" prop="userNo" />
      <el-table-column label="Username" prop="username" />
      <el-table-column label="Class No" prop="classNo" />
      <el-table-column label="Password" prop="passwordHash" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" placeholder="Type to search" size="small" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >Edit
          </el-button
          >
          <el-button
              size="small"
              type="primary"
              @click="handleDelete(scope.$index, scope.row)"
          >Delete
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-pagination
      :page-size='pageObject.size'
      :total='pageObject.total'
      layout="prev, pager, next"
      @size-change='handleSizeChange'
      @current-change='handleCurrentChange'
  />
  <el-dialog
      v-model="centerDialogVisible"
      align-center
      title="Modify Student Information"
      width="30%"
  >
    <div>
      <el-form
          :label-position="'right'"
          :model="formLabelAlign"
          label-width="100px"
          style="max-width: 460px"
      >
        <el-form-item label="Class No">
          <el-input v-model="formLabelAlign.classNo" />
        </el-form-item>
        <el-form-item label="User No">
          <el-input v-model="formLabelAlign.userNo" />
        </el-form-item>
        <el-form-item label="Username">
          <el-input v-model="formLabelAlign.username" />
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="formLabelAlign.passwordHash" />
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleChangeUserInfo">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {computed, onMounted, reactive, ref} from 'vue'
import httpInstance from '@/utils/http.js'
import {ElMessage} from "element-plus";

const centerDialogVisible = ref(false)
const tableData = ref([])
const search = ref('')
const pageObject = reactive({
  total: 0,
  size: 0,
  current: 0,
})
const formLabelAlign = reactive({
  classId: null,
  classNo: null,
  passwordHash: null,
  roleId: null,
  userId: null,
  userNo: null,
  username: null
})

const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.username.toLowerCase().includes(search.value.toLowerCase())
    )
)

const page = ref(1)
const size = ref(15)

const handleEdit = async (index, row) => {
  const userId = row.userId
  const res = await httpInstance.get(`/user/${userId}`)
  formLabelAlign.classId = res.data.classId
  formLabelAlign.classNo = res.data.classNo
  formLabelAlign.passwordHash = res.data.passwordHash
  formLabelAlign.roleId = res.data.roleId
  formLabelAlign.userId = res.data.userId
  formLabelAlign.userNo = res.data.userNo
  formLabelAlign.username = res.data.username
  centerDialogVisible.value = true
}

const handleChangeUserInfo = async () => {
  const res = await httpInstance.put(`/user`, {
    classId: formLabelAlign.classId,
    classNo: formLabelAlign.classNo,
    passwordHash: formLabelAlign.passwordHash,
    roleId: formLabelAlign.roleId,
    userId: formLabelAlign.userId,
    userNo: formLabelAlign.userNo,
    username: formLabelAlign.username
  })
  if (res.code === 0) {
    centerDialogVisible.value = false
    ElMessage.success('Modified student information successfully.')
    await getUserList()
  } else {
    ElMessage.error('Failed to modify student information.')
  }
}

const handleDelete = async (index, row) => {
  const userId = row.userId
  console.log(userId)
  const res = await httpInstance.delete(`/user/remove/${userId}`)
  if (res.code === 0) {
    ElMessage.success('Succeeded in deleting the user.')
    await getUserList()
  } else {
    ElMessage.error(res.message)
  }
}

const getUserList = async () => {
  const res = await httpInstance.get(`/user/page?page=${page.value}&size=${size.value}`)
  tableData.value = res.data.records
  pageObject.total = res.data.total
  pageObject.size = res.data.size
  pageObject.current = res.data.current
}

const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
}

const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
  page.value = val
  getUserList()
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
  position: relative;

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
}

.el-pagination {
  --el-pagination-bg-color: #24292f;
  --el-pagination-text-color: #e6edf3;
  --el-pagination-button-color: #e6edf3;
  --el-pagination-button-disabled-bg-color: #24292f;
  position: absolute;
  bottom: 20px;
}
</style>
