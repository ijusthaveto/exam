<template>
  <el-main>
    <el-scrollbar height="650px">
      <QuestionList :singleList="singleList" :mutipleList="mutipleList" :boolList="boolList" />
    </el-scrollbar>
  </el-main>
</template>
  
<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import httpInstance from '@/utils/http.js';
import { useExamStore } from '@/stores/examStore';

const route = useRoute();
const examId = ref(route.query.examId);
const questionList = ref([]);
const store = useExamStore()
store.examId = examId.value

const getQuestionListByExamId = async () => {
  const res = await httpInstance.get(`/exam/start/${examId.value}`);
  console.log(res)
  questionList.value = res.data;
};

const singleList = computed(() => {
  return questionList.value.filter((item) => item.questionType === 'Single Choice');
});

const mutipleList = computed(() => {
  return questionList.value.filter((item) => item.questionType === 'Multiple Choice');
});

const boolList = computed(() => {
  return questionList.value.filter((item) => item.questionType === 'True/False');
});

onMounted(() => {
  getQuestionListByExamId();
});
</script>
  