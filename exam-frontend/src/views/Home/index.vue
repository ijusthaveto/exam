<template>
  <el-row :gutter="20" justify="center" class="ad-container">
    <el-col :span="20" class="ad">Examination System</el-col>
    <el-col :span="20" class="ad-bottom">Flexible and Convenient Exam Administration</el-col>
    <el-col :span="20" style='font-size: 50px;font-weight: bold;'>
      <router-link to="/register" style="color: white">Sign up for Exam</router-link>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'index'
}
</script>

<style scoped>
.ad-container {
  height: 600px;
  padding: 100px;
}

.ad {
  font-size: 96px;
  color: white;
  font-weight: bold;
  margin: 10px;
}

.ad-bottom {
  font-size: 50px;
  font-weight: bold;
  color: white;
}

.ad-register {
  font-size: 16px;
}
</style>
