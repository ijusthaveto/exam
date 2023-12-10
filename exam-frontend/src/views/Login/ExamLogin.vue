<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <label for="username">Username:</label>
      <input type="text" v-model="username" id="username" placeholder="username" required /><br />
      <label for="password">Password:</label>
      <input
        type="password"
        v-model="password"
        id="password"
        placeholder="password"
        required
      /><br />
      <button type="submit">Login</button>
    </form>
    <router-link to="/register">Don't have an account? Register here.</router-link>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import http from '@/apis/http'
import router from '../../router/index'

const username = ref('')
const password = ref('')

const login = async () => {
  const res = await http.post('/user/login', {
    username: username.value,
    password: password.value
  })

  if (res.code === 0) {
    localStorage.setItem('tokenValue', res.data.tokenValue)
    localStorage.setItem('tokenName', res.data.tokenName)
    alert(res.message)
    await router.push('/')
  } else {
    alert(res.message)
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

button {
  background-color: #007bff;
  color: #fff;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

router-link {
  margin-top: 10px;
  display: block;
  color: #007bff;
  text-decoration: none;
}

router-link:hover {
  text-decoration: underline;
}
</style>
