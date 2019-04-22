<template>
  <div id="setting">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/home')}"
      title="关于">
    </van-nav-bar>
    <div style="margin-top: 80px;text-align: center;">
      <p style="font-size: 25px;font-weight: bold;">校园二手交易系统</p>
      <p style="width: 80%;margin: 45px auto;">
        一款基于MVC的校园二手交易系统<br>给学生提供一个平台，用于交易闲置的物品。<br>
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "",
  }
</script>

<style scoped>

</style>
