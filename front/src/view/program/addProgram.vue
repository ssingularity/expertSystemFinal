<template>
  <div class="card">
    <el-row>
      <el-col>
        <el-card class="card">
          <h2>新增项目</h2>
        </el-card>
      </el-col>
      <el-col>
        <el-card class="card">
          <el-form  label-width="100px">
            <el-row :gutter="20">
              <el-col :span="4">
                <el-form-item label="技术专家数量">
                  <el-input v-model="numberTech" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="管理专家数量">
                  <el-input v-model="numberMng" ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="财务专家数量">
                  <el-input v-model="numberAcc" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="4">
                <el-form-item label="是否机密">
                  <el-select v-model="secretFlag" @change="getService" placeholder="请选择">
                    <el-option label="是" value="是"></el-option>
                    <el-option label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="密级">
                  <el-select v-model="secret" :disabled="secretshow" placeholder="请选择">
                    <el-option
                      v-for="item in secretoptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="单位" v-for="(val, index) in compNum" :key="index">
              <el-input v-model="compNum[index]" ></el-input>
            </el-form-item>
            <el-button @click="addCompany" style="margin-left: 100px; margin-top: -10px; margin-bottom: 20px">增加单位</el-button><br>
            <div style="margin-left: 100px; margin-top: -10px; margin-bottom: 20px">
              <p>批量导入单位</p>
              <input
                ref="upload"
                type="file"
                accept=".xls,.xlsx"
                class="upload_file"
                @change="readExcel($event)"
              />
            </div>
            <el-row :gutter="20">
              <el-col :span="5">
                <el-form-item label="评审时间">
                  <el-date-picker
                    value-format="yyyy-MM-dd HH:mm:ss"
                    v-model="startTime"
                    type="datetime"
                    placeholder="选择开始时间">
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :offset="2" :span="5">
                <el-date-picker
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  v-model="endTime"
                  placeholder="选择结束时间">
                </el-date-picker>
              </el-col>
              <el-col>
                <el-form-item label="领域">
                  <el-select v-model="area" placeholder="请选择">
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
                <el-form-item label="备注">
                  <el-input v-model="keyword" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div style="text-align: center">
            <el-button type="primary" @click="submitForm">立即创建</el-button>
            <el-button @click="resetForm()">重置</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import XLSX from 'xlsx'
  import { addProgram }from '@/api/program'
  import { Message } from 'element-ui'

  export default {
    name: "add",
    data() {
      return {
        id:'',
        name:'',
        numberTech:'',
        numberMng:'',
        numberAcc:'',
        company:'',
        secret:'',
        area:'',
        startTime:'',
        endTime:'',
        keyword: '',
        compNum: [""],
        secretoptions: [{
            value: '秘密',
            label: '秘密'
          }, {
              value: '机密',
              label: '机密'
          }, {
              value: '绝密',
              label: '绝密'
        }],
        secretFlag:'',
        secretshow:''
      }
    },
    methods:{
      getService(){
        // this.secretFlag=!this.secretFlag;
        if (this.secretFlag==='是') this.secretshow=false;
        else {
          this.secretshow=true;
          this.secret=''
        }
        this.$forceUpdate()
      },
      addCompany() {
        this.compNum.push("");
        var compSum = "";
        this.compNum.forEach((c) => {compSum += c + ", "});
        this.company =compSum;
        console.log(this.company)
      },
      resetForm(){
        this.$router.go(0)
      },
      submitForm(){
        let compSum = "";
        this.compNum.forEach((c) => {compSum += c + ", "});
          // this.secret=this.secret.length?"否":this.secret;
        let data = {
          name: this.name,
          numberTech:this.numberTech,
          numberMng:this.numberMng,
          numberAcc:this.numberAcc,
          company: compSum,
          area:this.area,
          startTime: this.startTime,
          endTime:this.endTime,
          keyword: this.keyword,
          secret: this.secretFlag,
          secretLevel: this.secret
        };
        addProgram(data)
          .then(res => {
            Message({
              message: '成功创建项目',
              type: 'success',
              duration: 5 * 1000
            })
            this.$router.push('/program')
          })
          .catch(error => {
            this.$router.push('/program')
          })
      },
      readExcel(e) {
        let that = this;
        const files = e.target.files;
        if (files.length <= 0) {
          return false;
        } else if (!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())) {
          alert("上传格式不正确，请上传xls或者xlsx格式")
          return false;
        } else {
          that.upload_file = files[0].name;
        }
        const fileReader = new FileReader();
        fileReader.onload = ev => {
          try {
            const data = ev.target.result;
            const workbook = XLSX.read(data, {
              type: "binary"
            });
            const wsname = workbook.SheetNames[0]; //取第一张表
            const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); //生成json表格内容
            var li = []
            for (var company in ws) {
              li.push(Object.values(ws[company])[0])
            }
            console.log(li)
            that.compNum = li;
          } catch (e) {
            return false;
          }
        };
        fileReader.readAsBinaryString(files[0]);
      }
    }
  }
</script>

<style scoped>

</style>
