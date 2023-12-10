import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8090',
  timeout: 5000,
})

http.interceptors.request.use(
  (config) => {

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
