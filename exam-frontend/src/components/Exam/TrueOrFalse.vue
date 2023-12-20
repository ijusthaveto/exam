<template>
  <div>
    <div v-for="(item, index) in data" :key="index" style="margin-bottom:10px">
      <p :id="'question' + (index + 1)">
        {{ index + 1 }}. {{ item.questionContent }}
      </p>
      <el-radio-group v-model="item.userAnswer" @change="updateUserAnswer">
        <el-radio label="TRUE">True</el-radio>
        <el-radio label="FALSE">False</el-radio>
      </el-radio-group>
    </div>
  </div>
</template>
  
<script setup>
import { useExamStore } from "@/stores/examStore";

const props = defineProps(["data"]);
const store = useExamStore()

const updateUserAnswer = () => {
  store.judgeAnswer = props.data
}
</script>

  