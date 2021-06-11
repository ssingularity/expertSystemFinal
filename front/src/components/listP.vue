<template>
    <div>
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
                        label="项目编号"
                >
                </el-table-column>
                <el-table-column
                        prop="area"
                        label="领域"
                ></el-table-column>
                <el-table-column
                        prop="type"
                        label="类型"
                ></el-table-column>
                <el-table-column
                        prop="keyword"
                        label="描述"
                ></el-table-column>
                <el-table-column
                        prop="time"
                        sortable
                        label="时间"
                ></el-table-column>
                <el-table-column
                        prop="company"
                        label="单位"
                ></el-table-column>
                <el-table-column
                        prop="state"
                        label="状态"
                ></el-table-column>
                <el-table-column
                        prop="number"
                        label="所需人数"
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
                        <el-button round @click="handleClick(scope.row)" size="small">详情</el-button>
<!--                        <el-button round @click="handleDelete(scope.row)" size="small">删除</el-button>-->
                    </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "listP",
        created: function () {
            this.load()
        },
        methods:{
            load: function (){
                this.$http.get('http://localhost:8080/program/get_all').then((res) => {
                    this.tableData = res.data
                    for (var i = 0; i < this.tableData.length; i++) {
                        // if (this.tableData[i].secret) this.tableData[i].secret='机密'
                        // else this.tableData[i].secret='非机密'
                        var d = new Date(this.tableData[i].time)
                        this.tableData[i].time = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() //+ ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
                    }
                    console.log(res)
                }).catch(function (err) {
                    alert(err)
                })
            },
            handleClick(row){
                this.$router.push({path: '/programDetail', query: {id: row.id}})
            },
            handleDelete(row){
                this.$http.get('http://localhost:8080/program/delete/'+row.id).then((res) => {
                    // this.tableData = res.data
                    console.log(res)
                }).catch(function (err) {
                    alert(err)
                })
            }
        },
        data(){
            return{
                tableData:[]
            }
        }
    }
</script>

<style scoped>

</style>