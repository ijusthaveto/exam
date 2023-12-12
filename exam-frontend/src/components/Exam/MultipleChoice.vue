<template>
    <div>
      <div v-for="(item, index) in data" :key="index">
        <p :id="'question' + (index + 1)">
          {{ index + 1 }}.{{ item.questionContent }}
        </p>
        <el-checkbox-group>
          <el-checkbox :label="index" v-for="index in JSON.parse(item.options)" :key="index">
            {{ index }}.{{ JSON.parse(item.options)[index] }}
          </el-checkbox>
        </el-checkbox-group>
      </div>
    </div>
  </template>
  
  <script setup>
  const props = defineProps(["data"]);
  </script>
  