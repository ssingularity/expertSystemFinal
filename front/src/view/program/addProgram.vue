<template>
    <div>
        <navi></navi>
        <div style="margin-top: 10px">
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
                                <!--                                <el-option label="其他" value=" "></el-option>-->
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
                <el-button @click="addCompany" style="margin-left: 100px; margin-top: -10px; margin-bottom: 20px">增加单位</el-button>
                <el-row :gutter="20">
                    <el-col :span="10">
                        <el-form-item label="评审时间">
<!--                            <el-date-picker-->
<!--                                    v-model="date"-->
<!--                                    type="date"-->
<!--                                    placeholder="选择日期">-->
<!--                            </el-date-picker>-->
                            <el-date-picker
                                    v-model="time"
                                    type="datetimerange"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期">
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
                        <el-form-item label="备注">
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
                    numberTech:'',
                    numberMng:'',
                    numberAcc:'',
                    company:'',
                    secret:'',
                    area:'',
                    date:'',
                    time:'',
                    keyword: '',
                    compNum: ["",],
                secretoptions: [{
                    value: '秘密',
                    label: '秘密'
                }, {
                    value: '机密',
                    label: '机密'
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
                    this.name='';
                    this.numberTech='';
                    this.numberMng='';
                    this.numberAcc='';
                    this.company='';
                    this.time='',
                    this.secret='';
                    this.secretFlag='';
                    this.secretshow='';
                    this.area='';
                    this.date='';
                    this.keyword='';
                    this.compNum=["",]
            },
            submitForm(){
                let compSum = "";
                this.compNum.forEach((c) => {compSum += c + ", "});
                this.secret=this.secret.length?"不涉密":this.secret;
                let data = {
                    name: this.name,
                    numberTech:this.numberTech,
                    numberMng:this.numberMng,
                    numberAcc:this.numberAcc,
                    company: compSum,
                    area:this.area,
                    time: this.time,
                    keyword: this.keyword,
                    secret: this.secret
                };
                console.log(data);
                const url = 'http://localhost:8080/program/insert/';
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
                        console.log(response.data);
                        this.id=response.data.id;
                        console.log('get response');
                        //auto choose
                        this.$http.get('http://localhost:8080/program/auto/'+ this.id).then((res) => {
                            // this.tableData = res.data
                            console.log(res.data);
                            if (res.data==="专家太少，自动选满缺少专家") alert("专家太少，自动选择缺少专家")
                        }).catch(function (err) {
                            alert(err)
                        });
                        //redirect
                        this.$router.push({path: '/programList'})
                    })
                    .catch(error => {
                        JSON.stringify(error);
                        console.log(error)
                    })

            }
        }
    }
</script>

<style scoped>

</style>