<template>
  <div class="backgroundImg">
    <div style="z-index: 1; position: fixed; top: 50%;left: 50%;margin-left: -50px;margin-top: -50px;">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="用户名" prop="username" class="item">
          <el-input type="text" maxlength="12" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" class="item">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!--<div class="wrap" id="wrap">
      <div class="logGet">
        &lt;!&ndash; 头部提示信息 &ndash;&gt;
        <div class="logD logDtip">
          <p class="p1">登录</p>
        </div>
        &lt;!&ndash; 输入框 &ndash;&gt;
        <div class="lgD">
          <img src="assets/logo.png" width="20" height="20" alt="" />
          <input type="text" placeholder="输入用户名" />
        </div>
        <div class="lgD">
          <img src="img/logPwd.png" width="20" height="20" alt="" />
          <input type="text" placeholder="输入用户密码" />
        </div>
        <div class="logC">
          <a><button @click="login">登 录</button></a>
        </div>
      </div>
    </div>-->

    <!--<div style="z-index: -1; position: absolute; width: 100%;height: 100%">
      &lt;!&ndash;<img src="../assets/switzerland01.jpg" style="width: 100%;height: 100%">&ndash;&gt;
    </div>-->

    <!--<div style="z-index: 1; position: fixed; top: 50%;left: 50%;margin-left: -50px;margin-top: -50px;">
      <el-container>
        &lt;!&ndash;<el-header>
          <router-link to="/blogs">

          </router-link>
        </el-header>&ndash;&gt;
        <el-main>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="用户名" prop="username" class="item">
              <el-input type="text" maxlength="12" v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" class="item">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </div>-->

  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        password: '111111',
        username: 'markerhub'
      },
      rules: {
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      //因为下面axios若直接用this，会指的是axios地址
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 提交逻辑
          this.$axios.post('http://localhost:8081/login', this.ruleForm).then((res)=>{
            const token = res.headers['authorization']
            _this.$store.commit('SET_TOKEN', token)
            _this.$store.commit('SET_USERINFO', res.data.data)
            _this.$router.push("/blogs")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    /*this.$notify({
      title: '看这里：',
      message: '关注公众号：MarkerHub，回复【vueblog】，领取项目资料与源码',
      duration: 1500
    });*/
  }
}
</script>

<style scoped>
    /deep/ .item .el-form-item__label {
      color: white !important;
    }

    .backgroundImg {
      width: 100% !important;
      height: 100% !important;
      background-image: url(../assets/switzerland01.jpg) !important;
      position:fixed;
      background-size:100% 100%;
      z-index: -1
    }

    * {
      margin: 0;
      padding: 0;
    }

    #wrap {
      height: 600px;
      width: 100%;
      background-position: center center;
      position: relative;

    }

    #head {
      height: 120px;
      width: 100px;
      background-color: #66CCCC;
      text-align: center;
      position: relative;
    }

    #wrap .logGet {
      height: 408px;
      width: 368px;
      position: absolute;
      background-color: #FFFFFF;
      top: 100px;
      right: 15%;
    }

    .logC a button {
      width: 100%;
      height: 45px;
      background-color: #ee7700;
      border: none;
      color: white;
      font-size: 18px;
    }

    .logGet .logD.logDtip .p1 {
      display: inline-block;
      font-size: 28px;
      margin-top: 30px;
      width: 86%;
    }

    #wrap .logGet .logD.logDtip {
      width: 86%;
      border-bottom: 1px solid #ee7700;
      margin-bottom: 60px;
      margin-top: 0px;
      margin-right: auto;
      margin-left: auto;
    }

    .logGet .lgD img {
      position: absolute;
      top: 12px;
      left: 8px;
    }

    .logGet .lgD input {
      width: 100%;
      height: 42px;
      text-indent: 2.5rem;
    }

    #wrap .logGet .lgD {
      width: 86%;
      position: relative;
      margin-bottom: 30px;
      margin-top: 30px;
      margin-right: auto;
      margin-left: auto;
    }

    #wrap .logGet .logC {
      width: 86%;
      margin-top: 0px;
      margin-right: auto;
      margin-bottom: 0px;
      margin-left: auto;
    }


    .title {
      font-family: "宋体";
      color: #FFFFFF;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      /* 使用css3的transform来实现 */
      font-size: 36px;
      height: 40px;
      width: 30%;
    }

    .copyright {
      font-family: "宋体";
      color: #FFFFFF;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      /* 使用css3的transform来实现 */
      height: 60px;
      width: 40%;
      text-align: center;
    }
</style>