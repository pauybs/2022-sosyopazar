import { CloseRounded } from '@mui/icons-material';
import { Autocomplete, Box, Dialog, DialogContent, DialogTitle, IconButton, TextField, Typography } from '@mui/material';
import { useSnackbar } from 'notistack';
import { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getMeRequest } from '../api/controllers/account-controller';
import { getProvincesRequest } from '../api/controllers/province-controller';
import NavbarMenu from './NavbarMenu';


const Navbar = () => {
    const { enqueueSnackbar } = useSnackbar();
    const navigate = useNavigate();
    const [user, setUser] = useState(null)
    const selectedProvince = JSON.parse(localStorage.getItem("selectedProvince"));

    const [chooseProvinceModal, setChooseProvinceModal] = useState(false);
    const [province, setProvince] = useState(selectedProvince ? selectedProvince : null)
    const [provinces, setProvinces] = useState([]);

    const fetchProvinces = async () => {
        try {
            let res = await getProvincesRequest();

            if (res) {
                setProvinces(res.data)
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

    const checkAuthentication = async () => {

        try {
            let res = await getMeRequest()
            if (res) {
                setUser(res.data)
                localStorage.setItem('user', JSON.stringify(res.data));
            }
        } catch (error) {
            if (error?.response?.status === 401) {
                localStorage.removeItem('user');
                localStorage.removeItem('token');
            }
        }
    }

    useEffect(() => {
        fetchProvinces();
    }, []);

    useLayoutEffect(() => {
        checkAuthentication();
    }, [])

    return (
        <>
            <nav className="navbar">
                <div className="navbarNav">
                    <div className="logo">
                        <Link to={'/'}>SosyoPazar</Link >
                        <span style={{ margin: "0 .5rem" }}>|</span>
                        <button className="navButton" onClick={() => setChooseProvinceModal(true)} >
                            {province ? province?.provinceName : 'Şehir Seçin'}
                        </button>
                    </div>
                    {
                        user ?
                            <NavbarMenu user={user} /> :
                            <div className="nav-buttons">
                                <button className="navButton" onClick={() => navigate('/login')} >GİRİŞ YAP</button>
                                <button className="navButton registerButton" onClick={() => navigate('/register')} >ÜYE OL</button>
                            </div>
                    }
                </div>
            </nav >

            {/* Şehir seç modal */}
            <Dialog
                fullWidth
                maxWidth='xs'
                open={chooseProvinceModal}
                onClose={() => setChooseProvinceModal(false)}
            >
                <DialogTitle>
                    <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: '.5rem' }}>
                        <Typography>
                            Öncelikle şehir seçelim!
                        </Typography>
                        <IconButton onClick={() => setChooseProvinceModal(false)} fontSize='small'>
                            <CloseRounded />
                        </IconButton>
                    </Box>
                </DialogTitle>
                <DialogContent>
                    <Autocomplete
                        options={provinces}
                        value={province}
                        onChange={(event, newValue) => {
                            setProvince(newValue);
                            localStorage.setItem('selectedProvince', JSON.stringify(newValue));
                            window.location.reload();
                        }}
                        getOptionLabel={(option) => option.provinceName}
                        renderInput={(params) => <TextField {...params} label="Şehir seçin" />}
                    />
                </DialogContent>
            </Dialog>
        </>
    );
};

export default Navbar;