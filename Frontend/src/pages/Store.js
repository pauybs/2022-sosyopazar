import React, { useEffect, useLayoutEffect, useState } from 'react'
import { Grid, Box, Typography, Rating, Tabs, Tab, Button, Container, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, TextField } from '@mui/material'
import { getSellerRequest, updFollowSellerRequest, updUnFollowSellerRequest } from '../api/controllers/seller-controller';
import { getSellerAverageRequest, saveSellerCommentRequest } from '../api/controllers/seller-comment-controller';
import { a11yProps, TabPanel } from '../components/MuiTabPanel';
import { CloseRounded, GroupAddRounded, GroupRemoveRounded } from '@mui/icons-material';
import { useSnackbar } from 'notistack';
import SellerProfilePhoto from '../components/SellerProfilePhoto';
import SellerCoverPhoto from '../components/SellerCoverPhoto';
import Navbar from '../components/Navbar';
import StorePost from '../components/StorePost';
import StoreComment from '../components/StoreComment';
import StoreProduct from '../components/StoreProduct';
import StoreContact from '../components/StoreContact';
import axios from 'axios';
import { BASE_URL } from '../api/ApiProvider';

const Store = () => {
    const { enqueueSnackbar } = useSnackbar();
    const sellerId = (new URLSearchParams(window.location.search)).get("id")
    const user = JSON.parse(localStorage.getItem("user"));
    const [myProfile, setMyProfile] = useState(false)

    useEffect(() => {
        if (parseInt(sellerId) === parseInt(user?.id)) {
            setMyProfile(true)
        }
    }, [sellerId, user])

    //MUI TAB
    const [tabValue, setTabValue] = useState(0);
    const handleChange = (event, newValue) => {
        setTabValue(newValue);
    }

    //MUI DIALOG
    const [commentDialog, setCommentDialog] = useState(false)

    //GET STORE
    const [store, setStore] = useState(null)
    const fetchStore = async () => {
        try {
            let res = await getSellerRequest(sellerId);

            if (res) {
                setStore(res.data)
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

    //GET STORE SCORE
    const [storeScore, setStoreScore] = useState(null)
    const fetchStoreScore = async () => {
        try {
            let res = await getSellerAverageRequest(sellerId);
            if (res) {
                setStoreScore(res.data.averageScore)
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

    //FOLLOW FUNC
    const followFunc = async () => {
        try {
            if (store?.followed === true) {
                let res = await updUnFollowSellerRequest(sellerId);

                if (res) {
                    enqueueSnackbar('Mağazayı takibi bıraktın :(', { variant: 'error' });
                    fetchStore()

                }
            } else if (store?.followed === false) {
                let res = await updFollowSellerRequest(sellerId);

                if (res) {
                    enqueueSnackbar('Mağazayı takip ettin!', { variant: 'success' });
                    fetchStore()
                }
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

    //ADD COMMENT
    const [comment, setComment] = useState({
        comment: "",
        score: null,
        sellerId: sellerId,
    })

    const closeCommentDialog = () => {
        setComment({
            comment: "",
            score: null,
            sellerId: sellerId,
        });
        setCommentDialog(false);
    }

    const handleAddComment = async () => {
        try {
            let res = await saveSellerCommentRequest(comment);
            if (res) {
                enqueueSnackbar('Başarıyla yorum yaptın!', { variant: 'success' });
                closeCommentDialog()
                fetchStoreScore()
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

    const [pp, setPp] = useState("");
    const [cp, setCp] = useState("");

    const checkPhotos = async () => {
        try {
            let cpRes = await axios.get(`${BASE_URL}/api/seller/${sellerId}/coverPhoto`);
            let ppRes = await axios.get(`${BASE_URL}/api/seller/${sellerId}/profilePhoto`);

            if (cpRes) {
                setCp(`${BASE_URL}/api/seller/${sellerId}/coverPhoto`);
            }
            if (ppRes) {
                setPp(`${BASE_URL}/api/seller/${sellerId}/profilePhoto`);
            }
        } catch (error) {
        }
    }

    useEffect(() => {
        checkPhotos()
        fetchStore()
        fetchStoreScore()
    }, [])



    return (
        <Container>
            <Navbar />
            <section className='defaultSection'>
                {
                    myProfile ?
                        <SellerCoverPhoto photoUrl={cp} fetchStore={fetchStore} /> :
                        <Box
                            className='store-cover'
                            sx={{
                                backgroundSize: cp ? "cover" : "contain",
                                backgroundImage: cp ? `url(${BASE_URL}/api/seller/${store?.id}/coverPhoto)` : "url(/assets/no-image.svg)"
                            }} />
                }
                <Grid container spacing={2} pb={5} >
                    <Grid item xs={12} sm={4} md={3}>
                        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', mt: '-7.5rem', mb: '1rem' }}>
                            {
                                myProfile ?
                                    <SellerProfilePhoto
                                        photoUrl={pp}
                                        fetchStore={fetchStore}
                                    /> :
                                    <Box
                                        className='store-profile-img'
                                        sx={{ backgroundImage: pp ? `url(${BASE_URL}/api/seller/${store?.id}/profilePhoto)` : "url(/assets/no-image.svg)", cursor: 'pointer' }} />
                            }
                            <Typography variant='overline' sx={{ mt: '1rem', mb: '.5rem', lineHeight: 'normal' }} fontSize={18}>{store?.storeName}</Typography>
                            <Rating
                                value={storeScore}
                                readOnly
                            />
                            {
                                !myProfile &&
                                <Typography onClick={() => setCommentDialog(true)} sx={{ cursor: 'pointer' }} color='primary' variant='body2'>Dükkanı değerlendir</Typography>
                            }
                        </Box>
                        <Tabs
                            orientation="vertical"
                            value={tabValue}
                            onChange={handleChange}
                            sx={{ borderRight: 1, borderColor: 'divider' }}
                        >
                            <Tab sx={{ alignItems: 'flex-start' }} label="GÖNDERİLER" {...a11yProps(0)} />
                            <Tab sx={{ alignItems: 'flex-start' }} label="YORUMLAR" {...a11yProps(1)} />
                            <Tab sx={{ alignItems: 'flex-start' }} label="ÜRÜNLER" {...a11yProps(2)} />
                            <Tab sx={{ alignItems: 'flex-start' }} label="İLETİŞİM" {...a11yProps(3)} />
                        </Tabs>
                    </Grid>
                    <Grid item xs={12} sm={8} md={9}>
                        <Box sx={{ display: 'flex', justifyContent: 'flex-end', mx: 4, mt: 4 }}>
                            {
                                !myProfile &&
                                <Button onClick={() => followFunc()} variant='outlined' startIcon={store?.followed ? <GroupRemoveRounded /> : <GroupAddRounded />}>
                                    {store?.followed ? 'TAKİBİ BIRAK' : 'TAKİP ET'} <span style={{ paddingLeft: 5 }}>|  {store?.followersCount}</span>
                                </Button>
                            }
                        </Box>
                        <TabPanel value={tabValue} index={0}>
                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: '1.5rem', m: 4 }}>
                                <StorePost sellerId={sellerId} myProfile={myProfile} enqueueSnackbar={enqueueSnackbar} />
                            </Box>
                        </TabPanel>
                        <TabPanel value={tabValue} index={1}>
                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: '1.5rem', m: 4 }}>
                                <StoreComment sellerId={sellerId} commentDialog={commentDialog} setCommentDialog={setCommentDialog} fetchStoreScore={fetchStoreScore} enqueueSnackbar={enqueueSnackbar} />
                            </Box>
                        </TabPanel>
                        <TabPanel value={tabValue} index={2}>
                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: '1.5rem', m: 4 }}>
                                <StoreProduct sellerId={sellerId} myProfile={myProfile} enqueueSnackbar={enqueueSnackbar} />
                            </Box>
                        </TabPanel>
                        <TabPanel value={tabValue} index={3}>
                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: '1.5rem', m: 4 }}>
                                <StoreContact store={store} />
                            </Box>
                        </TabPanel>
                    </Grid>
                </Grid>
            </section >


            <Dialog
                fullWidth
                maxWidth='xs'
                open={commentDialog}
                onClose={() => closeCommentDialog()}
            >
                <DialogTitle>
                    <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: '.5rem' }}>
                        <Typography>
                            Puanlayıp yorum yapın!
                        </Typography>
                        <IconButton onClick={() => closeCommentDialog()} fontSize='small'>
                            <CloseRounded />
                        </IconButton>
                    </Box>
                </DialogTitle>
                <DialogContent>
                    <Box>
                        <Rating
                            value={comment.score}
                            onChange={(event, newValue) => {
                                comment.score = newValue;
                                setComment({ ...comment })
                            }}
                        />
                        <TextField
                            fullWidth
                            multiline
                            rows={3}
                            placeholder="Yorumunuzu yazın"
                            onChange={(e) => {
                                comment.comment = e.target.value
                                setComment({ ...comment })
                            }}
                        />
                    </Box>
                </DialogContent>
                <DialogActions sx={{ px: 3, pb: 2 }}>
                    <Button onClick={() => handleAddComment()} variant='contained'>Yorum Yap</Button>
                </DialogActions>
            </Dialog>
        </Container >
    )
}
export default Store;