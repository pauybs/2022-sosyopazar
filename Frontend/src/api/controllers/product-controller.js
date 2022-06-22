import axios from "axios";

export const getProductRequest = () => axios.get(`/product`);

export const saveProductRequest = (product) => axios.post(`/product`, product);

export const getProductPhotoRequest = ({ productId, photoId }) => axios.get(`/product/${productId}/photo/${photoId}`);

export const getProductsRequest = (sellerId) => axios.get(`/product/all?contentOwnerId=${sellerId}`);

export const findProductRequest = ({ provinceId, searchText }) => axios.get(`/product/search/byProvince?provinceId=${provinceId}&searchText=${searchText}`);
