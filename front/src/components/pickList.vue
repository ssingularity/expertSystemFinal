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
                        label="ID"
                >
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                ></el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别"
                ></el-table-column>
                <el-table-column
                        prop="birth"
                        label="生日"
                        sortable
                ></el-table-column>
                <el-table-column
                        prop="type"
                        label="类型"
                        sortable
                ></el-table-column>
                <el-table-column
                        prop="area"
                        label="领域"
                        sortable
                ></el-table-column>
                <el-table-column
                        prop="company"
                        label="单位"
                        sortable
                ></el-table-column>
                <el-table-column
                        prop="secret"
                        label="是否机密"
                ></el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button round @click="handleClick(scope.row)" >查看</el-button>
                        <el-button round @click="handleClick(scope.row)">选择</el-button>
                    </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "list",
        created: function () {
        this.loadData()
        },
        methods: {
            loadData: function () {
                this.$http.get('http://localhost:8080/expert/get_all').then((res) => {
                    this.tableData = res.data
                    console.log(res)
                }).catch(function (err) {
                    alert(err)
                })
            },
            handleClick(row){
                this.$router.push({path: '/expertDetail', query: {id: row.id}})
            },
            handlePick(row){
                let data = {
                    expertID: row.id,
                    programID: this.number,
                    time:this.company,
            }
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
                        this.$router.push({path: '/programList'})
                    })
                    .catch(error => {
                        JSON.stringify(error)
                        console.log(error)
                    })
            }
        },
        data(){
            return{
                tableData:[],
                search:''
            }
        }
    }
</script>

<style scoped>

</style>
<!--建议三个专家：可以根据项目确定专家，在项目页面可以删掉专家(检查会议开始时间与现在的时间），
list每一行有 确定选择和 查看详情；详情页面有 修改返回；修改页面有确认 取消
顶部导航栏？
-->