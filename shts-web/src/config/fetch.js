import axios from 'axios'
import qs from 'qs'

axios.defaults.baseURL = "http://127.0.0.1";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

export default (method = 'GET', url = '', data = {}) => {
  return new Promise((resolve) => {
    if (method === 'GET') {
      axios.get(url).then((res) => {
        resolve(res.data);
      }).catch(() => {
        resolve({
          status: 600,
          errMsg: '未知错误',
          data: null
        });
      })
    } else if (method === 'POST') {
      axios.post(url, qs.stringify(data)).then(res => {
        resolve(res.data);
      }).catch(() => {
        resolve({
          status: 600,
          errMsg: '未知错误',
          data: null
        });
      })
    }
  });
}





