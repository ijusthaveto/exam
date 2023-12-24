<template>
  <el-row v-if="!loggedIn" :gutter="20" type="flex" class="row-bg" align="middle">
    <el-col :span="2">
      <router-link to='/' style="color: white;font-weight: bold">Online Exam</router-link>
    </el-col>
    <el-col :span="2" :offset="16">
      <router-link to='/login' style='color: white'>Sign in</router-link>
    </el-col>
    <el-col :span="2">
      <router-link to='/register' style='color: white'>Sign up</router-link>
    </el-col>
    <el-col :span="2">
      <router-link to='/adminLogin' style='color: white'>Admin here</router-link>
    </el-col>
  </el-row>

  <el-row v-else :gutter="20" align="middle" class="row-bg" type="flex">
    <el-col :span="20">
      <router-link style="color: white; font-weight: bold" to="/">Online Exam</router-link>
    </el-col>
    <el-col :span="2">
      <router-link style="color: white" to="/history">Exam history</router-link>
    </el-col>
    <el-col :span="2">
      <router-link style="color: white" to="/user">Your exam</router-link>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus';

const loggedIn = ref(false)
const router = useRouter()

const handleExit = () => {
  localStorage.clear()
  ElMessage.success('Exit successfully.')
  router.push('/login')
}

onMounted(() => {
  loggedIn.value = localStorage.getItem('loginId') ? true : false
})
</script>

<style scoped>
</style>
