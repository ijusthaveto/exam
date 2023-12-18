<template>
    <div>
      <div v-for="(item, index) in data" :key="index">
        <p :id="'question' + (index + 1)">
          {{ index + 1 }}.{{ item.questionContent }}
        </p>
        <p>
          <el-radio-group>
            <el-radio :label="2" v-for="(value, key) in JSON.parse(item.options)" :key="key">
              {{ key }}. {{ value }}
            </el-radio> 
          </el-radio-group>
        </p>
      </div>
    </div>
  </template>
  
  <script setup>
  const props = defineProps(["data"]);
  console.log(JSON.parse(props.data[1].options).A)
  </script>
  