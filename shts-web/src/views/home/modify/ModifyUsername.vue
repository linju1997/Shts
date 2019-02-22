<template>
    <div code="question">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.push('/userinfo/edit')}"
                title="修改用户名"/>
        <div class="form-body">
            <van-cell-group style="overflow: hidden;">
                <van-field
                        label="用户名"
                        v-model="username"
                        class="field-body"
                />
            </van-cell-group>
            <div class="form-commit">
                <van-button type="default" :block="true">修改用户名</van-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ModifyUsername",
        data: function () {
            return {
                username: '忆'
            }

        }
    }
</script>

<style scoped>
    .form-body {
        width: 98%;
        margin: 30px auto;
    }

    .form-body .form-commit {
        margin: 20px 0;
    }
</style>