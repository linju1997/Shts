import {sendCode} from './getData'

function sendCode1() {
  let data = {
    email:'2218890669@qq.com'
  }
  sendCode(data)
    .then(ret=>console.log(ret))
    .catch(err=>console.log(err))
}

sendCode1()
