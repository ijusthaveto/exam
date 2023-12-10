import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8090',
  timeout: 5000,
})

http.interceptors.request.use(
  (config) => {
    const tokenName = localStorage.getItem('tokenName')
    const tokenValue = localStorage.getItem('tokenValue')
      if (tokenName && tokenValue) {
          config.headers[tokenName] = tokenValue
      }
    return config
  },
  (e) => Promise.reject(e)
)

http.interceptors.response.use(
  (res) => res.data,
  (e) => {
    return Promise.reject(e)
  }
)

export default http
