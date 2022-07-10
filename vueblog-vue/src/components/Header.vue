<template>
  <div>
    <el-menu
        :default-active="activeIndex"
        :default-openeds="defaultOpenedsArray"
        class="el-menu-demo"
        mode="horizontal"
        router
    >
      <el-menu-item index="0">logo</el-menu-item>
      <div class="flex-grow" />
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="/blog/add" href="">写博客</el-menu-item>
      <!--   elementplus官方文档错误 应是el-submenu标签   -->
      <el-submenu v-if="hasLogin" index="3">
        <template #title>我</template>
        <el-menu-item index="3-1" @click="logout">退出</el-menu-item>
      </el-submenu>
      <el-menu-item v-else index="/login" href="">未登录</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { ref } from 'vue'

const activeIndex = ref('1')

export default {
  name: "Header",
  data() {
    return {
      activeIndex: activeIndex,
      defaultOpenedsArray: [],
      hasLogin: false,
      user: {
        username: '',
        avatar: ""
      }
    }
  },
  created() {
    if(this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
      this.hasLogin = true
    }
  },
  methods: {
    logout() {
      const _this = this
      this.$axios.get('http://localhost:8081/logout', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then((res) => {
        _this.$store.commit('REMOVE_INFO');
        //该回调函数用了=>，箭头函数没有自己的this，所以回调函数里的this还是上下文的this，若没用=>，则需要在回调之前
        //将this先赋给变量，再使用这个变量
        this.hasLogin = false;
        _this.$router.push('/login');
      });
    }
  }
}
</script>

<style scoped>
  .flex-grow {
    flex-grow: 1;
  }
</style>