import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import { Box, Container, Grid, Typography } from '@mui/material'
import { Link, useNavigate } from 'react-router-dom'
import { loginRequest } from '../api/controllers/account-controller'
import { useSnackbar } from 'notistack'

const Login = () => {
    const navigate = useNavigate();
    const { enqueueSnackbar } = useSnackbar();

    const [form, setForm] = useState({
        password: "",
        username: ""
    })

    const onChangeInput = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            let res = await loginRequest(form);
            if (res) {
                navigate(res.data.role === "ROLE_SELLER" ? '/store?id=' + res.data.id : '/dashboard');
                enqueueSnackbar('Başarıyla giriş yaptın!', { variant: 'success' });
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
                    <Grid item xs={12} md={6}>
                        <img src='/assets/loginImg.svg' width='100%' alt='' />
                    </Grid>
                    <Grid item xs={12} md={6} display='flex' justifyContent='center' alignItems='center'>
                        <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", justifyContent: "center", padding: "0 3rem", textAlign: "left" }}>
                            <h1 style={{ marginBottom: "2rem" }}>Pazar seni özlemişti hoşgeldin :)</h1>
                            <input autoFocus required onChange={(e) => onChangeInput(e)} name='username' type='text' className='customInput' placeholder="Kullanıcı Adın" />
                            <input required onChange={(e) => onChangeInput(e)} name='password' type='password' className='customInput' placeholder="Şifren" />
                            <button type="submit" style={{ width: "100%" }} className='loginRegisterBtn'>Giriş Yap</button>
                        </form>
                    </Grid>
                </Grid>

                <Box style={{ marginTop: "5rem", padding: "2rem", textAlign: "center", backgroundColor: "rgba(169, 146, 125, 0.5)" }}>
                    <Typography>Nasıl yanii! Henüz hesabın yok mu? <Link to={'/register'}><b>Buradan</b></Link> hemen oluşturabilirsin :)</Typography>
                </Box>
            </section >
        </Container >
    )
}
export default Login;