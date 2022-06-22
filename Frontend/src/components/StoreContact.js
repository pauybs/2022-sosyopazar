import { Box, Grid, Typography } from '@mui/material';
import React from 'react'
import MapBox from './MapBox';

const StoreContact = ({ store }) => {
    return (
        <>
            <Box sx={{ borderRadius: "2rem", overflow: "hidden" }}>
                <MapBox latitude={store?.latitude ? store?.latitude : 39.1667} longitude={store?.longitude ? store?.longitude : 35.6667} />
            </Box>
            <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                    <Typography variant="overline" lineHeight="unset">
                        WEB SİTESİ
                    </Typography>
                    <Typography variant="body1">
                        {store?.webSite ? store?.webSite : "Bilinmiyor"}
                    </Typography>
                </Grid>
                <Grid item xs={12} md={6}>
                    <Typography variant="overline" lineHeight="unset">
                        TELEFON NUMARASI
                    </Typography>
                    <Typography variant="body1">
                        {store?.phone ? store?.phone : "Bilinmiyor"}
                    </Typography>
                </Grid>
                <Grid item xs={12} md={6}>
                    <Typography variant="overline" lineHeight="unset">
                        MAİL ADRESİ
                    </Typography>
                    <Typography variant="body1">
                        {store?.mail ? store?.mail : "Bilinmiyor"}
                    </Typography>
                </Grid>
                <Grid item xs={12} md={6}>
                    <Typography variant="overline" lineHeight="unset">
                        ŞEHİR
                    </Typography>
                    <Typography variant="body1">
                        {store?.province?.provinceName ? store?.province?.provinceName : "Bilinmiyor"}
                    </Typography>
                </Grid>
                <Grid item xs={12} md={12}>
                    <Typography variant="overline" lineHeight="unset">
                        ADRES
                    </Typography>
                    <Typography variant="body1">
                        {store?.fullAddress ? store?.fullAddress : "Bilinmiyor"}
                    </Typography>
                </Grid>
            </Grid>
        </>
    )
}

export default StoreContact;