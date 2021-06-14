<template>
    <div>
        <navi></navi>
        <div style="margin-top: 30px">
            <el-container>
                <el-aside width="200px">
                    <p class="content" ><span style="font-weight: bold">领域:</span>{{area}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">单位:</span>{{company}}<br/></p>
                    <p class="content" > <span style="font-weight: bold">描述:</span>{{keyword}}<br/></p>
                    <p class="content" > <span style="font-weight: bold">时间：</span>{{time}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">所需人数：</span>{{number}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">状态：</span>{{state}}<br/></p>
                    <el-row style="margin: 10px"><el-button type="primary" round @click="end">结束项目</el-button></el-row>
                </el-aside>
                <el-main>
                    <el-table :data="tableData"
                              stripe
                              style="width: 100%">
                        <el-table-column>
                            <el-table-column
                                    type="index"
                            >
                            </el-table-column>
                            <el-table-column
                                    prop="id"
                                    label="记录编号"
                            ></el-table-column>
                            <el-table-column
                                    prop="expertID"
                                    label="专家身份证号"
                            ></el-table-column>
                            <el-table-column
                                    prop="expertName"
                                    label="专家姓名"
                            ></el-table-column>
                            <el-table-column
                                    prop="type"
                                    label="专家类型"
                            ></el-table-column>
                            <el-table-column
                                    prop="company"
                                    label="专家单位"
                            ></el-table-column>
                            <el-table-column
                                    prop="phone"
                                    label="专家手机"
                            ></el-table-column>
                            <el-table-column
                                    prop="expertArea"
                                    label="专家领域"
                            ></el-table-column>
                            <el-table-column
                                prop="secret"
                                label="是否涉密项目"
                            ></el-table-column>
                            <el-table-column
                                    prop="secretLevel"
                                    label="涉密程度"
                            ></el-table-column>
                            <el-table-column
                                    prop="startTime"
                                    label="评审开始时间"
                            ></el-table-column>
                            <el-table-column
                                    prop="comment"
                                    label="评价"
                                    width="100"
                            ></el-table-column>
                            <el-table-column
                                    prop="score"
                                    label="分数"
                            ></el-table-column>
                            <el-table-column
                                    fixed="right"
                                    label="操作"
                                    width="160">
                                <template slot-scope="scope">
                                    <el-button round size="small" @click="handleComment(scope.row)" >评价</el-button>
                                    <el-button round size="small" @click="handleDelete(scope.row)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table-column>
                    </el-table>
                  <el-row style="margin-top: 20px">
                      <el-button @click="autoChoose">自动选择项目专家</el-button>
                      <el-button @click="dumpAsExcel">导出项目专家</el-button>
                  </el-row>
                    <el-dialog title="输入评价" :visible.sync="dialogVisible">
                        <el-input
                                v-model="comment"
                                clearable
                                placeholder="请填写描述"
                        />
<!--                        <el-input-->
<!--                                style="margin: 30px"-->
<!--                                v-model="rate"-->
<!--                                maxlength="8"-->
<!--                                placeholder="请选择整体分数"-->
<!--                                @input="change($event)"-->
<!--                        />-->
                        <div class="block">
                            <span class="demonstration">选择整体分数</span>
                            <el-slider v-model="rate"></el-slider>
                        </div>
                        <span slot="footer" class="dialog-footer">
                        <el-button @click="dialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="pushComment()">确 定</el-button>
                        </span>
                    </el-dialog>
                </el-main>
            </el-container>
<!--            <list style="margin-top: 30px"></list>-->
        </div>
        <div style="margin-top: 30px">
            <el-input v-model="search" style="width: 300px"
                      placeholder="请输入专家姓名" prefix-icon="el-icon-search"/>
            <el-table :data="candidate.filter(data=>!search || data.name.includes(search))"
                      stripe
                      style="width: 100%">
                <el-table-column>
                    <!--<template slot="header"  scope="scope" >-->
                    <!--<el-input v-model="search" style="width: 300px"-->
                    <!--placeholder="请输入书名" prefix-icon="el-icon-search"/>-->
                    <!--</template>-->
                    <el-table-column
                            prop="company"
                            label="工作单位"
                    ></el-table-column>
                    <el-table-column
                            prop="name"
                            label="姓名"
                    ></el-table-column>
                    <el-table-column
                            prop="id"
                            label="身份证号"
                    ></el-table-column>
                    <el-table-column
                            prop="gender"
                            label="性别"
                    ></el-table-column>
                    <el-table-column
                            prop="jobPosition"
                            label="职务"
                    ></el-table-column>
                    <el-table-column
                            prop="jobTitle"
                            label="职称"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="type"
                            label="专家类型"
                    ></el-table-column>
                    <el-table-column
                            prop="area"
                            label="行业领域"
                    ></el-table-column>
                    <el-table-column
                            prop="phone"
                            label="手机"
                    ></el-table-column>
                    <el-table-column
                            prop="secret"
                            label="是否涉密人员"
                    ></el-table-column>
                    <el-table-column
                            prop="secretLevel"
                            label="涉密程度"
                    ></el-table-column>
                    <el-table-column
                            prop="address"
                            label="通讯地址"
                            width="200"
                    ></el-table-column>
                    <el-table-column
                            prop="postcode"
                            label="邮编"
                            width="50"
                    ></el-table-column>
                    <el-table-column
                            prop="recommendCompany"
                            label="推荐单位"
                    ></el-table-column>
                    <el-table-column
                            prop="filler"
                            label="填报人"
                    ></el-table-column>
                    <el-table-column
                            prop="fillerContact"
                            label="填报人联系方式"
                    ></el-table-column>
                    <el-table-column
                            prop="isBlocked"
                            label="是否被拉黑"
                    ></el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="150">
                        <template slot-scope="scope">
                            <el-button size="small" round @click="handleClick(scope.row)" >查看</el-button>
                            <el-button size="small" round @click="handlePick(scope.row)">选择</el-button>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import navi from "../../components/navi";
    // import list from "../../components/pickList";
    export default {
        name: "program",
        components: {navi},
        data(){
            return{
                tableData:[],
                candidate:[],
                search:'',
                programId:'',
                type:'技术',
                area:'核工程',
                keyword:'',
                number:'',
                time:'',
                realtime:'',
                company:'',
                state: '',
                dialogVisible:false,
                clickedrecord:'',
                comment:'',
                rate:'',
                startTime:'',
                endTime:'',
                secret:'',
                secretLevel:''
            }
        },
        mounted: function () {
            this.load()
        },
        methods: {
            dumpAsExcel:function() {
              // todo: dowload URL
              window.open("http://localhost:8080/file/downloadexcelbyprogram/"+this.$route.query.id, "_blank");
              console.log("dump "+this.$route.query.id)
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
            change () {
                this.$forceUpdate()
            },
            load(){
                let that=this
                var url = 'http://localhost:8080/program/find/' + this.$route.query.id
                this.$http.get(url, {
                    headers: {
                        'Access-Control-Allow-Credentials': true,
                        'Access-Control-Allow-Origin': true
                    }
                })
                    .then(res => {
                        console.log(res.data)
                        // console.log(this)
                        this.programID=res.data.id
                        that.number = res.data.numberAcc+res.data.numberTech+res.data.numberMng
                        that.realtime = res.data.time
                        that.startTime=res.data.startTime
                        that.endTime=res.data.endTime
                        var d = new Date(res.data.startTime)
                        that.time = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
                        that.type = res.data.type
                        that.area = res.data.area
                        that.keyword = res.data.keyword
                        that.company = res.data.company
                        that.state = res.data.state
                        that.secret=res.data.secret
                        that.secretLevel=res.data.secretLevel
                        console.log(res.data.type)
                        // console.log(that.isbn)
                    }).catch(error => {
                    JSON.stringify(error)
                    console.log(error)
                })
                this.$http.get('http://localhost:8080/record/getP/'+ this.$route.query.id).then((res) => {
                    this.tableData = res.data
                    for (var i = 0; i < this.tableData.length; i++) {
                        var d = new Date(this.tableData[i].startTime)
                        this.tableData[i].startTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
                    }
                    console.log(res.data)
                }).catch(function (err) {
                    alert(err)
                })
                this.$http.get('http://localhost:8080/expert/get_all').then((res) => {
                    this.candidate = res.data
                    for (var i = 0; i < this.tableData.length; i++) {
                        var d = new Date(this.tableData[i].birth)
                        this.tableData[i].birth = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() //+ ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
                    }
                    // console.log(res.data)
                }).catch(function (err) {
                    alert(err)
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
                this.$router.push({path: '/expertDetail', query: {id: row.id}})
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
    .el-aside {
        background-color: #D3DCE6;
        /*margin-top: 30px;*/
        /*margin-left: 10px;*/
        color: #333;
        text-align: center;
        line-height: 30px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 20px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>