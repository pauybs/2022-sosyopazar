import { CloseRounded } from '@mui/icons-material';
import { Alert, AlertTitle, Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, Grid, IconButton, TextField, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { getProductsRequest, saveProductRequest } from '../api/controllers/product-controller';
import ProductCard from './ProductCard';

const StoreProduct = ({ sellerId, myProfile, enqueueSnackbar }) => {
    const [productDialog, setProductDialog] = useState(false)

    //GET PRODUCTS
    const [productList, setProductList] = useState([])
    const fetchProductList = async () => {
        try {
            let res = await getProductsRequest(sellerId);
            if (res) {
                setProductList(res.data);
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    //ADD PRODUCT
    const [product, setProduct] = useState({
        description: "",
        price: 0,
        productName: ""
    })

    const closeProductDialog = () => {
        setProduct("");
        setProductDialog(false);
    }

    const handleAddProduct = async () => {

        try {
            let res = await saveProductRequest(product);
            if (res) {
                enqueueSnackbar('Başarıyla ürün eklendi!', { variant: 'success' });
                fetchProductList()
                closeProductDialog()
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    useEffect(() => {
        fetchProductList();
    }, [])

    return (
        <>
            {
                myProfile &&
                <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Button onClick={() => setProductDialog(true)} variant='outlined'>
                        YENİ ÜRÜN EKLE
                    </Button>
                </Box>
            }
            {
                productList.length > 0 ?
                    <Grid container spacing={2}>
                        {productList.map((val, i) =>
                            <ProductCard key={i} product={val} myProfile={myProfile} fetchProductList={fetchProductList} />
                        )} </Grid> :
                    <Alert severity="warning">
                        <AlertTitle>Henüz ürün eklenmemiş :(</AlertTitle>
                        Bu mağaza henüz ürün eklememiş.
                    </Alert>
            }

            <Dialog
                fullWidth
                maxWidth='sm'
                open={productDialog}
                onClose={() => closeProductDialog()}
            >
                <DialogTitle>
                    <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: '.5rem' }}>
                        <Typography>
                            Yeni Ürün Ekleyin!
                        </Typography>
                        <IconButton onClick={() => closeProductDialog()} fontSize='small'>
                            <CloseRounded />
                        </IconButton>
                    </Box>
                </DialogTitle>
                <DialogContent>
                    <Grid container spacing={2}>
                        <Grid item xs={12} md={12}>
                            <TextField
                                fullWidth
                                label="Ürün Adı"
                                onChange={(e) => {
                                    setProduct({ ...product, productName: e.target.value })
                                }}
                            />
                        </Grid>

                        <Grid item xs={12} md={12}>
                            <TextField
                                fullWidth
                                label="Fiyatı"
                                onChange={(e) => {
                                    setProduct({ ...product, price: e.target.value })
                                }}
                            />
                        </Grid>
                        <Grid item xs={12} md={12}>
                            <TextField
                                fullWidth
                                label="Açıklaması"
                                onChange={(e) => {
                                    setProduct({ ...product, description: e.target.value })
                                }}
                            />
                        </Grid>
                    </Grid>
                </DialogContent>
                <DialogActions sx={{ px: 3, pb: 2 }}>
                    <Button onClick={() => handleAddProduct()} variant='contained'>Ekle</Button>
                </DialogActions>
            </Dialog >
        </>
    )
}

export default StoreProduct;