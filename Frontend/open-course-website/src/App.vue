<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/login">Login</router-link> |
    <router-link to="/main">Main</router-link> |
    <router-link to="/course">Course</router-link> |
    <router-link to="/chapter">Chapter</router-link> |
    <router-link to="/admin">Admin</router-link> |
    <router-link to="/rank">Rank</router-link> |
    <router-link to="/remark">Remark</router-link> |
    <router-link to="/test">Test</router-link> |
  </nav>
  <router-view/>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
<script setup>
</script>