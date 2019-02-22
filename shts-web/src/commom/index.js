import cookie from 'vue-cookies';
import axios from 'axios';

export default {
    // 接口
    api: {
        sendCode: '/api/v1/code',
        login: '/api/v1/login',
        validateLogin: '/api/v1/login/validate'
    },
    // 检测登陆状态
    checkLoginStatus: function () {
        let token = cookie.get("token");
        if (token !== null) {
            return true;
            // axios.post(this.api.validateLogin,{token: token}, (ret) => {
            //     }).catch(() => {
            //
            // });
        }
        return false;
    },
    validateUsername: function (username) {
        if (username.length === 0) {
            return false;
        }
        return true;
    },
    validateEmail: function (email) {
        let reg = new RegExp("^[0-9]{5,10}$");
        return reg.test(email);

    },
    validateCode: function (code) {
        let reg = new RegExp("^[0-9]{6}$");
        return reg.test(code);
    },
    /**
     * 验证码倒计时
     */
    countDownTime: 60,
    countDown: function (VNode) {
        let el = VNode.$el;
        if (this.countDownTime <= 0) {
            el.textContent = "重新发送验证码";
            this.countDownTime = 60;
        } else {
            setTimeout(() => {
                el.textContent = this.countDownTime-- + 's';
                this.countDown(VNode);
            }, 1000);
        }
    }
}
