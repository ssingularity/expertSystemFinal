<template>
    <div>
        <navi></navi>
        <div style="margin-top: 10px">
            <el-form  label-width="100px">
                <el-row :gutter="20">
<!--                    <el-col :span="6">-->
<!--                        <el-form-item label="项目名称">-->
<!--                            <el-input v-model="name" ></el-input>-->
<!--                        </el-form-item>-->
<!--                    </el-col>-->
                    <el-col :span="4">
                        <el-form-item label="专家数量">
                            <el-input v-model="number" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6" style="align-items: end">
                        <el-form-item label="类型">
                            <el-select v-model="type" clearable placeholder="请选择">
                                <el-option label="专业" value="专业"></el-option>
                                <el-option label="财务" value="财务"></el-option>
                                <el-option label="其他" value="其他"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="单位" v-for="(val, index) in compNum" :key="index">
                  <el-input v-model="compNum[index]" ></el-input>
                </el-form-item>
                <el-button @click="addCompany" style="margin-left: 100px; margin-top: -10px; margin-bottom: 20px">增加单位</el-button>
                <el-row :gutter="20">
                    <el-col :span="10">
                        <el-form-item label="项目日期">
                            <el-date-picker
                                    v-model="date"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
<!--                        <el-form-item label="项目日期">-->
<!--                        <el-date-picker-->
<!--                                v-model="date"-->
<!--                                type="datetimerange"-->
<!--                                range-separator="至"-->
<!--                                start-placeholder="开始日期"-->
<!--                                end-placeholder="结束日期">-->
<!--                        </el-date-picker>-->
<!--                        </el-form-item>-->
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="领域">
                            <!--                            <el-input v-model="Form.type" ></el-input>-->
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
                        <el-form-item label="描述">
                            <el-input v-model="keyword" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item>
                    <el-button type="primary" @click="submitForm()">立即创建</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import navi from "../../components/navi";
    export default {
        name: "add",
        components: {navi},
        data(){
            return{
                id:'',
                    name:'',
                    number:'',
                    company:'',
                    type:'',
                    area:'',
                    date:'',
                    keyword: '',
                    compNum: ["",],
            }
        },
        methods:{
            addCompany() {
              this.compNum.push("");
              var compSum = ""
              this.compNum.forEach((c) => {compSum += c + ", "})
              this.company =compSum
              console.log(this.company)
            },
            resetForm(){
                // this.birth='',
                    this.name='',
                    this.number='',
                    this.company='',
                    this.type='',
                    this.area='',
                    this.date='',
                    this.keyword=''
                    this.compNum=["",]
            },
            submitForm(){
              var compSum = ""
              this.compNum.forEach((c) => {compSum += c + ", "})
                let data = {
                    name: this.name,
                    number: this.number,
                    company: compSum,
                    type: this.type,
                    area:this.area,
                    time: this.date,
                    keyword: this.keyword
                }
                console.log(data)
                var url = 'http://localhost:8080/program/insert/'
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
                        this.id=response.data.id
                        console.log('get response')
                        //auto choose
                        this.$http.get('http://localhost:8080/program/auto/'+ this.id).then((res) => {
                            // this.tableData = res.data
                            console.log(res.data)
                        }).catch(function (err) {
                            alert(err)
                        })
                        //redirect
                        this.$router.push({path: '/programList'})
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