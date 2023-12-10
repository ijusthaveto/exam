<template>
  <footer>
    <p>&copy; 2023 My App. All rights reserved.</p>
  </footer>
</template>

<script>
export default {
  name: "ExamFooter"
}
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

main {
  flex: 1;
  padding: 1rem;
}

footer {
  background-color: #4CAF50;
  color: white;
  padding: 1rem;
  text-align: center;
  margin-top: auto;
}
</style>
