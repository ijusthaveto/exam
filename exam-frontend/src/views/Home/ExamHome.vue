<template>
  <div class="main-container">
    <ExamHeader></ExamHeader>
    <main>Content</main>
    <ExamFooter></ExamFooter>
  </div>
</template>

<script setup>
import ExamHeader from '@/components/ExamHeader.vue'
import ExamFooter from '@/components/ExamFooter.vue'
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

header {
  background-color: #4caf50;
  color: white;
  padding: 1rem;
  text-align: center;
}

main {
  flex: 1;
  padding: 1rem;
}

footer {
  background-color: #4caf50;
  color: white;
  padding: 1rem;
  text-align: center;
  margin-top: auto;
}
</style>
