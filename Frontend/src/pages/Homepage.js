import React, { useEffect, useState } from 'react'
import { Box, Grid, Container, Divider, Typography } from '@mui/material'
import Navbar from '../components/Navbar';
import { getSellersRequest } from '../api/controllers/seller-controller'
import StoreCard from '../components/StoreCard';
import Footer from '../components/Footer';
import { useNavigate } from 'react-router-dom';
import { useSnackbar } from 'notistack';

const Homepage = () => {
    const { enqueueSnackbar } = useSnackbar();

    const selectedProvince = JSON.parse(localStorage.getItem("selectedProvince"));
    const [sellers, setSellers] = useState([])
    const [searchText, setSearchText] = useState("")
    const navigate = useNavigate();

    const fetchSellers = async () => {
        try {
            let res = await getSellersRequest(selectedProvince && selectedProvince.id);
            if (res) {
                setSellers(res.data)
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

    const findProduct = () => {
        navigate("search/" + searchText)
    }

    useEffect(() => {

        fetchSellers();

    }, [])
    return (
        <Container>
            <Navbar />
            <section className='defaultSection' style={{ textAlign: 'center' }}>
                <Box sx={{ margin: '2rem 0' }}>
                    <h2 className='title'>SosyoPazar'a Hoşgeldin!</h2>
                    <p className='subTitle'>Bugün çevrende ne arıyorsun?</p>
                </Box>
                <form onSubmit={findProduct}>
                    <input value={searchText} onChange={e => setSearchText(e.target.value)} className='searchInput' placeholder='Mağaza, sektör veya ürün arayın' type="text" />
                </form>
                <img style={{ width: "100%", height: "auto", marginTop: "2rem" }} src='/assets/searchImage.svg' alt='' />
                <Box style={{ width: "100%", height: "5rem", marginTop: "-4rem", backgroundColor: "rgba(169, 146, 125, 0.5)" }}></Box>
            </section>
            <section
                className="defaultSection" >
                <Box padding={4} sx={{ textAlign: 'left' }}>
                    <h3 className='title'>Bulunduğun konuma yakın mekanlar</h3>
                    <p className='subTitle'>Belki lazım olur :)</p>
                    <Divider sx={{ my: "1rem" }} />
                    <Grid container spacing={2}>
                        {
                            sellers.length > 0 ?
                                sellers.map((val, i) =>
                                    <StoreCard key={i} store={val} fetchSellers={fetchSellers} />
                                ) :
                                <Grid item xs={12} md={12}>
                                    <Box sx={{
                                        display: "flex",
                                        justifyContent: "center",
                                        alignItems: "center",
                                        gap: "2rem",
                                        marginTop: "1rem"
                                    }}>
                                        <img
                                            style={{
                                                width: "30%",
                                                height: "auto"
                                            }}
                                            src="/assets/bulunamadi.svg"
                                            alt=""
                                        />
                                        <Typography variant="h5">
                                            Bu bölgede henüz dükkan bulunmuyor!
                                        </Typography>
                                    </Box>
                                </Grid>
                        }
                    </Grid>
                </Box>
            </section >
            <Footer />
        </Container >
    )
}

export default Homepage;