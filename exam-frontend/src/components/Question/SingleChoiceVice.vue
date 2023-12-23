<template>
  <div class='center-container'>
    <el-table :data="filterTableData" style="width: 100%" >
      <el-table-column label='Question' width='600px'>
        <template #default='scope'>
          <el-popover effect='light' trigger='hover' placement='top' width='auto'>
            <template #default>
              <div v-for='(value, key) in JSON.parse(scope.row.options)' :key='key'>
                {{key}}. {{value}}
              </div>
            </template>
            <template #reference>
              {{scope.row.questionContent}}
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="Level">
        <template #default='scope'>
          <el-tag effect='dark' :type='handleLevelType(scope.row)'>{{scope.row.difficultyLevel}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label='Subject' prop='subjectName'/>
      <el-table-column label='Answer' prop='correctAnswer'/>
      <el-table-column label='Score' prop='score'/>

      <el-table-column align="right" fixed='right'>
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
      title="Modify Single Question Information"
      width="30%"
  >
    <div>
      <el-form
          :label-position="'right'"
          :model="formLabelAlign"
          label-width="100px"
          style="max-width: 460px"
      >
        <el-form-item label="Question">
          <el-input v-model="formLabelAlign.questionContent" />
        </el-form-item>
        <el-form-item label="Level">
          <el-input v-model="formLabelAlign.difficultyLevel" />
        </el-form-item>
        <el-form-item label="Subject">
          <el-input v-model="formLabelAlign.subjectName" />
        </el-form-item>
        <el-form-item label="Bank">
          <el-input v-model="formLabelAlign.bankTitle" />
        </el-form-item>
        <el-form-item label="Option">
          <el-input v-model="formLabelAlign.options" />
        </el-form-item>
        <el-form-item label="Answer">
          <el-input v-model="formLabelAlign.correctAnswer" />
        </el-form-item>
        <el-form-item label="Score">
          <el-input v-model="formLabelAlign.score" />
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
  questionId: null,
  subjectId: null,
  questionType: null,
  difficultyLevel: null,
  questionContent: null,
  options: null,
  bankId: null,
  correctAnswer: null,
  score: null,
  subjectName: null,
  bankTitle: null
})

const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.questionContent.toLowerCase().includes(search.value.toLowerCase())
    )
)

const page = ref(1)
const size = ref(15)

const handleEdit = async (index, row) => {
  const questionId = row.questionId
  const res = await httpInstance.get(`/question/info/${questionId}`)
  console.log(res)
  formLabelAlign.questionId = res.data.questionId
  formLabelAlign.subjectId = res.data.subjectId
  formLabelAlign.questionType = res.data.questionType
  formLabelAlign.difficultyLevel = res.data.difficultyLevel
  formLabelAlign.questionContent = res.data.questionContent
  formLabelAlign.options = res.data.options
  formLabelAlign.bankId = res.data.bankId
  formLabelAlign.correctAnswer = res.data.correctAnswer
  formLabelAlign.score = res.data.score
  formLabelAlign.subjectName = res.data.subjectName
  formLabelAlign.bankTitle = res.data.bankTitle
  centerDialogVisible.value = true
}

const handleChangeUserInfo = async () => {
  const res = await httpInstance.put(`/question`, {
    questionId: formLabelAlign.questionId,
    subjectId: formLabelAlign.subjectId,
    questionType: formLabelAlign.questionType,
    difficultyLevel: formLabelAlign.difficultyLevel,
    questionContent: formLabelAlign.questionContent,
    options: formLabelAlign.options,
    bankId: formLabelAlign.bankId,
    correctAnswer: formLabelAlign.correctAnswer,
    score: formLabelAlign.score,
    subjectName: formLabelAlign.subjectName,
    bankTitle: formLabelAlign.bankTitle
  })
  if (res.code === 0) {
    centerDialogVisible.value = false
    ElMessage.success('Modified Question information successfully.')
    await getSingleQuestionList()
  } else {
    ElMessage.error('Failed to modify Question information.')
  }
}

const handleDelete = async (index, row) => {
  const userId = row.userId
  console.log(userId)
  const res = await httpInstance.delete(`/user/remove/${userId}`)
  if (res.code === 0) {
    ElMessage.success('Succeeded in deleting the user.')
    await getSingleQuestionList()
  } else {
    ElMessage.error(res.message)
  }
}

const getSingleQuestionList = async () => {
  const res = await httpInstance.post(`/question/page`, {
    page: page.value,
    size: size.value,
    subjectId: null,
    questionType: 'Single Choice',
    difficultyLevel: null,
    keyword: null
  })
  tableData.value = res.data.records
  console.log(res)
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
  getSingleQuestionList()
}

const handleLevelType = (row) => {
  if (row.difficultyLevel === 'Easy') {
    return 'success'
  } else if (row.difficultyLevel === 'Medium') {
    return 'warning'
  } else {
    return 'danger'
  }
}


onMounted(() => {
  getSingleQuestionList()
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

    .el-table-column {
      height: 50px;

      .el-tag--success {
        --el-tag-bg-color: #1c2518;
        --el-tag-border-color: #25371c;
        --el-tag-hover-color: #67c23a;
      }

      .el-tag--warning {
        --el-tag-bg-color: var(--el-color-warning-light-9);
        --el-tag-border-color: var(--el-color-warning-light-8);
        --el-tag-hover-color: var(--el-color-warning);
      }

      .el-tag--danger {
        --el-tag-bg-color: var(--el-color-danger-light-9);
        --el-tag-border-color: var(--el-color-danger-light-8);
        --el-tag-hover-color: var(--el-color-danger);
      }
    }
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
