import { Box, Button, Card, CardActionArea, CardContent, CardMedia, Dialog, DialogActions, DialogContent, DialogTitle, Divider, Grid, IconButton, TextField, Tooltip, Typography } from '@mui/material';
import React, { useState } from 'react';
import RUG from 'react-upload-gallery';
import ImageGallery from 'react-image-gallery';
import { BASE_URL } from '../api/ApiProvider';
import { CloseRounded, StarRounded } from '@mui/icons-material';
import { Link } from 'react-router-dom';

const ProductCard = ({ product, myProfile, fetchProductList }) => {

    const [productDetailDialog, setProductDetailDialog] = useState(false);

    const [productImages, setProductImages] = useState(
        product.photoIdList && product.photoIdList.length > 0 &&
        product.photoIdList.map(v => ({
            original: `${BASE_URL}/api/product/${product.id}/photo/${v}`,
            thumbnail: `${BASE_URL}/api/product/${product.id}/photo/${v}`,
            originalHeight: 300,
            thumbnailHeight: 50,
            originalClass: "product-card-image-list",
            thumbnailClass: "product-card-image-list",
        }))
    )
    return (
        <Grid key={product.id} item xs={12} sm={6} md={4} >
            <Card onClick={() => setProductDetailDialog(true)} sx={{ boxShadow: "0px 4px 24px rgba(0, 0, 0, 0.1)", borderRadius: "20px" }}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        image={product.photoIdList && product.photoIdList.length > 0 ? `${BASE_URL}/api/product/${product.id}/photo/${product.photoIdList[0]}` : "/assets/no-image.svg"}
                        alt={product.productName}
                        height="300"
                    />
                    <CardContent>
                        <Tooltip title={product.productName}>
                            <Typography sx={{ whiteSpace: "nowrap", overflow: "hidden", textOverflow: "ellipsis" }} gutterBottom variant="body1" component="div">
                                {product.productName}
                            </Typography>
                        </Tooltip>
                    </CardContent>
                </CardActionArea>
            </Card >

            <Dialog
                fullWidth
                maxWidth="sm"
                open={productDetailDialog}
                onClose={() => setProductDetailDialog(false)}
            >
                <DialogTitle display="flex" justifyContent="space-between" alignItems="center">
                    <Typography variant='h6'>Ürün detayları</Typography>
                    <IconButton onClick={() => setProductDetailDialog(false)}>
                        <CloseRounded />
                    </IconButton>
                </DialogTitle>
                <DialogContent>
                    <Grid container spacing={2}>
                        <Grid item xs={12} md={12}>
                            {
                                productImages && productImages.length > 0 &&
                                < ImageGallery items={productImages} showFullscreenButton={false} showPlayButton={false} />
                            }
                        </Grid>
                        <Grid item xs={12} md={8}>
                            <Box display="flex" flexDirection="column">
                                <Typography variant="caption">Ürün Adı</Typography>
                                <Typography variant="body1">{product.productName ? product.productName : "Belirtilmemiş"}</Typography>
                            </Box>
                        </Grid>
                        <Grid item xs={12} md={4}>
                            <Box display="flex" flexDirection="column">
                                <Typography variant="caption">Ürün Fiyatı</Typography>
                                <Typography variant="body1">{product.price ? product.price + "₺" : "Belirtilmemiş"}</Typography>
                            </Box>
                        </Grid>
                        <Grid item xs={12} md={12}>
                            <Box display="flex" flexDirection="column">
                                <Typography variant="caption">Ürün Açıklaması</Typography>
                                <Typography variant="body1">{product.description ? product.description : "Belirtilmemiş"}</Typography>
                            </Box>
                        </Grid>
                        <Divider />
                        <Grid item xs={12} md={12}>
                            <Box display="flex" flexDirection="column">
                                <Typography variant="caption">Satıcı</Typography>
                                <Link to={`/store?id=${product.contentOwner.id}`}>
                                    <Typography variant="body1" display="flex" alignItems="center" gap=".5rem">
                                        <span>{product.contentOwner.storeName}</span>
                                        <span
                                            style={{
                                                display: "flex",
                                                alignItems: "center",
                                                gap: ".25rem",
                                                backgroundColor: "#E9E4DE",
                                                color: "#5E503F",
                                                padding: ".25rem .5rem",
                                                borderRadius: ".5rem",
                                            }}>
                                            <StarRounded fontSize='small' color='inherit' />
                                            {product.contentOwner.averageScore}
                                        </span>
                                    </Typography>
                                </Link>
                            </Box>
                        </Grid>
                        <Grid item xs={12} md={12} >
                            {
                                myProfile &&
                                <RUG
                                    headers={{ Authorization: `Bearer ${localStorage.getItem("token")}` }}
                                    inOrder={true}
                                    autoUpload={false}
                                    action={`${BASE_URL}/api/product/${product.id}/savePhoto/`}// upload route
                                    source={() => fetchProductList()} // response image source
                                    sorting={false}
                                    accept={['jpg', 'jpeg', 'png', 'webp']}
                                />
                            }
                        </Grid >
                    </Grid>
                </DialogContent>
            </Dialog>
        </Grid >
    )
}

export default ProductCard;