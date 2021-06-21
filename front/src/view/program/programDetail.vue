<template>
  <div class="card">
    <el-row>
      <el-col>
        <el-card class="card">
          <h2>项目详情</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="card" style="height: 60vh;">
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
        <el-card class="card" style="height: 60vh">
          <el-table :data="tableData" stripe style="width: 100%" height="50vh"
                    cell-style="text-align: center"
                    header-cell-style="text-align: center">
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
            <el-button :disabled="program.state === 1" type="danger" @click="end">冻结项目</el-button>
          </el-row>
          <el-dialog title="输入评价" :visible.sync="dialogVisible">
            <el-input
              v-model="comment"
              clearable
              placeholder="请填写描述"
            />
            <div class="block">
              <span class="demonstration">选择整体分数</span>
              <el-slider v-model="rate"></el-slider>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="pushComment">确 定</el-button>
            </span>
          </el-dialog>
        </el-card>
      </el-col>

      <el-col v-if="program.state === 0">
        <el-card class="card">
          <el-table :data="candidate.filter(data => !search || data.name.includes(search))" style="width: 100%;margin-top:10px;"
                    cell-style="text-align: center"
                    border height="50vh"  header-cell-style="text-align: center">
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
                <template slot="header" slot-scope="scope">
                  <el-input
                    v-model="search"
                    prefix-icon="el-icon-search"
                    size="mini"
                    placeholder="输入专家姓名"/>
                </template>
                <template slot-scope="scope">
                  <el-button size="small" round @click="handleClick(scope.row)" >查看</el-button>
                  <el-button size="small" type="primary" round @click="handlePick(scope.row)">选择</el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { getProgramById } from '@/api/program'
  import { getRecordByProgramId } from '@/api/record'
  import { getExperts } from '@/api/expert'

  export default {
    name: "program",
    data(){
      return{
        tableData:[],
        candidate:[],
        program: {
          keyword:'',
          area:'',
          startTime:'',
          endTime:'',
          number: 0,
          company:'',
          state: 0,
        },
        search:'',
        comment:'',
        rate:'',
        dialogVisible:false,
      }
    },
    mounted: function () {
      this.load()
    },
    methods: {
      dumpAsExcel:function() {
        window.open("http://localhost:8080/file/downloadexcelbyprogram/"+this.$route.query.id, "_blank");
      },
      autoChoose:function(){
        this.$http.get('http://localhost:8080/program/auto/'+ this.$route.query.id).then((res) => {
          // this.tableData = res.data
          console.log(res.data)
          if (res.data==="专家太少，自动选满缺少专家") alert("专家太少，自动选择缺少专家")
        }).catch(function (err) {
          alert(err)
        })
        this.$router.go(0)
      },
      change(e) {
        this.$forceUpdate()
      },
      load(){
        getProgramById(this.$route.query.id)
          .then(res => {
            this.program = res.data
          })
        getRecordByProgramId(this.$route.query.id)
          .then(res => {
            this.tableData = res.data
          })
        getExperts()
          .then(res => {
            this.candidate = res.data;
          })
      },
      end(){
          var url = 'http://localhost:8080/program/endProgram/' + this.$route.query.id
          this.$http.get(url).then((res) => {
              console.log(res.data)
          }).catch(function (err) {
              alert(err)
          })

      },
      handleComment(row){
          this.dialogVisible=true;
          this.clickedrecord=row
      },
      pushComment(){
          let data = {
              id:this.clickedrecord.id,
              expertID:this.clickedrecord.expertID,
              programID:this.programID,
              expertName:this.clickedrecord.expertName,
              type:this.clickedrecord.type,
              expertArea:this.clickedrecord.expertArea,
              phone:this.clickedrecord.phone,
              company:this.clickedrecord.company,
              secret:this.secret,
              secretLevel:this.secretLevel,
              startTime:this.startTime,
              endTime:this.endTime,
              comment: this.comment,
              score: this.rate

          }
          var url = 'http://localhost:8080/record/update'
          this.$http({
              method: 'post',
              url: url,
              headers: {
                  'Access-Control-Allow-Credentials': true,
                  'Access-Control-Allow-Origin': true,
                  'Content-Type': 'application/json'
              },
              data: JSON.stringify(data)
          })
              .then(response => {
                  console.log(response.data)
                  console.log('get response')
                  //redirect
                  // this.$router.push({path: '/expert'})
              })
              .catch(error => {
                  JSON.stringify(error)
                  console.log(error)
              })
          this.dialogVisible = false
          this.$router.go(0)
      },
      handleDelete(row){
          var url = 'http://localhost:8080/record/delete/'+row.id
          this.$http({
              method: 'delete',
              url: url,
              headers: {
                  'Access-Control-Allow-Credentials': true,
                  'Access-Control-Allow-Origin': true
              }
          })
              .then(response => {
                  console.log(response.data)
              })
              .catch(error => {
                  JSON.stringify(error)
                  console.log(error)
              })
          this.$router.go(0)
          this.$notify({
              title: '提醒',
              message: '已移除此专家',
              type: 'warning'
          });
      },
      handleClick(row){
          this.$router.push({path: '/expert_detail', query: {id: row.id}})
      },
      handlePick(row) {
          let data = {
              expertID: row.id,
              expertName:row.name,
              type:row.type,
              expertArea:row.area,
              phone:row.phone,
              company:row.company,
              secret:this.secret,
              secretLevel:this.secretLevel,
              programID: this.$route.query.id,
              startTime:this.startTime,
              endTime:this.endTime

          }
          console.log(data)
          var url = 'http://localhost:8080/record/insert/'
          this.$http({
              method: 'post',
              url: url,
              headers: {
                  'Access-Control-Allow-Credentials': true,
                  'Access-Control-Allow-Origin': true,
                  'Content-Type': 'application/json'
              },
              data: JSON.stringify(data)
          })
              .then(response => {
                  console.log(response.data)
                  console.log('get response')
                  //redirect
                  // this.$router.push({path: '/programDetail', query: {id: this.$route.query.id}})
                  this.$router.go(0)
              })
              .catch(error => {
                  JSON.stringify(error)
                  console.log(error)
              })
          }
        }
    }
</script>

<style scoped>
</style>
