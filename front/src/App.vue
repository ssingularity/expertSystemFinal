<template>
  <div id="app">
    <el-menu
      style="opacity: 0.9; margin: 5px"
      class="el-menu-demo"
      mode="horizontal"
      :router="true"
      background-color="#58A5F5"
      text-color="#fff"
      active-text-color="#FFEFBA"
      default-active="/expert"
    >
      <el-menu-item index="/expert">
        专家列表
      </el-menu-item>
      <el-menu-item index="/program">
        项目汇总
      </el-menu-item>
    </el-menu>
    <transition name="el-fade-in-linear" mode="out-in">
      <router-view />
    </transition>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  #app {
    background: #EEEEEE;
  }
</style>
