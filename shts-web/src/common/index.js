import cookie from 'vue-cookies'
export default {
    // 接口
    api: {
        sendCode: '/api/v1/code',
        login: '/api/v1/login',
        register: '/api/v1/register',

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
    validatePassword: function (password) {
        if (password.length === 0) {
            return false;
        }
        return true;
    },
    validateEmail: function (email) {
        let reg = new RegExp("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");
        return reg.test(email);

    },
    validateCode: function (code) {
        let reg = new RegExp("^[0-9]{6}$");
        return reg.test(code);
    },

}
