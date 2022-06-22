import axios from 'axios';

export const getSellerCommentsRequest = (sellerId) => axios.get(`/sellerComment?sellerId=${sellerId}`);

export const saveSellerCommentRequest = (data) => axios.post(`/sellerComment`, data);

export const getSellerAverageRequest = (sellerId) => axios.get(`/sellerComment/averageScore?sellerId=${sellerId}`);
