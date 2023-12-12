<template>
    <div>
      <div v-for="(item, index) in data" :key="index">
        <p :id="'question' + (index + 1)">
          {{ index + 1 }}.{{ item.questionContent }}
        </p>
        <el-radio-group>
          <el-radio label="true">True</el-radio>
          <el-radio label="false">False</el-radio>
        </el-radio-group>
      </div>
    </div>
  </template>
  
  <script setup>
  const props = defineProps(["data"]);
  </script>
  