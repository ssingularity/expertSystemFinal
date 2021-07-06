<template>
  <el-card class="card">
    <el-table :data="tableData" style="width: 100% ;margin-top:10px;" border
              cell-style="text-align: center"
              header-cell-style="text-align: center">
      <el-table-column
        width="200"
        prop="id"
        label="项目编号"
      />
      <el-table-column
        prop="area"
        label="领域"
      />
      <el-table-column
        prop="keyword"
        label="备注"
      />
      <el-table-column
        prop="startTime"
        sortable
        label="评审开始时间"
      />
      <el-table-column
        prop="endTime"
        sortable
        label="评审结束时间"
      />
      <el-table-column
        sortable
        prop="state"
        label="状态"
      />
      <el-table-column
        prop="number"
        label="所需人数"
      />
      <el-table-column
        fixed="right"
        width="150">
        <template slot="header" slot-scope="scope">
          <el-button size="small" icon="el-icon-circle-plus-outline" type="success" @click="addProgram">随机选择专家</el-button>
        </template>
        <template slot-scope="scope">
          <el-button round @click="handleClick(scope.row)" size="small" type="primary">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="text-align: center">
      <el-pagination
        @current-change="getProgramsByOffset"
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="20"
      />
    </div>
  </el-card>
</template>

<script>
  import { getPrograms, getSize, getProgramsByOffset } from '@/api/program'

  export default {
    name: "programList",
    created: function () {
      this.loadData()
    },
    methods: {
      loadData: function () {
        getPrograms()
          .then(res => {
            this.tableData = res.data;
          })
        getSize()
          .then(res => {
            this.total = res.data
          })
      },
      handleClick(row) {
        this.$router.push({path: '/program_detail', query: {id: row.id}})
      },
      addProgram() {
        this.$router.push('/add_program')
      },
      getProgramsByOffset(offset) {
        getProgramsByOffset(offset)
          .then(res => {
            this.tableData = res.data
          })
      }
    },
    data: function () {
      return {
        tableData: [],
        total: 0
      }
    }
  }
</script>

<style scoped>

</style>
