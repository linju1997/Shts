export default {

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
