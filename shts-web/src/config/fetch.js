import axios from 'axios'
import qs from 'qs'
import {Toast} from "vant"

axios.defaults.baseURL = "http://fves24.cn:8081";
axios.defaults.withCredentials = true;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

export default (method = 'GET', url = '', data = {}) => {
  return new Promise((resolve, reject) => {
    if (method === 'GET') {
      axios.get(url).then((res) => {
        resolve(res.data);
      }).catch(() => {
        Toast({
          position: "top",
          message: "网络错误"
        })
      })
    } else if (method === 'POST') {
      axios.post(url, qs.stringify(data)).then(res => {
        resolve(res.data);
      }).catch(() => {
        Toast({
          position: "top",
          message: "网络错误"
        })
      })
    }
  });
}





