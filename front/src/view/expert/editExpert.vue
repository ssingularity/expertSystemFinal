<template>
    <div>
        <navi></navi>
        <div style="margin-top: 10px">
            <el-form  label-width="100px">
                <el-row :gutter="20">
                    <el-col :span="6">
                        <el-form-item label="姓名">
                            <el-input v-model="name" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="证件号">
                            <el-input v-model="id" ></el-input>
                        </el-form-item>
                    </el-col>
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
                    <el-col :span="6" style="align-items: end">
                        <el-form-item label="电话">
                            <el-input v-model="phone" ></el-input>
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
                    <el-col :span="4">
                        <el-form-item label="性别">
                            <el-select v-model="gender" clearable placeholder="请选择">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                                <el-option label="其他" value=" "></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="单位">
                    <el-input v-model="company" ></el-input>
                </el-form-item>
                <el-row :gutter="20">
                    <el-col :span="6">
                        <el-form-item label="出生日期">
                            <el-date-picker
                                    v-model="birth"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="主领域">
                            <!--                            <el-input v-model="Form.type" ></el-input>-->
                            <el-select v-model="firstArea" filterable placeholder="请选择">
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
                            <el-select v-model="secondaryArea" filterable multiple placeholder="请选择">
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
                            <el-input v-model="introduction" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item>
                    <el-button type="primary" @click="submitForm()">立即更新</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import navi from "../../components/navi";
    export default {
        name: "edit",
        components: {navi},
        data(){
            return{
                    id:'',
                    name:'',
                    phone:'',
                    gender:'',
                    secret:'',
                    company:'',
                    type:'',
                    area:'',
                    introduction:'',
                    birth:'',
                firstArea:'',
                secondaryArea:'',
                secretoptions: [{
                    value: '一般',
                    label: '一般'
                }, {
                    value: '重要',
                    label: '重要'
                }],
                value: '',
                secretFlag:'',
                secretshow:''
            }
        },
        mounted: function () {
            this.load()
        },
        methods:{
            getService(){
                // this.secretFlag=!this.secretFlag;
                if (this.secretFlag==='是') this.secretshow=false
                else this.secretshow=true
                this.$forceUpdate()
            },
            load() {
                let that = this
                var url = 'http://localhost:8080/expert/get/' + this.$route.query.id
                this.$http.get(url, {
                    headers: {
                        'Access-Control-Allow-Credentials': true,
                        'Access-Control-Allow-Origin': true
                    }
                })
                    .then(res => {
                        // console.log(res.data)
                        that.id = res.data.id
                        that.name = res.data.name
                        that.phone = res.data.phone
                        that.gender = res.data.gender
                        that.birth =res.data.birth
                        that.type = res.data.type
                        that.area = res.data.area   // 把领域第一个作为主领域 后续的放入副领域
                        let arr=that.area.split(",")
                        that.firstArea=arr.length>=1?arr[0]:''
                        let list=arr.slice(1,arr.length)
                        that.secondaryArea=list
                        that.introduction = res.data.introduction
                        that.company = res.data.company
                        that.secret = res.data.secret
                        if (that.secret.length>0){
                            that.secretFlag='是'
                            that.secretshow=false
                        }else{
                            that.secretFlag='否'
                            that.secretshow=true
                        }
                        console.log(res.data.store)
                        // console.log(that.isbn)
                    }).catch(error => {
                    JSON.stringify(error)
                    console.log(error)
                })
            },
            resetForm(){
                this.$router.push({path: '/expertDetail', query: {id: this.$route.query.id}})
            },
            submitForm(){
                // let that = this
                this.area=this.firstArea+','+this.secondaryArea
               let data = {
                   id:this.$route.query.id,
                    name: this.name,
                    phone: this.phone,
                   gender: this.gender,
                   secret: this.secret,
                   company:this.company,
                   type: this.type,
                   area:this.area,
                   introduction:this.introduction,
                   birth: this.birth
                }
                var url = 'http://localhost:8080/expert/insert/'
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
                        this.$router.push({path: '/expert'})
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