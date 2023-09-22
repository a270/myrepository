<template>
  <el-container class="home_container">
    <el-header>
      <div class="home_title">UUShop社区优选平台后台管理系统</div>
      <div class="home_userinfoContainer">
        <el-avatar :src="admin.imgUrl"></el-avatar>
        <el-dropdown style="position: relative;top: -10px;left: 10px;">
                  <span class="el-dropdown-link home_userinfo">
                    {{admin.name}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
                  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>

      <el-aside width="200px" style="background-color: #a5e7f0">

        <el-menu router>
          <el-submenu v-for="(item,index) in $router.options.routes" v-if="item.show" :index="index+''">
            <template slot="title"><i :class="item.icon"></i>{{item.name}}</template>
            <el-menu-item v-for="item2 in item.children" v-if="item2.show" :index="item2.path"
                          :class="$route.path==item2.path?'is-active':''">
              <div style="position: relative;left: 20px;" >
                <i :class="item2.icon"></i>{{item2.name}}
              </div>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <router-view></router-view>
        </el-main>
        <el-footer>@V1.0-Spring Cloud Alibaba + Vue</el-footer>
      </el-container>

    </el-container>

  </el-container>
</template>
<script>
  export default{
    methods: {
      logout(){
        let _this = this;
        this.$confirm('注销登录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          localStorage.removeItem('access-admin')
          _this.$router.replace({path: '/login'})
        })
      }
    },
    mounted: function () {
      let admin = JSON.parse(window.localStorage.getItem('access-admin'))
        this.admin = admin
      // let _this = this
      // //首先需要校验token合法性
      // axios({
      //   url:this.$store.state.globalhost+'account-service/admin/checkToken',
      //   method:'get',
      //   headers:{
      //     token:admin.token
      //   }
      // }).then((response) => {
      //   if(response.data.code == 0){
      //     this.admin = admin
      //   }
      //   if(response.data.code == -1){
      //     _this.$alert(response.data.msg, '提示', {
      //       confirmButtonText: '确定'
      //     }).then((response) => {
      //       localStorage.removeItem('access-admin')
      //       _this.$router.replace({path: '/login'})
      //     })
      //   }
      // })
    },
    data(){
      return {
        admin:''
      }
    }
  }
</script>
<style>
  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-aside {
    background-color: #ECECEC;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
  }

  .el-footer {
    background-color: #ea7e53;
    color: #fff;
    font-size: 22px;
    line-height: 60px;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }
</style>
