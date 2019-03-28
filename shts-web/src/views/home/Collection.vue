<template>
  <div id="contact">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/home')}"
      title="我的收藏">
    </van-nav-bar>
  </div>
</template>

<script>
    import qs from 'qs';

    export default {
        name: "Contact",
        data: function () {
            return {}
        }
    }
</script>

<style scoped>
    #form .form-commit {
        margin: 20px 10px;
    }

</style>