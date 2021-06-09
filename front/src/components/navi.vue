<template>
    <div>
        <div id="guide" style="text-align:center">
            <el-button type="primary" icon="el-icon-user-solid" @click="toExpert()">专家列表</el-button>
            <el-button type="primary" icon="el-icon-s-grid" @click="toProgram()">项目列表</el-button>
            <el-button type="primary" icon="el-icon-edit" @click="toEditExpert()">新增专家</el-button>
            <el-button type="primary" icon="el-icon-edit" @click="toaddProgram()">新增项目</el-button>
        </div>
    </div>
</template>

<script>
    // import addProgram from "@/view/program/addProgram";

    export default {
        name: "navi",
     methods: {
         toExpert: function () {
             this.$router.push({path: '/expert'})
         },
         toProgram: function () {
             this.$router.push({path: '/programList'})
         },
         toEditExpert: function () {
             this.$router.push({path: '/editExpert'})
         },
         toaddProgram: function () {
             this.$router.push({path: '/addProgram'})
         },

     }
    }
</script>

<style scoped>

</style>