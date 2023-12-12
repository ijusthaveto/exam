<template>
    <div class="question-container">
      <h1 class="exam-title" style="text-align: center">Exam 1</h1>
      <h2>0x01. Single Choice</h2>
      <SingleChoice :data="singleList" />
      <h2>0x02. Multiple Choice</h2>
      <MultipleChoice :data="mutipleList" />
      <h2>0x03. True or False</h2>
      <TrueOrFalse :data="boolList" />
    </div>
  </template>
  
  <script setup>
  import SingleChoice from "@/components/Exam/SingleChoice.vue";
  import MultipleChoice from "@/components/Exam/MultipleChoice.vue";
  import TrueOrFalse from "@/components/Exam/TrueOrFalse.vue";
  
  const props = defineProps(["singleList", "mutipleList", "boolList"]);
  </script>
  