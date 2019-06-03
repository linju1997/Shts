<template>
  <div id="question">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/userinfo/edit')}"
      title="修改用户名">
    </van-nav-bar>
    <van-cell-group style="overflow: hidden;">
      <van-field
        label="用户名"
        v-model="userInfo.username"
        class="field-body">
      </van-field>
    </van-cell-group>
    <div class="form-commit">
      <van-button
        type="default"
        :block="true"
        @click="commit"
      >
        修改用户名
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
      return {
      }
    },
    methods:{
      commit:function () {
        if (this.userInfo.username === '') {
          this.$toast('参数不能为空');
          return;
        }
        modifyUsername({username:this.userInfo.username}).then(res=>{
          if (res.status === "success") {
            this.$toast(res.data);
          }else{
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
