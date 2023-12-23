<template>
  <div class='center-container'>
    <el-form :model="form" label-width="200px">
      <el-form-item label="Exam Title">
        <el-input v-model="form.examTitle" style="width: 220px" />
      </el-form-item>
      <el-form-item label="Bank Name">
        <el-select v-model="form.bankId" placeholder="Select the Bank Name" style="width: 220px">
          <el-option v-for='item in bankList' :key='item.bankId' :label="item.bankTitle" :value="item.bankId" />
        </el-select>
      </el-form-item>
      <el-form-item label="Class No">
        <el-select v-model="form.classId" placeholder="Select the Class No" style="width: 220px">
          <el-option v-for='item in clazzList' :key='item.classId' :label="item.classNo" :value="item.classId" />
        </el-select>
      </el-form-item>
      <el-form-item label="Start Time">
        <el-date-picker
            v-model="form.startTime"
            placeholder="Pick a date"
            style="width: 220px"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="End Time">
        <el-date-picker
            v-model="form.endTime"
            placeholder="Pick a date"
            style="width: 220px"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="Single Question">
        <el-col :span="11">
          <el-input v-model.number="form.singleNum" style="width: 220px" />
        </el-col>
        <el-col :span="2" class="text-center">
          <span class="text-gray-500">-</span>
        </el-col>
        <el-col :span="11">
          <el-input v-model.number="form.singleScore" style="width: 220px" />
        </el-col>
      </el-form-item>
      <el-form-item label="Multiple Question">
        <el-col :span="11">
          <el-input v-model.number="form.multipleNum" style="width: 220px" />
        </el-col>
        <el-col :span="2" class="text-center">
          <span class="text-gray-500">-</span>
        </el-col>
        <el-col :span="11">
          <el-input v-model.number="form.multipleScore" style="width: 220px" />
        </el-col>
      </el-form-item>
      <el-form-item label="Judge Question">
        <el-col :span="11">
          <el-input v-model.number="form.boolNum" placeholder='Input number' style="width: 220px" />
        </el-col>
        <el-col :span="2" class="text-center">
          <span class="text-gray-500">-</span>
        </el-col>
        <el-col :span="11">
          <el-input v-model.number="form.boolScore" style="width: 220px" />
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {ElMessage} from "element-plus";
import httpInstance from '@/utils/http.js'

const bankList = ref([])
const clazzList = ref([])

const form = reactive({
  examTitle: null,
  startTime: null,
  endTime: null,
  classId: null,
  bankId: null,
  singleNum: null,
  singleScore: null,
  multipleNum: null,
  multipleScore: null,
  boolNum: null,
  boolScore: null
})

const getBankList = async () => {
  const res = await httpInstance.get('/bank/all')
  bankList.value = res.data
}
const getClazzList = async () => {
  const res = await httpInstance.get('/clazz/list')
  clazzList.value = res.data
}

const onSubmit = async () => {
  const res = await httpInstance.post('/exam/addTest', {
    examTitle: form.examTitle,
    startTime: form.startTime,
    endTime: form.endTime,
    classId: form.classId,
    bankId: form.bankId,
    multipleNum: form.multipleNum,
    multipleScore: form.multipleScore,
    singleNum: form.singleNum,
    singleScore: form.singleScore,
    boolNum: form.boolNum,
    boolScore: form.boolScore,
  })
  if (res.code === 0) {
    ElMessage.success('The exam was created successfully.')
  } else {
    ElMessage.error(res.message)
  }
}

onMounted(() => {
  getBankList()
  getClazzList()
})
</script>

<style lang='scss' scoped>
.center-container {
  display: grid;
  place-items: center;
  height: 700px;
  margin: 0;
}
</style>
