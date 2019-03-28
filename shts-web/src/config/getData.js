import fetch from './fetch'

/**
 * API
 */
let api = {
  sendCode: '/api/v1/code',
  login: '/api/v1/login',
  exit: '/api/v1/exit',
  register: '/api/v1/register',

  // UserInfo
  getUserInfo: '/api/v1/userinfo',
  updateUserInfo: '/api/v1/userinfo/update',
  modifyUsername: '/api/v1/modify/username',
  modifyAddress: '/api/v1/modify/address',
  modifyEmail: '/api/v1/modify/email',

  // feedback
  feedback: '/api/v1/feedback',
  feedback1: '/api/v1/feedback/1',
  feedback2: '/api/v1/feedback/2',
  feedbackUpdate: '/api/v1/feedback/udpate'
}

/**
 * 发送验证码
 */
export const sendCode = (data) => fetch('POST', api.sendCode, data)

export const login = (data) => fetch('POST', api.login, data)

export const exit = () => fetch('POST', api.exit);

export const register = (data) => fetch('POST', api.register, data)

export const feedback = (data) => fetch('POST', api.feedback, data)

export const feedBack1 = () => fetch('POST', api.feedback1)

export const feedBack2 = (data) => fetch('POST', api.feedback2, data)

export const getUserInfo = (data) => fetch('POST', api.getUserInfo, data)

export const updateUserInfo = (data) => fetch('POST', api.updateUserInfo, data)

export const modifyUsername = (data) => fetch('POST', api.modifyUsername, data)

export const modifyAddress = (data) => fetch('POST', api.modifyAddress, data);

export const modifyEmail = (data) => fetch('POST', api.modifyEmail, data);


