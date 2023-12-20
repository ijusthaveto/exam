<template>
  <div>
    <div v-for="(item, index) in data" :key="index" style="margin-bottom:10px">
      <p :id="'question' + (index + 1)">
        {{ index + 1 }}. {{ item.questionContent }}
      </p>
      <p>
        <el-radio-group>
          <div>
            <el-radio class="radio-item" :label="key" v-for="(value, key) in JSON.parse(item.options)" :key="key">{{ key }}. {{ value }}</el-radio>
          </div>
        </el-radio-group>
      </p>
    </div>
  </div>
</template>
  
<script setup>
const props = defineProps(["data"]);
</script>

<style scoped>
.radio-item {
  width: 50%;
}
</style>
  