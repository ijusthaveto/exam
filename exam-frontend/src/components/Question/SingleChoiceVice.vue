<template>
  <div class="container">
    <div class="manage-header">
      <el-form :inline="true">
        <el-form-item>
          <el-input class="search-item" v-model="keyword" placeholder="QuestionName" size="small" />
          <el-select class="search-item" v-model="subjectId" placeholder="Question Subject" size="small">
            <el-option v-for="item in questionSubjectList" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-select class="search-item" v-model="difficultyLevel" placeholder="Question Level" size="small">
            <el-option v-for="item in questionLevelList" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-link @click="onSubmit">Search</el-link>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
  
<script setup>
import { onMounted, ref } from 'vue'
import httpInstance from '@/utils/http';

const keyword = ref('')
const subjectId = ref(null)
const difficultyLevel = ref('')
const page = ref(1)
const size = ref(10)

const getSubjectList = async () => {
  const res = await httpInstance.get('/question/subject', {
    questionType: 'Single Choice'
  })
  console.log(res)
}

onMounted(() => {
  getSubjectList()
})
</script>
  
<style lang="less">
.container {
  display: flex;
  height: 100%;

  .search-item {
    margin: 5px;
    width: 200px;
  }
}
</style>