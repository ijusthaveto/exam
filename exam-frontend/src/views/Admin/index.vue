<template>
  <div class="main-container">
    <el-container>
      <el-header class="nav">
        <el-row :gutter="20" type="flex" class="row-bg" align="middle">
          <el-col :span="20">
            <router-link to="/" style="color: white; font-weight: bold">Online Exam</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Your profile</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/admin" style="color: white">Sign out</router-link>
          </el-col>
        </el-row>
      </el-header>
      <el-container>
        <el-aside width="260px">
          <el-scrollbar height="690px">
            <el-menu default-active="2" text-color="white" background-color="#0d1117" style="border:none"
              @open="handleOpen" @close="handleClose">
              <el-sub-menu index="1">
                <template #title>
                  <el-icon>
                    <user />
                  </el-icon>
                  <span><router-link class="white-link" to="/admin/student">Student Information</router-link></span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="1-1"><router-link class="white-link" to="/admin/student/import">Student
                      Import</router-link></el-menu-item>
                  <el-menu-item index="1-2"><router-link class="white-link" to="/admin/student">Student
                      Management</router-link></el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="2">
                <template #title>
                  <el-icon>
                    <EditPen />
                  </el-icon>
                  <span><router-link class="white-link" to="/admin/question">Question Management</router-link></span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="2-1"><router-link class="white-link" to="/admin/question">Question
                      Bank</router-link></el-menu-item>
                  <el-menu-item index="2-2"><router-link class="white-link" to="/admin/question/single">Single Choice
                      Question</router-link></el-menu-item>
                  <el-menu-item index="2-3"><router-link class="white-link" to="/admin/question/multi">Multiple Choice
                      Question</router-link></el-menu-item>
                  <el-menu-item index="2-4"><router-link class="white-link" to="/admin/question/bool">True/False
                      Question</router-link></el-menu-item>
                  <el-menu-item index="2-5"><router-link class="white-link" to="/admin/question/import">Test Paper
                      Import</router-link></el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="3">
                <template #title>
                  <el-icon>
                    <Tickets />
                  </el-icon>
                  <span><router-link class="white-link" to="/admin/exam">Exam Paper Management</router-link></span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="3-1"> <router-link class="white-link" to="/admin/exam/generate">Exam Paper
                      Generation</router-link></el-menu-item>
                  <el-menu-item index="3-2"> <router-link class="white-link" to="/admin/exam">Exam Paper
                      Administration</router-link></el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="4">
                <template #title>
                  <el-icon>
                    <Checked />
                  </el-icon>
                  <span><router-link class="white-link" to="/admin/grade">Grade Management</router-link></span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="4-1"><router-link class="white-link" to="/admin/grade">Student Grade
                      Management</router-link></el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
            </el-menu>
          </el-scrollbar>
        </el-aside>
        <el-main>
          <RouterView />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {
  EditPen,
  User,
  Tickets,
  Checked
} from '@element-plus/icons-vue'
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
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

.white-link {
  color: white;
}</style>

