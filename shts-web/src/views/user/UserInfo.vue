<template>
  <div id="loginAndRegister">
    <div class="nav">
      <van-nav-bar
        title="个人信息填写"
        right-text="跳过"
        @click-right="()=>{this.$router.push('/home')}">
      </van-nav-bar>
    </div>
    <div
      id="form"
      class="form-body"
    >
      <van-cell-group style="overflow: hidden;">
        <van-field
          label="手机号码"
          left-icon="contact"
          v-model="phone"
          placeholder="移动 | 联通 | 电信"
          class="field-body">
        </van-field>
        <van-field
          label="地址"
          left-icon="envelop-o"
          v-model="address"
          type="text"
          placeholder="收货地址">
        </van-field>
        <van-field
          label="备注"
          left-icon="edit"
          v-model="remark"
          type="text"
          placeholder="备注"
        >
        </van-field>
      </van-cell-group>
      <div class="form-commit">
        <van-button
          type="default"
          @click="commit"
          :block="true"
        >
          提交
        </van-button>
      </div>
    </div>
  </div>
</template>

<script>
  import {updateUserInfo} from '../../config/getData'

  export default {
    name: 'FillUserInfo',
    data: function () {
      return {
        phone: '',
        address: '',
        remark: '',
      }
    },
    methods: {
      commit: function () {
        if (this.phone === '' || this.address === '' || this.remark === '') {
          this.$toast("参数不能为空");
          return;
        }
        updateUserInfo({
          phone: this.phone,
          address: this.address,
          remark: this.remark
        }).then(res => {
          if (res.status !== "success") {
            this.$toast("个人信息台添加失败，请稍后到个人信息页面添加");
          }
          this.$toast("注册成功，补全个人信息");
          this.$router.push('/home');
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
