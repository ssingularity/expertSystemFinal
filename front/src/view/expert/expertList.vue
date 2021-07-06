<template>
  <el-card class="card">
    <el-table :data="tableData.filter(data => !search || data.name.includes(search))" style="width: 100%;margin-top:10px;" border height="100vh"
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
    <div style="text-align: center">
      <el-pagination
        @current-change="getExpertsByOffset"
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="20"
      />
    </div>
    <div style="text-align: center; margin-top: 10px">
      <el-button type="" @click="addExpert">手动新增专家</el-button>
      <el-button type="primary" @click="uploadExcel">导入专家列表</el-button>
      <el-button href="/api/expert/excel" type="success" @click="dumpAsExcel">导出专家列表</el-button>
      <el-button type="warning" @click="blockList">查看拉黑专家</el-button>
    </div>
    <el-dialog
      title="上传专家信息"
      :visible.sync="dialogVisible"
      width="500px">
      <div style="text-align: center">
        <el-upload
          class="upload-demo"
          drag
          action="/api/expert/excel"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件</div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="afterUpload">完 成</el-button>
        </span>
    </el-dialog>
    <el-dialog
      title="拉黑专家列表"
      :visible.sync="blockedExpert"
      width="500px">
      <p>身份证号(ID) - 姓名</p>
      <p v-for="blockedExpert in blocked" :key="blockedExpert.id">
        {{blockedExpert.id}} - {{blockedExpert.name}}
      </p>
    </el-dialog>
  </el-card>
</template>

<script>
  import { getExperts, getBlockedExperts, getSize, getExpertsByOffset } from '@/api/expert'

  export default {
    name: "expert",
    created: function () {
      this.loadData()
    },
    methods: {
      loadData: function () {
        getExperts()
          .then(res => {
            this.tableData = res.data
          })
        getBlockedExperts()
          .then(res => {
              this.blocked = res.data
          })
        getSize()
          .then(res => {
            this.total = res.data
          })
      },
      handleClick: function (data) {
        this.$router.push({path: '/expert_detail', query: {id: data.id}})
      },
      addExpert: function (data) {
        this.$router.push({path: '/add_expert', query: {id: data.id}})
      },
      dumpAsExcel:function() {
        window.open("/api/expert/excel", "_blank");
      },
      uploadExcel:function() {
        this.dialogVisible = true
      },
      afterUpload:function () {
        location.reload()
      },
      blockList:function() {
        this.blockedExpert = true
      },
      getExpertsByOffset(offset) {
        getExpertsByOffset(offset)
          .then(res => {
            this.tableData = res.data
          })
      }
    },
    data: function () {
      return {
        tableData: [],
        dialogVisible: false,
        blockedExpert: false,
        search: '',
        total: 0,
        blocked: []
      }
    }
  }
</script>

<style scoped>

</style>
