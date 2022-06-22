import { Alert, AlertTitle, Box, Grid, Tab } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { findProductRequest } from '../api/controllers/product-controller';
import CustomLayout from '../components/CustomLayout';
import StoreCard from '../components/StoreCard';
import ProductCard from '../components/ProductCard';
import { TabPanel, TabContext, TabList } from '@mui/lab';
import { useParams } from 'react-router-dom';
import { useSnackbar } from 'notistack';

const Search = () => {
    const { enqueueSnackbar } = useSnackbar();

    const selectedProvince = JSON.parse(localStorage.getItem("selectedProvince"));
    const { searchText } = useParams();
    const [inputText, setInputText] = useState(searchText)
    const [products, setProducts] = useState([])
    const [stores, setStores] = useState([])

    const findProduct = async () => {
        try {
            let res = await findProductRequest({ provinceId: selectedProvince.id, searchText: inputText })

            if (res) {
                setProducts(res.data)
                let x = res.data.map(v => v.contentOwner)
                const sellerList = Array.from(new Set(x.map(a => a.id)))
                    .map(id => {
                        return x.find(a => a.id === id)
                    })
                setStores(sellerList)
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

    const [value, setValue] = useState('1');

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    useEffect(() => {
        findProduct()
    }, [inputText])


    return (
        <CustomLayout>
            <Box sx={{ textAlign: "center" }}>
                <Box sx={{ mt: 10 }}>
                    <input value={inputText} onChange={(e) => setInputText(e.target.value)} className='searchInput' placeholder='Mağaza, sektör veya ürün arayın' type="text" />
                </Box>
                <img style={{ width: "100%", height: "auto", marginTop: "2rem" }} src='/assets/searchImage.svg' alt='' />
                <Box style={{ width: "100%", height: "5rem", marginTop: "-4rem", backgroundColor: "rgba(169, 146, 125, 0.5)" }} />
            </Box>
            <Box>
                <TabContext value={value}>
                    <Box sx={{ borderBottom: 1, p: 2, borderColor: 'divider' }}>
                        <TabList onChange={handleChange} aria-label="lab API tabs example">
                            <Tab label="Ürünler" value="1" />
                            <Tab label="Mağazalar" value="2" />
                        </TabList>
                    </Box>
                    <TabPanel value='1'>
                        {
                            products && products.length > 0 ?
                                < Grid container spacing={2}>
                                    <Grid item md={12}>
                                        <Alert severity='info'>Toplam {products.length} sonuç bulunmuştur.</Alert>
                                    </Grid>
                                    {
                                        products.map((v, i) =>
                                            <ProductCard key={i} product={v} />
                                        )
                                    }
                                </Grid> :
                                <Alert severity="warning">
                                    <AlertTitle>Sonuç bulunamadı</AlertTitle>
                                    Üst kısımdan yeni arama yapabilirsiniz.
                                </Alert>
                        }
                    </TabPanel>
                    <TabPanel value='2'>
                        {
                            stores && stores.length > 0 ?
                                < Grid container spacing={2}>
                                    <Grid item md={12}>
                                        <Alert severity='info'>Toplam {stores.length} sonuç bulunmuştur.</Alert>
                                    </Grid>
                                    {
                                        stores.map((v, i) =>
                                            <StoreCard key={i} store={v} />
                                        )
                                    }
                                </Grid> :
                                <Alert severity="warning">
                                    <AlertTitle>Sonuç bulunamadı</AlertTitle>
                                    Üst kısımdan yeni arama yapabilirsiniz.
                                </Alert>
                        }
                    </TabPanel>
                </TabContext>
            </Box>
        </CustomLayout >
    )
}

export default Search;