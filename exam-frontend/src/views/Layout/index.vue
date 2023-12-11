<template>
  <div class='main-container'>
    <el-container>
      <el-header class="nav">
        <LayoutNav/>
      </el-header>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import LayoutNav from '@/views/Layout/components/LayoutNav.vue'</script>

<style scoped>
.main-container {
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
