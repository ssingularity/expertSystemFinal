<template>
  <el-card class="card">
    <el-table :data="tableData" style="width: 100%;margin-top:10px;" border height="100vh"
              cell-style="text-align: center"
              header-cell-style="text-align: center">
      <el-table-column>
        <el-table-column
          prop="company"
          label="工作单位"
          width="200"
        />
        <el-table-column
          prop="name"
          label="姓名"
          width="80"
        />
        <el-table-column
          prop="id"
          label="身份证号"
          width="200"
        />
        <el-table-column
          prop="gender"
          width="50"
          label="性别"
        />
        <el-table-column
          prop="jobPosition"
          label="职务"
        />
        <el-table-column
          prop="jobTitle"
          label="职称"
        />
        <el-table-column
          prop="type"
          label="专家类型"
          width="100"
        />
        <el-table-column
          prop="area"
          label="行业领域"
          width="150"
        />
        <el-table-column
          prop="phone"
          label="手机"
        />
        <el-table-column
          prop="secret"
          label="是否涉密人员"
        />
        <el-table-column
          prop="secretLevel"
          label="涉密程度"
          width="100"
        />
        <el-table-column
          fixed="right"
          label="操作"
          width="200">
          <template slot-scope="scope">
            <el-button round type="primary" size="small" @click="handleClick(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
    <div style="text-align: center; margin-top: 10px">
      <el-button type="" @click="dumpAsExcel">手动新增专家</el-button>
      <el-button type="primary" @click="uploadExcel">导入专家列表</el-button>
      <el-button type="success" @click="dumpAsExcel">导出专家列表</el-button>
      <el-button type="warning" @click="blockList">查看拉黑专家</el-button>
    </div>
  </el-card>
</template>

<script>
  import { getExperts } from '@/api/expert'

  export default {
    name: "expert",
    created: function () {
      this.loadData()
    },
    methods: {
      loadData: function () {
        getExperts()
          .then(res => {
            this.tableData = res.data;
          })
      },
      handleClick: function (data) {
        this.$router.push({path: '/expert_detail', query: {id: data.id}})
      }
    },
    data: function () {
      return {
        tableData: []
      }
    }
  }
</script>

<style scoped>

</style>
