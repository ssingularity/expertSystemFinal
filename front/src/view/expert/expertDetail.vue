<template>
  <div class="card">
    <el-row>
      <el-col>
        <el-card class="card">
          <h2>专家详情</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="card" style="height: 70vh;">
          <el-form label-position="right" label-width="100px" style="text-align: center; margin-top: 8vh">
            <el-form-item label="姓名：">
              {{expert.name}}
            </el-form-item>
            <el-form-item label="性别：">
              <el-tag type="warning">{{expert.gender}}</el-tag>
            </el-form-item>
            <el-form-item label="类型：">
              <el-tag>{{expert.type}}</el-tag>
            </el-form-item>
            <el-form-item label="领域：">
              <el-tag type="success">{{expert.area}}</el-tag>
            </el-form-item>
            <el-form-item label="单位：">
              <el-tag type="info">{{expert.company}}</el-tag>
            </el-form-item>
            <el-form-item label="机密类型">
              <el-tag type="info">{{expert.secretLevel}}</el-tag>
            </el-form-item>
            <el-form-item label="是否拉黑">
              <el-tag type="danger">{{expert.isBlocked}}</el-tag>
            </el-form-item>
          </el-form>
          <el-row style="text-align: center"><el-button style="margin-top: 30px" type="primary" round @click="edit">修改信息</el-button></el-row>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="card" style="height: 70vh">
          <el-table :data="tableData" border style="width: 100%; margin-right: 20px" height="60vh"
                    cell-style="text-align: center"
                    header-cell-style="text-align: center">
            <el-table-column
              prop="programID"
              label="项目编号"
            />
            <el-table-column
              prop="secret"
              label="是否涉密项目"
            />
            <el-table-column
              prop="secretLevel"
              label="涉密程度"
            />
            <el-table-column
              prop="startTime"
              label="评审开始时间"
            />
            <el-table-column
              prop="endTime"
              label="评审结束时间"
            />
            <el-table-column
              label="评价"
              width="200"
            >
              <template slot-scope="scope">
                <div v-if="scope.row.comment === null">暂无评价</div>
                {{scope.row.comment}}
              </template>
            </el-table-column>
            <el-table-column
              prop="score"
              label="分数"
              width="50"
            />
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { getRecordByExpertId } from '@/api/record'
  import { getExpertById } from '@/api/expert'

  export default {
    name: "expertDetail",
    data(){
      return {
        expert: {
          name:'',
          gender:'',
          age:'',
          type:'',
          area:'',
          company:'',
          secretLevel: '',
          isBlocked: '',
        },
        tableData:[]
      }
    },
      created: function () {
        this.load()
      },
      methods: {
        load() {
          getExpertById(this.$route.query.id)
            .then(res => {
              this.expert = res.data
            })
          getRecordByExpertId(this.$route.query.id)
            .then(res => {
              this.tableData = res.data
            })
        },
        edit(){
            this.$router.push({path: '/reverseExpert', query: {id: this.$route.query.id}})
        }
        }
    }
</script>

<style scoped>
</style>
