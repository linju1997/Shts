<template>
    <div id="loginAndRegister">
        <div class="nav">
            <van-nav-bar
                    left-text="主页"
                    :title="titleName">
                <div slot="left">
                    <router-link to="/">
                        <van-icon size="23px" name="arrow-left"></van-icon>
                    </router-link>
                </div>
            </van-nav-bar>
        </div>
        <div id="form" class="form-body">
            <van-cell-group style="overflow: hidden;">
                <van-field
                        label="用户名"
                        left-icon="contact"
                        v-model="username"
                        placeholder="请输入用户名"
                        v-if="show&0b1000"
                        class="field-body">
                </van-field>
                <van-field
                        label="密码"
                        left-icon="edit"
                        v-model="password"
                        type="password"
                        v-if="show&0b0100"
                        class="field-body">
                </van-field>
                <van-field
                        label="邮箱"
                        left-icon="envelop-o"
                        v-model="email"
                        type="text"
                        ref="email"
                        v-if="show&0b0010"
                        placeholder="推荐使用QQ邮箱"
                >
                </van-field>
                <van-field
                        label="验证码"
                        left-icon="edit"
                        v-model="code"
                        type="text"
                        center
                        v-if="show&0b0001"
                        placeholder="请输入短信验证码"
                >
                    <van-button slot="button"
                                size="small"
                                type="primary"
                                v-if="countDownTime<0"
                                @click="sendEmailCode">发送验证码
                    </van-button>
                    <van-button slot="button"
                                size="small"
                                type="primary"
                                disabled
                                v-if="countDownTime>=0"
                                @click="sendEmailCode">{{countDownTime}}
                    </van-button>
                </van-field>
            </van-cell-group>
            <div id="login_type">
                <p>
                    <span @click="switchLoginType">{{loginType}}</span>
                    <span v-if="titleName==='登录'"
                          @click="switchRegister"
                          style="float: right;color: blue"
                    >
                        还未注册?
                    </span>
                </p>
            </div>
            <div class="form-commit">
                <van-button type="default" @click="commit" :block="true">{{titleName}}</van-button>
            </div>
        </div>
    </div>
</template>

<script>
    import qs from 'qs';

    export default {
        name: 'loginAndRegister',
        data: function () {
            return {
                titleName: '登录',
                loginType: '邮箱登录',
                username: '',
                password: '',
                email: '2558807914@qq.com',
                code: '',
                show: 0b0011,
                countDownTime: -1
            }
        },
        methods: {
            switchLoginType() {
                this.titleName = '登录';
                this.show = this.show === 0b0011 ? 0b1100 : 0b0011;
            },
            switchRegister() {
                this.titleName = '注册';
                this.show = 0b1011;
            },
            sendEmailCode: function () {
                if (!this.$cm.validateEmail(this.email)) {
                    this.$toast("请输入有效的邮箱");
                    return;
                }
                this.$axios.post(this.$cm.api.sendCode, qs.stringify({
                    email: this.email
                })).then((res) => {
                    let ret = res.data;
                    if (ret.status === 'success') {
                        this.$toast(ret.data.msg);
                        // 发送验证码倒计时,默认5s
                        this.countDownTime = 5;
                        let timer = setInterval(() => {
                            if (this.countDownTime <= 0) {
                                this.countDownTime = -1;
                                clearInterval(timer);
                            }
                            this.countDownTime = this.countDownTime - 1;
                        }, 1000);
                    } else {
                        this.$toast(ret.data.msg);
                    }
                }).catch(() => {
                    this.$toast("网络错误");
                });
            },
            commit:function(){
                if (this.titleName === '注册') {
                    this.register();
                } else {
                    this.login();
                }
            },
            login: function () {
                if (this.loginType === '账号密码登录') {
                    if (!this.$cm.validateUsername(this.username)) {
                        this.$toast("用户名不能为空");
                        return
                    }
                    if (!this.$cm.validatePassword(this.password)) {
                        this.$toast("密码不能为空");
                        return
                    }
                    this.$axios.post(this.$cm.api.login, qs.stringify({
                        username: this.username,
                        password: this.password
                    })).then(res => {
                        let ret = res.data;
                        this.$toast(ret.data.msg);
                        this.$router.push("/home");
                    }).catch(() => {
                        this.$toast("网络错误");
                    });
                } else if (this.loginType === '邮箱登录') {
                    if (!this.$cm.validateEmail(this.email)) {
                        this.$toast("请输入有效的邮箱");
                        return;
                    }
                    if (!this.$cm.validateCode(this.code)) {
                        this.$toast("验证码有误");
                        return;
                    }
                    this.$axios.post(this.$cm.api.login, qs.stringify({
                        email: this.email,
                        code: this.code,
                        type: '2223'
                    })).then(res => {
                        let ret = res.data;
                        this.$toast(ret.data.msg);
                        this.$router.push("/home");
                    }).catch(() => {
                        this.$toast("网络错误");
                    });
                } else {
                    this.$toast("登录方式有误");
                }
            },
            register: function () {
                if (!this.$cm.validateUsername(this.username)) {
                    this.$toast("用户名不能为空");
                    return
                }
                if (!this.$cm.validateEmail(this.email)) {
                    this.$toast("请输入有效的邮箱");
                    return;
                }
                if (!this.$cm.validateCode(this.code)) {
                    this.$toast("验证码有误");
                    return;
                }
                this.$axios.post(this.$cm.api.register, qs.stringify({
                    username: this.username,
                    email: this.email,
                    code: this.code
                })).then(res => {
                    let ret = res.data;
                    this.$toast(ret.data.msg);
                    this.switchLoginType();
                }).catch(() => {
                    this.$toast("网络错误");
                });
            }
        }
    }
</script>

<style scoped>

    #form .form-commit {
        margin: 20px 10px;
    }

    #login_type {
        margin: 5px 10px;
        padding: 0 10px;
    }

</style>