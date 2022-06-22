import axios from "axios";

export const getIndustryRequest = () => axios.get(`/industry`);

export const saveIndustryRequest = () => axios.post(`/industry`);

export const deleteIndustryRequest = () => axios.delete(`/industry`);
