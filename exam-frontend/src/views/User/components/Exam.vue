<template>
  <div class="main-container">
    <el-container>
      <question-container></question-container>
    </el-container>
  </div>
</template>

<script setup>
import QuestionContainer from "@/components/Exam/QuestionContainer.vue";
</script>

<style scoped>
.main-container {
  color: white;
  height: 100vh;
}

el-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}


el-main {
  flex: 1 0 auto;
}

.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  color: var(--el-color-primary);
}
</style>
