<script setup>
import httpInstance from '@/utils/http';
import {ref} from 'vue'

const secretCode = ref('')
const adminLogin = async () => {
  const res = httpInstance.post('/admin/login', {
    secretCode: secretCode.value
  })
  console.log(res)
}

</script>

<template>
  <div class="main-container">
    <el-container>
      <el-header class="nav">
        <el-row :gutter="20" type="flex" class="row-bg" align="middle">
          <el-col :span="20">
            <router-link to="/" style="color: white; font-weight: bold">Online Exam</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Admin profile</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Sign out</router-link>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-form style="
            min-width: 460px;
            position: absolute;
            top: 40%;
            left: 50%;
            transform: translate(-50%, -50%);
          ">
          <el-form-item style="font-family: ui-monospace">
            How have you been lately?<br />
            Let's begin the adventure
          </el-form-item>
          <el-form-item>
            <el-input placeholder="Secret Code" v-model="secretCode" type="text" style="height: 50px" />
          </el-form-item>
          <el-form-item>
            <el-link @click="adminLogin" style="position: absolute; right: 0%; font-size: 16px">Continue</el-link>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

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

.exam-container {
  width: 200px;
  display: grid;
  padding: 10px;
  margin: 10px;
  place-items: center;
  /*background-color: rebeccapurple;*/
  border: 1px white solid;
}

.exam-item {
  width: 150px;
  margin: 5px;
  display: grid;
  place-items: center;
}
</style>



