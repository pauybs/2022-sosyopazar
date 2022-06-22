import axios from "axios"
import { useMemo } from "react"

export const BASE_URL = "http://localhost:8080"

axios.defaults.baseURL = `${BASE_URL}/api/`

const ApiProvider = ({ children }) => {
    useMemo(() => {
        axios.interceptors.request.use(async (config) => {
            const token = localStorage.getItem("token")
            if (token && token !== null) {
                config.headers["Authorization"] = `Bearer ${token}`
            }
            return config
        }, async () => {
        });
        axios.interceptors.response.use(async (response) => {
            if (response.status === 401) {
                window.location.href = "/login"
                return response
            } else if (response?.data?.token) {
                localStorage.setItem("token", response?.data?.token)
            }
            return response;
        });
    }, [])
    return children
}

export default ApiProvider;