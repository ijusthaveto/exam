<template>
  <div class="main-container">
    <el-container>
      <el-header class="nav">
        <el-row :gutter="20" type="flex" class="row-bg" align="middle">
          <el-col :span="2">
            <router-link to="/" style="color: white;font-weight: bold">Online Exam</router-link>
          </el-col>
          <el-col :span="2" :offset="18">
            <router-link to="/register" style="color: white">Sign up</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Admin here</router-link>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-form
          style="
            min-width: 460px;
            position: absolute;
            top: 40%;
            left: 50%;
            transform: translate(-50%, -50%);
          "
        >
          <el-form-item style="font-family: ui-monospace">
            How have you been lately?<br />
            Let's begin the adventure
          </el-form-item>
          <el-form-item>
            <el-input placeholder="Username" v-model="username" type="text" style="height: 50px" />
          </el-form-item>
          <el-form-item>
            <el-input
              placeholder="Password"
              v-model="password"
              type="password"
              style="height: 50px"
            />
          </el-form-item>
          <el-form-item>
            <el-link @click="login" style="position: absolute; right: 0%; font-size: 16px"
              >Continue</el-link
            >
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import httpInstance from '@/utils/http'
import {ElMessage} from "element-plus";
import router from '@/router/index'

const username = ref('')
const password = ref('')

const login = async () => {
  const res = await httpInstance.post('/user/login', {
    username: username.value,
    password: password.value
  })
  if (res.code === 0) {
    ElMessage.success('Login successful.')
    localStorage.setItem('tokenName', res.data.tokenName)
    localStorage.setItem('tokenValue', res.data.tokenValue)
    localStorage.setItem('loginId', res.data.loginId)
    router.push('/user')
  } else {
    ElMessage.error(res.data.message)
    router.push('/login')
  }

}
</script>

<style scoped>
.main-container {
  color: white;
  height: 100vh;
}
el-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.nav {
  flex: 0 0 auto;
  padding-top: 20px;
  background-color: #24292f;
}
el-main {
  flex: 1 0 auto;
}
</style>
