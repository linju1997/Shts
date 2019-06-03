<template>
  <div id="loginAndRegister">
    <div class="nav">
      <van-nav-bar
        left-text="主页"
        :title="titleName"
      >
        <div slot="left">
          <router-link to="/">
            <van-icon
              size="23px"
              name="arrow-left">
            </van-icon>
          </router-link>
        </div>
        <div slot="right">
          <div
            @click="switchLogin"
            v-if="titleName==='注册'"
          >
            登录
          </div>
          <div
            @click="switchRegister"
            v-if="titleName==='登录'"
            style="color: blue;"
          >
            还未注册?
          </div>
        </div>
      </van-nav-bar>
    </div>
    <div
      id="form"
      class="form-body"
    >
      <van-cell-group style="overflow: hidden;">
        <van-field
          label="用户名"
          left-icon="contact"
          v-model="username"
          placeholder="请输入用户名"
          v-if="show&0b100"
          class="field-body">
        </van-field>
        <van-field
          label="邮箱"
          left-icon="envelop-o"
          v-model="email"
          type="text"
          ref="email"
          v-if="show&0b010"
          placeholder="推荐使用QQ邮箱">
        </van-field>
        <van-field
          label="验证码"
          left-icon="edit"
          v-model="code"
          type="text"
          center
          v-if="show&0b001"
          placeholder="请输入短信验证码"
        >
          <van-button
            slot="button"
            size="small"
            type="primary"
            v-if="countDownTime<0"
            @click="sendEmailCode"
          >
            发送验证码
          </van-button>
          <van-button
            slot="button"
            size="small"
            type="primary"
            disabled
            v-if="countDownTime>=0"
            @click="sendEmailCode"
          >
            {{ countDownTime }}
          </van-button>
        </van-field>
      </van-cell-group>
      <div class="form-commit">
        <van-button
          type="default"
          @click="commit"
          :block="true"
        >
          {{ titleName }}
        </van-button>
      </div>
    </div>
  </div>
</template>

<script>
  import {sendCode, login, register} from "../../config/getData";

  export default {
    name: 'LoginAndRegister',
    data: function () {
      return {
        titleName: '登录',
        username: '',
        email: '2558807914@qq.com',
        code: '',
        show: 0b011,
        countDownTime: -1
      }
    },
    methods: {
      switchLogin: function () {
        this.titleName = '登录';
        this.show = 0b011;
      },
      switchRegister: function () {
        this.titleName = '注册';
        this.show = 0b111;
      },
      sendEmailCode: function () {
        if (!this.$cm.validateEmail(this.email)) {
          this.$toast("请输入有效的邮箱");
          return;
        }

        sendCode({email: this.email}).then(ret => {
          if (ret.status === "success") {
            this.$toast(ret.data);
            this.countDownTime = 5;
            let timer = setInterval(() => {
              if (this.countDownTime <= 0) {
                this.countDownTime = -1;
                clearInterval(timer);
              }
              this.countDownTime = this.countDownTime - 1;
            }, 1000);
          } else {
            this.$toast(ret.errMsg);
          }
        });
      },
      commit: function () {
        if (this.titleName === '注册') {
          this.register();
        } else {
          this.login();
        }
      },
      login: function () {
        if (!this.$cm.validateEmail(this.email)) {
          this.$toast("请输入有效的邮箱");
          return;
        }
        if (!this.$cm.validateCode(this.code)) {
          this.$toast("验证码有误");
          return;
        }
        login({email: this.email, code: this.code}).then(ret => {
          if (ret.status === "success") {
            this.$store.commit("login")
            this.$router.push("/home");
          } else {
            this.$toast(ret.errMsg);
          }
        })
      },
      register: function () {
        if (!this.$cm.validateUsername(this.username)) {
          this.$toast("用户名长度必须为2-6为字符");
          return
        }
        if (!this.$cm.validateEmail(this.email)) {
          this.$toast("请输入有效的邮箱");
          return;
        }
        if (!this.$cm.validateCode(this.code)) {
          this.$toast("验证码有误");
          return;
        }
        register({username: this.username, email: this.email, code: this.code}).then(ret => {
          if (ret.status === "success") {
            this.$store.commit("login")
            this.$router.push("/userinfo");
          } else {
            this.$toast(ret.errMsg);
          }
        })
      }
    }
  }
</script>

<style scoped>

  #form .form-commit {
    margin: 20px 10px;
  }

</style>
