<template>
  <div class="question-container">

    <el-affix position="top" :offset="100">
      <div class="countdown-item">
        <el-countdown class="countdown-timer" :value="examTime" />
        <el-link @click="handleSubmit" style="margin-top:10px">Submit</el-link>
      </div>
    </el-affix>

    <h1 class="exam-title" style="text-align: center">Exam 1</h1>

    <h2 class="paper-title">0x01. Single Choice</h2>
    <SingleChoice :data="props.singleList" />

    <h2 class="paper-title">0x02. Multiple Choice</h2>
    <MultipleChoice :data="props.mutipleList" />

    <h2 class="paper-title">0x03. True or False</h2>
    <TrueOrFalse :data="props.boolList" />



  </div>
</template>
  
<script setup>
import { onMounted, ref } from 'vue'
import SingleChoice from "@/components/Exam/SingleChoice.vue";
import MultipleChoice from "@/components/Exam/MultipleChoice.vue";
import TrueOrFalse from "@/components/Exam/TrueOrFalse.vue";
import { useExamStore } from '@/stores/examStore';
import httpInstance from '@/utils/http';

const props = defineProps(["singleList", "mutipleList", "boolList"]);
const store = useExamStore()
const examId = store.examId
const limitTime = store.limitTime
const examTime = ref(Date.now() + 1000 * 60 * limitTime)

const getExamInfoByExamId = async () => {
  const res = await httpInstance.get(`/exam/info/${examId}`)
  store.limitTime = res.data.limitTime
}

onMounted(() => {
  getExamInfoByExamId()
})

const handleSubmit = async () => {
  console.log('single', store.singleAnswer)
  console.log('multiple', store.mutipleAnswer)
  console.log('judge', store.judgeAnswer)
  const res = await httpInstance.post('/exam/auto', {
    single: Array.from(store.singleAnswer),
    multiple: Array.from(store.mutipleAnswer),
    judge: Array.from(store.judgeAnswer),
    examId: store.examId
  })
    

  console.log(res)
}

</script>

<style scoped>
.exam-title {
  font-family: 'Times New Roman', Times, serif;
  font-size: 60px;
}

.paper-title {
  font-family: 'Times New Roman', Times, serif;
  font-size: 50px;
  display: inline-block;
  border-bottom: 5px solid white;
  line-height: 1.5em;
}

.countdown-item {
  height: auto;
  width: 200px;
  position: absolute;
  right: 0;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  border-radius: 40px;
  border: 3px solid white;
}

.countdown-timer {
  display: block;
  --el-statistic-content-color: white;
  --el-statistic-content-font-size: 30px;
}
</style>
  