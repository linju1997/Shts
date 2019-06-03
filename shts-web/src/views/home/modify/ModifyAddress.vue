<template>
  <div id="address-edit">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/userinfo/edit')}"
      title="地址编辑">
    </van-nav-bar>
    <van-cell-group style="overflow: hidden;">
      <van-field
        label="地址"
        v-model="userInfo.address"
        placeholder="交易具体地址，如：1号教学楼，302教室门口。"
        class="field-body">
      </van-field>
    </van-cell-group>
    <div class="form-commit">
      <van-button
        type="default"
        :block="true"
        @click="commit"
      >
        保存地址
      </van-button>
    </div>
  </div>
</template>

<script>
  import {modifyAddress} from "../../../config/getData";
  import {mapState} from 'vuex'

  export default {
    name: "AddressEdit",
    data: function () {
      return {
      }
    },
    methods: {
      commit: function () {
        if (this.userInfo.address === '') {
          this.$toast('参数不能为空');
          return;
        }
        modifyAddress({address: this.userInfo.address}).then(res => {
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
