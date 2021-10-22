<template>
   <el-container class="home-container">
    <el-header>
        <img src="../assets/logoet.png" alt />
        <el-button>退出系统</el-button>
    </el-header>
    <el-container>
        <el-aside width="200px"> 
           <el-menu
           router
      default-active="2"
      class="el-menu-vertical-demo"
      
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>测试</span>
        </template>
        <el-menu-item-group>
         
      
          <el-menu-item index="/maxscore">职工统计</el-menu-item>
           <el-menu-item index="/countBySch">职工列表</el-menu-item>
        </el-menu-item-group>
       
      </el-submenu>
      
    </el-menu>
        </el-aside>
        <el-main>
          <router-view>
          </router-view>
          </el-main>
    </el-container>
</el-container>
</template>
<script>

export default {
  name: 'app',
 
  data () {
    return {
      menuList: []

    }
  },
  methods: {
    
    handleOpen(){

    },
    handleClose(){
      
    }

  },
  created () {
   
  }
}
</script>
<style lang="less" scoped>
.home-container{
     height: 100%;
}
.el-header {
  background-color: #0b080a;
  display: flex;
  justify-content: left;
  align-items: center;
  .el-button {
    position: absolute;
    right: 10px;
  }
}
.el-aside {
  background-color: #535c63;
}
.el-main {
  background-color: #f3f5f6;
}
</style>
