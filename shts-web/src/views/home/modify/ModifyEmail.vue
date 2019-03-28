<template>
  <div id="question">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/userinfo/edit')}"
      title="修改邮箱">
    </van-nav-bar>
    <van-cell-group style="overflow: hidden;">
      <van-field
        label="新邮箱"
        placeholder="推荐使用QQ邮箱"
        v-model="userInfo.email"
        class="field-body">
      </van-field>
      <van-field
        label="验证码"
        v-model="code"
        class="field-body"
      >
        <van-button
          slot="button"
          size="small"
          type="primary"
          @click="sendEmailCode()"
        >
          发送验证码
        </van-button>
      </van-field>
    </van-cell-group>
    <div class="form-commit">
      <van-button
        type="default"
        :block="true"
        @click="commit"
      >
        修改邮箱
      </van-button>
    </div>
  </div>
</template>

<script>
  import {sendCode, modifyEmail, getUserInfo} from "../../../config/getData";

  export default {
    name: "ModifyEmail",
    data: function () {
      return {
        code: '',
        userInfo: {}
      }
    },
    methods: {
      sendEmailCode: function () {
        console.log(this.userInfo.email)
        sendCode({email: this.userInfo.email}).then(res => {
          if (res.status === 200) {
            this.$toast(res.data);
          } else {
            this.$toast(res.errMsg);
          }
        });
      },
      commit: function () {
        modifyEmail({newEmail: this.userInfo.email, code: this.code}).then(res => {
          if (res.status === 200) {
            this.$toast(res.data);
          } else {
            this.$toast(res.errMsg);
          }
        })
      }
    },
    created() {
      getUserInfo().then(res => {
        if (res.status === 200) {
          this.userInfo = res.data;
          this.$store.commit('changeUserInfo', this.userInfo);
        } else {
          this.$toast(res.errMsg);
        }
      });
    }
  }
</script>

<style scoped>

  .field-body {
    margin-top: 10px;
  }

  .form-commit {
    width: 94%;
    margin: 20px auto;
  }
</style>
