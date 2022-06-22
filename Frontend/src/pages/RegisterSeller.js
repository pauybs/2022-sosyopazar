import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import { Autocomplete, Box, Container, Grid, Typography } from '@mui/material'
import { Link, useNavigate } from 'react-router-dom'
import { getProvincesRequest } from '../api/controllers/province-controller'
import { getIndustryRequest } from '../api/controllers/industy-controller'
import { sellerRegisterRequest } from '../api/controllers/account-controller'
import { useSnackbar } from 'notistack'


const RegisterSeller = () => {
    const navigate = useNavigate();
    const { enqueueSnackbar } = useSnackbar();

    const [provinces, setProvinces] = useState([])
    const [selectedProvince, setSelectedProvince] = useState(null)
    const [industries, setIndustries] = useState([])
    const [selectedIndustry, setSelectedIndustry] = useState(null)

    const [form, setForm] = useState({
        industryId: null,
        password: "",
        provinceId: null,
        storeName: "",
        username: ""
    })



    const onChangeInput = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    }


    const fetchProvince = async () => {
        try {
            let res = await getProvincesRequest();

            if (res) {
                setProvinces(res.data)
            }
        } catch (error) {
            console.log(error)
        }
    }


    const fetchIndustry = async () => {
        try {
            let res = await getIndustryRequest();

            if (res) {
                setIndustries(res.data)
            }
        } catch (error) {
            console.log(error)
        }
    }


    const handleSubmit = async (e) => {
        e.preventDefault()
        form.industryId = selectedIndustry?.id;
        form.provinceId = selectedProvince?.id;
        setForm({ ...form })
        try {
            let res = await sellerRegisterRequest(form);
            if (res) {
                navigate('/dashboard');
                enqueueSnackbar('Kaydın başarıyla yapıldı!', { variant: 'success' });
            }
        } catch (error) {
            if (error?.response?.status === 400) {
                enqueueSnackbar(error?.response?.data?.message, { variant: 'error' });
            } else {
                enqueueSnackbar('Hatayla karşılaştık :(', { variant: 'error' });
            }
        }
    }


    useEffect(() => {

        fetchProvince()
        fetchIndustry()

    }, [])

    return (
        <Container>
            <Navbar />
            <section className='defaultSection'>
                <Grid container padding={0}>
                    <Grid item xs={12} md={7}>
                        <img src='/assets/loginImg.svg' width='100%' alt='' />
                    </Grid>
                    <Grid item xs={12} md={5} display='flex' justifyContent='center' alignItems='center'>
                        <form onSubmit={handleSubmit} style={{ width: '100%', display: "flex", flexDirection: "column", justifyContent: "center", padding: "0 3rem", textAlign: "left" }}>
                            <h1 style={{ marginBottom: "2rem" }}>Pazarın yıldızı olmaya çok yakınsın!</h1>
                            <input required onChange={(e) => onChangeInput(e)} name="storeName" type='text' className='customInput' placeholder="Ünvanın" />
                            <input required onChange={(e) => onChangeInput(e)} name="username" type='text' className='customInput' placeholder="Kullanıcı Adın" />
                            <Autocomplete
                                options={provinces}
                                value={selectedProvince}
                                onChange={(event, newValue) => {
                                    setSelectedProvince(newValue);
                                }}
                                getOptionLabel={(option) => option.provinceName}
                                renderInput={params => (
                                    <div ref={params.InputProps.ref} >
                                        <input required {...params.inputProps} style={{ width: "-webkit-fill-available" }} className='customInput' placeholder='Şehrin' />
                                    </div>
                                )}
                            />
                            <Autocomplete
                                options={industries}
                                value={selectedIndustry}
                                onChange={(event, newValue) => {
                                    setSelectedIndustry(newValue);
                                }}
                                getOptionLabel={(option) => option.name}
                                renderInput={params => (
                                    <div ref={params.InputProps.ref} >
                                        <input required {...params.inputProps} style={{ width: "-webkit-fill-available" }} className='customInput' placeholder='Sektörün' />
                                    </div>
                                )}
                            />
                            <input required onChange={(e) => onChangeInput(e)} name="password" type='password' className='customInput' placeholder="Şifren" />
                            <button type='submit' className='loginRegisterBtn'>Üye Ol</button>
                            <button onClick={() => navigate('/register')} className='loginRegisterForSellerBtn'>Bireysel üyelik istiyorum.</button>
                        </form>

                    </Grid>
                </Grid>
                <Box style={{ marginTop: "5rem", padding: "2rem", textAlign: "center", backgroundColor: "rgba(169, 146, 125, 0.5)" }}>
                    <Typography>Hesabım var. <Link to={'/login'}><b>Ben bi giriş yapayım.</b></Link></Typography>
                </Box>
            </section >
        </Container >
    )
}
export default RegisterSeller;