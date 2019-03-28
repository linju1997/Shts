<template>
  <div id="setting">
    <van-nav-bar
        left-text="返回"
        left-arrow
        @click-left="()=>{this.$router.push('/home')}"
        title="关于">
    </van-nav-bar>
    这是一个基于MVC开发的校园二手交易系统
  </div>
</template>

<script>
    export default {
        name: "Setting",
    }
</script>

<style scoped>

</style>