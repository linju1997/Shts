<template>
  <div id="question">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/userinfo/edit')}"
      title="修改个性签名">
    </van-nav-bar>
    <van-cell-group style="overflow: hidden;">
      <van-field
        label="个性签名"
        type="textarea"
        label-width="80"
        v-model="userInfo.remark"
        class="field-body">
      </van-field>
    </van-cell-group>
    <div class="form-commit">
      <van-button
        type="default"
        :block="true"
        @click="commit"
      >
        确认修改
      </van-button>
    </div>
  </div>
</template>

<script>
  import {modifyUsername} from "../../../config/getData";
  import {mapState} from 'vuex'


  export default {
    name: "ModifyUsername",
    data: function () {
      return {}
    },
    methods: {
      commit: function () {
        if (this.userInfo.remark === '') {
          this.$toast('参数不能为空');
          return;
        }
        modifyUsername({remark: this.userInfo.remark}).then(res => {
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
