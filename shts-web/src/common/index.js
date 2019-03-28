export default {
    // 接口
    api: {
        sendCode: '/api/v1/code',
        login: '/api/v1/login',
        register: '/api/v1/register',
        feedback: '/api/v1/feedback',

        // UserInfo
        getUserInfo: '/api/v1/userinfo'
    },
    validateUsername: function (username) {
        return username.length >= 6;

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
