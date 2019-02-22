<template>
    <div code="question">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.push('/userinfo/edit')}"
                title="修改邮箱"/>
        <div class="form-body">
            <van-cell-group style="overflow: hidden;">
                <van-field
                        label="新邮箱"
                        v-model="email"
                        placeholder="推荐使用QQ邮箱"
                        class="field-body"
                />
                <van-field
                        label="验证码"
                        v-model="code"
                        class="field-body"
                >
                    <van-button slot="button" size="small" type="primary" @click="sendEmailCode()">发送验证码
                    </van-button>
                </van-field>
            </van-cell-group>
            <div class="form-commit">
                <van-button type="default" :block="true">修改邮箱</van-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ModifyEmail",
        data: function () {
            return {
                "email": '',
                "code": ''
            }
        },
        methods: {
            sendEmailCode: function () {
                this.$toast.success({message: "发送验证码"})
            }
        }

    }
</script>

<style scoped>
    .form-body {
        width: 98%;
        margin: 30px auto;
    }


    .form-body .field-body {
        margin-top: 10px;
    }

    .form-body .form-commit {
        margin: 20px 0;
    }
</style>