<template>
  <div class="card">
    <el-row>
      <el-col>
        <el-card class="card">
          <h2>修改专家信息</h2>
        </el-card>
      </el-col>
      <el-col>
        <el-card class="card">
          <el-form  label-width="100px">
            <el-row :gutter="20">
              <el-col :span="6">
                <el-form-item label="姓名">
                  <el-input v-model="expert.name" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="证件号">
                  <el-input v-model="expert.id" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="是否机密">
                  <el-select v-model="expert.secret" placeholder="请选择">
                    <el-option label="是" value="是"></el-option>
                    <el-option label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="密级">
                  <el-select v-model="expert.secretLevel" :disabled="isSecret" placeholder="请选择">
                    <el-option
                      v-for="item in secretoptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12" style="align-items: end">
                <el-form-item label="职务">
                  <el-input v-model="expert.jobPosition" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" style="align-items: end">
                <el-form-item label="职称">
                  <el-input v-model="expert.jobTitle" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" style="align-items: end">
                <el-form-item label="电话">
                  <el-input v-model="expert.phone" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6" style="align-items: end">
                <el-form-item label="类型">
                  <el-select v-model="expert.type" clearable placeholder="请选择">
                    <el-option label="技术" value="技术"></el-option>
                    <el-option label="管理" value="管理"></el-option>
                    <el-option label="财务" value="财务"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="性别">
                  <el-select v-model="expert.gender" clearable placeholder="请选择">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                    <el-option label="其他" value=" "></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="是否拉黑">
                  <el-select v-model="expert.isBlocked" clearable placeholder="请选择">
                    <el-option label="是" value="是"></el-option>
                    <el-option label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="单位">
              <el-input v-model="expert.company" ></el-input>
            </el-form-item>
            <el-row :gutter="20">
              <el-col :span="6">
                <el-form-item label="主领域">
                  <el-select v-model="expert.firstArea" clearable placeholder="请选择" @change="refresh">
                    <el-option label="集成电路" value="集成电路"></el-option>
                    <el-option label="人工智能" value="人工智能"></el-option>
                    <el-option label="生物医药" value="生物医药"></el-option>
                    <el-option label="网络空间" value="网络空间"></el-option>
                    <el-option label="新能源" value="新能源"></el-option>
                    <el-option label="核能" value="核能"></el-option>
                    <el-option label="航天" value="航天"></el-option>
                    <el-option label="航空" value="航空"></el-option>
                    <el-option label="船舶（含海洋工程）" value="船舶（含海洋工程）"></el-option>
                    <el-option label="电子信息" value="电子信息"></el-option>
                    <el-option label="新材料" value="新材料"></el-option>
                    <el-option label="智能装备" value="智能装备"></el-option>
                    <el-option label="应急" value="应急"></el-option>
                    <el-option label="空间信息（含北斗导航）" value="空间信息（含北斗导航）"></el-option>
                    <el-option label="其他（含财务）" value="其他（含财务）"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="副领域">
                  <el-select v-model="expert.secondaryArea" filterable multiple placeholder="请选择" @change="refresh">
                    <el-option label="集成电路" value="集成电路"></el-option>
                    <el-option label="人工智能" value="人工智能"></el-option>
                    <el-option label="生物医药" value="生物医药"></el-option>
                    <el-option label="网络空间" value="网络空间"></el-option>
                    <el-option label="新能源" value="新能源"></el-option>
                    <el-option label="核能" value="核能"></el-option>
                    <el-option label="航天" value="航天"></el-option>
                    <el-option label="航空" value="航空"></el-option>
                    <el-option label="船舶（含海洋工程）" value="船舶（含海洋工程）"></el-option>
                    <el-option label="电子信息" value="电子信息"></el-option>
                    <el-option label="新材料" value="新材料"></el-option>
                    <el-option label="智能装备" value="智能装备"></el-option>
                    <el-option label="应急" value="应急"></el-option>
                    <el-option label="空间信息（含北斗导航）" value="空间信息（含北斗导航）"></el-option>
                    <el-option label="其他（含财务）" value="其他（含财务）"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="9">
                <el-form-item label="简介">
                  <el-input v-model="expert.introduction" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item>
              <el-button type="primary" @click="submitForm()">立即更新</el-button>
              <el-button @click="resetForm()">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { getExpertById, updateExpert } from '@/api/expert'
  import { Message } from 'element-ui'
  import _ from 'lodash'

  export default {
    name: "edit",
    data(){
      return{
        expert: {
          id:'',
          name:'',
          phone:'',
          gender:'',
          isBlocked:'',
          secret:'',
          company:'',
          type:'',
          area:'',
          introduction:'',
          birth:'',
          firstArea:'',
          jobPosition: '',
          jobTitle: '',
          secondaryArea: [],
        },
        secretoptions: [{
            value: '一般',
            label: '一般'
        }, {
            value: '重要',
            label: '重要'
        }, {
            value: '核心',
            label: '核心'
        }],
      }
    },
    mounted: function () {
      this.load()
    },
    computed: {
      isSecret() {
        return this.expert.secret !== '是'
      }
    },
    methods:{
      load() {
        getExpertById(this.$route.query.id)
          .then(res => {
            this.expert = res.data;
            const area = this.expert.area
            const areaList = area.split(',')
            this.expert.firstArea = areaList[0]
            if (areaList.length > 1) {
              this.expert.secondaryArea = areaList.slice(1)
            }
          })
        },
        resetForm(){
            this.$router.go(0)
        },
      refresh() {
         this.$forceUpdate()
      },
      submitForm(){
        let area = this.expert.firstArea
        _.forEach(this.expert.secondaryArea, (val) => area = area + ',' + val)
        this.expert.area = area
        updateExpert(this.expert)
          .then(res => {
            Message({
              message: '成功更新专家',
              type: 'success',
              duration: 5 * 1000
            })
            this.$router.push({path: '/expert'})
          })
      }
    }
  }
</script>

<style scoped>

</style>
