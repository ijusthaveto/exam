<template>
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
        <el-link @click="handleLogin" style="position: absolute; right: 0%; font-size: 16px">Continue</el-link>
      </el-form-item>
    </el-form>
  </template>

<script setup>
import { ref } from 'vue';
import { useAdminStore } from '@/stores/adminStore'
import router from '@/router/index';
import {ElMessage} from 'element-plus'

const secretCode = ref('');
const adminStore = useAdminStore();

const handleLogin = async () => {
  const { success, message } = await adminStore.adminLogin(secretCode.value);

  if (success) {
    router.push('/admin');
    ElMessage.success('Administrator successfully logged in.')
  } else {
    ElMessage.error(message);
  }
};
</script>
