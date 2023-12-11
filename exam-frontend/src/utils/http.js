import axios from 'axios'

const httpInstance = axios.create({
    baseURL: 'http://localhost:8090',
    timeout: 5000
})

// axios请求拦截器
httpInstance.interceptors.request.use(
    (config) => {
        const tokenName = localStorage.getItem('tokenName')
        const tokenValue = localStorage.getItem('tokenValue')
        if (tokenName !== undefined && tokenValue != '') {
            config.headers[tokenName] = tokenValue
        }
        return config
    },
    (e) => Promise.reject(e)
)

// axios响应式拦截器
httpInstance.interceptors.response.use(
    (res) => res.data,
    (e) => {
        return Promise.reject(e)
    }
)

export default httpInstance
