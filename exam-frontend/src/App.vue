<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
ul {
  list-style: none;
}
a {
  text-decoration: none;
}
* {
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Microsoft YaHei", "Helvetica", "Tahoma", "Geneva", "Arial", sans-serif;
  background-color: black;
}
</style>
