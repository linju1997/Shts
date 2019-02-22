<template>
    <div id="loginAndRegister">
        <div class="nav">
            <van-nav-bar
                    left-text="主页"
                    title="登陆注册">
                <div slot="left">
                    <router-link to="/">
                        <van-icon size="23px" name="arrow-left"></van-icon>
                    </router-link>
                </div>
            </van-nav-bar>
        </div>
        <van-tabs v-model="active" @change="switchTab">
            <van-tab title="登陆">
                <div id="login" class="form-body">
                    <van-cell-group style="overflow: hidden;">
                        <van-field
                                label="邮箱"
                                left-icon="envelop-o"
                                v-model="email"
                                type="text"
                                ref="email"
                                placeholder="推荐使用QQ邮箱"
                        >
                            <div slot="icon">@qq.com</div>
                        </van-field>
                        <van-field
                                label="验证码"
                                left-icon="edit"
                                v-model="code"
                                type="text"
                                center
                                placeholder="请输入短信验证码"
                        >
                            <van-button slot="button" size="small" type="primary"
                                        @click="sendEmailCode">发送验证码
                            </van-button>
                        </van-field>
                    </van-cell-group>
                    <div class="form-commit">
                        <van-button type="default" @click="login" :block="true">登陆</van-button>
                    </div>
                </div>
            </van-tab>
            <van-tab title="注册">
                <div id="register" class="form-body">
                    <van-cell-group style="overflow: hidden;">
                        <van-field
                                label="用户名"
                                left-icon="contact"
                                v-model="username"
                                placeholder="好的名称会有好运气哦"
                                class="field-body">
                        </van-field>
                        <van-field
                                label="邮箱"
                                left-icon="envelop-o"
                                v-model="email"
                                type="text"
                                ref="email"
                                placeholder="推荐使用QQ邮箱"
                        >
                            <div slot="icon">@qq.com</div>
                        </van-field>
                        <van-field
                                label="验证码"
                                left-icon="edit"
                                v-model="code"
                                type="text"
                                center
                                placeholder="请输入短信验证码"
                        >
                            <van-button slot="button" size="small" type="primary"
                                        @click="sendEmailCode">发送验证码
                            </van-button>
                        </van-field>
                    </van-cell-group>
                    <div class="form-commit">
                        <van-button type="default" @click="login" :block="true">登陆</van-button>
                    </div>
                </div>
            </van-tab>
        </van-tabs>
    </div>
</template>

<script>
    import {Toast} from 'vant';
    import axios from 'axios';
    import qs from 'qs';

    Toast.setDefaultOptions({position: "top", duration: 1000});

    export default {
        name: 'loginAndRegister',
        data: function () {
            return {
                active: 0,
                tabName: '',
                username: '',
                email: '',
                code: ''
            }
        },
        methods: {
            switchTab: function (index, title) {
                this.active = index;
                this.tabName = title;
            },
            sendEmailCode: function () {
                if (!this.$common.validateEmail(this.email)) {
                    Toast({message: "请输入有效的邮箱"});
                    return;
                }
                axios.post(this.$common.api.sendCode, qs.stringify({email: this.qq_email})).then((res) => {
                    let ret = res.data;
                    if (ret.status === 'success') {
                        Toast({message: ret.data.msg});
                        // 发送验证码倒计时
                        // this.$common.countDown(this.$refs.sendCode);
                    } else {
                        Toast({message: ret.data.msg})
                    }
                }).catch(() => {
                    Toast({message: "网络错误"})
                });
            },
            login: function () {
                if (!this.$common.validateEmail(this.email)) {
                    Toast({message: "请输入有效的邮箱"});
                    return;
                }
                if (!this.$common.validateCode(this.code)) {
                    Toast({message: "验证码有误"});
                    return;
                }
                axios.post(this.$common.api.login, qs.stringify({
                    email: this.qq_email,
                    code: this.code
                })).then(res => {
                    let ret = res.data;
                    Toast({message: ret.data.msg});
                    if (ret.status === 'success') {
                        this.$router.push('/home');
                    } else {
                        Toast({message: "网络错误"});
                    }
                }).catch(() => {
                    Toast({message: "网络错误"});
                });
            },
            register: function () {
                if (!this.$common.validateEmail(this.email)) {
                    Toast({message: "请输入有效的邮箱"});
                    return;
                }
                if (!this.$common.validateCode(this.code)) {
                    Toast({message: "验证码有误"});
                    return;
                }
                axios.post(this.$common.api.register, qs.stringify({
                    username: this.username,
                    email: this.qq_email,
                    code: this.code
                })).then(res => {
                    let ret = res.data;
                    Toast({message: ret.data.msg});
                    if (ret.status === 'success') {
                        this.$router.push('/home');
                    }
                }).catch(() => {
                    Toast({message: "网络错误"});
                });
            }
        },
        computed: {
            qq_email: function () {
                return this.email + '@qq.com';
            }
        }
    }
</script>

<style scoped>
    #login .form-commit {
        margin: 20px 10px;
    }

    #register .form-commit {
        margin: 20px 10px;
    }
</style>