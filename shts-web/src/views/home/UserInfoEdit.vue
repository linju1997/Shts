<template>
  <div id="question">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/home')}"
      title="修改个人信息"></van-nav-bar>
    <van-cell-group>
      <van-cell
        title="用户名"
        :value="userInfo.username"
        to="/userinfo/modify/username"
        is-link>
      </van-cell>
      <van-cell
        title="邮箱"
        :value="userInfo.email"
        to="/userinfo/modify/email"
        is-link>
      </van-cell>
      <van-cell
        title="手机号码"
        :value="userInfo.phone"
        to="/userinfo/modify/phone"
        is-link>
      </van-cell>
      <van-cell
        title="地址"
        :value="userInfo.address"
        to="/userinfo/modify/address"
        is-link>
      </van-cell>
      <van-cell
        title="注销账号"
        value="注销后无法恢复"
        @click="()=>{this.$toast({message:'暂时无法注销',duration:1000,position:'top'})}"
        is-link
        size="large">
      </van-cell>
    </van-cell-group>
  </div>
</template>

<script>
  import {getUserInfo} from '@/config/getData'

  export default {
    name: "UserInfoEdit",
    data: function () {
      return {
        userInfo: {
          phone: '',
          address: '',
          email: '',
          username: ''
        }
      }
    },
    methods: {},
    created() {
      getUserInfo().then(res => {
        if (res.status === 200) {
          this.userInfo = res.data;
          this.$store.commit('changeUserInfo', this.userInfo);
        } else {
          this.$toast(res.errMsg);
        }
      });
    },
    computed: {}
  }
</script>

<style scoped>

</style>
