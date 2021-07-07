<template>
  <div class="card">
    <el-row>
      <el-col>
        <el-card class="card">
          <h2>项目详情</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="card" style="min-height: 60vh;">
          <el-form label-position="right" label-width="100px" style="text-align: center; margin-top: 5vh">
            <el-form-item label="领域：">
              <el-tag>{{program.area}}</el-tag>
            </el-form-item>
            <el-form-item label="单位：">
              <el-tag type="warning">{{program.company}}</el-tag>
            </el-form-item>
            <el-form-item label="描述：">
              {{program.keyword}}
            </el-form-item>
            <el-form-item label="开始时间：">
              <el-tag type="success">{{program.startTime}}</el-tag>
            </el-form-item>
            <el-form-item label="结束时间：">
              <el-tag type="danger">{{program.endTime}}</el-tag>
            </el-form-item>
            <el-form-item label="所需人数：">
              <el-tag type="info">{{program.number}}</el-tag>
            </el-form-item>
            <el-form-item label="状态：">
              <el-tag type="success">{{program.state}}</el-tag>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="card" >
          <el-table :data="tableData" stripe style="width: 100%" height="80vh"
                    :cell-style="style"
                    :header-cell-style="style">
            <el-table-column>
              <el-table-column
                prop="expertName"
                label="姓名"
              />
              <el-table-column
                prop="type"
                label="类型"
              />
              <el-table-column
                prop="company"
                label="单位"
              />
              <el-table-column
                prop="phone"
                label="手机号"
              />
              <el-table-column
                prop="expertArea"
                label="领域"
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
              <el-table-column
                fixed="right"
                label="操作"
                width="160">
                <template slot-scope="scope">
                  <el-button round size="small" @click="handleComment(scope.row)">评价</el-button>
                  <el-button round size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
          <el-row style="margin-top: 20px; text-align: right">
            <el-button :disabled="program.state === 1" @click="autoChoose">自动选择项目专家</el-button>
            <el-button type="success" @click="dumpAsExcel">导出项目专家</el-button>
            <el-button :disabled="program.state === 1" type="danger" @click="end">结束项目</el-button>
          </el-row>
          <el-dialog title="输入评价" :visible.sync="dialogVisible">
            <el-form>
              <el-form-item label="评价">
                <el-input
                  v-model="comment"
                  clearable
                  placeholder="请填写描述"
                />
              </el-form-item>
              <el-form-item label="分数">
                <el-slider v-model="rate" style="margin-top: 50px"></el-slider>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="pushComment">确 定</el-button>
            </span>
          </el-dialog>
        </el-card>
      </el-col>

      <el-col v-if="program.state === 0">
        <el-card class="card">
          <el-row>
            <el-col :offset="2" :span="18">
              <el-input placeholder="请输入内容" v-model="search">
                <el-select v-model="select" slot="prepend" placeholder="请选择" style="width: 150px">
                  <el-option label="姓名" value="1"></el-option>
                  <el-option label="专家类型" value="2"></el-option>
                  <el-option label="行业领域" value="3"></el-option>
                </el-select>
                <el-button type="primary" slot="append" icon="el-icon-search" @click="searchFor"></el-button>
              </el-input>
            </el-col>
            <el-col :offset="1" :span="2">
              <el-button type="primary" size="medium" @click="reset">重置</el-button>
            </el-col>
          </el-row>
          <el-table :data="candidate" style="width: 100%;margin-top:10px;"
                    :cell-style="style"
                    border min-height="50vh"
                    :header-cell-style="style">
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
                  <el-button size="small" round @click="handleClick(scope.row)" >查看</el-button>
                  <el-button size="small" type="primary" round @click="handlePick(scope.row)">选择</el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
          <div style="text-align: center">
            <el-pagination
              @current-change="getExpertsByOffset"
              :current-page.sync="currentPage"
              background
              layout="prev, pager, next"
              :total="total"
              :page-size="20"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { getProgramById, autoChooseProgramById, endProgramById  } from '@/api/program'
  import { getRecordByProgramId, insertRecord, deleteRecord, updateRecord } from '@/api/record'
  import { getExpertsByOffset } from '@/api/expert'
  import { Message } from 'element-ui'

  export default {
    name: "program",
    data() {
      return {
        tableData: [],
        candidate: [],
        program: {
          keyword: '',
          area: '',
          startTime: '',
          endTime: '',
          number: 0,
          company: '',
          state: 0,
        },
        search: '',
        select: '',
        currentPage: 1,
        comment: '',
        rate: '',
        dialogVisible: false,
        total: 0,
        style: {
          'text-align': 'center'
        }
      }
    },
    mounted: function () {
      this.load()
    },
    methods: {
      dumpAsExcel: function () {
        window.open(`/api/program/${this.$route.query.id}/record/excel`, "_blank");
      },
      autoChoose: function () {
        autoChooseProgramById(this.$route.query.id)
          .then(res => {
            Message({
              message: '自动选择专家成功',
              type: 'success',
              duration: 5 * 1000
            })
            this.load()
          })
          .catch(error => {
            this.load()
          })
      },
      load() {
        getProgramById(this.$route.query.id)
          .then(res => {
            this.program = res.data
          })
        getRecordByProgramId(this.$route.query.id)
          .then(res => {
            this.tableData = res.data
          })
        getExpertsByOffset('', '', 1)
          .then(res => {
            this.candidate = res.data.experts
            this.total = res.data.total
          })
      },
      end() {
        endProgramById(this.$route.query.id)
          .then(res => {
            Message({
              message: '成功结束项目',
              type: 'success',
              duration: 5 * 1000
            })
            this.load()
          })
      },
      handleComment(row) {
        this.dialogVisible = true;
        this.clickedrecord = row
      },
      reset() {
        this.search = ''
        this.select = ''
        this.currentPage = 1
        getExpertsByOffset('', '', 1)
          .then(res => {
            this.candidate = res.data.experts
            this.total = res.data.total
          })
      },
      pushComment() {
        let data = {
          id: this.clickedrecord.id,
          expertID: this.clickedrecord.expertID,
          programID: this.$route.query.id,
          expertName: this.clickedrecord.expertName,
          type: this.clickedrecord.type,
          expertArea: this.clickedrecord.expertArea,
          phone: this.clickedrecord.phone,
          company: this.clickedrecord.company,
          secret: this.program.secret,
          secretLevel: this.program.secretLevel,
          startTime: this.program.startTime,
          endTime: this.program.endTime,
          comment: this.comment,
          score: this.rate

        }
        updateRecord(data)
          .then(res => {
            Message({
              message: '评价成功',
              type: 'success',
              duration: 5 * 1000
            })
            this.load()
            this.dialogVisible = false
          })
      },
      handleDelete(row) {
        deleteRecord(row.id)
          .then(res => {
            Message({
              message: '成功删除专家',
              type: 'success',
              duration: 5 * 1000
            })
            this.load()
          })
      },
      getExpertsByOffset(offset) {
        getExpertsByOffset(this.select, this.search, offset)
          .then(res => {
            this.candidate = res.data.experts
            this.total = res.data.total
          })
      },
      searchFor() {
        this.currentPage = 1
        getExpertsByOffset(this.select, this.search, 1)
          .then(res => {
            this.candidate = res.data.experts
            this.total = res.data.total
          })
      },
      handleClick(row) {
        this.$router.push({path: '/expert_detail', query: {id: row.id}})
      },
      handlePick(row) {
        let data = {
          expertID: row.id,
          expertName: row.name,
          type: row.type,
          expertArea: row.area,
          phone: row.phone,
          company: row.company,
          secret: this.program.secret,
          secretLevel: this.program.secretLevel,
          programID: this.$route.query.id,
          startTime: this.program.startTime,
          endTime: this.program.endTime

        }
        console.log(data)
        insertRecord(data)
          .then(res => {
            Message({
              message: '成功选择专家',
              type: 'success',
              duration: 5 * 1000
            })
            this.load()
          })
      }
    }
  }
</script>

<style scoped>
</style>
