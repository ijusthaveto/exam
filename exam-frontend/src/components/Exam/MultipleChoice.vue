<template>
  <div>
    <div v-for="(item, index) in props.data" :key="index" style="margin-bottom:10px">
      <p :id="'question' + (index + 1)">
        {{ index + 1 }}. {{ item.questionContent }}
      </p>
      <el-checkbox-group v-model="userAnswer[index]" @change="updateUserAnswer(index, key)">
        <el-checkbox style="width:50%" :label="key" v-for="(value, key) in JSON.parse(item.options)" :key="key">
          {{ key }}. {{ value }}
        </el-checkbox>
      </el-checkbox-group>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import { useExamStore } from '@/stores/examStore';

const store = useExamStore()
const props = defineProps(["data"]);
const copyProps = [...props.data]

const userAnswer = ref([])

const updateUserAnswer = (index, selectedOptions) => {
  userAnswer[index] = selectedOptions
  copyProps[index].userAnswer = JSON.stringify(userAnswer.value[index])
  store.mutipleAnswer = copyProps
}
</script>
