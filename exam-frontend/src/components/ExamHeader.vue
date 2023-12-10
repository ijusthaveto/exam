<template>
  <header>
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/login" v-if="!isLoggedIn">Sign up</router-link>
      <router-link to="/register" v-if="!isLoggedIn">Sign in</router-link>
      <button v-if="isLoggedIn" @click="logout">Sign out</button>
    </nav>
  </header>
</template>

<script setup>
import http from '../apis/http'
import router from '../router/index'

const isLoggedIn = !!localStorage.getItem('tokenValue')
const logout = async () => {
  const res = await http.post('/user/logout')
  console.log(res)
  localStorage.clear();
  await router.push('/login');
  alert('Logout successful')
}
</script>

<style scoped>
header {
  background-color: #4caf50;
  color: white;
  padding: 1rem;
  text-align: center;
}

nav {
  display: flex;
  justify-content: space-around;
  margin-bottom: 1rem;
}

nav a,
button {
  text-decoration: none;
  color: black;
  padding: 0.5rem 1rem;
  cursor: pointer;
}

nav a:hover,
button:hover {
  text-decoration: underline;
}
</style>
