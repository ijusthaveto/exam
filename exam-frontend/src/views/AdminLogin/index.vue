<script setup>
import AdminLoginForm from '@/components/Admin/AdminLoginForm.vue'

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
        <AdminLoginForm />
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



