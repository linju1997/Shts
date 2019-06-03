<template>
  <div id="question">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/userinfo/edit')}"
      title="修改手机号码">
    </van-nav-bar>
    <van-cell-group style="overflow: hidden;">
      <van-field
        label="手机号码"
        v-model="phone"
        class="field-body">
      </van-field>
    </van-cell-group>
    <div class="form-commit">
      <van-button
        type="default"
        :block="true"
        @click="commit"
      >
        修改手机号码
      </van-button>
    </div>
  </div>
</template>

<script>
  import {modifyPhone} from "../../../config/getData";
  import {mapState} from 'vuex'

  export default {
    name: "ModifyUsername",
    data: function () {
      return {
        phone: ''
      }
    },
    methods: {
      commit: function () {
        if (this.phone === '' || this.phone.length !== 11) {
          this.$toast("输入的手机号码有误");
          return
        }
        modifyPhone({phone: this.phone}).then(res => {
          if (res.status === "success") {
            this.$toast(res.data);
          } else {
            this.$toast(res.errMsg);
          }
        })
      }
    },
    computed: {
      ...mapState(['userInfo'])
    }
  }
</script>

<style scoped>
  .form-commit {
    width: 94%;
    margin: 20px auto;
  }
</style>
