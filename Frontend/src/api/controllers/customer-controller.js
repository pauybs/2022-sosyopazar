import axios from "axios";

export const customerFollowRequest = (data) => axios.post(`/customer/follow`, data);

export const getMyFollows = () => axios.get(`/customer/myFollows`);
