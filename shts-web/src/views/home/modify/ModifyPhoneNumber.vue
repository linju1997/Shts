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
        v-model="userInfo.phone"
        class="field-body">
      </van-field>
    </van-cell-group>
    <div class="form-commit">
      <van-button
        type="default"
        :block="true"
      >
        修改手机号码
      </van-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ModifyUsername",
    data: function () {
      return {
        userInfo: {}
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
  .form-commit {
    width: 94%;
    margin: 20px auto;
  }
</style>
