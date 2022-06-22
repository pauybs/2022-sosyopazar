import axios from 'axios';

export const getProvincesRequest = () => axios.get(`/province`);