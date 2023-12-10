<template>
  <div class="register-container">
    <h2>Register</h2>
    <form @submit.prevent="register">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" required><br>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required><br>
      <label for="confirmPassword">Confirm Password:</label>
      <input type="password" id="confirmPassword" v-model="confirmPassword" required><br>
      <button type="submit">Register</button>
    </form>
    <router-link to="/login">Already have an account? Login here.</router-link>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const username = ref("");
const password = ref("");
const confirmPassword = ref("");
const register = async () => {
  // Check if passwords match
  if (password.value !== confirmPassword.value) {
    console.error("Passwords do not match");
    return;
  }

  try {
    const response = await axios.post('http://localhost:8090/user/register', {
      username: username.value,
      password: password.value,
      confirmPassword: confirmPassword.value
    });

    // Handle the response as needed
    console.log("Registration successful:", response.data);
  } catch (error) {
    // Handle errors
    console.error("Registration failed:", error.response.data);
  }
};
</script>

<style scoped>
.register-container {
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
  background-color: #28a745;
  color: #fff;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #218838;
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
