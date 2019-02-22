<template>
    <div code="question">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.go(-1)}"
                title="修改个人信息"/>
        <van-cell-group style="width:98%;margin: 30px auto;">
            <van-cell title="用户名" value="忆" to="/userinfo/modify/username" is-link/>
            <van-cell title="支付密码" value="修改" to="/userinfo/modify/password" is-link/>
            <van-cell title="邮箱" value="2218890669@qq.com" to="/userinfo/modify/email" is-link/>
            <van-cell title="注销账号" value="注销后无法恢复"
                      @click="()=>{this.$toast({message:'暂时无法注销',duration:1000,position:'top'})}" is-link size="large"/>
        </van-cell-group>
    </div>
</template>

<script>
    export default {
        name: "UserInfoEdit",
        data: function () {
            return {}
        }
    }
</script>

<style scoped>
    .form-body {
        width: 96%;
        margin: 30px auto;
    }


    .form-body .field-body {
        margin-top: 10px;
    }

    .form-body .form-commit {
        margin: 20px 0;
    }
</style>