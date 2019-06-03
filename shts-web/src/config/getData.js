import fetch from './fetch'

/**
 * API
 */
let api = {
  sendCode: '/code',
  login: '/login',
  exit: '/exit',
  register: '/register',

  // UserInfo
  getUserInfo: '/userinfo',
  updateUserInfo: '/userinfo/update',
  // modifyUsername: '/modify/username',
  // modifyAddress: '/modify/address',
  modifyEmail: '/modify/email',

  // feedback
  feedback: '/feedback',
  feedback1: '/feedback/1',
  feedback2: '/feedback/2',
  feedbackUpdate: '/feedback/udpate',

  // publishGoods
  publishGoods: '/goods/publish',
  updateGoods: '/goods/update',

  // Goods
  swipeGoods: '/goods/swipe',
  newPublishGoods: '/goods/new',
  getGoodsById: '/goods/gid'
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

export const modifyUsername = (data) => fetch('POST', api.updateUserInfo, data)

export const modifyRemark = (data) => fetch('POST', api.updateUserInfo, data)

export const modifyAddress = (data) => fetch('POST', api.updateUserInfo, data)

export const modifyPhone = (data) => fetch('POST', api.updateUserInfo, data)

export const modifyEmail = (data) => fetch('POST', api.modifyEmail, data)

export const publishGoods = (data) => fetch('POST', api.publishGoods, data)

export const updateGoods = (data) => fetch('POST', api.updateGoods, data)

export const swipeGoods = () => fetch('POST', api.swipeGoods)

export const newPublishGoods = () => fetch('POST', api.newPublishGoods)

export const getGoodsById = (data) => fetch('POST', api.getGoodsById, data)
