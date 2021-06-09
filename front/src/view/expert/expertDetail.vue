<template>
    <div>
        <navi></navi>
        <div style="margin-top: 30px">
            <el-container>
                <el-aside width="200px">
                    <p class="content" > <span style="font-weight: bold">姓名:</span>{{name}}<br/></p>
                    <p class="content" > <span style="font-weight: bold">性别：</span>{{gender}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">年龄：</span>{{age}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">类型:</span>{{type}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">领域:</span>{{area}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">单位:</span>{{company}}<br/></p>
                    <p class="content" ><span style="font-weight: bold">机密类型:</span>{{secret}}<br/></p>
                    <el-row style="margin: 10px"><el-button type="primary" round @click="edit">修改信息</el-button></el-row>
                </el-aside>
                <el-main>
                    <el-table :data="tableData"
                              stripe
                              style="width: 100%">
                        <el-table-column>
                            <!--<template slot="header"  scope="scope" >-->
                            <!--<el-input v-model="search" style="width: 300px"-->
                            <!--placeholder="请输入书名" prefix-icon="el-icon-search"/>-->
                            <!--</template>-->
                            <el-table-column
                                    prop="id"
                                    label="ID"
                            >
                            </el-table-column>
                            <el-table-column
                                    prop="programID"
                                    label="项目编号"
                            ></el-table-column>
                            <el-table-column
                                    prop="time"
                                    label="时间"
                            ></el-table-column>
                            <el-table-column
                                    prop="comment"
                                    label="评价"
                                    width="400"
                            ></el-table-column>
                            <el-table-column
                                    prop="ontime"
                                    label="分数"
                            ></el-table-column>
<!--                            <el-table-column-->
<!--                                    prop="type"-->
<!--                                    label="类型"-->
<!--                            ></el-table-column>-->
                            <el-table-column
                                    fixed="right"
                                    label="操作"
                                    width="150">
                                <template slot-scope="scope">
                                    <el-button round size="small" @click="handleComment(scope.row)" >评价</el-button>
                                    <el-button round size="small" @click="handleDelete(scope.row)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table-column>
                    </el-table>
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

<!--            <div class="detail_title">-->
<!--                <p class="title" >{{name}}</p>-->
<!--            </div>-->
<!--            <div class="detail_main">-->
<!--                <div class="detail_cover">-->
<!--                    <img  class="cover" :src="cover" alt="" >&lt;!&ndash;150*140px&ndash;&gt;-->
<!--                </div>-->
<!--                <div class="detail_info">-->
<!--                    <p class="content" > <span style="font-weight: bold">姓名:</span>{{name}}<br/></p>-->
<!--                    <p class="content" > <span style="font-weight: bold">性别：</span>{{gender}}<br/></p>-->
<!--                    <p class="content" ><span style="font-weight: bold">年龄：</span>{{age}}<br/></p>-->
<!--                    <p class="content" ><span style="font-weight: bold">类型:</span>{{type}}<br/></p>-->
<!--                    <p class="content" ><span style="font-weight: bold">领域:</span>{{area}}<br/></p>-->
<!--                    <p class="content" ><span style="font-weight: bold">单位:</span>{{company}}<br/></p>-->
<!--                    <p class="content" ><span style="font-weight: bold">机密类型:</span>{{secret}}<br/></p>-->

<!--                </div>-->
<!--                <div class="detail_right">&lt;!&ndash;购物车&ndash;&gt;-->
<!--                    <br/>-->
<!--                    <br/>-->
<!--                    数量:-->
<!--                    <input type="text" name="add_number" placeholder="请输入数字" v-model="addnum"><br/>-->
<!--                    <button type="button" class="top" @click="addtobag (name,addnum,store,logstate,user)"> 加入购物车 </button>-->
<!--                    <button type="button" class="top"> 直接购买 </button>-->
<!--                </div>-->
<!--            </div>-->
        </div>
    </div>
<!--    雷达图，信息，过往记录-->
</template>

<script>
    import navi from "../../components/navi";
    export default {
        name: "expertDetail",
        components: {navi},
        data(){
            return{
                expertID:'',
                name:'赵旭',
                gender:'男',
                age:'42',
                type:'技术',
                area:'核工程',
                company:'',
                secret: '',
                showRecord:false,
                dialogVisible:false,
                commentID:'',
                clickedrecord:'',
                comment:'',
                rate:'',
                tableData:[]
            }
        },
        created: function () {
            this.load()
        },
        methods: {
            load(){
                let that=this
                var url = 'http://localhost:8080/expert/get/' + this.$route.query.id
                this.$http.get(url, {
                    headers: {
                        'Access-Control-Allow-Credentials': true,
                        'Access-Control-Allow-Origin': true
                    }
                })
                    .then(res => {
                        console.log(res.data)
                        // console.log(this)
                        this.expertID=res.data.id
                        that.name = res.data.name
                        that.gender = res.data.gender
                        // that.age =
                        that.type = res.data.type
                        that.area = res.data.area
                        that.company = res.data.company
                        that.secret = res.data.secret
                        // if ( res.data.secret) that.secret='机密'
                        // else that.secret='非机密'
                        var birthday=res.data.birth;
                        var birthday_time=birthday.split("-");
                        var birthYear = birthday_time[0];
                        var d= new Date();
                        var nowYear=d.getFullYear();
                        // alert(nowYear)
                        that.age=nowYear-birthYear;
                        console.log(res.data.store)
                        // console.log(that.isbn)
                    }).catch(error => {
                    JSON.stringify(error)
                    console.log(error)
                })
                this.$http.get('http://localhost:8080/record/getE/'+ this.$route.query.id).then((res) => {
                    this.tableData = res.data
                    console.log(res)
                }).catch(function (err) {
                    alert(err)
                })
            },
            change () {
                // this.$forceUpdate()
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
                    message: '已移除此项目',
                    type: 'warning'
                });
            },
            handleComment(row){
                this.dialogVisible=true
                // this.commentID=row.id
                this.clickedrecord=row
            },
            pushComment(){
                let data = {
                    id:this.clickedrecord.id,
                    expertID:this.expertID,
                    programID:this.clickedrecord.programID,
                    time: this.clickedrecord.time,
                    comment: this.comment,
                    ontime: this.rate

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
            },
            edit(){
                this.$router.push({path: '/reverseExpert', query: {id: this.$route.query.id}})

            }
        }
    }
</script>

<style scoped>
    /*@import "../../../statics/style.css";*/
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        /*margin-top: 30px;*/
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