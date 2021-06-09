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
                        width="50"
                ></el-table-column>
                <el-table-column
                        prop="birth"
                        label="生日"
                        sortable
                ></el-table-column>
                <el-table-column
                        prop="phone"
                        label="电话"
                ></el-table-column>
                <el-table-column
                        prop="type"
                        label="类型"
                ></el-table-column>
                <el-table-column
                        prop="area"
                        label="领域"
                ></el-table-column>
                <el-table-column
                        prop="company"
                        label="单位"
                        width="200"
                ></el-table-column>
                <el-table-column
                        prop="secret"
                        label="是否机密"
                        width="50"
                ></el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button round @click="handleClick(scope.row)" >查看</el-button>
<!--                        <el-button round>编辑</el-button>-->
                    </template>
                </el-table-column>
            </el-table-column>
        </el-table>
      <div style="text-align: center; margin-top: 10px">
        <el-button type="primary" icon="el-icon-user-solid" @click="dumpAsExcel">导出专家列表</el-button>
        <el-button type="primary" icon="el-icon-user-solid" @click="uploadExcel">导入专家列表</el-button>
      </div>
      <el-dialog
          title="上传专家信息"
          :visible.sync="dialogVisible"
          width="500px">
        <div style="text-align: center">
          <el-upload
              class="upload-demo"
              drag
              :with-credentials="true"
              action="http://localhost:8080/file/importexcel"
              multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件</div>
          </el-upload>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="afterUpload">完 成</el-button>
        </span>
      </el-dialog>
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
                    for (var i = 0; i < this.tableData.length; i++) {
                        // if (this.tableData[i].secret) this.tableData[i].secret='机密'
                        // else this.tableData[i].secret='非机密'
                        var d = new Date(this.tableData[i].birth)
                        this.tableData[i].birth = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() //+ ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
                    }
                    console.log(res)
                }).catch(function (err) {
                    alert(err)
                })
            },
            handleClick(row){
                this.$router.push({path: '/expertDetail', query: {id: row.id}})
            },
            dumpAsExcel:function() {
              // todo: dowload URL
              window.open("http://localhost:8080/file/downloadexcel", "_blank");
              console.log("dump")
            },
            uploadExcel:function() {
              this.dialogVisible = true
              console.log("upload")
            },
            afterUpload:function () {
              location.reload()
            }
        },
        data(){
            return{
                tableData:[],
                search:'',
                dialogVisible: false
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