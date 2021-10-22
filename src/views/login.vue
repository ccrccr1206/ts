<template>
    <div class="login-container">
        <el-form :model="ruleForm" :rules="rules" ref="loginFormRef" label-width="100px" status-icon class="login-form">
            <h2 class="login-title">用户登录</h2>
            <el-form-item label="用户姓名" prop="name">
                <el-input v-model="ruleForm.name" 
                placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="用户密码" prop="password">
                <el-input v-model="ruleForm.password" show-password 
                placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm()"
                round plain size="mini">登录</el-button>
                <el-button type="primary" @click="resetForm()" 
                round size="mini" plain>取消</el-button>
             
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import qs from 'qs' 

    export default {
        name:"Login",
        data(){
            return {
                ruleForm: {
                    name: '',
                    password:'',
                },
                
               /* rules: {
                    name: [
                        { required: true, message: '请输入用户姓名', trigger: 'blur' },
                      
                        { pattern: /^[a-zA-Z0-9_]*$/, message:'用户姓名只包含数字大小写字母下划线'
                        ,trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入用户密码', trigger: 'blur' },
                        { min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur' }
                    ],
                }*/
            }
        },
        methods: {
            submitForm() {
                this.$refs.loginFormRef.validate(valid => {
                    if (valid) {
                      /*   let param = new URLSearchParams()
                        param.append('username',this.ruleForm.username)
                        param.append('password',this.ruleForm.password)  */
                        let  paramsSerializer= qs.stringify(this.ruleForm)
                        this.$http.post('/user/login',paramsSerializer).then(resp => {
                            console.log(resp)
                           if(resp.data.code===200){
                            this.$alert('恭喜，登录成功', '登录提醒')
                           
                            this.$router.push('/home')
                           }
                        })
                    } else {
                        this.$message({
                            type:'error',
                            message:'表单项输入有误!',
                        })
                    }
                });
            },
            resetForm(formName) {
                this.$refs.loginFormRef.resetFields()
            },
           
        }
    }
</script>

<style scoped>
    .login-container {
        position: absolute;
        width: 100%;
        height: 100%;
    
    }
    .login-form {
        width: 350px;
        /* 上下间隙 160px, 左右自动居中 */
        margin: 160px auto;
        /* 0.3为透明度 0为透明 1为完全不透明 */
        background-color: rgb(255, 255, 255, 0.6);
        padding: 24px;
        border-radius: 70px;
    }
    .login-title {
        color: #303133;
        text-align: center;
    }
</style>