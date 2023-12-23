<template>
  <el-main>
    <el-scrollbar height="785px">
      <QuestionList :singleList="singleList" :mutipleList="mutipleList" :boolList="boolList" />
    </el-scrollbar>
  </el-main>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import httpInstance from '@/utils/http.js';
import { useExamStore } from '@/stores/examStore';
import {ElMessage} from 'element-plus'
import { useRouter} from 'vue-router'

const route = useRoute();
const router = useRouter()
const examId = ref(route.query.examId);
const questionList = ref([]);
const store = useExamStore()
store.examId = examId.value

const getQuestionListByExamId = async () => {
  const res = await httpInstance.get(`/exam/start/${examId.value}`);
  if (res.code === 0) {
    questionList.value = res.data;
  } else {
    router.push('/login')
    ElMessage.error(res.message)
  }
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
