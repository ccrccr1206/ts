!<template>
    <div>
        <el-card type="box-card">
            <div id="main" style="width:600px;height:400px;">
            </div>
             <el-form :inline="true" :model="queryForm" class="demo-form-inline">
  <el-form-item label="姓名">
    <el-input v-model="queryForm.empName" placeholder="姓名"></el-input>
  </el-form-item>
  <el-form-item label="部门">
    <el-select v-model="queryForm.deptName" placeholder="部门">
      <el-option label="人事部" value="shanghai"></el-option>
      <el-option label="后勤部" value="beijing"></el-option>
      <el-option label="业务部" value="beijing"></el-option>
    </el-select>
  </el-form-item>
   <el-form-item label="学历">
    <el-select v-model="queryForm.empDegreeName" placeholder="学历">
      <el-option label="专科" value="shanghai"></el-option>
      <el-option label="本科" value="beijing"></el-option>
      <el-option label="研究生" value="beijing"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="queryData">查询</el-button>
  </el-form-item>
</el-form>
          <el-table
                :data="userList"
                style="width: 100%">
                <el-table-column
                  prop="id"
                  label="id"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="empName"
                  label="员工姓名"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="sex"
                  label="性别">
                </el-table-column>
                <el-table-column
                  prop="age"
                  label="年龄">
                </el-table-column>
                <el-table-column
                  prop="deptName"
                  label="部门">
                </el-table-column>
                <el-table-column
                  prop="empDegreeName"
                  label="学历">
                  
                </el-table-column>
                 <el-table-column
                  fixed="right"
                  label="操作"
                  width="100">
          <template slot-scope="userList">
            <el-button type="text" class="button" @click="showEditForm(userList)">编辑</el-button>
            <el-button type="text" class="button" @click="deleteCar(userList.id)">删除</el-button>
          </template>
    </el-table-column>
              </el-table>
                   <!-- 3.分页条-->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNumber"
            :page-sizes="[4, 6, 8, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
        
        </el-card>
    </div>
</template>

<script>
export default{
    name: 'app',
  data () {
    return {
        /* 当前页 */
                page: 1,
                /* 每页记录数 */
                size: 8,
                /* 总记录数 */
                total: 0,
                rows:[],
                pageNumber: 1,
                pageSize: 4,
                empName:'',
                total: 0,
                userList:[],
              queryForm:{
                id:'',
                age:'',
                sex:'',
                empName:'',
                deptName:'',
                empDegreeName:''
              }
    }
  },
  methods: {
      /* 更改每页记录数 */
            handleSizeChange(pageSize) {
                /* 赋值新的每页记录数 */
                this.pageSize = pageSize
                /* 回显 */
                this.queryCarList()
            },
            /* 更改当前页 */
            handleCurrentChange(pageNumber) {
                /* 赋值新的当前页 */
                this.pageNumber = pageNumber
                /* 回显 */
                this.queryData()
            },
           
           
                /* 查询列表数据 */
            queryCarList() {
                this.$http.get(`/user/querySomeStu?empName=${this.empName}&pageNumber=${this.pageNumber}&pageSize=${this.pageSize}`, this.queryForm).then(resp => {
                    if (resp.status === 200) {
                       this.userList = resp.data.rows
                        this.pageNumber = resp.data.pageNumber
                        this.pageSize = resp.data.pageSize
                        this.total = resp.data.total
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
            queryData () {
                this.$http.get(`/user/query?empName=${this.queryForm.empName}`
                  
                ).then(resp => {//resp
             
                this.userList=resp.data
                }).catch(error => console.log(error))
            },
                deleteCar(id) {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {

                    this.$http.delete(`/user/delete?id=${this.id}`).then(resp => {
                      console.log(resp)
                        if (resp.data.code === 200) {

                            this.$message.success("删除成功")
                            this.queryData()
                        } 
                    }).catch(e => {
                        console.log(e)
                    })

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },

  },
  mounted () {
    this.queryCarList() 
    this.queryData()
  }
}
</script>

<style scoped>
</style>