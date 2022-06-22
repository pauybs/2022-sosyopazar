import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import { Box, Container, Grid, Typography } from '@mui/material'
import { Link, useNavigate } from 'react-router-dom'
import { customerRegisterRequest } from '../api/controllers/account-controller'
import { useSnackbar } from 'notistack'


const Register = () => {
    const navigate = useNavigate();
    const { enqueueSnackbar } = useSnackbar();

    const [form, setForm] = useState({
        name: "",
        password: "",
        username: ""
    })

    const onChangeInput = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            let res = await customerRegisterRequest(form);
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
                            <h1 style={{ marginBottom: "2rem" }}>Pazarda gezinmeye çok yakınsın!</h1>
                            <input required onChange={(e) => onChangeInput(e)} name="name" type='text' className='customInput' placeholder="Adın" />
                            <input required onChange={(e) => onChangeInput(e)} name="username" type='text' className='customInput' placeholder="Kullanıcı Adın" />
                            <input required onChange={(e) => onChangeInput(e)} name="password" type='password' className='customInput' placeholder="Şifren" />
                            <button type='submit' className='loginRegisterBtn'>Üye Ol</button>
                            <button onClick={() => navigate('/registerSeller')} className='loginRegisterForSellerBtn'>Mağaza üyeliği istiyorum.</button>
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
export default Register;