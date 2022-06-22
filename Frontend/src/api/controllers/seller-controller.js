import axios from "axios";

export const getSellersRequest = (provinceId) => axios.get(provinceId ? `/seller/province/${provinceId}` : `/seller`);

export const getSellerRequest = (id) => axios.get(`/seller/${id}`);

export const updateContactRequest = (data) => axios.put(`/seller/updateContact`, data);

export const updFollowSellerRequest = (sellerId) => axios.put(`/seller/${sellerId}/follow`);

export const updUnFollowSellerRequest = (sellerId) => axios.put(`/seller/${sellerId}/unfollow`);

export const addProfilePhoto = (file) => axios.put(`/seller/upload/profilePhoto`, file);

export const addCoverPhoto = (file) => axios.put(`/seller/upload/coverPhoto`, file);

export const searchSellerRequest = ({ searchText, provinceId }) => axios.get(`/seller/search${provinceId && '/byProvince'}?${provinceId ? 'provinceId=' + provinceId + '&searchText=' + searchText : 'searchText=' + searchText}`);
