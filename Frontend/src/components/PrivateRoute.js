
import React, { Fragment } from "react"
import { useState, useEffect } from "react"
import { Navigate, Outlet } from "react-router-dom"
import SuspenseFallback from "./SuspenseFallback"
import CustomLayout from "./CustomLayout"
import { getMeRequest } from "../api/controllers/account-controller"

const PrivateRoute = ({ component: Component, ...rest }) => {

    const [AUTH_STATUS, setAUTH_STATUS] = useState("WAITING")

    const checkAuthentication = async () => {

        try {
            let res = await getMeRequest()
            if (res) {
                setTimeout(() => {
                    setAUTH_STATUS("SUCCESS")
                }, 500);
                localStorage.setItem('user', JSON.stringify(res.data));
            }
        } catch (error) {
            if (error?.response?.status === 401) {
                setAUTH_STATUS("FAILED")
                localStorage.removeItem('user');
                localStorage.removeItem('token');
            }
        }
    }

    useEffect(() => {

        checkAuthentication()

    }, [])

    return (
        <Fragment>
            {
                AUTH_STATUS === "WAITING" ? <SuspenseFallback />
                    : AUTH_STATUS === "FAILED" ? <Navigate to="/login" />
                        : AUTH_STATUS === "SUCCESS" ? <CustomLayout><Outlet /></CustomLayout>
                            : <div>Error</div>
            }
        </Fragment>

    )
}

export default PrivateRoute