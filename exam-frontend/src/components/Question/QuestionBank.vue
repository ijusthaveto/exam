<template>
  <div class='center-container'>
    <el-table :data="filterTableData" style="width: 100%">
      <el-table-column label="Bank Title" prop="bankTitle" />
      <el-table-column label="Subject Name" prop="subjectName" />
      <el-table-column label="Single Question" prop="singleNum" />
      <el-table-column label="Multiple Question" prop="multipleNum" />
      <el-table-column label="Judge Question" prop="judgeNum" />
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
      title="Modify Bank Information"
      width="30%"
  >
    <div>
      <el-form
          :label-position="'right'"
          :model="formLabelAlign"
          label-width="100px"
          style="max-width: 460px"
      >
        <el-form-item label="Title">
          <el-input v-model="formLabelAlign.bankTitle" />
        </el-form-item>
        <el-form-item label="Subject">
          <el-input v-model="formLabelAlign.subjectName" />
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleChangeBankInfo">
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
  bankId: null,
  subjectId: null,
  bankTitle: null,
  subjectName: null,
  singleNum: null,
  multipleNum: null,
  judgeNum: null
})

const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.subjectName.toLowerCase().includes(search.value.toLowerCase())
    )
)

const page = ref(1)
const size = ref(15)

const handleEdit = async (index, row) => {
  const bankId = row.bankId
  const res = await httpInstance.get(`/bank/${bankId}`)
  formLabelAlign.bankId = res.data.bankId
  formLabelAlign.subjectId = res.data.subjectId
  formLabelAlign.bankTitle = res.data.bankTitle
  formLabelAlign.subjectName = res.data.subjectName
  formLabelAlign.singleNum = res.data.singleNum
  formLabelAlign.multipleNum = res.data.multipleNum
  formLabelAlign.judgeNum = res.data.judgeNum
  centerDialogVisible.value = true
}

const handleChangeBankInfo = async () => {
  const res = await httpInstance.put(`/bank`, {
    bankId: formLabelAlign.bankId,
    subjectId: formLabelAlign.subjectId,
    bankTitle: formLabelAlign.bankTitle,
    subjectName: formLabelAlign.subjectName,
    singleNum: formLabelAlign.singleNum,
    multipleNum: formLabelAlign.multipleNum,
    judgeNum: formLabelAlign.judgeNum
  })
  if (res.code === 0) {
    centerDialogVisible.value = false
    ElMessage.success('Modified Bank information successfully.')
    await getBankList()
  } else {
    ElMessage.error('Failed to modify Bank information.')
  }
}

const handleDelete = async (index, row) => {
  const bankId = row.bankId
  console.log(bankId)
  const res = await httpInstance.delete(`/bank/remove/${bankId}`)
  if (res.code === 0) {
    ElMessage.success('Succeeded in deleting the user.')
    await getBankList()
  } else {
    ElMessage.error(res.message)
  }
}

const getBankList = async () => {
  const res = await httpInstance.get(`/bank/page?page=${page.value}&size=${size.value}`)
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
  getBankList()
}

onMounted(() => {
  getBankList()
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
