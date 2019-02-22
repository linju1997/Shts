<template>
    <div code="modify-password">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.push('/userinfo/edit')}"
                title="修改密码"/>
        <div class="form-body">
            <van-cell-group>
                <van-field
                        label="原密码"
                        v-model="oldpassword"
                        class="field-body"
                />
                <van-field
                        label="新密码"
                        v-model="password"
                        class="field-body"
                />
            </van-cell-group>

            <div class="form-commit">
                <van-button type="default" :block="true">修改密码</van-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ModifyPassword",
        data: function () {
            return {
                oldpassword: '',
                password: ''
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