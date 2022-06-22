import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import Homepage from "../pages/Homepage";
import Login from "../pages/Login";
import Register from "../pages/Register";
import RegisterSeller from "../pages/RegisterSeller";
import Search from "../pages/Search";
import Store from "../pages/Store";
import PrivateRoute from "./PrivateRoute";

const Router = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Homepage />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/registerSeller" element={<RegisterSeller />} />
                <Route path="/store" element={<Store />} />
                <Route path="/search/:searchText" element={<Search />} />

                <Route element={<PrivateRoute />}>
                    <Route path="/dashboard" element={<Dashboard />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default Router;