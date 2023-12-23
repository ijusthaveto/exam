<template>
  <div class="question-container">

    <el-affix position="top" :offset="100">
      <div class="countdown-item">
        <el-countdown class="countdown-timer" :value="examTime" />
        <el-link @click="handleSubmit" style="margin-top:10px">Submit</el-link>
      </div>
    </el-affix>

    <h1 class="exam-title" style="text-align: center">{{ examTitle }}</h1>

    <h2 class="paper-title">0x01. Single Choice</h2>
    <SingleChoice :data="props.singleList" />

    <h2 class="paper-title">0x02. Multiple Choice</h2>
    <MultipleChoice :data="props.mutipleList" />

    <h2 class="paper-title">0x03. True or False</h2>
    <TrueOrFalse :data="props.boolList" />



  </div>
</template>

<script setup>
import { onBeforeMount, ref, onUnmounted } from 'vue';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import { useExamStore } from '@/stores/examStore';
import httpInstance from '@/utils/http';

const props = defineProps(["singleList", "mutipleList", "boolList"]);
const store = useExamStore();
const router = useRouter();
const examId = store.examId;
const examTitle = ref('');
const examTime = ref(Date.now() + 1000 * store.limitTime);

const getExamInfoByExamId = async () => {
  const res = await httpInstance.get(`/exam/info/${examId}`);
  store.limitTime = res.data.limitTime;
  examTitle.value = res.data.examTitle;
  examTime.value = Date.now() + 1000 * 60 * store.limitTime;
};

const autoSavePaper = async () => {
  const res = await httpInstance.post('/exam/auto', {
    single: Array.from(store.singleAnswer),
    multiple: Array.from(store.mutipleAnswer),
    judge: Array.from(store.judgeAnswer),
    examId: store.examId,
  });
  if (res.code === 0) {
    ElMessage.success('The test paper is saved successfully.');
    return true
  } else {
    router.push('/login');
    ElMessage.error(res.message);
    return false
  }
}

const handleSubmit = () => {
  const success = autoSavePaper()
  if (success) {
    router.push({
      path: '/history',
    })
  }

};

onBeforeMount(() => {
  getExamInfoByExamId();

  const timer = setInterval(autoSavePaper, 60 * 1000);

  onUnmounted(() => {
    clearInterval(timer);
  });
});
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
  margin-bottom: 15px;
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
