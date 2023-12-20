<template>
  <div>
    <div v-for="(item, index) in data" :key="index" style="margin-bottom:10px">
      <p :id="'question' + (index + 1)">
        {{ index + 1 }}. {{ item.questionContent }}
      </p>
      <el-checkbox-group>
        <el-checkbox style="width:50%" :label="index" v-for="(value, key) in JSON.parse(item.options)" :key="key">
          {{ key }}. {{ value }}
        </el-checkbox>
      </el-checkbox-group>
    </div>
  </div>
</template>
  
<script setup>
import { ref } from 'vue';
const props = defineProps(["data"]);
</script>
  