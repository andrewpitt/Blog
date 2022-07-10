<template>
  <!--<img src="./assets/logo.png">-->
  <!--<div>
    <p>
      If Element Plus is successfully added to this project, you'll see an
      <code v-text="'<el-button>'"></code>
      below
    </p>
    <el-button type="primary">el-button</el-button>
  </div>-->
 <!-- <HelloWorld msg="Welcome to Your Vue.js App"/>
  <Blogs />-->
  <!--<div id="nav">
    <router-link to="/" Tag="a">blogs页面</router-link>
  </div>-->
  <router-view />

</template>

<script>
/*import HelloWorld from './components/HelloWorld.vue'
import Blogs from "@/views/Blogs";*/

export default {
  name: 'App',
  components: {
    /*Blogs,
    HelloWorld*/
  }
}
</script>

<style>
/*不然登录页最上方有一个60px高的空白*/
/*#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}*/
</style>
