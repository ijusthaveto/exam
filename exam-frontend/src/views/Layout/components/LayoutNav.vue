<template>
  <el-row :gutter='20' type="flex" class="row-bg" align="middle">
    <el-col :span="2">
      <router-link to='/' style="color: white;font-weight: bold">Online Exam</router-link>
    </el-col>
    <el-col :span="2" :offset='16'>
      <router-link to='/login' style='color: white'>Sign in</router-link>
    </el-col>
    <el-col :span="2">
      <router-link to='/register' style='color: white'>Sign up</router-link>
    </el-col>
    <el-col :span="2">
      <router-link to='/admin' style='color: white'>Admin here</router-link>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'LayoutNav'
}
</script>

<style scoped>
</style>
