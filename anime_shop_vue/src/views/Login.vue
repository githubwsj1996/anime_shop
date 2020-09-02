<template>
  <div style="width: 100%; margin:0; padding: 0;">
    <div class="log_top">
      <p style="margin: 0 auto;padding: 0;">欢迎登录^_^</p>
    </div>

    <div class="log_center"></div>

    <div class="log_main">
      <!--用户表单 -->
      <van-form validate-first @submit="onSubmit">
        <van-field
          v-model="username"
          clearable
          label="用户名"
          right-icon="question-o"
          placeholder="请输入用户名"
          left-icon="contact"
          @blur="checkName"
          @click-right-icon="$toast('用户名可以由字母,数字,下划线组成')"
          :rules="[{required : true,message:'用户名不能为空'},
                                 {validator: this.$validator_user.validator_userName1,message:'输入的长度不能超过20位,请重新输入'},
                                 {validator: this.$validator_user.validator_userName2,message:'输入的字符含有特殊字符,请重新输入'},]"
        />
        <!--密码-->
        <van-field id="password"
          v-model="password"
          clearable
          type="password"
          label="密码"
          right-icon="question-o"
          placeholder="请输入密码"
          left-icon="closed-eye"
          @click-right-icon="$toast('密码必须是数字、字母、下划线')"
          :rules="[{required : true,message:'密码不能为空'},
                                 {validator: this.$validator_user.validator_passWord1, message: '登录密码不能少于6个字符'}]"
        />
        <!--验证码-->
        <van-field
          v-model="verifyCode"
          clearable
          label="验证码"
          placeholder="请输入验证码"
          left-icon="info-o"
          @focus="verifyCodeClick"
          :rules="[{required : true,message:'验证码不能为空'},
                                 {validator: validator_verifyCode,message: '验证码输入错误'}]"
        >
          <template #button>
            <div @click="refreshCode" class="verifyCode">
              <s-identify :identifyCode="identifyCode"></s-identify>
            </div>
            <div class="identifyBox" @click="refreshCode" v-show="verifyCode_show">看不清,换一张！</div>
          </template>
        </van-field>
        <!--登录按钮-->
        <div class="bottom_btn">
          <van-button type="danger" size="large" @click="onReset">重置</van-button>
          <van-button
            type="primary"
            size="large"
            native-type="submit"
            style="margin-top: 10px;margin-bottom: 10px"
          >登录</van-button>
        </div>
      </van-form>

      <a
        style="color: orangered;position:absolute;
                right:0;margin-right: 10px;margin-bottom: 100px"
        href="/register"
      >没有账号，请先注册~~</a>
    </div>
  </div>
</template>

<script>
import Toast from "vant/lib/toast";
import SIdentify from "@/utils/SIdentify.vue";
import md5 from "js-md5";
export default {
  name: "login",
  data() {
    return {
      errors: [],
      username: "",
      password: "",
      verifyCode: "",
      identifyCodes: "1234567890",
      identifyCode: "",
      verifyCode_show: false
    };
  },
  components: { SIdentify },
  mounted() {
    // 验证码初始化
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
  },
  methods: {
    //验证码
    validator_verifyCode(val) {
      if (val !== this.identifyCode) {
        this.refreshCode();
        return false;
      }
      return true;
    },
    //检索用户名是否重复
    checkName() {
      if (this.username !== null) {
        axios
          .get("http://localhost:8040//UserInfo/checkName/" + this.username)
          .then(function(resp) {
            if (resp.data.code === 1) {
              Toast.fail(resp.data.data);
              history.go(0);
            }
          });
      }
    },
    //重置
    onReset() {
      history.go(0);
    },
    //登录
    onSubmit() {
      let user = {
        user_name: this.username,
        user_password: this.password
      };
      const _this=this
      axios.post("http://localhost:8040/UserInfo/login", user).then(function(resp) {
          let data = resp.data;
          if (data.code === 0) {
            if (data.data.userPerm === "0") {
              let user = {
                userId: data.data.userId,
                username: data.data.username,
                password:data.data.password,
                userAddressId: data.data.userAddressId,
                userIcon: data.data.userIcon
              };
              sessionStorage.setItem("user", JSON.stringify(user));
              _this.$router.push("/user_actions");
            } else if (data.data.userPerm === "1") {
              let admin = {
                userId: data.data.userId,
                username: data.data.username,
                password:data.data.password,
                userAddressId: data.data.userAddressId,
                userIcon: data.data.userIcon
              };
              sessionStorage.setItem("admin", JSON.stringify(admin));
              Toast.success("管理员来了");
            }
          } else {
            Toast.fail(data.data);
            history.go(0);
          }
        });
    },
    //验证码
    verifyCodeClick() {
      this.verifyCode_show = true;
    },
    // 生成随机数
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
    // 切换验证码
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    // 生成四位随机验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ];
      }
      console.log(this.identifyCode);
    }
  }
};
</script>

<style scoped>
.log_top {
  width: 100%;
  height: 60px;
  font-size: 24px;
  line-height: 60px;
  text-align: center;
  color: #f6371e;
  margin: 0;
  display: flex;
}

.log_main {
  margin-top: 10px;
}

.log_center {
  width: 100%;
  height: 300px;
  background-image: url("../../public/static/images/bg/logbg.jpg");
  background-size: 100%;
}

.identifyBox {
  color: dodgerblue;
}

.bottom_btn {
  margin-top: 20px;
}
</style>